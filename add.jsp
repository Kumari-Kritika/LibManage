
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Page</title>
    </head>
    <body>
        <form action="add" method="POST">
            BookID: <input type="number" placeholder="Enter BookID" name="bookid"><br/>
            BookName: <input type="varchar" placeholder="Enter BookName" name="bookname"><br/>
            AuthorName: <input type="varchar" placeholder="Enter AuthorName" name="authorname"><br/>
            CategoryID: <input type="number" placeholder="Enter CategoryID" name="categoryid"><br/>
            <input type="submit">
            
        </form>
    </body>
</html>
