import good.*;

import java.util.Date;

public class IMain {

    public static void main(String[] args) {
        testGoodI();
    }

    private static void testGoodI() {
        Invoice invoice = new Invoice(-132523);
        ComplexInvoice complexInvoice = new ComplexInvoice(21439, new Date());

        GoodInvoicePrinter_IA badInvoicePrinter = new GoodInvoicePrinter();
        badInvoicePrinter.print(invoice);
        badInvoicePrinter.printComplexInvoice(complexInvoice);

        GoodInvoicePrinter_IB someOtherBadInvoicePrinter = new SomeOtherGoodInvoicePrinter();
        someOtherBadInvoicePrinter.someOtherPrintMethod(invoice);
    }
}
