import bad.BadGraphicsService;
import bad.BadRectangle;
import bad.BadSquare;

public class LMain {
    public static void main(String[] args) {
        testBadL();
    }

    private static void testBadL() {
        BadRectangle actuallySquare = new BadSquare(20, 30);
        BadGraphicsService badGraphicsService = new BadGraphicsService();

        badGraphicsService.checkForArea(actuallySquare);
    }

}
