<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Vehicles</title>
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
            <form action="vehicles" method="post">
                <input type="hidden" name="action" value="add">
                <input type="hidden" name="id" value="${userAccount.userId}">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Plate Number</label>
                        <input type="text" name="PlateNumber" class="form-control" value="${PlateNumber}" required="">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-md-6">
                        <label>Brand</label>
                        <select name="Brandid" class="form-select">
                            <c:forEach items="${brands}" var="br">
                                <option value="${br.brandID}" 
                                        <c:if test="${br.brandID == brandid}">selected="selected"</c:if>>
                                    ${br.brandName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-md-6">
                        <label>Model</label>
                        <select name="Modelid" class="form-select" >
                            <c:forEach items="${models}" var="md">
                                <option value="${md.modelID}" 
                                        <c:if test="${md.modelID == modelid}">selected="selected"</c:if>>
                                    ${md.modelName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-md-6">
                        <label>Manufacture Year</label>
                        <input type="text" name="ManufactureYear" class="form-control" value="${year}" required="">
                    </div>

                </div>
                <div class="form-group">
                    <div class="form-group col-md-6">
                        <label>Engine Number</label>
                        <input type="text" name="EngineNumber" class="form-control" value="${EngineNumber}" required="">
                    </div>
                </div>

                <div style="color: red">${error}</div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary px-4">Create new car</button>
                </div>
            </form>
        </div>
    </body>
</html>
