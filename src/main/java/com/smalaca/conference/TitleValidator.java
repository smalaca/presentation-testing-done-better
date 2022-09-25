package com.smalaca.conference;

class TitleValidator {
    boolean isValid(String title) {
        return title != null && !title.isBlank() && title.length() > 8;
    }
}
