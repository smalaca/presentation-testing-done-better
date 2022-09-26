package com.smalaca.conference;

class TitleValidator {
    void validate(String title) {
        if (title.isBlank()) {
            throw new IllegalArgumentException("Invalid title.");
        }

        if (title.length() < 8) {
            throw new IllegalArgumentException("Invalid title.");
        }

        if (title.length() > 40) {
            throw new IllegalArgumentException("Invalid title.");
        }
    }
}
