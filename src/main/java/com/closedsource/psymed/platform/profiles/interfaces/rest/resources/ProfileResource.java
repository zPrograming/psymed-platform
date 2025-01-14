package com.closedsource.psymed.platform.profiles.interfaces.rest.resources;

public record ProfileResource(
        Long id,
        String fullName,
        String email,
        String streetAddress
) { }
