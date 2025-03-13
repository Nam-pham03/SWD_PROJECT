<%-- 
    Document   : adminSlideNavComponent
    Created on : Mar 8, 2022, 11:52:14 AM
    Author     : KhoaHD7621
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Core</div>
               
                <div class="sb-sidenav-menu-heading">Manage</div>
                
                <a class="nav-link ${requestScope.destPage eq "manageAccount" ? "active" : ""}" href="AdminManageAccountController">
                    <i class="bi bi-person-lines-fill sb-nav-link-icon"></i>
                    Customer 
                </a>
                
                
                <div class="sb-sidenav-menu-heading">Addons</div>
                <a class="nav-link" href="#!">
                    <div class="sb-nav-link-icon"><i class="fas fa-chart-area"></i></div>
                    Revenue report
                </a>
              
                <div class="sb-sidenav-menu-heading">Utils</div>
                <a class="nav-link ${requestScope.destPage eq "sendMail" ? "active" : ""}" href="SendEmailController?action=pageDirect">
                    <div class="sb-nav-link-icon"><i class="bi bi-envelope"></i></div>
                    Send mail
                </a>
            </div>
        </div>
        <div class="sb-sidenav-footer">
            <div class="small">Logged in as:</div>
            Admin
        </div>
    </nav>
</div>