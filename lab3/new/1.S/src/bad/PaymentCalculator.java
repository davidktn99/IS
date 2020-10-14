package bad;

import bad.BadEmployee;

public class PaymentCalculator {

    private BadEmployee employee;

    public PaymentCalculator(BadEmployee employee) {
        this.employee = employee;
    }

    public void setEmployee(BadEmployee employee) {
        this.employee = employee;
    }

    public int calculatePay() {
        switch (employee.getStatus()) {
            case "A":
                return 1;
            case "B":
                return 2;
            default:
                return 0;
        }
    }
}
