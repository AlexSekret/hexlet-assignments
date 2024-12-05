@import exercise.dto.users.UserPage
@param UserPage user
<html lang="en">
    <head>
        <title>Hexlet users</title>
    </head>
  <body>
    <div>
      <main>
            <table class="table table-bordered">
              <thead>
                <tr>
                  <th>#</th>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Email</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th scope="row">${user.getUser().getId()}</th>
                  <td>${user.getUser().getFirstName()}</td>
                  <td>${user.getUser().getLastName()}</td>
                  <td>${user.getUser().getEmail()}</td>
                </tr>
              </tbody>
            </table>
      </main>
    </div>
  </body>
</html>