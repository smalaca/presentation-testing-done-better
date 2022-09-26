package com.smalaca.conference;

class SlotNumberValidator {
    void validate(int slotNumber) {
        if (slotNumber < 1) {
            throw new IllegalArgumentException("Invalid slot number.");
        }
    }
}
