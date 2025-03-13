<%-- 
    Document   : manageAccount1
    Created on : Feb 14, 2024, 6:37:41 PM
    Author     : PCASUS
--%>

<%@ page import="java.util.List" %>
<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="org.apache.commons.fileupload.disk.DiskFileItemFactory" %>
<%@ page import="org.apache.commons.fileupload.servlet.ServletFileUpload" %>
<%@ page import="java.io.File" %>
<%@ page import="java.util.Iterator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" language="java"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="components/adminHeadComponent.jsp" %>
        <title>Manage Account</title>
    </head>
    <body class="sb-nav-fixed">
        <%@include file="components/adminNavBarComponent.jsp" %>
        <div id="layoutSidenav">
            <%@include file="components/manageSlideNavComponent.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Manage Account</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">All current Accounts in system</li>
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
                        <!-- Account table -->
                        <div class="card mb-4">
                            <div class="card-header fw-bold">
                                <i class="bi bi-table"></i>
                                All Plants Table
                                <!-- Block btn -->
                                <span class="float-end">
                                    <!-- Button trigger modal -->
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#blockBtnXYZC">
                                        <i class="bi bi-plus-square"></i> Add new Seller
                                    </button>
                                    <!-- Modal -->

                                    <div class="modal fade" id="blockBtnXYZC" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered modal-lg">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Account Information</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <form action="CreateSellerAccountController" method="POST" >
                                                    <div class="modal-body">
                                                        <input type="hidden" name="pid"/>
                                                        <div class="form-outline mb-5">
                                                            <label class="form-label" for="update-profile-input-email">Email <span style="color: red; font-weight: bold">*</span></label>
                                                            <input type="email" id="update-profile-input-email" class="form-control form-control-lg" required placeholder="example@gmail.com" name="email" />
                                                        </div>
                                                        <div id="update-profile-input-email-error" class="form-outline mb-2 fst-italic" style="margin-top: -35px; color: red; font-size: 14px;"></div>


                                                        <div class="form-outline mb-5">
                                                            <label class="form-label" for="update-profile-input-fullname">Full Name <span style="color: red; font-weight: bold">*</span></label>
                                                            <input type="text" id="update-profile-input-fullname" class="form-control form-control-lg" required placeholder="Nguyen Van A" name="name" />
                                                        </div>
                                                        <div id="update-profile-input-fullname-error" class="form-outline mb-2 fst-italic" style="margin-top: -35px; color: red; font-size: 14px;"></div>

                                                        <div class="form-outline mb-5">
                                                            <label class="form-label" for="update-profile-input-phone">Phone Number</label>
                                                            <input type="text" id="update-profile-input-phone" class="form-control form-control-lg" required placeholder="0xxxxxxxxx" name="phone" />
                                                        </div>
                                                        <div id="update-profile-input-phone-error" class="form-outline mb-2 fst-italic" style="margin-top: -35px; color: red; font-size: 14px;"></div>




                                                        <div class="form-outline mb-5">
                                                            <label class="form-label" for="update-profile-input-address">Address</label>
                                                            <input type="text" id="update-profile-input-address" class="form-control form-control-lg" required placeholder="123 Main St" name="address" />
                                                        </div>
                                                        <div id="update-profile-input-address-error" class="form-outline mb-2 fst-italic" style="margin-top: -35px; color: red; font-size: 14px;"></div>


                                                        <div class="form-outline mb-3">
                                                            <label class="form-label" for="date3Example">Birth date <span style="color: red; font-weight: bold">*</span></label>
                                                            <input type="date" id="date3Example" class="form-control form-control-lg"
                                                                   required name="date"/>
                                                        </div>


                                                        <div class="form-outline mb-3">
                                                            <label class="form-label" for="gender3Example">Gender <span style="color: red; font-weight: bold">*</span></label>
                                                            <select name="gender" class="form-select form-select-lg" id="gender3Example">
                                                                <option value="0">Female</option>
                                                                <option value="1">Male</option>

                                                            </select>
                                                        </div>










                                                        <div class="modal-footer">
                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                            <button id="update-profile-btn" type="submit" class="btn btn-danger"  >Create</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                        </div>
                                    </div>

                                </span>
                            </div>
                            <div class="card-body">
                                <table id="plantsTable">
                                    <thead>
                                        <tr>
                                            <th>Id</th>
                                            <th>Email</th>
                                            <th>Name</th>
                                            <th>Phone</th>
                                            <th>Status</th>
                                            <th>Address</th>
                                            <th>Gender</th>
                                            <th>DOB</th>
                                            <th>Role</th>
                                            <th>Action</th>

                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Id</th>
                                            <th>Email</th>
                                            <th>Name</th>
                                            <th>Phone</th>
                                            <th>Status</th>
                                            <th>Address</th>
                                            <th>Gender</th>
                                            <th>DOB</th>
                                            <th>Role</th>
                                            <th>Action</th>

                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach items="${requestScope.listAccounts}" var="LP">
                                            <c:if test="${LP.staff_role == 3}">
                                                <tr>
                                                    <td>${LP.account_id}</td>
                                                    <td>${LP.email}</td>
                                                    <td>${LP.fullname}</td>
                                                    <td>${LP.phone}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${LP.getStatus() == 1}">
                                                                <span style="color: green;">Active</span>
                                                            </c:when>
                                                            <c:when test="${LP.getStatus() == 0}">
                                                                <span style="color: red;">UnActive</span>
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td>${LP.address}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${LP.gender == 0}">
                                                                Male
                                                            </c:when>
                                                            <c:when test="${LP.gender == 1}">
                                                                Female
                                                            </c:when>
                                                        </c:choose>
                                                    </td>
                                                    <td>${LP.dob}</td>
                                                    <td>Seller</td>
                                                    <td>
                                                        <!-- Block btn -->
                                                        <span>
                                                            <!-- Button trigger modal -->
                                                            <button type="button" class="btn btn-outline-success w-100" data-bs-toggle="modal" data-bs-target="#blockBtnd${LP.account_id}">
                                                                Change Status
                                                            </button>


                                                            <!-- Modal -->
                                                            <div class="modal fade" id="blockBtnd${LP.account_id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                                <div class="modal-dialog modal-dialog-centered">
                                                                    <div class="modal-content">
                                                                        <div class="modal-header">
                                                                            <h5 class="modal-title" id="exampleModalLabel">Warning</h5>
                                                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                        </div>
                                                                        <div class="p-4 text-center fs-3" style="color: red;">
                                                                            Are you sure you want to change status seller ? <span class="text-dark">${LP.fullname}</span> ?
                                                                        </div>

                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                                            <form action="ChangeStatusSellerController?email=${LP.email}" method="POST">
                                                                                <button type="submit" class="btn btn-danger">Change Status</button>
                                                                            </form>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                            <!-- Modal -->
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
                <jsp:include page="components/adminFooter.jsp"></jsp:include>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/simple-datatables@latest" crossorigin="anonymous"></script>
        <script src="js/datatables-simple-demo.js"></script>
        <script>
            document.getElementById('update-profile-input-email').addEventListener('input', function () {
                let email = this.value.trim();
                let errorDiv = document.getElementById('update-profile-input-email-error');
                let isValid = /\S+@\S+\.\S+/.test(email); // Basic email validation regex

                if (!isValid) {
                    errorDiv.textContent = 'Please enter a valid email address.';
                    this.classList.add('is-invalid');
                } else {
                    errorDiv.textContent = '';
                    this.classList.remove('is-invalid');
                }
            });
        </script>

        <script>
            document.getElementById('update-profile-input-pass').addEventListener('input', function () {
                let password = this.value.trim();
                let errorDiv = document.getElementById('update-profile-input-pass-error');
                let isValid = true;

                // Check length
                if (password.length < 6 || password.length > 50) {
                    isValid = false;
                    errorDiv.textContent = 'Password must be between 6 and 50 characters long.';
                }
                // Check for leading or consecutive spaces
                else if (/^\s/.test(password) || /\s{2}/.test(password)) {
                    isValid = false;
                    errorDiv.textContent = 'Password cannot start with a space or contain consecutive spaces.';
                } else {
                    errorDiv.textContent = '';
                }

                // Update input field styling based on validity
                if (!isValid) {
                    this.classList.add('is-invalid');
                } else {
                    this.classList.remove('is-invalid');
                }
            });
        </script>


        <script>
            function validateFullName() {
                var fullNameInput = document.getElementById('update-profile-input-fullname').value;
                var errorElement = document.getElementById('update-profile-input-fullname-error');
                // Kiểm tra xem có dấu cách ở đầu hay không
                if (fullNameInput.startsWith(' ')) {
                    errorElement.textContent = "Invalid Name !";
                    return false;
                }
                // Kiểm tra xem có hai dấu cách liên tiếp hay không
                if (fullNameInput.includes('  ')) {
                    errorElement.textContent = "Invalid Name";
                    return false;
                }
                // Xóa thông báo lỗi nếu hợp lệ
                errorElement.textContent = "";
                return true;
            }

            // Thêm sự kiện để kiểm tra khi người dùng nhập vào ô input
            document.getElementById('update-profile-input-fullname').addEventListener('input', validateFullName);
        </script>


        <script>
            function validateAddress() {
                var addressInput = document.getElementById('update-profile-input-address').value;
                var errorElement = document.getElementById('update-profile-input-address-error');
                // Kiểm tra xem có dấu cách ở đầu hay không
                if (addressInput.startsWith(' ')) {
                    errorElement.textContent = "Địa chỉ không được bắt đầu bằng dấu cách.";
                    return false;
                }
                // Kiểm tra xem có hai dấu cách liên tiếp hay không
                if (addressInput.includes('  ')) {
                    errorElement.textContent = "Địa chỉ không được chứa hai dấu cách liên tiếp.";
                    return false;
                }
                // Xóa thông báo lỗi nếu hợp lệ
                errorElement.textContent = "";
                return true;
            }

            // Thêm sự kiện để kiểm tra khi người dùng nhập vào ô input
            document.getElementById('update-profile-input-address').addEventListener('input', validateAddress);
        </script>


        <script>
            function validatePhoneNumber() {
                var phoneNumber = document.getElementById("update-profile-input-phone").value;
                var errorDiv = document.getElementById("update-profile-input-phone-error");
                var regex = /^0\d{9}$/; // Bắt đầu bằng số 0 và có tổng cộng 10 chữ số

                if (!regex.test(phoneNumber)) {
                    errorDiv.innerHTML = "Số điện thoại không hợp lệ. Vui lòng nhập số điện thoại bắt đầu bằng số 0 và có độ dài 10 số.";
                    return false;
                } else {
                    errorDiv.innerHTML = "";
                    return true;
                }
            }

            // Bắt sự kiện thay đổi trong ô input
            document.getElementById("update-profile-input-phone").addEventListener("input", validatePhoneNumber);
        </script>

        <script>
            function validateIdentityNumber() {
                var identityNumber = document.getElementById("update-profile-input-identity").value;
                var errorDiv = document.getElementById("update-profile-input-identity-error");
                var regex = /^0\d{11}$/; // Bắt đầu bằng số 0 và có tổng cộng 12 chữ số

                if (!regex.test(identityNumber)) {
                    errorDiv.innerHTML = "Số chứng minh nhân dân không hợp lệ. Vui lòng nhập số bắt đầu bằng số 0 và có độ dài 12 số.";
                    return false;
                } else {
                    errorDiv.innerHTML = "";
                    return true;
                }
            }

            // Bắt sự kiện thay đổi trong ô input
            document.getElementById("update-profile-input-identity").addEventListener("input", validateIdentityNumber);
        </script>



    </body>
</html>
