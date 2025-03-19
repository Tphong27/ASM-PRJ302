<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Vehicles Dashboard</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/vehicle.css">
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
        <div class="container mt-3 main-content">
            <div class="row justify-content-center mt-5">
                <div class="col-lg-10">
                    <h2 class="text-center mb-4">🚗 Your Vehicles</h2>

                    <table class="table table-bordered table-hover text-center align-middle">
                        <thead class="table-dark">
                            <tr>
                                <th>Plate Number</th>
                                <th>Brand</th>
                                <th>Model</th>
                                <th>Year</th>
                                <th>Engine Number</th>
                                <th>Inspection Result</th>
                                <th></th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listVehicles}" var="lv" varStatus="index">
                                <tr>
                                    <td>${lv.plateNumber}</td>
                                    <td>${lv.brand.brandName}</td>
                                    <td>${lv.model.modelName}</td>
                                    <td>${lv.manufactureYear}</td>
                                    <td>${lv.engineNumber}</td>
                                    <td>
                                        <span class="badge
                                              <c:choose>
                                                  <c:when test="${lv.inspectionRecords.result == 'Pass'}">bg-success</c:when>
                                                  <c:when test="${lv.inspectionRecords.result == 'Fail'}">bg-danger</c:when>
                                                  <c:when test="${lv.inspectionRecords.result == 'Pending'}">bg-warning text-dark</c:when>
                                                  <c:when test="${lv.inspectionRecords.result == 'Testing'}">bg-primary</c:when>
                                                  <c:otherwise>bg-secondary</c:otherwise>
                                              </c:choose>
                                              ">
                                            ${lv.inspectionRecords.result}
                                        </span>
                                    </td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/vehicles" method="get" class="d-inline">
                                            <input type="hidden" name="action" value="edit">
                                            <input type="hidden" name="id" value="${lv.vehicleID}">
                                            <button type="submit" class="btn btn-sm btn-success">
                                                <i class="bi bi-pencil-square"></i> Edit
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/vehicles" method="get" class="d-inline">
                                            <input type="hidden" name="action" value="viewAll">
                                            <input type="hidden" name="id" value="${lv.vehicleID}">
                                            <button type="submit" class="btn btn-sm btn-primary">
                                                <i class="bi bi-eye"></i> View All
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <div class="container mt-4">
                        <form action="${pageContext.request.contextPath}/vehicles" method="get">
                            <input type="hidden" name="action" value="add">
                            <button type="submit" class="btn btn-primary btn-lg">
                                <i class="bi bi-plus-lg"></i> Add new vehicle profile
                            </button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>
