@import exercise.dto.MainPage
@import exercise.util.NamedRoutes
@param MainPage page

@template.layout.page(
    content = @`
      @if(page.getName() != null)
      <div>
        Добро пожаловать, ${page.getName().toString()}.
      </div>
      <form action="${NamedRoutes.logoutPath()}" method="post">
        <input type="submit" class="btn btn-primary" value="Выйти" />
      </form>
      @else
        <a href="${NamedRoutes.buildSessionPath()}">Войти</a>
      @endif
    `
)