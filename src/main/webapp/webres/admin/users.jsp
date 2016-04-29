<%-- 
    Document   : users
    Created on : 11-02-2016, 22:35:00
    Author     : Bahram
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="dk.dtu.map.servlets.*" %>
<%@ page import="dk.dtu.locationservice.dto.*" %>
<%@ page import="dk.dtu.locationservice.idao.*" %>
<%
    if (request.getSession().getAttribute("username") == null) {
        response.sendRedirect("../notallowed.jsp");
        return;
    }

    Controller controller = new Controller();
    /*
     String uid=request.getParameter("uid");
     if(uid!=null){
     controller.deleteUser(Long.valueOf(uid));
     }**/

    List<User> users = controller.getAllUsers();
    request.setAttribute("allUsers", users);


%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <style>
            body{
                position: relative;
                text-align: center;    
            }
            .center{
                margin-top: 50px;
                position: absolute;
                left: 50%;
                transform: translate(-50%, -50%);
            }
            table{
                alignment-adjust: central;
                text-align: center;
                border: 1px solid;

            }

            th{
                padding: 5px;
                background: #bbbbbb
            }
            td{
                padding: 5px;
                text-align: center;
            }
            tr:nth-child(even){
                background: #efefef;
            }
            .inputbt{
                padding: 5px;
            }

        </style>
        <title>users</title>
    </head>
    <body>
        <%@include file="adminmenu.jsp" %>

        <div class="center">

            <table>
                <tr>
                    <th>ID</th>
                    <th>User Name</th>
                    <th>Phone</th>
                    <th>Mail</th>
                    <th>Description</th>
                    <th>Delete</th>
                    <!-- <th>Show Location</th>-->
                </tr>
                <c:forEach items="${allUsers}" var="user" varStatus="loop">
                    <tr>
                        <td><c:out value="${user.uid}"/></td>
                        <td><c:out value="${user.name}"/></td>
                        <td><c:out value="${user.phone}"/></td>
                        <td><c:out value="${user.mail}"/></td>
                        <td><c:out value="${user.description}"/></td>
                        <td>
                           <!-- <form name="deleteUser" action="users.jsp?uid=${user.uid}" method="POST"> -->
                            <input class="inputbt" type="submit" value="Delete" name="${user.uid}" onclick="confirmDelete(this.name)"/>
                            <!-- </form> -->
                        </td>
                        <!--<td><input class="inputbt"  type="submit"  value="Show Location" name="${user.uid}" onclick="showLocations(this.name)" /></td>-->


                    </tr>

                </c:forEach>       
            </table>
        </div>



        <script>
            // this method whs used to show a list of locations
            function showLocations(str) {
                if (str === "") {
                    return;
                }
                xhttp = new XMLHttpRequest();
                xhttp.onreadystatechange = function () {
                    if (xhttp.readyState === 4 && xhttp.status === 200) {
                        document.getElementById("locations").innerHTML = xhttp.responseText;
                    }
                };
                xhttp.open("GET", "../../locations?format=html&uid=" + str, true);
                xhttp.send();

            }

            function confirmDelete(uid) {
                var conf = confirm("Are you sure you want to delete this user!");
                if (conf === true) {
                    xhttp = new XMLHttpRequest();
                       xhttp.onreadystatechange = function () {
                    if (xhttp.readyState === 4 && xhttp.status === 200) {
                         location.reload(true);
                    }
                };
                    xhttp.open("DELETE", "../../service/users/" + uid, true);
                    xhttp.send();
                   
                }

            }
        </script>

    </body>
</html>
