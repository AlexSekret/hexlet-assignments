package exercise.dto.users;

import exercise.model.User;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

// BEGIN
@AllArgsConstructor
@Getter
public final class UsersPage {
    List<User> users;
}
// END
