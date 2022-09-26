package com.smalaca.conference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PresentationFactoryTest {
    private static final String TITLE = "Testing better";
    private static final String CONTENT = "Learn how to write better tests.";
    private static final int SLOT_NUMBER = 13;
    private final PresentationFactory factory = new PresentationFactory();

    @Test
    void shouldRecognizeInvalidDto() {
        Executable executable = () -> factory.create(null);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Missing dto.");
    }

    @Test
    void shouldCreatePresentation() {
        PresentationDto dto = new PresentationDto(TITLE, CONTENT, SLOT_NUMBER);

        Presentation actual = factory.create(dto);

        assertThat(actual).extracting("title").isEqualTo(TITLE);
        assertThat(actual).extracting("content").isEqualTo(CONTENT);
        assertThat(actual).extracting("slotNumber").isEqualTo(SLOT_NUMBER);
        assertThat(actual).extracting("presentationId").isInstanceOf(UUID.class);
    }
}