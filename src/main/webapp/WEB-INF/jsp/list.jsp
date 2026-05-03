<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Library Management</title>
    <link rel="stylesheet" href="/css/style.css">
    <link href="https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap" rel="stylesheet">
</head>
<body>
    <div class="container">
        <header>
            <h1>Library Management System</h1>
            <a href="/add-book" class="btn btn-primary">Add New Book</a>
        </header>

        <div class="search-container">
            <form action="/" method="get">
                <input type="text" name="keyword" value="${param.keyword}" placeholder="Search by title or author...">
                <button type="submit" class="btn btn-outline">Search</button>
                <c:if test="${not empty param.keyword}">
                    <a href="/" class="btn btn-outline">Clear</a>
                </c:if>
            </form>
        </div>

        <div class="card">
            <table>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Author</th>
                        <th>Publication Year</th>
                        <th>Actions</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="book" items="${books}">
                        <tr>
                            <td>${book.id}</td>
                            <td><strong>${book.title}</strong></td>
                            <td>${book.author.name}</td>
                            <td>${book.publicationYear}</td>
                            <td>
                                <div class="action-buttons">
                                    <a href="/edit-book/${book.id}" class="btn btn-sm btn-outline">Edit</a>
                                    <form action="/delete-book/${book.id}" method="post" style="display: inline;">
                                        <button type="submit" class="btn btn-sm btn-danger" onclick="return confirm('Are you sure you want to delete this book?');">Delete</button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <c:if test="${totalPages > 1}">
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="/?page=${currentPage - 1}${not empty param.keyword ? '&keyword='.concat(param.keyword) : ''}" class="btn btn-outline btn-sm">&laquo; Previous</a>
                </c:if>
                
                <span class="page-info">Page ${currentPage} of ${totalPages}</span>
                
                <c:if test="${currentPage < totalPages}">
                    <a href="/?page=${currentPage + 1}${not empty param.keyword ? '&keyword='.concat(param.keyword) : ''}" class="btn btn-outline btn-sm">Next &raquo;</a>
                </c:if>
            </div>
        </c:if>
    </div>
</body>
</html>
