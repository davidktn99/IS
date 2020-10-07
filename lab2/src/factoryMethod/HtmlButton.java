package factoryMethod;

public class HtmlButton implements Button {

    public void render() {
        System.out.println("<button>Test factoryMethod.Button</button>");
        onClick();
    }

    public void onClick() {
        System.out.println("Click! factoryMethod.Button says - 'Hello World!'");
    }
}