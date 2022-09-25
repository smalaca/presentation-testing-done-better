package com.smalaca.conference;

class ContentValidator {
    boolean isValid(String content) {
        return content != null && !content.isBlank();
    }
}
