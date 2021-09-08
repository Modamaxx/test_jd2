package model;

public class Employer {
    private String name;
    private Double salary;
    private Department department;
    private Position position;

    public Employer(String name, Double salary,String departmentName, String positionName) {
        this.name = name;
        this.salary = salary;
        this.department=new Department(departmentName);
        this.position=new Position(positionName);
    }
    public Employer(){}


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
}
