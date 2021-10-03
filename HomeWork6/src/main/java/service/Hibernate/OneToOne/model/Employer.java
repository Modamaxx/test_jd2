package service.Hibernate.OneToOne.model;
import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Employer implements Serializable {
    @Id
    @GeneratedValue
    private int id;
    @Column
    private String name;
    @OneToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    private Department department;


    public Employer(String name,String departmentName) {
        this.name = name;
        this.department=new Department(departmentName);
    }
    public Employer(){}


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

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
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