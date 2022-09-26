package com.smalaca.conference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ContentValidatorTest {
    private final ContentValidator validator = new ContentValidator();

    @ParameterizedTest
    @NullSource
    @ValueSource(strings = {"", "    "})
    void shouldRecognizeInvalidContent(String content) {
        Executable executable = () -> validator.validate(content);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid content.");
    }

    @Test
    void shouldRecognizeValidContent() {
        assertDoesNotThrow(() -> validator.validate("Testing better"));
    }
}