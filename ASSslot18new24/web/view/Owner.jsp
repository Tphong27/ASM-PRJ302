<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Owner Screen</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="Owner.jsp">🏠 Home</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user?UserId=${userAccount.userId}&action=view">
                                <i class="bi bi-person-fill"></i> Personal Information
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/vehicles?id=${userAccount.userId}&action=view">🚗 Your Vehicles</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.userId}&action=viewInspectionDate">🛠️ Inspection</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link text-danger" href="${pageContext.request.contextPath}/Logout">🚪 Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Main Content -->
        <div class="container mt-4">
            <div class="row justify-content-center">
                <div class="col-lg-8">
                    <div class="welcome-box text-center">
                        <h1 class="mb-3">👋 Welcome, ${userAccount.fullname}!</h1>
                        <p class="text-muted">Select an option from the menu above to continue.</p>
                    </div>
                </div>
            </div>

            <!-- Notification Section -->
            <div class="row justify-content-center mt-4">
                <div class="col-lg-8">
                    <div class="notification-box">
                        <h2 class="mb-3">🔔 Notifications</h2>
                        <c:set var="notification" value="${sessionScope.notification}" />

                        <c:choose>
                            <c:when test="${empty notification}">
                                <p class="text-muted">No notifications available.</p>
                            </c:when>
                            <c:otherwise>
                                <ul class="list-group">
                                    <c:forEach items="${notification}" var="no">
                                        <li class="list-group-item border rounded shadow-sm mb-2">
                                            <strong>📅 Date: ${no.sentDate}</strong>
                                            <p class="mb-0">${no.message}</p>
                                        </li>
                                    </c:forEach>
                                </ul>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
        </div>
        <!-- Footer -->
        <div class="footer">
            <p>&copy; PRJ302. All rights reserved.</p>
        </div>
    </body>
</html>
