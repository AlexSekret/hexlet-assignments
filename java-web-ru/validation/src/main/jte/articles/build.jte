@import exercise.dto.articles.BuildArticlePage
@param BuildArticlePage page

@template.layout.page(
    content = @`
        @if(page.getErrors() != null)
            <ul>
                @for(var validator : page.getErrors().values())
                    @for(var error : validator)
                        <li>${error.getMessage()}</li>
                    @endfor
                @endfor
            </ul>
        @endif
        <form action="/articles" method="post">
          <div class="mb-3">
            <label for="title" class="form-label">Title</label>
            <input type="text" class="form-control" name="title" id="title" aria-describedby="Input the article title" value="${page.getTitle()}">
          </div>
          <div class="mb-3">
            <label for="content" class="form-label">Content</label>
            <textarea name="content">${page.getContent()}</textarea>
          </div>
          <button type="submit" class="btn btn-primary">Create fking article!</button>
        </form>
    `
)