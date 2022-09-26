package com.smalaca.conference;

import java.util.UUID;

class PresentationFactory {
    Presentation create(PresentationDto dto) {
        return new Presentation(presentationId(), dto.title(), dto.content(), dto.slotNumber());
    }

    private UUID presentationId() {
        return UUID.randomUUID();
    }
}
