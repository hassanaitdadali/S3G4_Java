package ma.emsi.s3g1.services;



import ma.emsi.s3g1.entities.User;
import ma.emsi.s3g1.repositories.UserRepository;
import org.springframework.stereotype.Service;
@Service
public class
UserService {
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User getUserByEmail(String email){
        User user;
        user = (User) userRepository.findUserByEmail(email);
        return user;
    }
    public User createUser(User user){
        User newUser = (User) userRepository.save(user);
        userRepository.flush();
        return newUser;
    }
}


