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
     * Get current user
     *
     * @return Current user
     */
    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @PutMapping("/me")
    public ResponseEntity<GlobalMessageResponse> updateCurrentUser(@Valid @RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.updateCurrentUser(userRequest));
    }
}
