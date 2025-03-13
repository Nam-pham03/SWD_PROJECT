<%-- 
    Document   : manageAccount
    Created on : Mar 8, 2022, 4:48:20 PM
    Author     : PAsus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tag" uri="/WEB-INF/tlds/mytaglib.tld"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="components/adminHeadComponent.jsp" %>
        <title>Account Customer</title>
    </head>
    <body class="sb-nav-fixed">
        <!-- Admin navbar -->
        <%@include file="components/adminNavBarComponent.jsp" %>
        <div id="layoutSidenav">
            <!-- Admin Slidenav -->
            <%@include file="components/adminSlideNavComponent.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Manage Customer</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">All current accounts in system</li>
                        </ol>
                        <c:choose>
                            <c:when test="${not empty requestScope.MSG_SUCCESS}">
                                <div class="alert alert-success fs-3" role="alert">
                                    ${requestScope.MSG_SUCCESS}
                                </div>
                            </c:when>
                            <c:when test="${not empty requestScope.MSG_ERROR}">
                                <div class="alert alert-danger fs-3" role="alert">
                                    ${requestScope.MSG_ERROR}
                                </div>
                            </c:when>
                        </c:choose>
                        <!-- Admin Account Table -->
                        
                        <!-- Active User Account Table -->
                        <div class="card mb-4">
                            <div class="card-header fw-bold">
                                <i class="bi bi-table"></i>
                                 User Accounts Table
                            </div>
                            <div class="card-body">
                                <table id="activeUserAccountTable">
                                    <thead>
                                        <tr>
                                            <tag:thAccountTable/>
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <tag:thAccountTable/>
                                            <th>Action</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach items="${requestScope.listAccounts}" var="LA">
                                            <c:if test="${LA.role eq 0}">
                                                <tr>
                                                    <td>${LA.account_id}</td>
                                                    <td>${LA.email}</td>
                                                    <td>${LA.fullname}</td>                                                
                                                    <td>${not empty LA.phone ? LA.phone : "Null"}</td>
                                                    <td><c:choose>
                                                            <c:when test="${LA.getStatus() == 1}">
                                                                <span style="color: green;">Active</span>
                                                            </c:when>
                                                            <c:when test="${LA.getStatus() == 0}">
                                                                <span style="color: red;">UnActive</span>
                                                            </c:when>
                                                        </c:choose></td>
                                                    <td>${LA.address}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${LA.gender == 0}">
                                                                Male
                                                            </c:when>
                                                            <c:when test="${LA.gender == 1}">
                                                                Female
                                                            </c:when>

                                                        </c:choose>
                                                    </td>
                                                    <td>${LA.dob}</td>
                                                    <td>User</td>
                                                    <td>
                                                        <!-- Block btn -->
                                                        <span>
                                                            <!-- Button trigger modal -->
                                                            <button type="button" class="btn btn-outline-danger w-100" data-bs-toggle="modal" data-bs-target="#blockBtn${LA.account_id}">
                                                                Change Status
                                                            </button>
                                                            <!-- Modal -->
                                                            <div class="modal fade" id="blockBtn${LA.account_id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog modal-dialog-centered">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLabel">Alert</h5>
                                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                        </div>
                                                                        <div class="p-4 text-center fs-3"
                                                                             style="color: red;">
                                                                            You are blocking a user named "<span class="text-dark">${LA.fullname}</span>" whose email is "<span class="text-dark">${LA.email}</span>"
                                                                        </div>
                                                                        <form action="ChangeStatusCustomerController" method="POST">
                                                                            <input type="hidden" name="email" value="${LA.email}"/>
                                                                            <div class="modal-footer">
                                                                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">No</button>
                                                                                <button id="update-profile-btn" type="submit" class="btn btn-danger" name="action" value="blockAccount">Yes</button>
                                                                            </div>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:if>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                        
                    </div>
                </main>
                <!-- Footer -->
                <jsp:include page="components/adminFooter.jsp"></jsp:include>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
    </body>
</html>
