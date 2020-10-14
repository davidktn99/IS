package bad;

public class EmployeeSaver {

    private BadEmployee employee;

    public void setEmployee(BadEmployee employee) {
        this.employee = employee;
    }

    public EmployeeSaver(BadEmployee employee) {
        this.employee = employee;
    }

    public void save() {

        System.out.printf("%s saved to database.\n", this.employee.getName());
    }


}
