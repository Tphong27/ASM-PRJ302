<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inspector Screen</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="Inspector.jsp">Menu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav w-100 justify-content-around">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?action=measureList">Vehicle Emission Measurement List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-3">
            <h1 class="text-center">Welcome Inspector ${userAccount.fullname}</h1>
            <p class="text-center">Select an option from the menu above to continue.</p>
            <c:set var="notification" value="${sessionScope.notification}" />

            <div class="container mt-3">
                <h2>Notifications</h2>

                <c:choose>
                    <c:when test="${empty notification}">
                        <p>No notifications available.</p>
                    </c:when>
                    <c:otherwise>
                        <ul>
                            <c:forEach items="${notification}" var="no">
                                <div>-----------------------------------------</div>
                                <li><b>Date: ${no.sentDate}:</b></li>
                                <h7>${no.message}</h7>
                            </c:forEach>
                        </ul>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </body>
</html>


