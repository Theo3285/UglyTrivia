package com.kata.refactoring.uglytrivia;

enum Categories {
    ZERO (0, "Pop"),
    ONE (1, "Science"),
    TWO (2, "Sports"),
    THREE (3, "Rock"),
    FOUR (4, "Pop"),
    FIVE (5, "Science"),
    SIX (6, "Sports"),
    SEVEN (7, "Rock"),
    EIGHT (8, "Pop"),
    NINE (9, "Science"),
    TEN (10, "Sports"),
    ELEVEN (11, "Rock");

    private final int place;
    private final String category;

    Categories(int place, String category) {
        this.place = place;
        this.category = category;
    }

    public String category() {
        return category;
    }

    public static Categories get(int aPlace) throws IllegalArgumentException {
        for (Categories category : Categories.values()) {
            if (category.place == aPlace)
                return category;
        }
        throw new IllegalArgumentException("Unknown category at place " + aPlace);
    }
}
