package exercise.controller;

import io.javalin.validation.ValidationException;
import org.apache.commons.lang3.StringUtils;
import exercise.util.Security;
import exercise.model.User;
import exercise.util.NamedRoutes;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.repository.UserRepository;
import exercise.dto.users.UserPage;
import io.javalin.http.NotFoundResponse;
import io.javalin.http.Context;


public class UsersController {

    public static void build(Context ctx) throws Exception {
        ctx.render("users/build.jte");
    }

    // BEGIN
    public static void create(Context ctx) {
        try {
            var firstName = ctx.formParamAsClass("firstName", String.class).get();
            var lastName = ctx.formParamAsClass("lastName", String.class).get();
            var email = ctx.formParamAsClass("email", String.class).get();
            var password = ctx.formParamAsClass("password", String.class).get();
            String securityToken = Security.generateToken();
            ctx.cookie("SecurityToken", securityToken);
            var user = new User(firstName, lastName, email, password, securityToken);
            UserRepository.save(user);
            ctx.redirect(NamedRoutes.userPath(user.getId()));
        } catch (ValidationException e) {
            ctx.result(e.getMessage()).status(422);
        }
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var user = UserRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));
        var securityToken = ctx.cookie("SecurityToken");
        var userToken = user.getToken();
        if (userToken.equals(securityToken)) {
            var page = new UserPage(user);
            ctx.render("users/show.jte", model("page", page));
        } else {
            ctx.redirect(NamedRoutes.buildUserPath());
        }
    }
    // END
}
