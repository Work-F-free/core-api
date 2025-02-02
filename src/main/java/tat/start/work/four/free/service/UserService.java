package tat.start.work.four.free.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tat.start.work.four.free.entity.Owner;
import tat.start.work.four.free.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Owner getUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }
}
