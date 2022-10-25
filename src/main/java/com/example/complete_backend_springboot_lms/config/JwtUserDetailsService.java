package com.example.complete_backend_springboot_lms.config;

import com.example.complete_backend_springboot_lms.entity.Admin;
import com.example.complete_backend_springboot_lms.exception.UserDefinedException;
import com.example.complete_backend_springboot_lms.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository userDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    /*****************************************************************************************************8*********************
     * Ability to retrive the User credential from database.
     * @param username
     * @return user
     * @throws UsernameNotFoundException
     **************************************************************************************************************************/

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin user = userDao.findByFirstName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new User(user.getFirstName(), user.getPassword(), new ArrayList<>());
    }


    //Ability to authenticate username and password.
    public void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new UserDefinedException("USER_DISABLED");
        } catch (BadCredentialsException e) {
            throw new UserDefinedException("INVALID_CREDENTIALS");
        }
    }

}
