package bad;

public class SomeOtherBadInvoicePrinter implements BadInvoicePrinter_IB {

	@Override
	public void someOtherPrintMethod(Invoice invoice) {
		System.out.println("Printing the invoice in a totally different way "
				+ invoice);
	}
}