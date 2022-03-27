package com.apollo.hotel.jpa.employee.web;

import com.apollo.hotel.infrastructure.validation.ValidationGroupSequence;
import com.apollo.hotel.infrastructure.web.EditMode;
import com.apollo.hotel.jpa.employee.*;
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
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService service;

    public EmployeeController(EmployeeService service){
        this.service = service;
    }

    @GetMapping
    public String index(Model model, @SortDefault.SortDefaults({
            @SortDefault("fullName.lastName"),
            @SortDefault("fullName.firstName")}) Pageable pageable) {
        model.addAttribute("employees", service.getEmployees(pageable));
        return "employees/list";
    }

    @GetMapping("/create")
    public String createEmployeeForm(Model model) {
        model.addAttribute("employee", new CreateEmployeeFormData());
        model.addAttribute("genders", Stream.of(
                new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
        model.addAttribute("editMode", EditMode.CREATE);
        return "employees/edit";
    }

    @PostMapping("/create")
    public String doCreateEmployee(@Validated(ValidationGroupSequence.class) @ModelAttribute("employee") CreateEmployeeFormData formData,
                                   BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", Stream.of(
                    new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
            model.addAttribute("editMode", EditMode.CREATE);
            return "employees/edit";
        }

        service.createEmployee(formData.toParameters());

        return "redirect:/employees";
    }

    @GetMapping("/{id}")
    public String editEmployeeForm(@PathVariable("id") EmployeeId employeeId, Model model) {
        Employee employee = service.getEmployee(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));
        model.addAttribute("employee", EditEmployeeFormData.fromEmployee(employee));
        model.addAttribute("genders", Stream.of(
                new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
        model.addAttribute("editMode", EditMode.UPDATE);
        return "employees/edit";
    }

    @PostMapping("/{id}")
    public String doEditEmployee(@PathVariable("id") EmployeeId employeeId,
                                 @Validated(EditEmployeeValidationGroupSequence.class) @ModelAttribute("employee") EditEmployeeFormData formData,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", Stream.of(
                    new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
            model.addAttribute("editMode", EditMode.UPDATE);
            return "employees/edit";
        }

        service.editEmployee(employeeId, formData.toParameters());

        return "redirect:/employees";
    }

    @PostMapping("/{id}/delete")
    public String doDeleteEmployee(@PathVariable("id") EmployeeId employeeId,
                                   RedirectAttributes redirectAttributes) {
        Employee employee = service.getEmployee(employeeId)
                .orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        service.deleteEmployee(employeeId);

        redirectAttributes.addFlashAttribute("deletedEmployeeName",
                employee.getFullName().getFullName());

        return "redirect:/employees";
    }
}
