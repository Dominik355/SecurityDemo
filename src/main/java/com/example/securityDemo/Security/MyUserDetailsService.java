package com.example.securityDemo.Security;

import com.example.securityDemo.Models.Database.User;
import com.example.securityDemo.Models.Transfer.UserDTO;
import com.example.securityDemo.Models.Transfer.UserRegistration;
import com.example.securityDemo.Repositories.UserRepository;
import com.example.securityDemo.Security.authorization.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import static com.example.securityDemo.Security.authorization.UserRole.*;
import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Qualifier("real")
    @Autowired
    private UserRepository userRepository;

    private static final List<User> USERS = Arrays.asList(
            new User("Emil Maly", STUDENT.name()),
            new User("Robo Krok", STUDENT.name()),
            new User("Kristian Nozicka",TEACHER.name()),
            new User("Gregor Rozok", ADMIN.name())
    );

    @PostConstruct
    private void encodePasswords() {
        USERS.stream().forEach(s ->
                s.setPassword(passwordEncoder.encode(s.getStudentName().split(" ")[0])));
        userRepository.saveAll(USERS);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new MyUserDetails(userRepository.findByStudentName(username)
            .orElseThrow(() -> new IllegalStateException("User " + username + " does not exists")));
    }

    public UserDTO loadStudentById(Integer id) {
        return new UserDTO(userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("User " + id + " does not exists")));
    }

    public List<User> getAllStudents() {
        return USERS;
    }

    public String createUser(UserRegistration userRegistration){
        userRegistration.setPassword(passwordEncoder.encode(userRegistration.getPassword()));
        userRepository.save(new User(userRegistration));
        return "User succesfully created";
    }

    @PostConstruct
    private void seeUsers() {
        this.userRepository.findAll().forEach( u -> {
            System.out.println("USER: " + u);
            System.out.println("        ROLE: " + u.getRole() + ", PERMISSIONS: " + UserRole.valueOf(u.getRole()).getGrantedAuthorities());
        });
    }

}
