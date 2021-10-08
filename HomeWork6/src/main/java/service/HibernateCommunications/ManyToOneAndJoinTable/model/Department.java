package service.HibernateCommunications.ManyToOneAndJoinTable.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String parent;
    @ManyToMany
    @JoinTable(name="employers_department",
            joinColumns=@JoinColumn(name="department_id"),
            inverseJoinColumns=@JoinColumn(name="employers_id"))
    private List<Employer> employers;

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent='" + parent + '\'' +
                '}';
    }

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
