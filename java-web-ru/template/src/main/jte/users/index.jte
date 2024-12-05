@import exercise.dto.users.UsersPage
@param UsersPage users

<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Hexlet users</title>
    </head>
  <body>
    <div>
      <main>
        <table>
            <caption>
                <thead>
                    <th>Пользователи</th>
                </thead>
            </caption>
            <tbody>
                    @for(var user : users.getUsers())
                    <tr>
                        <td>
                            <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
                        </td>
                    </tr>
                    @endfor
            </tbody>
        </table>
      </main>
    </div>
  </body>
</html>