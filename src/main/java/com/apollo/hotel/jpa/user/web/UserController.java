package com.apollo.hotel.jpa.user.web;

import com.apollo.hotel.infrastructure.validation.ValidationGroupSequence;
import com.apollo.hotel.infrastructure.web.EditMode;
import com.apollo.hotel.jpa.user.*;
import com.apollo.hotel.jpa.user.web.CreateUserFormData;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model, @SortDefault.SortDefaults({
            @SortDefault("userName.lastName"),
            @SortDefault("userName.firstName")}) Pageable pageable) {
        model.addAttribute("users", service.getUsers(pageable));
        return "users/list";
    }

    @GetMapping("/create")
    public String createUserForm(Model model) {
        model.addAttribute("user", new CreateUserFormData());
        model.addAttribute("genders", Stream.of(
                new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
        model.addAttribute("types", Stream.of(
                new Type[]{Type.USER, Type.ADMIN}).collect(Collectors.toList()));
        model.addAttribute("editMode", EditMode.CREATE);
        return "users/edit";
    }

    @PostMapping("/create")
    public String doCreateUser(@Validated(ValidationGroupSequence.class) @ModelAttribute("user") CreateUserFormData formData,
                               BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", Stream.of(
                    new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
            model.addAttribute("types", Stream.of(
                    new Type[]{Type.USER, Type.ADMIN}).collect(Collectors.toList()));
            model.addAttribute("editMode", EditMode.CREATE);
            return "users/edit";
        }

        service.createUser(formData.toParameters());

        return "redirect:/users";
    }

    // tag::edit-get[]
    @GetMapping("/{id}") //<.>
    public String editUserForm(@PathVariable("id") UserId userId, //<.>
                               Model model) {
        User user = service.getUser(userId)
                .orElseThrow(() -> new UserNotFoundException(userId)); //<.>
        model.addAttribute("user", EditUserFormData.fromUser(user)); //<.>
        model.addAttribute("genders", Stream.of(
                new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
        model.addAttribute("types", Stream.of(
                new Type[]{Type.USER, Type.ADMIN}).collect(Collectors.toList()));
        model.addAttribute("editMode", EditMode.UPDATE); //<.>
        return "users/edit"; //<.>
    }
    // end::edit-get[]

    // tag::edit-post[]
    @PostMapping("/{id}")
    public String doEditUser(@PathVariable("id") UserId userId,
                             @Validated(EditUserValidationGroupSequence.class) @ModelAttribute("user") EditUserFormData formData, //<.>
                             BindingResult bindingResult,
                             Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("genders", Stream.of(
                    new Gender[]{Gender.MALE, Gender.FEMALE, Gender.OTHER}).collect(Collectors.toList()));
            model.addAttribute("types", Stream.of(
                    new Type[]{Type.USER, Type.ADMIN}).collect(Collectors.toList()));
            model.addAttribute("editMode", EditMode.UPDATE);
            return "users/edit";
        }

        service.editUser(userId, formData.toParameters());

        return "redirect:/users";
    }
}
