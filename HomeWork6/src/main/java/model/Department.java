package model;

public class Department {
    private String name;
    private int parent;

    public Department(String name) {
        this.name = name;
    }
    public Department(){

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
