package good;

public class PaymentCalculator {

    private GoodEmployee employee;

    public PaymentCalculator(GoodEmployee employee) {
        this.employee = employee;
    }

    public void setEmployee(GoodEmployee employee) {
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
