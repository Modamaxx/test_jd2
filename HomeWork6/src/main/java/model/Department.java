package model;

public class Department {
    private int id;
    private String name;
    private String parent;

    public Department(String name){
        this.name=name;
    }

    public Department(int id,String name,String parent){
        this.id=id;
        this.name=name;
        this.parent=parent;
    }

    public Department(){

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

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }
}
