package service.HibernateCommunications.ManyToOneAndOneToMany.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String name;
    @Column
    private String parent;
    @OneToMany (mappedBy="department", fetch=FetchType.EAGER)
    private Collection<Employer> employers;

    public Collection<Employer> getEmployers() {
        return employers;
    }

    public void setEmployers(Collection<Employer> employers) {
        this.employers = employers;
    }

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
