package com.example.authservice.service;

import com.example.authservice.dto.UserDto;
import com.example.authservice.exception.GenericException;

import com.example.authservice.model.User;
import com.example.authservice.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public UserDto saveUser(User user){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var saveUser = userRepository.save(user);

        return new UserDto.Builder()
                .userName(saveUser.getUserName())
                .role(saveUser.getRole())
                .email(saveUser.getEmail())
                .build();


//        User saveUser = userRepository.save(user);
//
//        return new UserDto.Builder()
//                .userName(saveUser.getUserName())
//                .password(saveUser.getPassword())
//                .email(saveUser.getEmail())
//                .build();
    }



    //aşağıdaki methotda dönen obje burada tekrar çağırılır ve UserDto ya çevirilir.
    public UserDto getUser(String username) {

        var saveUser = findUserByUserName(username);

        return new UserDto.Builder()
                .userName(saveUser.getUserName())
                .role(saveUser.getRole())
                .email(saveUser.getEmail())
                .build();
    }



    // Bu methot bir user objesi döner
    public User findUserByUserName(String username){
        return userRepository.findUserByUserName(username)
                .orElseThrow(()-> GenericException.builder()
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .message("user not found by given Id")
                        .build());

    }



    public void deleteUser(String user){
        userRepository.deleteByUserName(user);
    }


//    public void getUser(String userName) {
//      boolean exists = userRepository.existsByUserName(userName);
//
//      if (!exists) {
//        throw new UserNotFoundException("user not found");
//      }
//      userRepository.existsByUserName(userName);
//
//    }






}















