package com.smalaca.conference;

class ContentValidator {
    void validate(String content) {
        if (content.isBlank()) {
            throw new IllegalArgumentException("Invalid content.");
        }
    }
}
