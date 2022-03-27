package com.apollo.hotel.jpa.customer.web;

import com.apollo.hotel.infrastructure.validation.ValidationGroupSequence;
import com.apollo.hotel.infrastructure.web.EditMode;
import com.apollo.hotel.jpa.customer.*;
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
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService service;

    public CustomerController(CustomerService service){
        this.service = service;
    }

    @GetMapping
    public String index(Model model, @SortDefault.SortDefaults({
            @SortDefault("fullName.lastName"),
            @SortDefault("fullName.firstName")}) Pageable pageable) {
        model.addAttribute("customers", service.getCustomers(pageable));
        return "customers/list";
    }

    @GetMapping("/create")
    public String createCustomerForm(Model model) {
        model.addAttribute("customer", new CreateCustomerFormData());
        model.addAttribute("genders", Stream.of(
                new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
        model.addAttribute("editMode", EditMode.CREATE);
        return "customers/edit";
    }

    @PostMapping("/create")
    public String doCreateCustomer(@Validated(ValidationGroupSequence.class) @ModelAttribute("customer") CreateCustomerFormData formData,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", Stream.of(
                    new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
            model.addAttribute("editMode", EditMode.CREATE);
            return "customers/edit";
        }

        service.createCustomer(formData.toParameters());

        return "redirect:/customers";
    }

    @GetMapping("/{id}")
    public String editCustomerForm(@PathVariable("id") CustomerId customerId, Model model) {
        Customer customer = service.getCustomer(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));
        model.addAttribute("customer", EditCustomerFormData.fromCustomer(customer));
        model.addAttribute("genders", Stream.of(
                new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
        model.addAttribute("editMode", EditMode.UPDATE);
        return "customers/edit";
    }

    @PostMapping("/{id}")
    public String doEditCustomer(@PathVariable("id") CustomerId customerId,
                             @Validated(EditCustomerValidationGroupSequence.class) @ModelAttribute("customer") EditCustomerFormData formData,
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", Stream.of(
                    new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
            model.addAttribute("editMode", EditMode.UPDATE);
            return "customers/edit";
        }

        service.editCustomer(customerId, formData.toParameters());

        return "redirect:/customers";
    }

    @PostMapping("/{id}/delete")
    public String doDeleteCustomer(@PathVariable("id") CustomerId customerId,
                               RedirectAttributes redirectAttributes) {
        Customer customer = service.getCustomer(customerId)
                .orElseThrow(() -> new CustomerNotFoundException(customerId));

        service.deleteCustomer(customerId);

        redirectAttributes.addFlashAttribute("deletedCustomerName",
                customer.getFullName().getFullName());

        return "redirect:/customers";
    }
}
