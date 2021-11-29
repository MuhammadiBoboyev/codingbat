package uz.pdp.task2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.task2.entity.User;
import uz.pdp.task2.payload.ApiResponse;
import uz.pdp.task2.payload.UserDto;
import uz.pdp.task2.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public ApiResponse addUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail()))
            return new ApiResponse("this email is already exists", false);
        userRepository.save(new User(userDto.getEmail(), userDto.getPassword()));
        return new ApiResponse("success added", true);
    }

    public ApiResponse editUser(int id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return new ApiResponse("user not found", false);
        if (userRepository.existsByEmailAndIdNot(userDto.getEmail(), id))
            return new ApiResponse("this email is already exists", false);
        User user = optionalUser.get();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        userRepository.save(user);
        return new ApiResponse("success full edited", true);
    }

    public ApiResponse deleteUser(int id) {
        if (!userRepository.existsById(id))
            return new ApiResponse("user not found", false);
        userRepository.deleteById(id);
        return new ApiResponse("success full deleted", true);
    }

}
