<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Page</title>
    </head>
    <body>
        <form action="update" method="POST">
            Enter BookID: <input type="number" placeholder="Enter BookID" name="bookid" required><br/>
            New Book Name: <input type="text" placeholder="Enter new Book Name" name="newBookName"><br/>
            New Author Name: <input type="text" placeholder="Enter new Author Name" name="newAuthorName"><br/>
            New Category ID: <input type="number" placeholder="Enter new Category ID" name="newCategoryID"><br/>
            <input type="submit" value="Update">
        </form>
    </body>
</html>
