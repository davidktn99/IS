import bad.AnotherBadClient;
import bad.BadClient;
import bad.BadServer;

public class OMain {

    public static void main(String[] args) {
        testBadO();
    }

    private static void testBadO() {
        BadClient client = new BadClient();
        BadServer server = new BadServer();
        server.reactToClient(client);

        AnotherBadClient anotherBadClient = new AnotherBadClient();
        server.reactToClient(anotherBadClient);


    }
}
