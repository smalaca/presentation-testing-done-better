package com.smalaca.conference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TitleValidatorTest {
    private final TitleValidator validator = new TitleValidator();

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "short", "shortie", "got 41 characters so it means is too long"})
    void shouldRecognizeInvalidTitle(String title) {
        Executable executable = () -> validator.validate(title);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid title.");
    }

    @Test
    void shouldRecognizeValidTitle() {
        assertDoesNotThrow(() -> validator.validate("Testing better"));
    }
}