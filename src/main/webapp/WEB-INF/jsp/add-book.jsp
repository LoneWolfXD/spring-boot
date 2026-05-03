<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Add Book</title>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
        <header>
            <h1>Add New Book</h1>
            <a href="/" class="btn btn-outline">Back to List</a>
        </header>

        <c:if test="${not empty param.error}">
            <div class="alert alert-error">Error saving book. Please check your inputs.</div>
        </c:if>

        <div class="card form-container">
            <form action="/add-book" method="post">
                <div class="form-group">
                    <label for="title">Book Title</label>
                    <input type="text" id="title" name="title" required placeholder="Enter book title">
                </div>

                <div class="form-group">
                    <label for="publicationYear">Publication Year</label>
                    <input type="number" id="publicationYear" name="publicationYear" placeholder="e.g. 2024">
                </div>

                <div class="form-group">
                    <label for="authorId">Select Existing Author</label>
                    <select id="authorId" name="author.id">
                        <option value="">-- Select Author --</option>
                        <c:forEach var="author" items="${authors}">
                            <option value="${author.id}">${author.name}</option>
                        </c:forEach>
                    </select>
                </div>
                
                <div class="form-divider">OR</div>

                <div class="form-group">
                    <label for="newAuthorName">Add New Author</label>
                    <input type="text" id="newAuthorName" name="newAuthorName" placeholder="New author's name">
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Save Book</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
