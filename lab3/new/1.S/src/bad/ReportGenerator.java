package bad;

import bad.BadEmployee;

public class ReportGenerator {

    private BadEmployee employee;

    public ReportGenerator(BadEmployee employee) {

        this.employee = employee;
    }

    public void setEmployee(BadEmployee employee) {
        this.employee = employee;
    }

    public String generateReport() {
        return String.format("%s worked %d hours.\n", this.employee.getName(), this.employee.getHours());
    }
}
