package comExampleRecipe;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "RecipeServlet", value = "/")
public class RecipeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<Recipe> recipes = RecipeDao.getAllRecipes();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("<html><body>");
        out.println("<h1>Рецепти</h1>");

        for (Recipe recipe : recipes) {
            out.println("<div>");
            out.println("<h2>" + recipe.getName() + "</h2>");
            out.println("<p>" + recipe.getDescription() + "</p>");
            out.println("<form method='post' action=''>");
            out.println("<input type='hidden' name='deleteId' value='" + recipe.getId() + "'/>");
            out.println("<button type='submit'>Изтрий</button>");
            out.println("</form>");
            out.println("</div><hr>");
        }

        out.println("<h2>Добави нова рецепта</h2>");
        out.println("<form method='post' action=''>");
        out.println("Име: <input type='text' name='name'/><br/>");
        out.println("Описание: <textarea name='description'></textarea><br/>");
        out.println("<button type='submit'>Запази</button>");
        out.println("</form>");

        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (req.getParameter("deleteId") != null) {
            int id = Integer.parseInt(req.getParameter("deleteId"));
            RecipeDao.deleteRecipe(id);
        } else {
            String name = req.getParameter("name");
            String description = req.getParameter("description");
            if (name != null && description != null) {
                RecipeDao.addRecipe(name, description);
            }
        }
        resp.sendRedirect("/");
    }
}

