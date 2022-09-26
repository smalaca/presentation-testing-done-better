package com.smalaca.conference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConferenceTest {
    private static final String VALID_TITLE = "Testing better";
    private static final String VALID_CONTENT = "Learn how to write better tests.";
    private static final String INVALID_CONTENT = "";
    private static final int INVALID_SLOT_NUMBER = 0;
    private static final int AVAILABLE_SLOT_NUMBER = 13;
    private static final int NOT_AVAILABLE_SLOT_NUMBER = 42;

    private final PresentationDtoFactory presentationDtoFactory = new PresentationDtoFactory(
            new TitleValidator(), new ContentValidator(), new SlotNumberValidator());
    private final PresentationFactory presentationFactory = new PresentationFactory();

    @Test
    void shouldRecognizeMissingTitle() {
        Conference conference = conference();

        Executable executable = () -> conference.register(null, VALID_CONTENT, AVAILABLE_SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid title received.");
    }

    @Test
    void shouldRecognizeInvalidTitleWhenBlank() {
        Conference conference = conference();

        Executable executable = () -> conference.register("", VALID_CONTENT, AVAILABLE_SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid title.");
    }

    @Test
    void shouldRecognizeInvalidTitleWhenTooShort() {
        Conference conference = conference();

        Executable executable = () -> conference.register("short", VALID_CONTENT, AVAILABLE_SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid title.");
    }

    @Test
    void shouldRecognizeInvalidTitleWhenTooLong() {
        Conference conference = conference();

        Executable executable = () -> conference.register("got 41 characters so it means is too long", VALID_CONTENT, AVAILABLE_SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid title.");
    }

    @Test
    void shouldRecognizeMissingContent() {
        Conference conference = conference();

        Executable executable = () -> conference.register(VALID_TITLE, null, AVAILABLE_SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid content received.");
    }

    @Test
    void shouldRecognizeInvalidContent() {
        Conference conference = conference();

        Executable executable = () -> conference.register(VALID_TITLE, INVALID_CONTENT, AVAILABLE_SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid content.");
    }

    @Test
    void shouldRecognizeInvalidSlotAsNotAvailable() {
        Conference conference = conference();

        Executable executable = () -> conference.register(VALID_TITLE, VALID_CONTENT, INVALID_SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid slot number.");
    }

    @Test
    void shouldRegisterPresentation() {
        Conference conference = conference();

        Presentation actual = conference.register(VALID_TITLE, VALID_CONTENT, AVAILABLE_SLOT_NUMBER);

        assertThat(actual).extracting("title").isEqualTo(VALID_TITLE);
        assertThat(actual).extracting("content").isEqualTo(VALID_CONTENT);
        assertThat(actual).extracting("slotNumber").isEqualTo(AVAILABLE_SLOT_NUMBER);
        assertThat(actual).extracting("presentationId").isInstanceOf(UUID.class);
    }

    @Test
    void shouldRecognizeNotAvailableSlot() {
        Conference conference = conference();
        conference.register(VALID_TITLE, VALID_CONTENT, NOT_AVAILABLE_SLOT_NUMBER);

        Executable executable = () -> conference.register(VALID_TITLE, VALID_CONTENT, NOT_AVAILABLE_SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Unavailable slot.");
    }

    private Conference conference() {
        return new Conference(presentationDtoFactory, presentationFactory);
    }
}