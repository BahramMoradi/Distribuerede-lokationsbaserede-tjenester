<%-- 
    Document   : adminmenu
    Created on : 22-02-2016, 12:29:30
    Author     : Bahram
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%
    if (request.getSession().getAttribute("username") == null) {
        response.sendRedirect("../notallowed.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <link href="../../css/menucss.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>adminmenu</title>
        
    </head>
    <body>
        <h1></h1>
        
        
        <nav> <ul>
           
                <c:if test="${not empty sessionScope.username}"> 
                    
                            <li><a href="map.jsp">Map</a></li>
                            <li><a href="users.jsp">Users</a></li>
                    
                    <li><form action="logout.action" method="post" >
                            <input type="hidden" value="Logout" >
                            <A HREF="../../logout.action">Logout</A>
                        </form>
                    </li>

                </c:if> 


            </ul>

        </nav>
        
    </body>
</html>
