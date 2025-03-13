<%-- 
    Document   : managePlant
    Created on : Mar 8, 2022, 9:09:21 PM
    Author     : PAsus
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%@include file="components/adminHeadComponent.jsp" %>

    </head>
    <body class="sb-nav-fixed">
        <!-- Admin navbar -->
        <%@include file="components/adminNavBarComponent.jsp" %>
        <div id="layoutSidenav">
            <!-- Admin Slidenav -->
            <%@include file="components/sellerNavBarComponent.jsp" %>
            <div id="layoutSidenav_content">
                <main>
                    <div class="container-fluid px-4">
                        <h1 class="mt-4">Manage Products</h1>
                        <ol class="breadcrumb mb-4">
                            <li class="breadcrumb-item active">All current orders in system</li>
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
                        <!-- Plant table -->
                        <div class="card mb-4">
                            <div class="card-header fw-bold">
                                <i class="bi bi-table"></i>
                                All Plants Table
                                <!-- Block btn -->
                                <span class="float-end">

                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#blockBtnXYZC">
                                        <i class="bi bi-plus-square"></i> Add new plant
                                    </button>

                                    <div class="modal fade" id="blockBtnXYZC" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                        <div class="modal-dialog modal-dialog-centered modal-lg">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="exampleModalLabel">Product Information</h5>
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <form action="SellerManageProductController" method="POST" enctype="multipart/form-data">
                                                    <div class="modal-body">
                                                        <input type="hidden" name="pid"/>
                                                        <div class="form-outline mb-3">
                                                            <label class="form-label" for="name3Example">Name <span style="color: red; font-weight: bold">*</span></label>
                                                            <input type="text" id="name3Example" class="form-control form-control-lg"
                                                                   required name="name"/>
                                                        </div>
                                                        <div class="form-outline mb-3">
                                                            <label class="form-label" for="img3Example">Image <span style="color: red; font-weight: bold">*</span></label>
                                                            <input type="file"  id="img3Example" class="form-control form-control-lg" accept="image/png, image/gif, image/jpeg" 
                                                                   required name="file"/>
                                                        </div>
                                                        <div class="form-outline mb-3">
                                                            <label class="form-label" for="price3Example">Price <span style="color: red; font-weight: bold">*</span></label>
                                                            <input type="number" min="0" max="999" pattern="^[1-9]\d*$" id="price3Example" class="form-control form-control-lg"
                                                                   required name="price"/>
                                                        </div>
                                                        <div class="form-outline mb-3">
                                                            <label class="form-label" for="descr3Example">Description <span style="color: red; font-weight: bold">*</span></label>
                                                            <textarea type="text" id="descr3Example" class="form-control form-control-lg"
                                                                      required name="description"></textarea>
                                                        </div>

                                                        <div class="form-outline mb-3">
                                                            <label class="form-label" for="year3Example" id="myInput">Release Date <span style="color: red; font-weight: bold">*</span></label>
                                                            <input type="date"  id="year3Example" class="form-control form-control-lg"
                                                                   required name="date"/>
                                                        </div>

                                                        <div class="form-outline mb-3">
                                                            <label class="form-label" for="descr3Example">Author <span style="color: red; font-weight: bold">*</span></label>
                                                            <textarea type="text" id="descr3Example" class="form-control form-control-lg"
                                                                      required name="Author"></textarea>
                                                        </div>

                                                        <div class="form-outline mb-3">
                                                            <label class="form-label" for="quantity3Example" id="myInput">Quantity <span style="color: red; font-weight: bold">*</span></label>
                                                            <input type="number" min="0" max="999" pattern="^[1-9]\d*$" id="quantity3Example" class="form-control form-control-lg"
                                                                   required name="quantity"/>
                                                        </div>

                                                      

                                                        <div class="form-outline mb-3">
                                                            <label class="form-label" for="brand3Example">Category<span style="color: red; font-weight: bold">*</span></label><br>
                                                            <c:forEach items="${categorys}" var="c" varStatus="loop">
                                                                <input type="checkbox" name="cid" value="${c.getCategory_id()}"> ${c.getCategory_name()}

                                                            </c:forEach>
                                                        </div>


                                                    </div>
                                                    <div class="modal-footer">
                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                        <button id="update-profile-btn" type="submit" class="btn btn-danger" name="action" value="createPlant">Create</button>
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
                                            <th>Name</th>
                                            <th style="width: 100px">Image</th>
                                            <th>Price</th>
                                            <th>Description</th>
                                            <th>Release Date</th>
                                            <th>Author</th>
                                            <th>Quantity</th>
                                            <th>Status</th>                                                    
                                            <th>Action</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr>
                                            <th>Id</th>
                                            <th>Name</th>
                                            <th style="width: 100px">Image</th>
                                            <th>Price</th>
                                            <th>Description</th>
                                            <th>Release Date</th>
                                            <th>Author</th>
                                            <th>Quantity</th>
                                            <th>Status</th>                                                    
                                            <th>Action</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>
                                        <c:forEach items="${requestScope.listPlants}" var="LP">
                                            <tr>
                                                <td>${LP.product_id}</td>
                                                <td>${LP.product_name}</td>
                                                <td style="width: 100px"><img src="${LP.img}" style="width: 50%;"></td>
                                                <td>$${LP.price}</td>
                                                <td>${LP.description}</td>
                                                <td>${LP.release_date}</td>
                                                <td>${LP.author}</td>
                                                <td>${LP.quantity}</td>

                                                <c:choose>
                                                    <c:when test="${LP.status == 1}">
                                                        <td style="color: blue;">Available</td>
                                                    </c:when>
                                                    <c:when test="${LP.status == 2}">
                                                        <td style="color: orange;">Sold out</td>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <td style="color: red;">Unavailable</td>
                                                    </c:otherwise>
                                                </c:choose>

                                                <td>
                                                    <span>
                                                        <button type="button" class="btn btn-outline-success w-100" data-bs-toggle="modal" data-bs-target="#blockBtn${LP.product_id}">
                                                            Update
                                                        </button>
                                                        <c:if test="${LP.status != 2}">
                                                            <button type="button" class="btn btn-outline-danger w-100" data-bs-toggle="modal" data-bs-target="#blockBtnd${LP.product_id}">
                                                                <i class="bi bi-trash"></i> Change status
                                                            </button>
                                                        </c:if>



                                                        <div class="modal fade" id="blockBtnd${LP.product_id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog modal-dialog-centered">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title" id="exampleModalLabel">Warning</h5>
                                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                    </div>
                                                                    <div class="p-4 text-center fs-3" style="color: red;">
                                                                        Are you sure you want to change the status of plant <span class="text-dark">${LP.product_name}</span> from your store?
                                                                    </div>
                                                                    <div class="modal-footer">
                                                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Cancel</button>
                                                                        <form action="ChangeStatusProductController?pid=${LP.product_id}" method="POST">
                                                                            <button type="submit" class="btn btn-danger">Change</button>
                                                                        </form>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>



                                                        <!-- Modal -->
                                                        <div class="modal fade" id="blockBtn${LP.product_id}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                            <div class="modal-dialog modal-dialog-centered">
                                                                <div class="modal-content">
                                                                    <div class="modal-header">
                                                                        <h5 class="modal-title" id="exampleModalLabel">Product Information</h5>
                                                                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                                    </div>
                                                                    <form action="SellerManageProductController" method="POST" enctype="multipart/form-data">
                                                                        <div class="modal-body">
                                                                            <input type="hidden" name="pid" value="${LP.product_id}"/>
                                                                            <div class="form-outline mb-3">
                                                                                <label class="form-label" for="name3Example">Name <span style="color: red; font-weight: bold">*</span></label>
                                                                                <input type="text" pattern="^(?!\s*$).+" id="name3Example" class="form-control form-control-lg"
                                                                                       required name="name" value="${LP.product_name}"/>
                                                                            </div>
                                                                            <div class="form-outline mb-3">
                                                                                <label class="form-label" for="price3Example">Price <span style="color: red; font-weight: bold">*</span></label>
                                                                                <input type="number" min="0" max="900000000" pattern="^[1-9]\d*$" id="price3Example" class="form-control form-control-lg"
                                                                                       required name="price" value="${LP.price}"/>
                                                                            </div>
                                                                            <div class="form-outline mb-3">
                                                                                <label class="form-label" for="img3Example">Image <span style="color: red; font-weight: bold">*</span></label><br><br>
                                                                                <img src="${LP.img}" style="width: 50%;"> <br><br>
                                                                                <input type="text"  id="img3Example" class="form-control form-control-lg"  readonly
                                                                                       required name="img" value="${LP.img}"/>
                                                                            </div>



                                                                            <div class="form-outline mb-3">
                                                                                <label class="form-label" for="img3Example"> <span style="color: red; font-weight: bold"></span></label>
                                                                                <input type="file"  id="img3Example" class="form-control form-control-lg" accept="image/png, image/gif, image/jpeg" 
                                                                                       name="file"/>
                                                                            </div>

                                                                            <div class="form-outline mb-3">
                                                                                <label class="form-label" for="descr3Example">Description <span style="color: red; font-weight: bold">*</span></label>
                                                                                <textarea type="text" pattern="^(?!\s*$).+" id="descr3Example" class="form-control form-control-lg"
                                                                                          required name="description">${LP.description}</textarea>
                                                                            </div>

                                                                            <div class="form-outline mb-3">
                                                                                <label class="form-label" for="year3Example">Release Date  <span style="color: red; font-weight: bold">*</span></label>
                                                                                <input type="date"   id="year3Example" class="form-control form-control-lg"
                                                                                       required name="date" value="${LP.release_date}"/>
                                                                            </div>

                                                                            <div class="form-outline mb-3">
                                                                                <label class="form-label" for="descr3Example">Author <span style="color: red; font-weight: bold">*</span></label>
                                                                                <input type="text" pattern="^(?!\s*$).+" id="descr3Example" class="form-control form-control-lg"
                                                                                       required name="Author" value="${LP.author}">
                                                                            </div>

                                                                            <div class="form-outline mb-3">
                                                                                <label class="form-label" for="quantity3Example">Quantity <span style="color: red; font-weight: bold">*</span></label>
                                                                                <input type="number" min="0" max="999" pattern="^[1-9]\d*$" id="quantity3Example" class="form-control form-control-lg"
                                                                                       required name="quantity" value="${LP.quantity}"/>
                                                                            </div>

                                                                            <div class="form-outline mb-3">
                                                                                <label class="form-label" for="brand3Example">Category<span style="color: red; font-weight: bold">*</span></label><br>
                                                                                <c:forEach items="${categorys}" var="c" >
                                                                                    <input <c:forEach items="${LP.category_id}" var="p"> 
                                                                                            ${(p == c.category_id)?"checked = \"checked\"":""}
                                                                                        </c:forEach>
                                                                                        type="checkbox" name="cid" value="${c.category_id}"> ${c.category_name}
                                                                                    <br>
                                                                                </c:forEach>
                                                                            </div>

                                                                         


                                     \







                                                                        </div>
                                                                        <div class="modal-footer">
                                                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                                                            <button id="update-profile-btn" type="submit" class="btn btn-danger" name="action" value="updatePlant">Update</button>
                                                                        </div>
                                                                    </form>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </span>
                                                </td>
                                            </tr>
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