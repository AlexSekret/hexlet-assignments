package exercise;

import io.javalin.Javalin;

import java.util.List;

public final class App {

    public static Javalin getApp() {

        // BEGIN
        List<String> phones = Data.getPhones();
        List<String> domains = Data.getDomains();
        var app = Javalin.create(config -> {
            // Включаем логгирование при разработке
            config.bundledPlugins.enableDevLogging();
        });
        // Описываем, что будет происходить при GET запросе на адрес /
        app.get("/phones", ctx -> ctx.json(phones));
        app.get("/domains", ctx -> ctx.json(domains));
        // Возвращаем настроенное приложение
        return app;
        // END
    }

    public static void main(String[] args) {
        Javalin app = getApp();
        app.start(7070);
    }
}
