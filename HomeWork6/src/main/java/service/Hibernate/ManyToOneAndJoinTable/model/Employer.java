package service.Hibernate.ManyToOneAndJoinTable.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Employer implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @ManyToMany
    @JoinTable (name="employers_department",
            joinColumns=@JoinColumn (name="employer_id"),
            inverseJoinColumns=@JoinColumn(name="department_id"))
    private List<Department> department;


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

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department=" + department +
                '}';
    }
}
