package com.example.demo.model.api;

public enum EGoal {
    UP_0_25(1.1),
    UP_0_50(1.2),
    SUPPORT(1),
    DOWN_0_25(0.9),
    DOWN_0_50(0.8);

    private final double i;

    public double getI() {
        return i;
    }

    EGoal(double i) {
        this.i = i;
    }

    public static EGoal valueOfIgnoreCase(String name) {
        for (EGoal value : values()) {
            if (value.name().equalsIgnoreCase(name)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Неизвесный тип хранилища");
    }
}
