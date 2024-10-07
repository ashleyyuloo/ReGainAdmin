// package com.regain.adminzn.security;

// import java.util.List;
// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.User;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.regain.adminzn.model.*;
// import com.regain.adminzn.repository.AdminRepository;

// @Service
// public class AdminDetailService implements UserDetailsService {

//     @Autowired
//     private AdminRepository adminRepository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         Optional<Admin> admin = adminRepository.findByUsername(username);
//         if(admin.isPresent()){
//             var adminObj = admin.get();
//             return User.builder()
//             .username(adminObj.getUsername())
//             .password(adminObj.getPassword())
//             .roles(adminObj.getRole().getRoleType())
//             .build();
//         } else{
//             throw new UsernameNotFoundException(username);
//         }
//     }
// }
