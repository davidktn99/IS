import bad.BadPrintingService;
import bad.HtmlInvoicePrinter;
import bad.Invoice;

public class DMain {

    public static void main(String[] args) {
        testBadD();
    }

    private static void testBadD() {
        Invoice invoice = new Invoice(665);

        BadPrintingService badPrintingService = new BadPrintingService();
        badPrintingService.print(invoice);

        badPrintingService = new BadPrintingService(new HtmlInvoicePrinter());
        badPrintingService.print(invoice);
    }
}
