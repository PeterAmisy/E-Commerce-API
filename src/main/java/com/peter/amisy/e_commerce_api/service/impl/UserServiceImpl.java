package com.peter.amisy.e_commerce_api.service.impl;


import com.peter.amisy.e_commerce_api.config.JwtUtils;
import com.peter.amisy.e_commerce_api.dto.AuthenticationRequest;
import com.peter.amisy.e_commerce_api.dto.AuthenticationResponse;
import com.peter.amisy.e_commerce_api.dto.UserDto;
import com.peter.amisy.e_commerce_api.model.Role;
import com.peter.amisy.e_commerce_api.model.User;
import com.peter.amisy.e_commerce_api.repository.RoleRepository;
import com.peter.amisy.e_commerce_api.repository.UserRepository;
import com.peter.amisy.e_commerce_api.service.UserService;
import com.peter.amisy.e_commerce_api.validator.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private static final String ROLE_USER = "ROLE_USER";

    @Autowired
    public UserServiceImpl(UserRepository repository, ObjectsValidator<UserDto> validator, PasswordEncoder passwordEncoder, JwtUtils jwtUtils, AuthenticationManager authManager, RoleRepository roleRepository) {
        this.repository = repository;
        this.validator = validator;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtils = jwtUtils;
        this.authManager = authManager;
        this.roleRepository = roleRepository;
    }

    private final UserRepository repository;
    private final ObjectsValidator<UserDto> validator;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authManager;
    private final RoleRepository roleRepository;

    @Override
    public Integer save(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return repository.save(user).getId();
    }



    @Override
    @Transactional
    public List<UserDto> findAll() {
        return repository.findAll()
                .stream()
                .map(UserDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto findById(Integer id) {
        return repository.findById(id)
                .map(UserDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("No user was found with the provided ID : " + id));
    }

    @Override
    public void delete(Integer id) {
        // todo check before delete
        repository.deleteById(id);
    }



    @Override
    @Transactional
    public AuthenticationResponse register(UserDto dto) {
        validator.validate(dto);
        User user = UserDto.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(
                findOrCreateRole(ROLE_USER)
        );
        var savedUser = repository.save(user);
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", savedUser.getId());
        claims.put("fullName", savedUser.getName() + " " + savedUser.getEmail());
        String token = jwtUtils.generateToken((UserDetails) savedUser, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        final User user = repository.findByEmail(request.getEmail()).get();
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getId());
        claims.put("fullName", user.getName() + " " + user.getEmail());
        final String token = jwtUtils.generateToken((UserDetails) user, claims);
        return AuthenticationResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public Integer update(UserDto userDto) {
        User user = UserDto.toEntity(userDto);
        return repository.save(user).getId();
    }

    private Role findOrCreateRole(String roleName) {
        Role role = roleRepository.findByName(roleName)
                .orElse(null);
        if (role == null) {
            return roleRepository.save(
                    Role.builder()
                            .name(roleName)
                            .build()
            );
        }
        return role;
    }
}
