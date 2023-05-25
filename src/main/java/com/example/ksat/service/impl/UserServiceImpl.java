package com.example.ksat.service.impl;

import com.example.ksat.domain.User;
import com.example.ksat.exception.UserNotFoundException;
import com.example.ksat.repository.UserRepository;
import com.example.ksat.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public void checkUserExistOrThrow(@NotNull Long userId) {
        if (!userRepository.existsById(userId)) {
            log.error("No User exist for this id: {} ", userId);
            throw new UserNotFoundException(userId);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserOrThrow(@NotNull Long userId) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            log.error("No User exist for this id: {} ", userId);
            throw new UserNotFoundException(userId);
        }

        return user.get();
    }
}
