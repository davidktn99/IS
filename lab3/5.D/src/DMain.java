import good.GoodPrintingService;
import good.HtmlInvoicePrinter;
import good.Invoice;

public class DMain {

    public static void main(String[] args) {
        testGoodD();
    }

    private static void testGoodD() {
        Invoice invoice = new Invoice(665);

        GoodPrintingService goodPrintingService = new GoodPrintingService();
        goodPrintingService.print(invoice);

        goodPrintingService = new GoodPrintingService(new HtmlInvoicePrinter());
        goodPrintingService.print(invoice);
    }
}
