package com.example.librarytest.support;

class Authority {
    private final int dayNumber;
    private final int bookNumber;
    private static final Authority NORMAL;
    private static final Authority LOWER;
    private static final Authority INFINITE;

    static {
        NORMAL = new Authority(30, 3);
        INFINITE = new Authority(Integer.MAX_VALUE, Integer.MAX_VALUE);
        LOWER = new Authority(15, 1);
    }

    private Authority(int dayNumber, int bookNumber) {
        this.dayNumber = dayNumber;
        this.bookNumber = bookNumber;
    }

    public static Authority getLOWER() {
        return LOWER;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public int getBookNumber() {
        return bookNumber;
    }

    public static Authority getNORMAL() {
        return NORMAL;
    }

    public static Authority getINFINITE() {
        return INFINITE;
    }
}
