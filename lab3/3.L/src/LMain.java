import bad.BadGraphicsService;
import bad.BadRectangle;
import bad.BadSquare;
import good.GoodGraphicsService;
import good.GoodRectangle;
import good.GoodSquare;

public class LMain {
    public static void main(String[] args) {
        testBadL();
        System.out.println("------------------------------\nNow testing the correct version\n");
        testGoodL();
    }

    private static void testBadL() {
        BadRectangle actuallySquare = new BadSquare(20, 30);
        BadGraphicsService badGraphicsService = new BadGraphicsService();

        badGraphicsService.checkForArea(actuallySquare);
    }

    private static void testGoodL() {
        GoodRectangle actuallySquare = new GoodSquare(20, 30);
        GoodGraphicsService goodGraphicsService = new GoodGraphicsService();

        goodGraphicsService.checkForArea(actuallySquare);
    }

}
