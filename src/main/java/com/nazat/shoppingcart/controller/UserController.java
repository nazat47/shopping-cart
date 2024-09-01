package com.nazat.shoppingcart.controller;

import com.nazat.shoppingcart.dto.UserDto;
import com.nazat.shoppingcart.entities.User;
import com.nazat.shoppingcart.exceptions.AlreadyExistsException;
import com.nazat.shoppingcart.exceptions.ResourceNotFoundException;
import com.nazat.shoppingcart.request.CreateUserRequest;
import com.nazat.shoppingcart.request.UpdateUserRequest;
import com.nazat.shoppingcart.response.ApiResponse;
import com.nazat.shoppingcart.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class UserController {

    private final IUserService iUserService;

    @GetMapping("/getById/{userId}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long userId) {
        try {
            User user = iUserService.getUserById(userId);
            UserDto userDto=iUserService.convertToUserDto(user);
            return ResponseEntity.ok(new ApiResponse("user found", userDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createUser(@RequestBody CreateUserRequest request) {
        try {
            User user = iUserService.createUser(request);
            UserDto userDto=iUserService.convertToUserDto(user);
            return ResponseEntity.ok(new ApiResponse("User created", userDto));
        } catch (AlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/update/{userId}")
    public ResponseEntity<ApiResponse> updateUser(@RequestBody UpdateUserRequest request, @PathVariable Long userId) {
        try {
            User user = iUserService.updateUser(request, userId);
            UserDto userDto=iUserService.convertToUserDto(user);
            return ResponseEntity.ok(new ApiResponse("User updated", userDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long userId) {
        try {
            iUserService.deleteUser(userId);
            return ResponseEntity.ok(new ApiResponse("User deleted", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
