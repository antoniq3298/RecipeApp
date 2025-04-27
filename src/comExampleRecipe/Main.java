package comExampleRecipe;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;



public class Main {
    public static void main(String[] args) throws Exception {
        RecipeDao.initDatabase();

        Server server = new Server(8080);
        ServletContextHandler handler = new ServletContextHandler();
        handler.addServlet(RecipeServlet.class, "/");
        server.setHandler(handler);

        server.start();
        server.join();
    }
}

