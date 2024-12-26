package exercise.dto.posts;

import java.util.List;
import java.util.Map;

import io.javalin.validation.ValidationError;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// BEGIN
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EditPostPage {
    private String id;
    private String name;
    private String body;
    private Map<String, List<ValidationError<Object>>> errors;
}
// END
