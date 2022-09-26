package com.smalaca.conference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PresentationTest {
    private static final UUID ID = UUID.randomUUID();
    private static final String TITLE = "Testing better";
    private static final String CONTENT = "Learn how to write better tests.";
    private static final int SLOT_NUMBER = 13;
    private static final int DIFFERENT_SLOT_NUMBER = 42;
    private static final int INVALID_SLOT_NUMBER = 0;

    @Test
    void shouldRecognizeWhenInvalidSlotVerified() {
        Presentation presentation = presentationFor(SLOT_NUMBER);

        Executable executable = () -> presentation.scheduledForSlot(INVALID_SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid slot number given.");
    }

    @Test
    void shouldRecognizeWhenIsScheduledForGivenSlot() {
        Presentation presentation = presentationFor(SLOT_NUMBER);

        boolean actual = presentation.scheduledForSlot(SLOT_NUMBER);

        assertThat(actual).isTrue();
    }

    @Test
    void shouldRecognizeWhenIsNotScheduledForGivenSlot() {
        Presentation presentation = presentationFor(SLOT_NUMBER);

        boolean actual = presentation.scheduledForSlot(DIFFERENT_SLOT_NUMBER);

        assertThat(actual).isFalse();
    }

    private Presentation presentationFor(int slotNumber) {
        return new Presentation(ID, TITLE, CONTENT, slotNumber);
    }
}