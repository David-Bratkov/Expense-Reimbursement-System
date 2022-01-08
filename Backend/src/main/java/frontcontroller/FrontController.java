package frontcontroller;

import io.javalin.Javalin;

public class FrontController {

    public FrontController(Javalin app) {
        //middle ware goes here to make sure the user is registered and such things
        new Dispatcher(app);//gets the javalin object and sends it directly to dispatcher
    }

}
