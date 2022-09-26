package com.smalaca.conference;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SlotNumberValidatorTest {
    private final SlotNumberValidator validator = new SlotNumberValidator();

    @ParameterizedTest
    @ValueSource(ints = {-13, -1, 0})
    void shouldRecognizeInvalidSlotNumber(int slotNumber) {
        Executable executable = () -> validator.validate(slotNumber);

        IllegalArgumentException actual = assertThrows(IllegalArgumentException.class, executable);
        assertThat(actual).hasMessage("Invalid slot number.");
    }

    @Test
    void shouldRecognizeValidSlotNumber() {
        assertDoesNotThrow(() -> validator.validate(13));
    }

}