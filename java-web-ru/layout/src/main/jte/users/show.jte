@import exercise.dto.users.UserPage
@param UserPage page

@template.layout.page(
    content = @`
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
                  <th scope="row">${page.getUser().getId()}</th>
                  <td>${page.getUser().getFirstName()}</td>
                  <td>${page.getUser().getLastName()}</td>
                  <td>${page.getUser().getEmail()}</td>
                </tr>
              </tbody>
            </table>
  `,
    footer = @`
      <a href="/">Main Page</a>
      <a href="/users">List of users</a>
  `
)