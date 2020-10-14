import bad.BadEmployee;
import good.EmployeeSaver;
import good.GoodEmployee;
import good.PaymentCalculator;
import good.ReportGenerator;

public class SMain {

    public static void main(String[] args) {
        testBadS();
        System.out.println("------------------------------\nNow testing the correct version");
        testGoodS();
    }

    private static void testBadS() {
        BadEmployee employee = new BadEmployee("1", "BadEmployee", 5);
        employee.calculatePay();
        employee.reportHours();
        employee.save();
    }

    private static void testGoodS() {
        GoodEmployee employee = new GoodEmployee("A", "Bob", 7);
        PaymentCalculator pc = new PaymentCalculator(employee);
        ReportGenerator rg = new ReportGenerator(employee);
        EmployeeSaver es = new EmployeeSaver(employee);

        System.out.println("\nPayment to employee: " + pc.calculatePay());
        System.out.println(rg.generateReport());
        es.save();
    }
}
