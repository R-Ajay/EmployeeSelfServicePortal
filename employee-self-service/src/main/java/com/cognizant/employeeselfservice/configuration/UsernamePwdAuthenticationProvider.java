//package com.cognizant.employeeselfservice.configuration;
//
//import com.cognizant.employeeselfservice.model.Customer;
//import com.cognizant.employeeselfservice.repository.CustomerRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.authentication.BadCredentialsException;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Component
//public class UsernamePwdAuthenticationProvider implements AuthenticationProvider {
//
//    @Autowired
//    private CustomerRepository customerRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String username = authentication.getName();
//        String password = authentication.getCredentials().toString();
//        List<Customer> customerList = customerRepository.findByEmail(username);
//
//        if(customerList!=null && !customerList.isEmpty()){
//           if(passwordEncoder.matches(password, customerList.get(0).getPassword())){
//               List<GrantedAuthority> authorities = new ArrayList<>();
//               authorities.add(new SimpleGrantedAuthority(customerList.get(0).getRole()));
//               return new UsernamePasswordAuthenticationToken(username, password, authorities);
//           }else {
//               throw new BadCredentialsException("Invalid Credentials");
//           }
//        }else {
//            throw new BadCredentialsException("No user is registered with is email");
//        }
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return (UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication));
//    }
//}
