package exercise;

import io.javalin.Javalin;
import io.javalin.validation.Validator;

import java.util.List;
import java.util.Map;

public final class App {

    private static final List<Map<String, String>> USERS = Data.getUsers();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/users", ctx -> {
            var query = ctx.queryParamMap();
            var users = USERS.subList(0, 5);
                int page = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
                int per = ctx.queryParamAsClass("per", Integer.class).getOrDefault(5);
                var begin = page * per - per;
                var end = page * per;
                users = USERS.subList(begin, end);
                ctx.json(users);
        });
        // END

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
