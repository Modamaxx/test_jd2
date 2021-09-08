package model;

public class Position {
    public Position(String name) {
        this.name = name;
    }
    public Position(){

    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
