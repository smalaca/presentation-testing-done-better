package com.smalaca.conference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class ConferenceTest {
    private static final String VALID_TITLE = "Testing better";
    private static final String VALID_CONTENT = "Learn how to write better tests.";
    private static final int SLOT_NUMBER = 13;
    private static final PresentationDto PRESENTATION_DTO = new PresentationDto(VALID_TITLE, VALID_CONTENT, SLOT_NUMBER);

    private final PresentationDtoFactory presentationDtoFactory = mock(PresentationDtoFactory.class);
    private final PresentationFactory presentationFactory = mock(PresentationFactory.class);
    private final Conference conference = new Conference(presentationDtoFactory, presentationFactory);

    @Test
    void shouldRecognizeMissingTitle() {
        Executable executable = () -> conference.register(null, VALID_CONTENT, SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid title received.");
    }

    @Test
    void shouldRecognizeMissingContent() {
        Executable executable = () -> conference.register(VALID_TITLE, null, SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid content received.");
    }

    @Test
    void shouldRegisterPresentation() {
        given(presentationDtoFactory.create(VALID_TITLE, VALID_CONTENT, SLOT_NUMBER)).willReturn(PRESENTATION_DTO);
        Presentation presentation = mock(Presentation.class);
        given(presentationFactory.create(PRESENTATION_DTO)).willReturn(presentation);

        Presentation actual = conference.register(VALID_TITLE, VALID_CONTENT, SLOT_NUMBER);

        assertThat(actual).isEqualTo(presentation);
    }

    @Test
    void shouldRecognizeNotAvailableSlot() {
        given(presentationDtoFactory.create(VALID_TITLE, VALID_CONTENT, SLOT_NUMBER)).willReturn(PRESENTATION_DTO);
        Presentation presentation = mock(Presentation.class);
        given(presentation.scheduledForSlot(SLOT_NUMBER)).willReturn(true);
        given(presentationFactory.create(PRESENTATION_DTO)).willReturn(presentation);
        conference.register(VALID_TITLE, VALID_CONTENT, SLOT_NUMBER);

        Executable executable = () -> conference.register(VALID_TITLE, VALID_CONTENT, SLOT_NUMBER);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Unavailable slot.");
    }
}