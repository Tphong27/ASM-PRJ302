<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create Account</title>
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
            <form action="user" method="post">
                <input type="hidden" name="action" value="add">
                <div class="row">
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label class="form-label"><strong>Email:</strong></label>
                            <input type="text" name="Email" class="form-control" value="${Email}" required="">
                        </div>
                        <div style="color: red">${error1}</div>

                    </div>
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label class="form-label"><strong>Full Name:</strong></label>
                            <input type="text" name="Fullname" class="form-control" value="${Fullname}" required="">
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label class="form-label"><strong>Password:</strong></label>
                            <input type="text" name="Password" class="form-control" value="${password}" required="">
                        </div>
                        <div style="color: red">${error2}</div>

                    </div>
                    <div class="col-md-6">
                        <div class="mb-3">
                            <label class="form-label"><strong>Phone:</strong></label>
                            <input type="text" name="Phone" class="form-control" value="${Phone}" required="">
                        </div>
                        <div style="color: red">${error3}</div>

                    </div>
                </div>

                <div class="mb-3">
                    <label class="form-label"><strong>Address:</strong></label>
                    <input type="text" name="Address" class="form-control" value="${Address}" required="">
                </div>

                <div class="mb-3">
                    <label class="form-label"><strong>Role: </strong></label>
                    <select name="roleid" class="form-select">
                        <c:forEach items="${listRoles}" var="lr">
                            <option value="${lr.roleID}" 
                                    <c:if test="${lr.roleID == roleid}">selected="selected"</c:if>>
                                ${lr.roleName}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div style="color: red">${success}</div>
                <div style="color: red">${errorMessage}</div>

                <button type="submit" class="btn btn-primary px-4">Create</button>
            </form>
        </div>
    </body>
</html>
