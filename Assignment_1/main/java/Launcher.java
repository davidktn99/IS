import presentationLayer.controller.LoginController;


/**
 * Created by David Katona on 25/11/2020
 */
public class Launcher {

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
        new LoginController(componentFactory.getAuthenticationService(), componentFactory.getAccountService(), componentFactory.getClientService());
    }

}
