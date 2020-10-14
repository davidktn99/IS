package good;

public class GoodInvoicePrinter implements InvoicePrinter{

    public void print(Invoice invoice) {
        System.out.println("Printing invoice " + invoice);
    }

}
