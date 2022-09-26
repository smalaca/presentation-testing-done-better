package com.smalaca.conference;

import java.util.UUID;

class Presentation {
    private final UUID presentationId;
    private final String title;
    private final String content;
    private final int slotNumber;

    Presentation(UUID presentationId, String title, String content, int slotNumber) {
        this.presentationId = presentationId;
        this.title = title;
        this.content = content;
        this.slotNumber = slotNumber;
    }

    boolean scheduledForSlot(int slotNumber) {
        return this.slotNumber == slotNumber;
    }
}
