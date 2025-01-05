@import exercise.dto.posts.PostsPage
@param PostsPage page

@template.layout.page(
page = page,
content = @`
    <div class="mx-auto p-4 py-md-5">
        <main>
            <h1>Все посты</h1>
            <table class="table table-striped">
                @for(var post : page.getPosts())
                    <tr>
                        <td>
                            ${post.getId()}
                        </td>
                        <td>
                            ${post.getName()}
                        </td>
                        <td>
                            ${post.getBody()}
                        </td>
                    </tr>
                @endfor
            </table>
        </main>
    </div>
`
)