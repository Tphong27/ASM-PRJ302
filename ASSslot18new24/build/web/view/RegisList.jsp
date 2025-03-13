<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Station Screen</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="height: 100px;">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/view/Station.jsp">Menu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav w-100 justify-content-around">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.getUserId()}&action=viewRegisList">Inspection Registration List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.getUserId()}&action=viewCheckList">CheckList</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h2 class="text-center">List of vehicles waiting for registration for inspection</h2>
            <table class="table table-striped table-bordered text-center">
                <thead class="table-dark">
                    <tr>
                        <th class="text-center">Vehicle ID</th>
                        <th class="text-center">Plate Number</th>
                        <th class="text-center">Brand</th>
                        <th class="text-center">Model</th>
                        <th class="text-center">Manufacture Year</th>
                        <th class="text-center">Engine Number</th>
                        <th class="text-center">Registration Date</th>
                        <th class="text-center">Inspection Date</th>
                        <th class="text-center"></th> 
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listRecords}" var="lr">
                        <c:if test="${lr.inspectionDate == null}">
                            <tr>
                        <form action="${pageContext.request.contextPath}/inspectionRecords" method="get">
                            <input type="hidden" name="action" value="SendRecords">
                            <input type="hidden" name="id" value="${lr.vehicles.vehicleID}">
                            <input type="hidden" name="stationid" value="${lr.stationID}">
                            <input type="hidden" name="recordid" value="${lr.recordID}">
                            <input type="hidden" name="reDate" value="${lr.registrationDate}">

                            <td class="align-middle">${lr.vehicles.vehicleID}</td>
                            <td class="align-middle">${lr.vehicles.plateNumber}</td>
                            <td class="align-middle">${lr.vehicles.brand.brandName}</td>
                            <td class="align-middle">${lr.vehicles.model.modelName}</td>
                            <td class="align-middle">${lr.vehicles.manufactureYear}</td>
                            <td class="align-middle">${lr.vehicles.engineNumber}</td>
                            <td class="align-middle">${lr.registrationDate}</td>
                            <td class="align-middle">
                                <input type="date" name="InspectionDate" class="form-control text-center" required>
                                <div class="text-danger mt-1">${errors[lr.recordID]}</div>

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
    </body>
</html>