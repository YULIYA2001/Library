//package com.golubovich.library.spring.config.security;
//
//
//import com.golubovich.library.spring.model.Person;
//import com.golubovich.library.spring.repository.PersonRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//
//@Service("userDetailsServiceImpl")
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    private final PersonRepository userRepository;
//
//    @Autowired
//    public UserDetailsServiceImpl(PersonRepository userRepository) {
//        this.userRepository = userRepository;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        Person person = userRepository.findByEmail(email).orElse(null);
//        if (person == null) {
//            throw new UsernameNotFoundException("User does not exists");
//        }
//        return SecurityUser.fromUser(person);
//    }
//}
