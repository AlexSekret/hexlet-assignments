package exercise;

import io.javalin.Javalin;
import io.javalin.http.NotFoundResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

// BEGIN

// END

public final class App {

    private static final List<Map<String, String>> COMPANIES = Data.getCompanies();

    public static Javalin getApp() {

        var app = Javalin.create(config -> {
            config.bundledPlugins.enableDevLogging();
        });

        // BEGIN
        app.get("/companies/{id}", ctx -> {
            String id = ctx.pathParamAsClass("id", String.class).get();
            var company = new HashMap<String, String>();
            for (Map<String, String> c : COMPANIES) {
                if (c.get("id").equals(id)) {
                    company.putAll(c);
                }
            }
            if (company.isEmpty()) {
                throw new NotFoundResponse("Company not found");
            }
            ctx.json(company);
        });
        // END

        app.get("/companies", ctx -> {
            ctx.json(COMPANIES);
        });

        app.get("/", ctx -> {
            ctx.result("open something like (you can change id): /companies/5");
        });

        return app;

    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
