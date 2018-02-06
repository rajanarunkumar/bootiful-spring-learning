package com.example.demo.service;

import com.example.demo.repository.UserRepository;
import com.example.demo.repository.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/user")
public class UserStreamService {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/v1/save")
    public Flux<User> save(@RequestBody @NotNull Flux<User> users) {
        return userRepository.saveAll(users);
    }

    @GetMapping("/v1/getAll")
    @Cacheable("users")
    public Optional<Flux<User>> getAll() {
        return Optional.of(userRepository.findAll());
    }

    @GetMapping("/v1/getBySID")
    @Cacheable("users")
    public Optional<Mono<User>> getById(@RequestParam String standardId) {
        return Optional.of(userRepository.findById(standardId));
    }
}
