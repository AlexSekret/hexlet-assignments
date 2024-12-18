@template.layout.page(
    content = @`
        <form action="/users" method="post">
          <div class="mb-3">
            <label for="firstName" class="form-label">Имя</label>
            <input type="text" class="form-control" id="firstName" name="firstName"aria-describedby="emailHelp">
          </div>
          <div class="mb-3">
            <label for="lastName" class="form-label">Фамилия</label>
            <input type="text" class="form-control" name="lastName" id="lastName" aria-describedby="emailHelp">
          </div>
          <div class="mb-3">
            <label for="email" class="form-label">Почтовый ящик</label>
            <input type="email" class="form-control" name="email" id="email" aria-describedby="emailHelp">
          </div>
          <div class="mb-3">
            <label for="password" class="form-label">Пароль</label>
            <input type="password" class="form-control" name="password" id="password">
          </div>
          <div class="mb-3">
            <label for="passwordConfirmation" class="form-label">Подтверждение пароля</label>
            <input type="password" class="form-control" name="passwordConfirmation" id="passwordConfirmation">
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    `
)