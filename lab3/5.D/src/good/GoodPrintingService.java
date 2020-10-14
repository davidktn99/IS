package good;

public class GoodPrintingService {

	private InvoicePrinter invoicePrinter;

	public GoodPrintingService() {
		this.invoicePrinter = new GoodInvoicePrinter();
	}

	public GoodPrintingService(InvoicePrinter printer) {
		this.invoicePrinter = printer;
	}

	public void print(Invoice invoice) {
		invoicePrinter.print(invoice);
	}

}
