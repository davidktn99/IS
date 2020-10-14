package good;

public class ReportGenerator {

    private GoodEmployee employee;

    public ReportGenerator(GoodEmployee employee) {

        this.employee = employee;
    }

    public void setEmployee(GoodEmployee employee) {
        this.employee = employee;
    }

    public String generateReport() {
        return String.format("%s worked %d hours.", this.employee.getName(), this.employee.getHours());
    }
}
