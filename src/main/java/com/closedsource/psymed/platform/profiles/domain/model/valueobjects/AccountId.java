package com.closedsource.psymed.platform.profiles.domain.model.valueobjects;

public record AccountId(Long id) {
    public AccountId {
        if (id == null) {
            throw new IllegalArgumentException("id cannot be null");
        }
    }
}
