<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Inspector Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/inspector.css">
        
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="Inspector.jsp">
                    <i class="bi bi-house"></i> Home
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?action=measureList">
                                <i class="bi bi-list-check"></i> Vehicle Emission Measurement List
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout">
                                <i class="bi bi-box-arrow-right"></i> Logout
                            </a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <!-- Main Content -->
        <div class="main-content">
            <!-- Inspector Hero Section -->
            <div class="inspector-hero">
                <div class="hero-content">
                    <div class="hero-text">
                        <h1>Welcome Inspector ${userAccount.fullname}</h1>
                        <p>Select an option from the menu above to continue.</p>
                    </div>
                    <a href="#notifications" class="btn-custom">View Notifications</a>
                </div>
            </div>

            <!-- Notification Section -->
            <div class="container mt-5">
                <div class="notification-card">
                    <h2 class="notification-title text-center">
                        <i class="bi bi-bell-fill"></i> Notifications
                    </h2>
                    <c:set var="notification" value="${sessionScope.notification}" />
                    <c:choose>
                        <c:when test="${empty notification}">
                            <p class="text-center">No notifications available.</p>
                        </c:when>
                        <c:otherwise>
                            <ul class="notification-list">
                                <c:forEach items="${notification}" var="no">
                                    <li class="notification-item">
                                        <i class="bi bi-calendar-fill"></i>
                                        <div>
                                            <strong>Date: ${no.sentDate}</strong>
                                            <p>${no.message}</p>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>

        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>


