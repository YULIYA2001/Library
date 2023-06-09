package com.golubovich.library.spring.config;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final UserDetailsService userDetailsService;
//
//    @Autowired
//    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/user/add").permitAll()
//                .antMatchers("/bank").permitAll()
////                .antMatchers("/admin/credits/**").permitAll()
//                .anyRequest()
//                .authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/user/login").permitAll()
//                .failureHandler(authenticationFailureHandler())
//                .and()
//                .exceptionHandling()
//                .and()
//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout", "POST"))
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/");
//    }
//
//    // for images to unauthorized users
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().antMatchers(
//                // статика
//                "/css/**",
//                "/images/**"
//        );
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(daoAuthenticationProvider());
//    }
//
//    @Bean
//    protected PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(12);
//    }
//
//    @Bean
//    protected DaoAuthenticationProvider daoAuthenticationProvider() {
//        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
//        return daoAuthenticationProvider;
//    }
//
//    @Bean
//    public AuthenticationFailureHandler authenticationFailureHandler() {
//        return (request, response, exception) -> {
//            request.getSession().setAttribute("error", "Пользователь не найден. Неверные данные");
//            response.sendRedirect("/auth/login");
//        };
//    }
//}
//
//
////@Configuration
////public class SecurityConfig {
////    private final UserDetailsService userDetailsService;
////
////    @Autowired
////    public SecurityConfig(@Qualifier("userDetailsServiceImpl") UserDetailsService userDetailsService) {
////        this.userDetailsService = userDetailsService;
////    }
////
//////    @Bean
//////    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
//////        UserDetails user = User.withUsername("user")
//////                .password(passwordEncoder.encode("password"))
//////                .roles("USER")
//////                .build();
//////
//////        UserDetails admin = User.withUsername("admin")
//////                .password(passwordEncoder.encode("admin"))
//////                .roles("USER", "ADMIN")
//////                .build();
//////
//////        return new InMemoryUserDetailsManager(user, admin);
//////    }
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http.cors().disable();
////        http.authorizeRequests()
////                .requestMatchers("/user/login").permitAll()
//////                .requestMatchers("/user/**").permitAll()
//////                .requestMatchers("/user/login").permitAll()
//////                .requestMatchers("/book/update/**").permitAll()
////                .anyRequest()
////                .authenticated()
////                .and()
//////                .formLogin(Customizer.withDefaults())
////                .formLogin()
////                .loginPage( "/user/login").permitAll()
////                .defaultSuccessUrl("/", true)
////                .failureHandler(authenticationFailureHandler())
////                .and()
////                .exceptionHandling()
////                .and()
////                .logout()
////                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout", "POST"))
////                .invalidateHttpSession(true)
////                .clearAuthentication(true)
////                .deleteCookies("JSESSIONID")
////                .logoutSuccessUrl("/");
////        return http.build();
////    }
////
////
////
////    // for images to unauthorized users
//////    @Override
//////    public void configure(WebSecurity web) {
//////        web.ignoring().requestMatchers(
//////                // статика
//////                "/css/**",
//////                "/images/**"
//////        );
//////    }
////
//////    @Override
//////    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//////        auth.authenticationProvider(daoAuthenticationProvider());
//////    }
////
////    @Bean
////    protected PasswordEncoder passwordEncoder() {
////        return new BCryptPasswordEncoder(12);
////    }
////
////    @Bean
////    protected DaoAuthenticationProvider daoAuthenticationProvider() {
////        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
////        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
////        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
////        return daoAuthenticationProvider;
////    }
////
////    @Bean
////    public AuthenticationFailureHandler authenticationFailureHandler() {
////        return (request, response, exception) -> {
////            request.getSession().setAttribute("error", "Пользователь не найден. Неверные данные");
////            response.sendRedirect("/user/login");
////        };
////    }
////
////}
