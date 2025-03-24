<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Station Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/station.css">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="Station.jsp">
                    <i class="bi bi-house"></i> Home
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.getUserId()}&action=viewRegisList">
                                <i class="bi bi-list-check"></i> Inspection Registration List
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.getUserId()}&action=viewCheckList">
                                <i class="bi bi-clipboard-check"></i> CheckList
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
            <div class="station-hero">
                <div class="station-content">
                    <h1 class="station-title">Welcome to Station, Mr. ${userAccount.getFullname()}</h1>
                    <p class="station-subtitle">Select an option to manage your station activities.</p>
                    <div class="station-buttons">
                        <a href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.getUserId()}&action=viewRegisList" class="station-button">
                            <i class="bi bi-list-check"></i> Inspection Registration List
                        </a>
                        <a href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.getUserId()}&action=viewCheckList" class="station-button">
                            <i class="bi bi-clipboard-check"></i> CheckList
                        </a>
                        <a href="${pageContext.request.contextPath}/Logout" class="station-button">
                            <i class="bi bi-box-arrow-right"></i> Logout
                        </a>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>
