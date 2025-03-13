<%-- 
    Document   : registration
    Created on : Mar 5, 2022, 7:11:13 PM
    Author     : PAsus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8"/>
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content=""/>
        <meta name="author" content=""/>
        <title>Registration</title>
        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="./images/favicon.png"/>
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
        <link href="css/my-styles.css" rel="stylesheet">
        <!-- Show hide password -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
              integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay" crossorigin="anonymous">

        <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
        <script src="https://unpkg.com/bootstrap-show-password@1.2.1/dist/bootstrap-show-password.min.js"></script>
    </head>

    <body>
        <!-- Contact Head -->
        <%@include file="components/contactHeadComponent.jsp" %>
        <!-- Navigation -->
        <%@include file="components/registrationNavComponent.jsp" %>
        <!-- Registration Session -->
        <section class="bg-image"
                 style="background-image: url('https://mdbcdn.b-cdn.net/img/Photos/new-templates/search-box/img4.webp');">
            <div class="mask d-flex align-items-center gradient-custom-3">
                <div class="container">
                    <div class="row d-flex justify-content-center align-items-center h-100">
                        <div class="col-12 col-md-9 col-lg-7 col-xl-6 pt-5 pb-5">
                            <div class="card" style="border-radius: 15px;">
                                <div class="card-body p-5">
                                    <h2 class="text-uppercase text-center mb-2 default-cursor">Create an account
                                    </h2>
                                    <c:if test="${not empty requestScope.MSG_SUCCESS}">
                                        <div class="alert alert-success" role="alert">
                                            ${requestScope.MSG_SUCCESS}
                                        </div>
                                    </c:if>
                                    <c:if test="${not empty requestScope.MSG_ERROR}">
                                        <div class="alert alert-danger" role="alert">
                                            ${requestScope.MSG_ERROR}
                                        </div>
                                    </c:if>
                                    <div id="register-error" class="form-outline mb-2 fst-italic"
                                         style="color: red; font-size: 18px;">
                                    </div>
                                    <form action="RegistrationController" method="POST" id="register-form">
                                        <!-- Email -->
                                        <div class="form-outline mb-5">
                                            <label class="form-label" for="register-mail-input">Your Email <span
                                                    style="color: red; font-weight: bold">*</span></label>
                                            <input id="register-mail-input" type="email"
                                                   class="form-control form-control-lg" required
                                                   placeholder="example@gmail.com"
                                                   name="email" value="${requestScope.email}" oninput="validateEmail(this.value)"/>
                                        </div>
                                        <div id="register-mail-input-error" class="form-outline mb-2 fst-italic"
                                             style="margin-top: -35px; color: red; font-size: 14px;">
                                        </div>


                                        <div class="form-outline mb-5">
                                            <label class="form-label" for="register-input-name">Your Name <span style="color: red; font-weight: bold">*</span></label>
                                            <input id="register-input-name" type="text" class="form-control form-control-lg" required placeholder="Nguyen Van A" name="name" oninput="validateName(this.value)"/>
                                        </div>  
                                        <div id="register-name-input-error" class="form-outline mb-2 fst-italic" style="margin-top: -35px; color: red; font-size: 14px;"></div>



                                        <!-- Phone -->
                                        <div class="form-outline mb-5">
                                            <label class="form-label" for="register-input-phone">Phone number <span
                                                    style="color: red; font-weight: bold">*</span></label>
                                            <input type="text" id="register-input-phone" class="form-control form-control-lg"
                                                   required placeholder="0791234xx"
                                                   name="phone" oninput="validatePhone(this.value)"/>
                                        </div>
                                        <div id="register-input-phone-error" class="form-outline mb-2 fst-italic"
                                             style="margin-top: -35px; color: red; font-size: 14px;">
                                        </div>


                                        <!-- Address -->
                                        <div class="form-outline mb-5">
                                            <label class="form-label" for="register-input-address">Address <span style="color: red; font-weight: bold">*</span></label>
                                            <input type="text" id="register-input-address" class="form-control form-control-lg" required placeholder="Your Address" name="address" oninput="validateAddress(this.value)"/>
                                        </div>
                                        <div id="register-input-address-error" class="form-outline mb-2 fst-italic" style="margin-top: -35px; color: red; font-size: 14px;"></div>

                         

                                        <!-- Gender -->
                                        <div class="form-outline mb-5">
                                            <label class="form-label">Gender <span style="color: red; font-weight: bold">*</span></label>
                                            <select class="form-select form-select-lg" id="register-input-gender" name="gender" required>
                                                <option value="true">Male</option>
                                                <option value="false">Female</option>
                                            </select>
                                        </div>
                                        <div id="register-input-gender-error" class="form-outline mb-2 fst-italic" style="margin-top: -10px; color: red; font-size: 14px;"></div>




                                        <div class="form-outline mb-5">
                                            <label class="form-label" for="register-input-birthdate">Birthdate <span style="color: red; font-weight: bold">*</span></label>
                                            <input type="date" id="register-input-birthdate" class="form-control form-control-lg" required name="birthdate"/>
                                        </div>
                                        <div id="register-input-birthdate-error" class="form-outline mb-2 fst-italic" style="margin-top: -35px; color: red; font-size: 14px;"></div>




                                        <!-- Password -->
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="register-input-psw">Password <span
                                                    style="color: red; font-weight: bold">*</span></label>
                                            <input id="register-input-psw" class="form-control form-control-lg"
                                                   data-toggle="password" class="form-control" type="password" maxlength="50"
                                                   placeholder="Password" required
                                                   name="password"  oninput="validatePassword(this.value)">
                                        </div>
                                        <div id="register-input-psw-error" class="form-outline mb-2 fst-italic"
                                             style="margin-top: -10px; color: red; font-size: 14px;">
                                        </div>

                                        <!-- Confirm Password -->
                                        <div class="form-outline mb-4">
                                            <label class="form-label" for="register-input-rp-psw">Repeat your password
                                                <span style="color: red; font-weight: bold">*</span>
                                            </label>
                                            <input id="register-input-rp-psw" class="form-control form-control-lg"
                                                   data-toggle="password" class="form-control" type="password" maxlength="50"
                                                   placeholder="Repeat password" required oninput="validateConfirmPassword(this.value)">
                                        </div>
                                        <div id="register-input-rp-psw-error" class="form-outline mb-2 fst-italic"
                                             style="margin-top: -10px; color: red; font-size: 14px;">
                                        </div>
                                        <!-- Check privacy -->
                                        <div class="form-check d-flex justify-content-start mb-5">
                                            <input id="register-privacy-checkbox" class="form-check-input me-2" type="checkbox" id="form2Example3cg" required/>
                                            <label class="form-check-label" for="form2Example3cg">
                                                I agree all statements in
                                                <!-- Button trigger modal -->
                                                <a class="text-body" style="cursor: pointer" data-bs-toggle="modal"
                                                   data-bs-target="#exampleModal">
                                                    <u>Terms of service</u>
                                                </a>
                                                <!-- Modal -->
                                                <div class="modal fade" id="exampleModal" tabindex="-1"
                                                     aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                    <div class="modal-dialog">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 class="modal-title" id="exampleModalLabel">Terms of
                                                                    service</h5>
                                                                <button type="button" class="btn-close"
                                                                        data-bs-dismiss="modal" aria-label="Close"></button>
                                                            </div>
                                                            <div class="modal-body">
                                                                ...
                                                            </div>
                                                            <div class="modal-footer">
                                                                <button type="button" class="btn btn-secondary"
                                                                        data-bs-dismiss="modal">Close</button>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </label>
                                        </div>
                                        <div id="register-privacy-checkbox-error" class="form-outline mb-3 fst-italic"
                                             style="margin-top: -35px; color: red; font-size: 14px;">
                                        </div>
                                        <!-- Registion Btn -->
                                        <div class="d-flex justify-content-center">
                                            <button id="register-btn" type="submit"
                                                    class="btn btn-success btn-block btn-lg gradient-custom-4 text-body border-0">
                                                Register</button>
                                        </div>
                                        <p class="text-center text-muted mt-4 mb-0">
                                            Have already an account? 
                                            <a href="login.jsp" class="fw-bold text-body"><u>Login here</u></a>
                                        </p>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- Footer -->
        <%@include file="components/footerComponent.jsp" %>
        <!-- Bootstrap core JS -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS -->
        <script src="js/scripts.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!--        <script src="js/register.js"></script>-->
         <script>
            function validateName(name) {
                var isValid = true;

                // Kiểm tra dấu cách ở đầu
                if (name[0] === ' ') {
                    isValid = false;
                }

                for (var i = 0; i < name.length - 1; i++) {
                    // Kiểm tra dấu cách liên tiếp
                    if (name[i] === ' ' && name[i + 1] === ' ') {
                        isValid = false;
                        break;
                    }
                }

                if (!isValid) {
                    document.getElementById('register-name-input-error').innerText = 'Invalid Name!';
                } else {
                    document.getElementById('register-name-input-error').innerText = '';
                }
            }
        </script>



        <script>
            function validateIdentity(identity) {
                var regex = /^[0-9]{9,12}$/; // Biểu thức chính quy kiểm tra từ 9 đến 12 chữ số
                if (!regex.test(identity)) {
                    document.getElementById('register-input-identity-error').innerText = 'Số căn cước không hợp lệ.';
                } else {
                    document.getElementById('register-input-identity-error').innerText = '';
                }
            }
        </script>


        <script>
            function validateAddress(address) {
                var isValid = true;

                // Kiểm tra dấu cách ở đầu
                if (address[0] === ' ') {
                    isValid = false;
                }

                for (var i = 0; i < address.length - 1; i++) {
                    // Kiểm tra dấu cách liên tiếp
                    if (address[i] === ' ' && address[i + 1] === ' ') {
                        isValid = false;
                        break;
                    }
                }

                if (!isValid) {
                    document.getElementById('register-input-address-error').innerText = 'Invalid Address';
                } else {
                    document.getElementById('register-input-address-error').innerText = '';
                }
            }
        </script>


        <script>
            function validatePassword(password) {
                var isValid = true;

                // Kiểm tra mật khẩu phải có ít nhất 6 ký tự
                if (password.length < 6) {
                    isValid = false;
                }

                // Kiểm tra mật khẩu không có dấu cách ở đầu
                if (password[0] === ' ') {
                    isValid = false;
                }

                for (var i = 0; i < password.length - 1; i++) {
                    // Kiểm tra dấu cách liên tiếp
                    if (password[i] === ' ' && password[i + 1] === ' ') {
                        isValid = false;
                        break;
                    }
                }

                if (!isValid) {
                    document.getElementById('register-input-psw-error').innerText = 'Password must be at least 6 characters long and cannot contain two consecutive spaces or start with a space.';
                } else {
                    document.getElementById('register-input-psw-error').innerText = '';
                }
            }
        </script>


        <script>
            function validateConfirmPassword(confirmPassword) {
                var password = document.getElementById('register-input-psw').value;
                if (password !== confirmPassword) {
                    document.getElementById('register-input-rp-psw-error').innerText = 'Passwords do not match.';
                } else {
                    document.getElementById('register-input-rp-psw-error').innerText = '';
                }
            }
        </script>

        <script>
            function validateEmail(email) {
                var regex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
                if (!regex.test(email)) {
                    document.getElementById('register-mail-input-error').innerText = 'Invalid Email Address.';
                } else {
                    document.getElementById('register-mail-input-error').innerText = '';
                }
            }
        </script>


        <script>
            function validatePhone(phone) {
                var regex = /^0\d{9}$/; // Starts with 0 and has 10 digits
                if (!regex.test(phone)) {
                    document.getElementById('register-input-phone-error').innerText = 'Invalid Phone Number.';
                } else {
                    document.getElementById('register-input-phone-error').innerText = '';
                }
            }
        </script>


        <script>
            document.getElementById('register-btn').addEventListener('click', function (event) {
                if (!document.getElementById('register-privacy-checkbox').checked) {
                    document.getElementById('register-privacy-checkbox-error').innerText = 'Please agree to the Terms of Service.';
                    event.preventDefault(); // Prevent form submission if checkbox not checked
                } else {
                    document.getElementById('register-privacy-checkbox-error').innerText = '';
                }
            });
        </script>

        <script>
            // Function to validate age
            function validateAge() {
                var birthdateInput = document.getElementById("register-input-birthdate").value;
                var birthdate = new Date(birthdateInput);
                var today = new Date();
                var age = today.getFullYear() - birthdate.getFullYear();
                var monthDiff = today.getMonth() - birthdate.getMonth();
                if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birthdate.getDate())) {
                    age--;
                }
                if (age < 18) {
                    document.getElementById("register-input-birthdate-error").innerText = "You must be at least 18 years old to register.";
                    return false;
                }
                return true;
            }

            // Event listener for form submission
            document.querySelector('form').addEventListener('submit', function (event) {
                if (!validateAge()) {
                    event.preventDefault(); // Prevent form submission if age is not valid
                }
            });
        </script>

    </body>

</html>