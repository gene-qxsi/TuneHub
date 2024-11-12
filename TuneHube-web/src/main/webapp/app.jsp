<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Данные из базы данных</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            height: 100vh;
            overflow: hidden;
        }

        table {
            width: 50%;
            border-collapse: collapse;
            margin: 20px auto;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
<h2 style="text-align: center;">Данные из базы данных</h2>
<table>
    <thead>
    <tr>
        <th>Данные</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty sessionScope.users}">
            <c:forEach var="data" items="${sessionScope.users}">
                <tr>
                   <td>${data}</td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td>Нет данных для отображения</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>
</body>
</html>