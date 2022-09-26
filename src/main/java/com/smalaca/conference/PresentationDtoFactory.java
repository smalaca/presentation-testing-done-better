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
        titleValidator.validate(title);
        contentValidator.validate(content);
        slotNumberValidator.validate(slotNumber);

        return new PresentationDto(title, content, slotNumber);
    }
}
