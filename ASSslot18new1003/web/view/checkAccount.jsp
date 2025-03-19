<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Check Account</title>
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

            <form action="${pageContext.request.contextPath}/user">
                <input type="hidden" name="action" value="add">
                <button type="submit" class="btn btn-primary" style="background-color: green">
                    Add new account
                </button>
                <h3 class="text-center text-white pt-1">.....</h3>
            </form>

            <form action="${pageContext.request.contextPath}/user">
                <input type="hidden" name="action" value="viewAll">
                <div class="input-group w-60">
                    <select name="roleid" class="form-select">
                        <c:forEach items="${ListRoles}" var="lr">
                            <option value="${lr.roleID}" 
                                    <c:if test="${lr.roleID == roleid}">selected="selected"</c:if>>
                                ${lr.roleName}
                            </option>
                        </c:forEach>
                    </select>
                    <button type="submit" class="btn btn-primary"> Search </button>
                </div>
            </form>

            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Full Name</th>
                            <th>Role</th>
                            <th>Email</th>
                            <th>Password</th>
                            <th>Phone</th>
                            <th>Address</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="la" items="${ListAccount}">
                            <tr>
                                <td>${la.fullname}</td>
                                <td>${la.roles.roleName}</td>
                                <td>${la.email}</td>
                                <td>${la.password}</td>
                                <td>${la.phone}</td>
                                <td>${la.address}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/user" onsubmit="return confirm('Are you sure you want to delete this account?');">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="UserId" value="${la.userId}">
                                        <button type="submit" class="btn btn-danger px-4">Delete Account</button>
                                    </form>
                                </td>
                                <c:if test="${la.roles.roleName == 'Owner'}">
                                    <td>
                                        <form action="${pageContext.request.contextPath}/vehicles">
                                            <input type="hidden" name="action" value="viewVehiclesOfUser">
                                            <input type="hidden" name="UserId" value="${la.userId}">
                                            <button type="submit" class="btn btn-primary px-4">View VehiclesList</button>
                                        </form>
                                    </td> 
                                </c:if>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

            </div>

        </div>
    </body>
</html>