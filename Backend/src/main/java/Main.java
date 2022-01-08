import frontcontroller.FrontController;
import io.javalin.Javalin;
import io.javalin.http.staticfiles.Location;


public class Main {

    public static void main(String[] args) {
        Javalin app = Javalin.create(javalinConfig -> {
            javalinConfig.addStaticFiles("/", Location.CLASSPATH);
        }).start(8000);
        new FrontController(app);

    }

}