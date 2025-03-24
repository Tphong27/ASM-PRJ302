<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Check Vehicle By Police</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vehicle2.css">
    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="view/Police.jsp">
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

            <!-- Vehicle Search and List Section -->
            <div class="container mt-5" id="vehicle-list">
                <h2 class="text-center">Vehicle Inspection Check</h2>

                <!-- Search Form -->
                <form action="${pageContext.request.contextPath}/vehicles" class="mb-4">
                    <input type="hidden" name="action" value="searchVehicle">
                    <div class="input-group w-50 mx-auto">
                        <input type="text" name="partOfPlate" class="form-control search-input" placeholder="Enter license plate...">
                        <button type="submit" class="btn btn-primary">
                            <i class="bi bi-search"></i> Search
                        </button>
                    </div>
                </form>

                <!-- Vehicle List Table -->
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                    <table class="table table-bordered table-hover">
                        <thead class="table-dark">
                            <tr>
                                <th>Plate Number</th>
                                <th>Brand</th>
                                <th>Model</th>
                                <th>Manufacture Year</th>
                                <th>Engine Number</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ListVehicles}" var="lv">
                                <tr>
                                    <td>${lv.plateNumber}</td>
                                    <td>${lv.brand.brandName}</td>
                                    <td>${lv.model.modelName}</td>
                                    <td>${lv.manufactureYear}</td>
                                    <td>${lv.engineNumber}</td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/vehicles" method="get">
                                            <input type="hidden" name="action" value="viewAllVehicleDetail">
                                            <input type="hidden" name="id" value="${lv.vehicleID}">
                                            <button type="submit" class="btn btn-success action-btn">
                                                View Inspection Record
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>
