package exercise;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import exercise.model.Post;

@SpringBootApplication
@RestController
public class Application {
    // Хранилище добавленных постов
    private List<Post> posts = Data.getPosts();

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // BEGIN
    @GetMapping("/posts")
    //список всех постов. Должен возвращаться статус 200 и заголовок X-Total-Count, в котором содержится количество постов
    public ResponseEntity<List<Post>> index(@RequestParam(defaultValue = "1") Integer page,
                                            @RequestParam(defaultValue = "10") Integer limit) {
        String postCount = String.valueOf(posts.size());
        return ResponseEntity.ok()
                .header("X-Total-Count", postCount)
                .body(posts);
    }

    @GetMapping("/posts/{id}")
    // просмотр конкретного поста. Если пост найден, должен возвращаться статус 200, если нет — статус 404
    public ResponseEntity<Optional<Post>> show(@PathVariable String id) {
        Optional<Post> foundPost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        return foundPost.isPresent() ? ResponseEntity.ok(foundPost) : ResponseEntity.notFound().build();

    }

    @PostMapping("/posts") // создание нового поста. Должен возвращаться статус 201
    public ResponseEntity<Post> create(@RequestBody Post post) {
        posts.add(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @PutMapping("/posts/{id}")
    // Обновление поста. Должен возвращаться статус 200. Если пост уже не существует, то должен возвращаться 204
    public ResponseEntity<Post> update(@PathVariable String id, @RequestBody Post data) {
        Optional<Post> maybePost = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
        ResponseEntity<Post> response;
        if (maybePost.isPresent()) {
            var post = maybePost.get();
            post.setTitle(data.getTitle());
            post.setId(data.getId());
            post.setBody(data.getBody());
            response = ResponseEntity.ok().body(post);
        } else {
            response = ResponseEntity.noContent().build();
        }
        return response;
    }
    // END

    @DeleteMapping("/posts/{id}")
    public void destroy(@PathVariable String id) {
        posts.removeIf(p -> p.getId().equals(id));
    }
}
