package com.peter.amisy.e_commerce_api.controller;

import com.peter.amisy.e_commerce_api.dto.UserDto;
import com.peter.amisy.e_commerce_api.model.User;
import com.peter.amisy.e_commerce_api.service.UserService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "user")
public class UserController {

    private final UserService service;

    @ApiResponses({
            @ApiResponse(responseCode = "302", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @Tag(name = "get", description = "SAVE  methods of User's APIs")
    @PostMapping("/")
    public ResponseEntity<Integer> save(
            @RequestBody UserDto userDto
    ) {
        return ResponseEntity.ok(service.update(userDto));
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "No Users found",
                    content = @Content) })
    @Tag(name = "get", description = "GET ALL methods of User's APIs")
    @GetMapping("/")
    public ResponseEntity<List<UserDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "No User found",
                    content = @Content) })
    @Tag(name = "get", description = "GET  methods of User APIs")
    @GetMapping("/{user-id}")
    public ResponseEntity<UserDto> findById(
            @PathVariable("user-id") Integer userId
    ) {
        return ResponseEntity.ok(service.findById(userId));
    }


    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(mediaType = "application/json",
                    schema = @Schema(implementation = User.class)) }),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content) })
    @Tag(name = "delete", description = "DELETE methods of User's APIs")
    @DeleteMapping("/{user-id}")
    public ResponseEntity<Void> delete(
            @PathVariable("user-id") Integer userId
    ) {
        service.delete(userId);
        return ResponseEntity.accepted().build();
    }

}
