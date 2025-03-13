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
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/home.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/users.css">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container">
                <a class="navbar-brand" href="view/Owner.jsp">🏠 Home</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="user?UserId=${userAccount.userId}&action=view">
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

        <!-- Main Content (wrap all content except footer) -->
        <div class="main-content">
            <!-- Profile Container -->
            <div class="profile-container">
                <h2 class="profile-header"><i class="bi bi-person-circle me-2"></i>Edit Your Information</h2>

                <form action="user" method="post">
                    <input type="hidden" name="UserId" value="${getUser.userId}">

                    <div class="info-item">
                        <i class="bi bi-envelope"></i>
                        <span class="info-label">Email:</span>
                        <input type="email" name="Email" class="form-control" value="${getUser.email}" readonly>
                    </div>

                    <div class="info-item">
                        <i class="bi bi-person"></i>
                        <span class="info-label">Full Name:</span>
                        <input type="text" name="Fullname" class="form-control" value="${getUser.fullname}" required>
                    </div>

                    <div class="info-item">
                        <i class="bi bi-lock"></i>
                        <span class="info-label">Password:</span>
                        <input type="text" name="Password" class="form-control" value="${getUser.password}" required>
                    </div>

                    <div class="info-item">
                        <i class="bi bi-telephone"></i>
                        <span class="info-label">Phone:</span>
                        <input type="tel" name="Phone" class="form-control" value="${getUser.phone}" required>
                    </div>

                    <div class="info-item">
                        <i class="bi bi-geo-alt"></i>
                        <span class="info-label">Address:</span>
                        <input type="text" name="Address" class="form-control" value="${getUser.address}" required>
                    </div>
                </form>
            </div>

            <!-- Edit Button Container -->
            <div class="btn-edit-container">
                <form action="user" method="post">
                    <input type="hidden" name="UserId" value="${getUser.userId}">
                    <button type="submit" class="btn btn-edit text-white"><i class="bi bi-save me-2"></i>Save Changes</button>
                </form>
            </div>
        </div>

        <!-- Footer -->
        <div class="footer">
            <p>&copy; PRJ302. All rights reserved.</p>
        </div>
    </body>
</html>

