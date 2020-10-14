package bad;

public class BadPrintingService {

	private InvoicePrinter invoicePrinter;

	public BadPrintingService() {
		this.invoicePrinter = new GoodInvoicePrinter();
	}

	public BadPrintingService(InvoicePrinter printer) {
		this.invoicePrinter = printer;
	}

	public void print(Invoice invoice) {
		invoicePrinter.print(invoice);
	}

}
