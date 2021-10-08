package model.dto;

public class EmployerSearchFilter extends PageableFilter {
    private String name;
    private EPredicateOperator predicateOperator;
    private String salary;
    private ESalaryOperator salaryOperator;

    public EmployerSearchFilter(String name, EPredicateOperator predicateOperator,
                                String salary, ESalaryOperator salaryOperator,int page,int size,
                                ESortDirection sortDirection) {
        super(page,size,sortDirection);
        this.name = name;
        this.predicateOperator = predicateOperator;
        this.salary = salary;
        this.salaryOperator = salaryOperator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EPredicateOperator getPredicateOperator() {
        return predicateOperator;
    }

    public void setPredicateOperator(EPredicateOperator predicateOperator) {
        this.predicateOperator = predicateOperator;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public ESalaryOperator getSalaryOperator() {
        return salaryOperator;
    }

    public void setSalaryOperator(ESalaryOperator salaryOperator) {
        this.salaryOperator = salaryOperator;
    }
}
