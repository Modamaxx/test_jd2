package com.example.demo.model.api;

public enum ELifestyle {
    SEDENTARY(1.2),
    INACTIVE(1.375),
    MOBILE(1.55),
    SUPER_MOBILE(1.7);

    private final double v;

    ELifestyle(double v) {
        this.v=v;
    }

    public static ELifestyle valueOfIgnoreCase(String name){
        for (ELifestyle value : values()) {
            if(value.name().equalsIgnoreCase(name)){
                return value;
            }
        }
        throw new IllegalArgumentException("Неизвесный тип хранилища");
    }

    public double getV() {
        return v;
    }
}
