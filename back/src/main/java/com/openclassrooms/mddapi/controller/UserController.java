package com.openclassrooms.mddapi.controller;

import com.openclassrooms.mddapi.dto.GlobalMessageResponse;
import com.openclassrooms.mddapi.dto.UserRequest;
import com.openclassrooms.mddapi.dto.UserResponse;
import com.openclassrooms.mddapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Retrieves the currently authenticated user's information.
     *
     * @return a ResponseEntity containing the UserResponse object with the user's details
     */
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    /**
     * Updates the currently authenticated user's information.
     *
     * @param userRequest the request containing the updated user details
     * @return a ResponseEntity containing a GlobalMessageResponse indicating the result of the operation
     */
    @PutMapping("/me")
    public ResponseEntity<GlobalMessageResponse> updateCurrentUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateCurrentUser(userRequest));
    }
}
