<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registration List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/navbar-footer.css">

    </head>
    <body>
        <!-- Navbar -->
        <nav class="navbar navbar-expand-lg navbar-dark">
            <div class="container">
                <a class="navbar-brand" href="view/Station.jsp">
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

        <div class="main-content">                        
            <div class="container mt-4">
                <h2 class="text-center">List of vehicles waiting for registration inspection</h2>

                <table class="table table-striped table-bordered  text-center">
                    <thead class="table-dark ">
                        <tr>
                            <th>Plate Number</th>
                            <th>Brand</th>
                            <th>Model</th>
                            <th>Manufacture Year</th>
                            <th>Engine Number</th>
                            <th>Registration Date</th>
                            <th>Inspection Date</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listRecords}" var="lr">
                            <c:if test="${lr.inspectionDate == null}">
                                <tr>
                            <form action="${pageContext.request.contextPath}/inspectionRecords" method="get" class="d-flex justify-content-center">
                                <input type="hidden" name="action" value="SendRecords">
                                <input type="hidden" name="id" value="${lr.vehicles.vehicleID}">
                                <input type="hidden" name="stationid" value="${lr.stationID}">
                                <input type="hidden" name="recordid" value="${lr.recordID}">
                                <input type="hidden" name="reDate" value="${lr.registrationDate}">

                                
                                <td class="align-middle">${lr.vehicles.plateNumber}</td>
                                <td class="align-middle">${lr.vehicles.brand.brandName}</td>
                                <td class="align-middle">${lr.vehicles.model.modelName}</td>
                                <td class="align-middle">${lr.vehicles.manufactureYear}</td>
                                <td class="align-middle">${lr.vehicles.engineNumber}</td>
                                <td class="align-middle">${lr.registrationDate}</td>
                                <td class="align-middle">
                                    <input type="date" name="InspectionDate" class="form-control text-center" required>
                                    <div class="error-message text-danger">
                                        <c:if test="${not empty errorMap[lr.recordID]}">
                                            ${errorMap[lr.recordID]}
                                        </c:if>
                                    </div>
                                </td>
                                <td class="align-middle">
                                    <button type="submit" class="btn btn-primary w-100">Choose Inspector</button>
                                </td>
                            </form>
                            </tr>
                        </c:if>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Footer -->
        <%@ include file="footer.jsp" %>
    </body>
</html>