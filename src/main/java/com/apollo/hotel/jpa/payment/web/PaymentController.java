package com.apollo.hotel.jpa.payment.web;

import com.apollo.hotel.infrastructure.validation.ValidationGroupSequence;
import com.apollo.hotel.infrastructure.web.EditMode;
import com.apollo.hotel.jpa.payment.*;
import com.apollo.hotel.jpa.reservation.ReservationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;
    private final ReservationService reservationService;

    public PaymentController(PaymentService paymentService, ReservationService reservationService){
        this.paymentService = paymentService;
        this.reservationService = reservationService;
    }

    @GetMapping
    public String index(Model model, @SortDefault.SortDefaults({
            @SortDefault("reservationId")}) Pageable pageable) {
        model.addAttribute("payments", paymentService.getPayments(pageable));
        return "payments/list";
    }

    @GetMapping("/create")
    public String createPaymentForm(Model model) {
        model.addAttribute("payment", new CreatePaymentFormData());
        model.addAttribute("reservations", reservationService.getCustomerAndCheckInDate());
        model.addAttribute("status", Stream.of(
                new Status[]{Status.SETTLED, Status.UNSETTLED}).collect(Collectors.toList()));
        model.addAttribute("editMode", EditMode.CREATE);
        return "payments/edit";
    }

    @PostMapping("/create")
    public String doCreatePayment(@Validated(ValidationGroupSequence.class) @ModelAttribute("payment") CreatePaymentFormData formData,
                                      BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("reservations", reservationService.getCustomerAndCheckInDate());
            model.addAttribute("status", Stream.of(
                    new Status[]{Status.SETTLED, Status.UNSETTLED}).collect(Collectors.toList()));model.addAttribute("editMode", EditMode.CREATE);
            return "payments/edit";
        }

        paymentService.createPayment(formData.toParameters());

        return "redirect:/payments";
    }

    @GetMapping("/{id}")
    public String editPaymentForm(@PathVariable("id") PaymentId paymentId, Model model) {
        Payment payment = paymentService.getPayment(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));
        model.addAttribute("reservations", reservationService.getCustomerAndCheckInDate());
        model.addAttribute("payment", EditPaymentFormData.fromPayment(payment));
        model.addAttribute("status", Stream.of(
                new Status[]{Status.SETTLED, Status.UNSETTLED}).collect(Collectors.toList()));model.addAttribute("editMode", EditMode.UPDATE);
        return "payments/edit";
    }

    @PostMapping("/{id}")
    public String doEditPayment(@PathVariable("id") PaymentId paymentId,
                                    @Validated(EditPaymentValidationGroupSequence.class) @ModelAttribute("payment") EditPaymentFormData formData,
                                    BindingResult bindingResult,
                                    Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("reservations", reservationService.getCustomerAndCheckInDate());
            model.addAttribute("status", Stream.of(
                    new Status[]{Status.SETTLED, Status.UNSETTLED}).collect(Collectors.toList()));model.addAttribute("editMode", EditMode.UPDATE);
            model.addAttribute("editMode", EditMode.UPDATE);
            return "payments/edit";
        }

        paymentService.editPayment(paymentId, formData.toParameters());

        return "redirect:/payments";
    }

    @PostMapping("/{id}/delete")
    public String doDeletePayment(@PathVariable("id") PaymentId paymentId,
                                      RedirectAttributes redirectAttributes) {
        Payment payment = paymentService.getPayment(paymentId)
                .orElseThrow(() -> new PaymentNotFoundException(paymentId));

        paymentService.deletePayment(paymentId);

        redirectAttributes.addFlashAttribute("deletedPaymentId",
                paymentId);

        return "redirect:/payments";
    }
}
