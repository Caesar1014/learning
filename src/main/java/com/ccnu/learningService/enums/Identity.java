package com.ccnu.learningService.enums;
public enum Identity {
    /**
     * 学生1，老师2
     */
    STUDENT(2),
    TEACHER(1);
    private final int value;

    Identity(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}