package com.smalaca.conference;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class PresentationDtoFactoryTest {
    private static final String TITLE = "Testing better";
    private static final String CONTENT = "Learn how to write better tests.";
    private static final int SLOT_NUMBER = 13;

    private final TitleValidator titleValidator = mock(TitleValidator.class);
    private final ContentValidator contentValidator = mock(ContentValidator.class);
    private final SlotNumberValidator slotNumberValidator = mock(SlotNumberValidator.class);
    private final PresentationDtoFactory factory = new PresentationDtoFactory(titleValidator, contentValidator, slotNumberValidator);

    @Test
    void shouldCreatePresentationDto() {
        PresentationDto actual = factory.create(TITLE, CONTENT, SLOT_NUMBER);

        assertThat(actual.title()).isEqualTo(TITLE);
        assertThat(actual.content()).isEqualTo(CONTENT);
        assertThat(actual.slotNumber()).isEqualTo(SLOT_NUMBER);
    }
}