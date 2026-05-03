<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit Book</title>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
        <header>
            <h1>Edit Book</h1>
            <a href="/" class="btn btn-outline">Back to List</a>
        </header>

        <c:if test="${not empty param.error}">
            <div class="alert alert-error">Error updating book. Please check your inputs.</div>
        </c:if>

        <div class="card form-container">
            <form action="/update-book/${book.id}" method="post">
                <div class="form-group">
                    <label for="title">Book Title</label>
                    <input type="text" id="title" name="title" value="${book.title}" required>
                </div>

                <div class="form-group">
                    <label for="publicationYear">Publication Year</label>
                    <input type="number" id="publicationYear" name="publicationYear" value="${book.publicationYear}">
                </div>

                <div class="form-group">
                    <label for="authorId">Author</label>
                    <select id="authorId" name="author.id" required>
                        <c:forEach var="author" items="${authors}">
                            <option value="${author.id}" ${author.id == book.author.id ? 'selected' : ''}>${author.name}</option>
                        </c:forEach>
                    </select>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Update Book</button>
                </div>
            </form>
        </div>
    </div>
</body>
</html>
