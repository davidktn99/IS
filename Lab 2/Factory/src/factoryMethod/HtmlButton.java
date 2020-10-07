package factoryMethod;

public class HtmlButton implements Button {

    public void render() {
        System.out.println("<button>Test factoryMethod.factory.Button</button>");
        onClick();
    }

    public void onClick() {
        System.out.println("Click! factoryMethod.factory.Button says - 'Hello World!'");
    }
}