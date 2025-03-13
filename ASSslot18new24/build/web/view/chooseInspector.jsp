<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Station Screen</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark" style="height: 100px;">
            <div class="container-fluid">
                <a class="navbar-brand" href="${pageContext.request.contextPath}/view/Station.jsp">Menu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav w-100 justify-content-around">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.userId}&action=viewRegisList">Vehicles List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionRecords?id=${userAccount.userId}&action=ViewCheckList">CheckList</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <div class="container mt-4">
            <h2 class="text-center mb-4">Inspection Details</h2>
            <form action="${pageContext.request.contextPath}/inspectionRecords" method="post">
                <input type="hidden" name="action" value="chooseInspector">
                <input type="hidden" name="id" value="${reID}">
                <input type="hidden" name="date" value="${InspectionDate}">
                <input type="hidden" name="staid" value="${stationid}">
                <input type="hidden" name="veid" value="${vehicleid}">

                
                <div class="row mb-3">
                    <div class="col-md-6">
                        <label class="form-label"><strong>Vehicle Plate:</strong></label>
                        <p class="form-control-plaintext">${vehicle.plateNumber}</p>
                    </div>
                    <div class="col-md-6">
                        <label class="form-label"><strong>Inspection Date:</strong></label>
                        <p class="form-control-plaintext">${InspectionDate}</p>
                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label"><strong>Select Inspector</strong></label>
                    <select name="inspectionId" class="form-select">
                        <c:forEach items="${listInspector}" var="ls">
                            <option value="${ls.userId}">${ls.fullname}</option>
                        </c:forEach>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary w-100">Send</button>

            </form>
        </div>
    </body>
</html>

