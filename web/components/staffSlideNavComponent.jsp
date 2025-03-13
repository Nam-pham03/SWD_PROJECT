<%-- 
    Document   : staffSlideNavComponent
    Created on : Nov 1, 2024, 1:55:40 PM
    Author     : PCASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="layoutSidenav_nav">
    <nav class="sb-sidenav accordion sb-sidenav-dark" id="sidenavAccordion">
        <div class="sb-sidenav-menu">
            <div class="nav">
                <div class="sb-sidenav-menu-heading">Core</div>
                <div class="sb-sidenav-menu-heading">Manage</div>
                
                <!-- Modified Dashboard link to go back to the previous page -->
                <a class="nav-link ${requestScope.destPage eq "manageAccount" ? "active" : ""}" href="javascript:history.back()">
                    <i class="bi bi-person-lines-fill sb-nav-link-icon"></i>
                    Go back
                </a>
            </div>
        </div>
        <div class="sb-sidenav-footer">
            <div class="small">Logged in as:</div>
            Staff
        </div>
    </nav>
</div>

