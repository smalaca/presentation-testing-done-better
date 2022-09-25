package com.smalaca.conference;

import java.util.ArrayList;
import java.util.List;

public class Conference {
    private final List<Presentation> presentations = new ArrayList<>();
    private final PresentationDtoFactory presentationDtoFactory;
    private final PresentationFactory presentationFactory;

    Conference(PresentationDtoFactory presentationDtoFactory, PresentationFactory presentationFactory) {
        this.presentationDtoFactory = presentationDtoFactory;
        this.presentationFactory = presentationFactory;
    }

    public Presentation register(String title, String content, int slotNumber) {
        if (title == null || content == null) {
            throw new IllegalArgumentException("Invalid input received.");
        }

        if (isAvailable(slotNumber)) {
            PresentationDto dto = presentationDtoFactory.create(title, content, slotNumber);
            Presentation presentation = presentationFactory.create(dto);
            presentations.add(presentation);

            return presentation;
        } else {
            throw new IllegalArgumentException("Unavailable slot.");
        }
    }

    private boolean isAvailable(int slotNumber) {
        return presentations
                .stream()
                .noneMatch(presentation -> presentation.scheduledForSlot(slotNumber));
    }
}
