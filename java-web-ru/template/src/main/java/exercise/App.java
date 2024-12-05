package exercise;

import io.javalin.Javalin;

import java.util.List;

import io.javalin.http.HttpResponseException;
import io.javalin.http.NotFoundResponse;
import exercise.model.User;
import exercise.dto.users.UserPage;
import exercise.dto.users.UsersPage;

import static io.javalin.rendering.template.TemplateUtil.model;

import io.javalin.rendering.template.JavalinJte;
import io.javalin.validation.Validator;

public final class App {

    // Каждый пользователь представлен объектом класса User
    private static final List<User> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
            config.fileRenderer(new JavalinJte());
        });

        // BEGIN
        app.get("/users", ctx -> {
            UsersPage users = new UsersPage(USERS);
            if (users.getUsers().isEmpty()) {
                throw new NotFoundResponse("Users not found");
            }
            ctx.render("users/index.jte", model("users", users));
        });
        app.get("/users/{id}", ctx -> {
            long id = ctx.pathParamAsClass("id", Long.class).get();
            User concreteUser = USERS.stream()
                    .filter(e -> e.getId() == id)
                    .toList().getFirst();
            if (concreteUser == null || concreteUser.getId() >= USERS.size() || id < 0) {
                throw new NotFoundResponse("User not found");
            }
            UserPage user = new UserPage(concreteUser);
            ctx.render("users/show.jte", model("user", user));
        });
        // END

        app.get("/", ctx -> {
            ctx.render("index.jte");
        });

        return app;
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
