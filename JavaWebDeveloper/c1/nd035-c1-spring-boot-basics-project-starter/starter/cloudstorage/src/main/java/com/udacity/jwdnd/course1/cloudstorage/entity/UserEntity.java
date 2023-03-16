package com.udacity.jwdnd.course1.cloudstorage.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private Integer userId;
    @NotNull(message = "This field must be not null")
    @NotBlank(message = "This field must be not blank")
    private String username;
    private String salt;
    @NotNull(message = "This field must be not null")
    @NotBlank(message = "This field must be not blank")
    private String password;
    private String firstName;
    private String lastName;
}
