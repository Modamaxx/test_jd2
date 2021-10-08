package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "departments")
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    private Integer parent;

    public Department(String name) {
        this.name = name;
    }

    public Department(String name, int parent) {
        this.name = name;
        this.parent = parent;
    }

    public Department(int id) {
        this.id = id;
    }

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
        this.parent = parent;
    }

    public Department() {

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

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }
}
