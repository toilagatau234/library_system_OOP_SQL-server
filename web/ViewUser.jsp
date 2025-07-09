<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <title>Admin Page</title>
        <link href="https://cdn.jsdelivr.net/npm/simple-datatables@latest/dist/style.css" rel="stylesheet" />
        <link href="./style/styles.css" rel="stylesheet" />
        <script src="https://use.fontawesome.com/releases/v6.1.0/js/all.js" crossorigin="anonymous"></script>

    </head>

    <body class="sb-nav-fixed" style="overflow-x: hidden">
        <%@include file="./Navbar.jsp"  %>

        <div id="layoutSidenav">

            <!-- Sidebar  -->
            <%@include file="./SidebarAdmin.jsp" %>

            <div id="layoutSidenav_content">
                <main>
                    <section>
                        <div class="container py-5">
                            <div class="row">
                                <div class="col-lg-4">
                                    <div class="card mb-4">
                                        <div class="card-body text-center">
                                            <img src="${user.avt}"
                                                 alt="avatar" class="rounded-circle img-fluid" style="width: 250px; height: 250px; object-fit: cover;">
                                            <h5 class="my-3">${user.username}</h5>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-lg-8">
                                    <form action="UpdateUser" method="post" class="card mb-4">
                                        <input type="hidden" name="username" value="${user.username}"/>
                                        <div class="card-body">
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Name</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <input type="text" name="name" value="${user.name}"/>

                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Gender</p>
                                                </div>
                                                <div class="col-sm-9">

                                                    <c:if test="${user.sex}">
                                                        <input class="form-check-input" type="radio" name="sex" value="true" id="flexRadioDefault1" checked>
                                                        <label class="form-check-label" for="flexRadioDefault1">
                                                            Male
                                                        </label>
                                                        <input class="form-check-input" type="radio" name="sex" value="false" id="flexRadioDefault2" >
                                                        <label class="form-check-label" for="flexRadioDefault2">
                                                            Female
                                                        </label>
                                                    </c:if>
                                                    <c:if test="${!user.sex}">
                                                        <input class="form-check-input" type="radio" name="sex" value="true" id="flexRadioDefault1" >
                                                        <label class="form-check-label" for="flexRadioDefault1">
                                                            Male
                                                        </label>
                                                        <input class="form-check-input" type="radio" name="sex" value="false" id="flexRadioDefault2" checked>
                                                        <label class="form-check-label" for="flexRadioDefault2">
                                                            Female
                                                        </label>
                                                    </c:if>

                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Date Birth</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <input name="datebirth" type="date" value="${user.datebirth}" />

                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Password</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <input  name="password" type="text" value="${user.password}" />
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Email</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <input  name="gmail" type="email" value="${user.gmail}" />
                                                </div>
                                            </div>
                                            <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Phone</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <input  name="phone" type="text" value="${user.phone}" />

                                                </div>
                                            </div>
<!--                                                    <hr>
                                            <div class="row">
                                                <div class="col-sm-3">
                                                    <p class="mb-0">Change Image Profile</p>
                                                </div>
                                                <div class="col-sm-9">
                                                    <input  name="avt" type="file" value="${user.avt}"/>

                                                </div>
                                            </div>-->
                                            <input  name="avt" type="hidden" value="${user.avt}"/>
                                        </div>
                                            <input style="width: 20%" href="UpdateUser?id=${user.username}" type="submit" class="btn btn-primary" value="Update Profile"></input>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </section>
                </main>

                <!-- Footer -->
                <%@include file="./Footer.jsp"%>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
        <script>
            window.addEventListener('DOMContentLoaded', event => {
                const sidebarToggle = document.body.querySelector('#sidebarToggle');
                if (sidebarToggle) {
                    sidebarToggle.addEventListener('click', event => {
                        event.preventDefault();
                        document.body.classList.toggle('sb-sidenav-toggled');
                        localStorage.setItem('sb|sidebar-toggle', document.body.classList.contains('sb-sidenav-toggled'));
                    });
                }

            });
        </script>

    </body>

</html>
