package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name="employers")
public class Employer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="name")
    private String name;
    @Column(name="salary")
    private Double salary;

    @OneToOne
    @JoinColumn(name = "department")
    private Department department;

    @OneToOne
    @JoinColumn(name = "position")
    private Position position;

    public Employer(String name, Double salary,String departmentName, String positionName) {
        this.name = name;
        this.salary = salary;
        this.department=new Department(departmentName);
        this.position=new Position(positionName);
    }
    public Employer(){}

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return id == employer.id && Objects.equals(name, employer.name) && Objects.equals(salary, employer.salary) && Objects.equals(department, employer.department) && Objects.equals(position, employer.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, salary, department, position);
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Employer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", department=" + department +
                ", position=" + position +
                '}';
    }
}
