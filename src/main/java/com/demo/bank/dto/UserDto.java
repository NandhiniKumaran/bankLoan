package com.demo.bank.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto
{
    private Long id;
    @NotNull(message = "firstName should not be null")
    @NotEmpty(message = "firstName should not be empty")
    private String firstName;
    @NotNull(message = "lastName should not be null")
    @NotEmpty(message = "lastName should not be empty")
    private String lastName;
    @NotNull(message = "email should not be null")
    @NotEmpty(message = "email should not be empty")
    @Email(message = "email not in correct format")
    private String email;
    private String password;
    private String role;
    private String username;
    private RoleList roleList;
}
