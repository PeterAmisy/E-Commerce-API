package com.peter.amisy.e_commerce_api.dto;

import com.peter.amisy.e_commerce_api.model.User;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserDto {

    private Integer id;

    @NotNull(message = "Le prenom ne doit pas etre vide")
    @NotEmpty(message = "Le prenom ne doit pas etre vide")
    @NotBlank(message = "Le prenom ne doit pas etre vide")
    private String name;

    @NotNull(message = "L'email ne doit pas etre vide")
    @NotEmpty(message = "L'email ne doit pas etre vide")
    @NotBlank(message = "L'email ne doit pas etre vide")
    @Email(message = "L'email n'est conforme")
    private String email;

    @NotNull(message = "Le mot de passe ne doit pas etre vide")
    @NotEmpty(message = "Le mot de passe ne doit pas etre vide")
    @NotBlank(message = "Le mot de passe ne doit pas etre vide")
    @Size(min = 8, max = 16, message = "Le mot de passe doit etre entre 8 et 16 caracteres")
    private String password;

    private boolean isAdmin;

    private boolean isActivated;


    public static UserDto fromEntity (User user) {

        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .isAdmin(user.isAdmin())
                .isActivated(user.isActivated())
                .build();
    }

    public static User toEntity (UserDto user) {

        return User.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .isAdmin(user.isAdmin())
                .isActivated(user.isActivated())
                .password(user.getPassword())
                .build();

    }
}