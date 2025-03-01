package org.anle.dto;

import java.util.Optional;

public record CustomerUpdateDto(
        Optional<String> name,
        Optional<String> email,
        Optional<String> description
) {
}
