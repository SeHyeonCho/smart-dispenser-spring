package com.hanium.smartdispenser.user.service;

import com.hanium.smartdispenser.user.domain.User;
import com.hanium.smartdispenser.user.dto.UserCreateDto;
import com.hanium.smartdispenser.user.dto.UserResponseDto;
import com.hanium.smartdispenser.user.exception.DuplicateEmailException;
import com.hanium.smartdispenser.user.exception.UserNotFoundException;
import com.hanium.smartdispenser.user.respository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponseDto createUser(UserCreateDto dto) {
        User user = User.of(dto.getName(), dto.getPassword(), dto.getEmail());

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new DuplicateEmailException(user.getEmail());
        }

        // 동시성 문제 해결
        // save되는 시점이 transaction이 끝날때인데 try-catch안에서 안이루어
        // 이 코드 넣어야되나 고민 해봐야 됨.

        try {
            return UserResponseDto.of(userRepository.save(user));
        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEmailException(e, user.getEmail());
        }
    }

    public User findByEmail(String email) {
        Optional<User> user = userRepository.findByEmail(email);
        return user.orElseThrow(() -> new UserNotFoundException(email));
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(()->new UserNotFoundException(id));
    }

}
