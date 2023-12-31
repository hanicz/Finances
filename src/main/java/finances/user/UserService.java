package finances.user;

import finances.exception.DuplicateException;
import finances.model.User;
import finances.repository.UserRepository;
import finances.user.dto.SignUpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), emptyList());
    }

    public User getUserByEmail(String email) {
        return this.userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public void signup(SignUpRequest signUp) {
        this.userRepository.findByEmail(signUp.email())
                .ifPresent(user -> {
                    throw new DuplicateException(STR. "User with this email '\{ signUp.email() }' already exists" );
                });
        User user = User.builder()
                .email(signUp.email())
                .password(this.passwordEncoder.encode(signUp.password()))
                .build();
        this.userRepository.save(user);
    }
}
