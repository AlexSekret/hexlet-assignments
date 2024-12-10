@import gg.jte.Content
@param Content content
@param Content footer

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <title>Hexlet Javalin Example</title>
        <a href="/">Main Page</a>
        <a href="/users">List of users</a>
    </head>
    <body>
        ${content}
    </body>
    <footer>
        ${footer}
    </footer>
</html>