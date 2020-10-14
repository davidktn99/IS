import bad.BadEmployee;
import bad.EmployeeSaver;
import bad.PaymentCalculator;
import bad.ReportGenerator;

public class SMain {

    public static void main(String[] args) {
        testBadS();
    }

    private static void testBadS() {
        BadEmployee employee = new BadEmployee("A", "Bob", 7);
        PaymentCalculator pc = new PaymentCalculator(employee);
        ReportGenerator rg = new ReportGenerator(employee);
        EmployeeSaver es = new EmployeeSaver(employee);

        System.out.println("Payment to employee: " + pc.calculatePay() + "\n");
        System.out.println(rg.generateReport());
        es.save();
    }
}
