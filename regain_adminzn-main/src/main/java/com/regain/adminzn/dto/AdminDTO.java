package com.regain.adminzn.dto;

import com.regain.adminzn.model.Role;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdminDTO {
    private int id;
    private String username;
    private String password;
    private String email;
    private Role role;
}
