package com.openclassrooms.mddapi.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Enumeration representing user roles in the system.
 */
@Schema(description = "Enumeration representing different user roles")
public enum Role {
    USER,
    ADMIN
}
