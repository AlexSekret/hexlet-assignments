package exercise.dto.users;

import exercise.model.User;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

// BEGIN
@Getter
@AllArgsConstructor
public final class UsersPage {
    List<User> users;
    String term;
}
// END
