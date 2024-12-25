package exercise.controller;

import static io.javalin.rendering.template.TemplateUtil.model;

import exercise.dto.posts.PostsPage;
import exercise.dto.posts.PostPage;
import exercise.model.Post;
import exercise.repository.PostRepository;

import io.javalin.http.Context;
import io.javalin.http.NotFoundResponse;

import java.util.List;

public class PostsController {

    public static final int PAGE_SIZE = 5;

    // BEGIN
    public static void show(Context ctx) {
        var id = ctx.pathParamAsClass("id", Long.class).get();
        var post = PostRepository.find(id)
                .orElseThrow(() -> new NotFoundResponse("Entity with id = " + id + " not found"));
        PostPage page = new PostPage(post);
        ctx.render("posts/show.jte", model("page", page));
    }

    public static void index(Context ctx) {
        Integer maxPageCount = (PostRepository.getEntities().size() / PAGE_SIZE) + 1;
        Integer pageNumber = ctx.queryParamAsClass("page", Integer.class).getOrDefault(1);
        List<Post> posts = PostRepository.findAll(pageNumber, PAGE_SIZE);
        PostsPage page = new PostsPage(posts);
        ctx.render("posts/index.jte", model("page", page, "pageNumber", pageNumber, "maxPageCount", maxPageCount));
    }
    // END
}
