package org.learning.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.learning.springsecurity.model.Customer;
import org.learning.springsecurity.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAppUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Customer> userEmail = Optional.ofNullable(customerRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User details is not found")));
        return null;
    }
}
