<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Your Vehicles</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="view/Owner.jsp">Menu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav w-100 justify-content-around">
                        <li class="nav-item">
                            <a class="nav-link" href="view/UserInfo.jsp">Personal Information</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/vehicles?id=${userAccount.userId}&action=view">Your Vehicles</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?&action=viewInspectionDate">Inspection</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-3">
            <form action="vehicles" method="get">
                <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Vehicle ID</th>
                                <th>Plate Number</th>
                                <th>Brand</th>
                                <th>Model</th>
                                <th>Manufacture Year</th>
                                <th>Engine Number</th>
                                <th>Inspection Result</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listVehicles}" var="lv">
                                <tr>
                                    <td>${lv.vehicleID}</td>
                                    <td>${lv.plateNumber}</td>
                                    <td>${lv.brand.brandName}</td>
                                    <td>${lv.model.modelName}</td>
                                    <td>${lv.manufactureYear}</td>
                                    <td>${lv.engineNumber}</td>
                                    <td>
                                        <span class="
                                              <c:choose>
                                                  <c:when test="${lv.inspectionRecords.result == 'Pass'}">text-success fw-bold</c:when>
                                                  <c:when test="${lv.inspectionRecords.result == 'Fail'}">text-danger fw-bold</c:when>
                                                  <c:when test="${lv.inspectionRecords.result == 'Pending'}">text-warning fw-bold</c:when>
                                                  <c:when test="${lv.inspectionRecords.result == 'Testing'}">text-primary fw-bold</c:when>
                                                  <c:otherwise>text-secondary</c:otherwise>
                                              </c:choose>
                                              ">
                                            ${lv.inspectionRecords.result}
                                        </span>
                                    </td>

                                    <td>
                                        <form action="${pageContext.request.contextPath}/vehicles" method="get">
                                            <input type="hidden" name="action" value="edit">
                                            <input type="hidden" name="id" value="${lv.vehicleID}">
                                            <button type="submit" class="btn btn-primary" style="background-color: green;" >
                                                Edit
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/vehicles" method="get">
                                            <input type="hidden" name="action" value="viewAll">
                                            <input type="hidden" name="id" value="${lv.vehicleID}">
                                            <button type="submit" class="btn btn-primary" style="background-color: blue;" >
                                                View All
                                            </button>
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </form>
            <form action="${pageContext.request.contextPath}/vehicles" method="get">
                <input type="hidden" name="action" value="add">
                <button type="submit" class="btn btn-primary px-4">Add new car</button>
            </form>

        </div>
    </body>
</html>
