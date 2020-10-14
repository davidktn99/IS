package good;

public class EmployeeSaver {

    private GoodEmployee employee;

    public void setEmployee(GoodEmployee employee) {
        this.employee = employee;
    }

    public EmployeeSaver(GoodEmployee employee) {
        this.employee = employee;
    }

    public void save() {

        System.out.printf("%s saved to database.\n", this.employee.getName());
    }


}
