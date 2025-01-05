<!-- BEGIN -->
@import gg.jte.Content
@import exercise.dto.BasePage
@param Content content
@param BasePage page = null

<html lang="en">
    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <title>Hexlet Javalin Flash</title>
    </head>
    <body>
    @if(page != null && page.getFlash() != null)
        <div class="alert alert-success" role="alert">
            ${page.getFlash()}
        </div>
    @endif
    ${content}
    </body>
</html>
<!-- END -->
