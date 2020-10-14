import bad.AnotherBadClient;
import bad.BadClient;
import bad.BadServer;
import good.AnotherGoodClient;
import good.GoodClient;
import good.GoodServer;

public class OMain {

    public static void main(String[] args) {
        testBadO();
        System.out.println("------------------------------\nNow testing the correct version\n");
        testGoodO();
    }

    private static void testBadO() {
        BadClient client = new BadClient();
        BadServer server = new BadServer();
        server.reactToClient(client);

        AnotherBadClient anotherBadClient = new AnotherBadClient();
        //server.reactToClient(anotherBadClient);
    }

    private static void testGoodO() {
        GoodClient client = new GoodClient();
        GoodServer server = new GoodServer();
        server.reactToClient(client);

        AnotherGoodClient anotherBadClient = new AnotherGoodClient();
        server.reactToClient(anotherBadClient);
    }
}
