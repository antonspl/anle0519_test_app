package org.anle.dto;

import java.time.OffsetDateTime;
import java.util.Optional;

public record CustomerDto(
        Long id,
        Optional<String> name,
        Optional<String> email,
        Optional<String> description,
        OffsetDateTime dateCreated
) {}
