@import gg.jte.Content
@param Content content
@param Content footer

@template.layout.page(
    content = @`
        <div>
            <h1>Greetings!</h1>
        </div>
    `,
    footer = @`
        <p>Thanks for visiting, come again soon!</p>
    `
)