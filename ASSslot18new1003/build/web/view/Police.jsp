<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Police Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/police.css">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="Police.jsp">
                    <i class="bi bi-house"></i> Home
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/vehicles?action=searchVehicle">
                                <i class="bi bi-list-check"></i> Vehicles List
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
            <!-- Police Hero Section -->
            <div class="police-hero">
                <div class="hero-content">
                    <div class="hero-text">
                        <h1>Welcome Officer ${userAccount.fullname}</h1>
                        <p>Access the vehicle list or manage inspections from the menu above.</p>
                    </div>
                    <a href="${pageContext.request.contextPath}/vehicles?action=searchVehicle" class="btn-custom">View Vehicles</a>
                </div>
            </div>
<<<<<<< HEAD

            <!-- Optional: Add a section for vehicle list or notifications if needed -->
            <!-- For now, we'll leave it empty or add a placeholder -->
            <div class="container mt-5">
                <!-- You can add a table or notification card here later if needed -->
            </div>
=======
>>>>>>> 9b0e173 (Cập nhật code)
        </div>
                
        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>


