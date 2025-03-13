<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Personal Information</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/users.css">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="view/Owner.jsp">
                    <i class="bi bi-house"></i> Home
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav w-100 d-flex justify-content-between">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user?UserId=${userAccount.userId}&action=view">
                                <i class="bi bi-person-fill "></i> Personal Information
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/vehicles?id=${userAccount.userId}&action=view">
                                <i class="bi bi-car-front-fill"></i> Your Vehicles
                            </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?&action=viewInspectionDate">
                                <i class="bi bi-clipboard-check"></i> Inspection
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
        <div class="container mt-4 main-content">
            <h2 class="text-center mb-4"><i class="bi bi-person-circle"></i> Your Information</h2>

            <div class="info-card">
                <div class="info-row">
                    <div class="info-label"><strong>Email:</strong></div>
                    <div class="info-value">${userAccount.email}</div>
                </div>
                <hr class="info-separator"> <!-- Vạch kẻ phân cách -->

                <div class="info-row">
                    <div class="info-label"><strong>Full Name:</strong></div>
                    <div class="info-value">${userAccount.fullname}</div>
                </div>
                <hr class="info-separator"> 

                <div class="info-row">
                    <div class="info-label"><strong>Password:</strong></div>
                    <div class="info-value">********</div> 
                </div>
                <hr class="info-separator"> 

                <div class="info-row">
                    <div class="info-label"><strong>Phone:</strong></div>
                    <div class="info-value">${userAccount.phone}</div>
                </div>
                <hr class="info-separator"> 

                <div class="info-row">
                    <div class="info-label"><strong>Address:</strong></div>
                    <div class="info-value">${userAccount.address}</div>
                </div>
            </div>

            <div class="text-center mt-4"> 
                <form action="${pageContext.request.contextPath}/user" method="get" style="display: inline;">
                    <input type="hidden" name="action" value="edit">
                    <input type="hidden" name="UserId" value="${userAccount.userId}">
                    <button type="submit" class="btn-custom">Edit Profile</button>
                </form>
            </div>
        </div>
        <%@ include file="footer.jsp" %>
    </body>
</html>