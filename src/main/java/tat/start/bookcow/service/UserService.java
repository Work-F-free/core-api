package tat.start.bookcow.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tat.start.bookcow.entity.Owner;
import tat.start.bookcow.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Owner getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
