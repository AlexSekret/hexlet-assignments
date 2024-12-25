@import exercise.util.NamedRoutes
@import exercise.dto.posts.PostPage
@param PostPage page
@template.layout.page(
    content = @`
        <table class="table table-bordered">
          <thead>
            <tr>
              <th>#</th>
              <th>Name</th>
              <th>Content</th>
            </tr>
          </thead>
          <tbody>
            <tr>
              <th scope="row">${page.getPost().getId()}</th>
              <td>${page.getPost().getName()}</td>
              <td>${page.getPost().getBody()}</td>
            </tr>
          </tbody>
        </table>
    `
)