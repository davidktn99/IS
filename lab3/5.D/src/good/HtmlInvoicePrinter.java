package good;

public class HtmlInvoicePrinter implements InvoicePrinter{

    public void print(Invoice invoice) {
        System.out.println("Printing html invoice " + invoice);
    }
}
