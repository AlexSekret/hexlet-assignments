@import exercise.dto.users.UsersPage
@param UsersPage page
@template.layout.page(
    content = @`
        <form action="/users" method="get">
          <input type="search" required name="term" placeholder="Input First Name" value="${page.getTerm()}">
          <input type="submit" value="Искать">
        </form>
        @for(var user : page.getUsers())
            <div>
            <a href="/users/${user.getId()}">${user.getFirstName()} ${user.getLastName()}</a>
            </div>
        @endfor
    `
)