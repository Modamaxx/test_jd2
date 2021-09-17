package model;

public class Position {
    private int id;
    private String name;
    public Position(String name) {
        this.name = name;
    }
    public Position(int id,String name) {
        this.name = name;
        this.id=id;
    }
    public Position(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
