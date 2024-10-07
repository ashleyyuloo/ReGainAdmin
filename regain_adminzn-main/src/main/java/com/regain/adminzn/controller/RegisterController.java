package com.regain.adminzn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.regain.adminzn.dto.AdminDTO;
import com.regain.adminzn.model.Admin;
import com.regain.adminzn.model.Role;
import com.regain.adminzn.repository.AdminRepository;
import com.regain.adminzn.repository.RoleRepository;


@Controller
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private RoleRepository roleRepository;

    // @Autowired
    // private PasswordEncoder passwordEncoder;

    // @PostMapping("/register")
    // public Admin createAdmin(@RequestBody AdminDTO adminDTO) {
    //     Admin admin = new Admin();
    //     admin.setUsername(adminDTO.getUsername());
    //     admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
    //     admin.setEmail(adminDTO.getEmail());

    //     Role role = adminRepository.findRoleById(adminDTO.getRole().getId());
    //     admin.setRole(role);

    //     return adminRepository.save(admin);
    // }

}

