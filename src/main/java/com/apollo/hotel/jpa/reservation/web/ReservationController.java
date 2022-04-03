package com.apollo.hotel.jpa.reservation.web;

import com.apollo.hotel.infrastructure.validation.ValidationGroupSequence;
import com.apollo.hotel.infrastructure.web.EditMode;
import com.apollo.hotel.jpa.customer.CustomerService;
import com.apollo.hotel.jpa.reservation.*;
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
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;
    private final CustomerService customerService;

    public ReservationController(ReservationService reservationService, CustomerService customerService){
        this.reservationService = reservationService;
        this.customerService = customerService;
    }

    @GetMapping
    public String index(Model model, @SortDefault.SortDefaults({
            @SortDefault("checkInDate"),
            @SortDefault("checkOutDate")}) Pageable pageable) {
        model.addAttribute("reservations", reservationService.getReservations(pageable));
        return "reservations/list";
    }

    @GetMapping("/create")
    public String createReservationForm(Model model) {
        model.addAttribute("reservation", new CreateReservationFormData());
        model.addAttribute("customers", customerService.getAllCustomersNameAndId());
        model.addAttribute("roomTypes", Stream.of(
                new RoomType[]{RoomType.SUPERIOR, RoomType.DELUXE, RoomType.SUITE, RoomType.VILLA}).collect(Collectors.toList()));
        model.addAttribute("reservationTypes", Stream.of(
                new ReservationType[]{ReservationType.BB, ReservationType.FULL_BOARD, ReservationType.HALF_BOARD}).collect(Collectors.toList()));
        model.addAttribute("paymentMethods", Stream.of(
                new PaymentMethod[]{PaymentMethod.CASH, PaymentMethod.CREDIT_CARD, PaymentMethod.DEBIT_CARD}).collect(Collectors.toList()));

        model.addAttribute("editMode", EditMode.CREATE);
        return "reservations/edit";
    }

    @PostMapping("/create")
    public String doCreateReservation(@Validated(ValidationGroupSequence.class) @ModelAttribute("reservation") CreateReservationFormData formData,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerService.getAllCustomersNameAndId());
            model.addAttribute("roomTypes", Stream.of(
                    new RoomType[]{RoomType.SUPERIOR, RoomType.DELUXE, RoomType.SUITE, RoomType.VILLA}).collect(Collectors.toList()));
            model.addAttribute("reservationTypes", Stream.of(
                    new ReservationType[]{ReservationType.BB, ReservationType.FULL_BOARD, ReservationType.HALF_BOARD}).collect(Collectors.toList()));
            model.addAttribute("editMode", EditMode.CREATE);
            model.addAttribute("paymentMethods", Stream.of(
                    new PaymentMethod[]{PaymentMethod.CASH, PaymentMethod.CREDIT_CARD, PaymentMethod.DEBIT_CARD}).collect(Collectors.toList()));

            return "reservations/edit";
        }

        reservationService.createReservation(formData.toParameters());

        return "redirect:/reservations";
    }

    @GetMapping("/{id}")
    public String editReservationForm(@PathVariable("id") ReservationId reservationId, Model model) {
        Reservation reservation = reservationService.getReservation(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));
        model.addAttribute("customers", customerService.getAllCustomersNameAndId());
        model.addAttribute("reservation", EditReservationFormData.fromReservation(reservation));
        model.addAttribute("roomTypes", Stream.of(
                new RoomType[]{RoomType.SUPERIOR, RoomType.DELUXE, RoomType.SUITE, RoomType.VILLA}).collect(Collectors.toList()));
        model.addAttribute("reservationTypes", Stream.of(
                new ReservationType[]{ReservationType.BB, ReservationType.FULL_BOARD, ReservationType.HALF_BOARD}).collect(Collectors.toList()));
        model.addAttribute("editMode", EditMode.UPDATE);
        model.addAttribute("paymentMethods", Stream.of(
                new PaymentMethod[]{PaymentMethod.CASH, PaymentMethod.CREDIT_CARD, PaymentMethod.DEBIT_CARD}).collect(Collectors.toList()));

        return "reservations/edit";
    }

    @PostMapping("/{id}")
    public String doEditReservation(@PathVariable("id") ReservationId reservationId,
                                 @Validated(EditReservationValidationGroupSequence.class) @ModelAttribute("reservation") EditReservationFormData formData,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("customers", customerService.getAllCustomersNameAndId());
            model.addAttribute("roomTypes", Stream.of(
                    new RoomType[]{RoomType.SUPERIOR, RoomType.DELUXE, RoomType.SUITE, RoomType.VILLA}).collect(Collectors.toList()));
            model.addAttribute("reservationTypes", Stream.of(
                    new ReservationType[]{ReservationType.BB, ReservationType.FULL_BOARD, ReservationType.HALF_BOARD}).collect(Collectors.toList()));
            model.addAttribute("editMode", EditMode.UPDATE);
            model.addAttribute("paymentMethods", Stream.of(
                    new PaymentMethod[]{PaymentMethod.CASH, PaymentMethod.CREDIT_CARD, PaymentMethod.DEBIT_CARD}).collect(Collectors.toList()));

            return "reservations/edit";
        }

        reservationService.editReservation(reservationId, formData.toParameters());

        return "redirect:/reservations";
    }

    @PostMapping("/{id}/delete")
    public String doDeleteReservation(@PathVariable("id") ReservationId reservationId,
                                   RedirectAttributes redirectAttributes) {
        Reservation reservation = reservationService.getReservation(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException(reservationId));

        reservationService.deleteReservation(reservationId);

        redirectAttributes.addFlashAttribute("deletedReservationId",
                reservationId);

        return "redirect:/reservations";
    }
}
