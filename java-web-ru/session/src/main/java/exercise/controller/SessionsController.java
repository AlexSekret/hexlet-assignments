package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.MainPage;
import exercise.dto.LoginPage;
import exercise.model.User;
import exercise.repository.UsersRepository;

import static exercise.util.Security.encrypt;

import exercise.util.NamedRoutes;
import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

public class SessionsController {

    // BEGIN
    public static void build(Context ctx) {
        LoginPage page = new LoginPage(ctx.formParam("name"), null);
        ctx.render("build.jte", model("page", page));
    }

    public static void delete(Context ctx) {
        ctx.sessionAttribute("currentUser", null);
        ctx.redirect(NamedRoutes.rootPath());
    }

    public static void login(Context ctx) {
        String encryptedUserInput = encrypt(ctx.formParam("password"));
        String userName = ctx.formParam("name");
        User user = UsersRepository.findByName(userName)
                .orElseThrow(() -> new NotFoundResponse("Wrong username or password"));
        if (!userName.equals(user.getName()) || !encryptedUserInput.equals(user.getPassword())) {
            LoginPage page = new LoginPage(userName, "Wrong username or password");
            ctx.render("build.jte", model("page", page));
        } else {
            ctx.sessionAttribute("currentUser", userName);
            ctx.redirect(NamedRoutes.rootPath());
        }
    }

    public static void index(Context ctx) {
        MainPage page = new MainPage(ctx.sessionAttribute("currentUser"));
        ctx.render("index.jte", model("page", page));
    }
    // END
}
