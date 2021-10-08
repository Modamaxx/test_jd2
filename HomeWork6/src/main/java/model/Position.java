package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="positions")
public class Position implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column (name="name")
    private String name;

    public Position(String name) {
        this.name = name;
    }
    public Position(int id) {
        this.id = id;
    }

    public Position(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public Position() {

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
