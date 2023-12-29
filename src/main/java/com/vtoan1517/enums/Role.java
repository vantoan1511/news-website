package com.vtoan1517.enums;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_ADMIN("admin"),
    ROLE_AUTHOR("author"),
    ROLE_USER("user");

    private final String description;

    Role(String description) {
        this.description = description;
    }
}
