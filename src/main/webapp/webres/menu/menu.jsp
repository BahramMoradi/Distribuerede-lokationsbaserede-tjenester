<%-- 
    Document   : menu
    Created on : 11-02-2016, 23:20:09
    Author     : Bahram Moradi
    source :http://line25.com/tutorials/how-to-create-a-pure-css-dropdown-menu
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>trackerindex</title>
        <link href="css/menucss.css" rel="stylesheet" type="text/css"/>
        <link href="css/appcss.css" rel="stylesheet" type="text/css"/>
    </head>
    <body>
        <div id="header">
            <h1>Tracker System</h1>
        </div>
        <nav> <ul>
                <li><a href=''>Introduction</a></li> 
                <li><a href=''>Tracker APP</a>
                    <ul>
                        <li><a href='webres/tracker/uml.jsp'>UML</a> </li> 
                        <li><a href='webres/tracker/screen.jsp'>Screen Shot</a></li>
                        <li><a href=''>Use cases</a>
                            <ul>
                                <li><a href='webres/usecases/UC-1 Create User Profile.html'>Create User Profile</a></li>
                                <li><a href='webres/usecases/UC-2 Update User Profile.html'>Update User Profile</a></li>
                                <li><a href='webres/usecases/UC-4 Send location record to remote service.html'>Sync Data</a></li>
                            </ul>
                        
                        </li>
                    </ul>
                </li> 
                <li><a href=''>RESTful API</a></li>
                <li><a href=''>Web Application</a></li>
                <li><a href=''>Backend</a></li>
                <li><a href='webres/image/supv/supv.jpg'>Supervisor</a></li>
                <c:if test="${empty sessionScope.username}">
                    <li><a href='webres/admin/login.jsp'>Login</a></li>
                    </c:if> 

                <c:if test="${not empty sessionScope.username}"> 
                    <li><form action="logout.action" method="post" >
                            <input type="hidden" value="Logout" >
                            <A HREF="logout.action">Logout</A>
                        </form>
                    </li>

                </c:if> 


            </ul>





        </nav>
       
    </body>
</html>
