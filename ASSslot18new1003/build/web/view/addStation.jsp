<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Station</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="view/Admin.jsp">Menu</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav w-100 justify-content-around">
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/user?action=viewAll">Account Information</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/inspectionStation?action=view">Inspection Station</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/vehicles?action=searchVehicleForAdmin">Vehicles List</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/brand?action=view">Brand</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/model?action=view">Model</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="${pageContext.request.contextPath}/Logout">Logout</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        <div class="container mt-3">
            <h1>Add Station</h1>
            <form action="${pageContext.request.contextPath}/inspectionStation" method="post">
                <input type="hidden" name="action" value="add">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label>Station Name</label>
                        <input type="text" name="stationName" class="form-control" value="${stationName}" required="">
                    </div>
                </div>                

                <div style="color: red">${error1}</div>

                <div class="form-group">
                    <div class="form-group col-md-6">
                        <label>Address</label>
                        <input type="text" name="Address" class="form-control" value="${Address}" required="">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-md-6">
                        <label>Phone</label>
                        <input type="text" name="Phone" class="form-control" value="${Phone}" required="">
                    </div>
                </div>
                <div class="form-group">
                    <div class="form-group col-md-6">
                        <label>Email</label>
                        <input type="text" name="Email" class="form-control" value="${Email}" required="">
                    </div>
                </div>

                <div style="color: red">${error}</div>

                <div class="form-group">
                    <button type="submit" class="btn btn-primary px-4">ADD</button>
                </div>
            </form>
        </div>
    </body>
</html>
