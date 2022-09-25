package com.smalaca.conference;

class PresentationDtoFactory {
    private final TitleValidator titleValidator;
    private final ContentValidator contentValidator;
    private final SlotNumberValidator slotNumberValidator;

    PresentationDtoFactory(
            TitleValidator titleValidator, ContentValidator contentValidator, SlotNumberValidator slotNumberValidator) {
        this.titleValidator = titleValidator;
        this.contentValidator = contentValidator;
        this.slotNumberValidator = slotNumberValidator;
    }

    PresentationDto create(String title, String content, int slotNumber) {
        if (!titleValidator.isValid(title)) {
            throw new IllegalArgumentException("Invalid title.");
        }

        if (!contentValidator.isValid(content)) {
            throw new IllegalArgumentException("Invalid content.");
        }

        if (!slotNumberValidator.isValid(slotNumber)) {
            throw new IllegalArgumentException("Invalid slot number.");
        }

        return new PresentationDto(title, content, slotNumber);
    }
}
