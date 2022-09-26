package com.smalaca.conference;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class PresentationTest {
    private static final UUID ID = UUID.randomUUID();
    private static final String TITLE = "Testing better";
    private static final String CONTENT = "Learn how to write better tests.";
    private static final int SLOT_NUMBER = 13;
    private static final int DIFFERENT_SLOT_NUMBER = 42;

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