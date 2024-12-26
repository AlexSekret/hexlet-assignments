package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.dto.posts.BuildPostPage;
import exercise.dto.posts.EditPostPage;
import exercise.util.NamedRoutes;

import io.javalin.http.Context;
import io.javalin.validation.ValidationException;
import io.javalin.http.NotFoundResponse;

import java.util.Map;
import java.util.Optional;

public class PostsController {

    public static void build(Context ctx) {
        var page = new BuildPostPage();
        ctx.render("posts/build.jte", model("page", page));
    }

    public static void create(Context ctx) {
        try {
            var name = ctx.formParamAsClass("name", String.class)
                    .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                    .get();

            var body = ctx.formParamAsClass("body", String.class)
                    .check(value -> value.length() >= 10, "Пост должен быть не короче 10 символов")
                    .get();

            var post = new Post(name, body);
            PostRepository.save(post);
            ctx.redirect(NamedRoutes.postsPath());

        } catch (ValidationException e) {
            var name = ctx.formParam("name");
            var body = ctx.formParam("body");
            var page = new BuildPostPage(name, body, e.getErrors());
            ctx.render("posts/build.jte", model("page", page)).status(422);
        }
    }

    public static void index(Context ctx) {
        var posts = PostRepository.getEntities();
        var postPage = new PostsPage(posts);
        ctx.render("posts/index.jte", model("page", postPage));
    }

    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Post not found"));

        var page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    // BEGIN
    public static void edit(Context ctx) {
        Long id = ctx.pathParamAsClass("page-id", Long.class).get();
        Optional<Post> post = PostRepository.find(id);
        if (post.isPresent()) {
            EditPostPage page = new EditPostPage(String.valueOf(id), post.get().getName(), post.get().getBody(), Map.of());
            PostPage postPage = new PostPage(post.get());
            ctx.render("posts/edit.jte", model("page", page, "postPage", postPage));
        } else {
            ctx.result("Post not found").status(404);
        }
    }

    public static void update(Context ctx) {
        Long id = ctx.pathParamAsClass("page-id", Long.class).get();
        Optional<Post> post = PostRepository.find(id);
        try {
            if (post.isPresent()) {
                var name = ctx.formParamAsClass("name", String.class)
                        .check(value -> value.length() >= 2, "Название не должно быть короче двух символов")
                        .get();

                var body = ctx.formParamAsClass("body", String.class)
                        .check(value -> value.length() >= 10, "Пост должен быть не короче 10 символов")
                        .get();
                post.get().setName(name);
                post.get().setBody(body);
                ctx.redirect(NamedRoutes.postsPath());
            } else {
                ctx.result("Post not found").status(404);
            }
        } catch (ValidationException e) {
            if (post.isPresent()) {
                var postPage = new PostPage(post.get());
                String name = ctx.formParam("name");
                String body = ctx.formParam("body");
                EditPostPage page = new EditPostPage(String.valueOf(id), name, body, e.getErrors());
                ctx.render("posts/edit.jte", model("page", page, "postPage", postPage)).status(422);
            }
        }
    }
    // END
}
