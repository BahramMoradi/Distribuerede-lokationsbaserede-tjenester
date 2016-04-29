

<%-- 
    Document/* global google */

   : trackermap
    Created on : 09-02-2016, 17:19:08
    Author     : Bahram
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@ page import="dk.dtu.map.servlets.*" %>
<%@ page import="dk.dtu.locationservice.dto.*" %>

<%
    if (request.getSession().getAttribute("username") == null) {
        response.sendRedirect("../notallowed.jsp");
        return;
    }
    Controller controller = new Controller();
    List<User> users = controller.getAllUsers();
    request.setAttribute("allUsers", users);
    //response.setIntHeader("Refresh", 5);
%>
<!DOCTYPE html>
<html>
    <head>
        <title>map</title>
        <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script async defer
                src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCxrhZrU7Qv2GZJAIQtaSI_6f2Z1JvFqNc&callback=initMap">

        </script>
        <script src="../../js/application.js"></script>
        <style type="text/css">
            html, body { height: 100%; margin: 0; padding: 0; }
            #map {
                height: 100%; 
                width: 100%; 
                position:absolute; 
                top: 0; 
                left: 0; 
                z-index: 0; 
            }
            table{
                margin: auto;
                border: 1px solid;
                padding-left:20px;
            }
            #draggable  {
                z-index: 1;
                margin: auto;
                margin-top:10px;
                width: 500px;
                padding: 10px;
                position: relative; 
                z-index: 1; 
                background: black;
                height: auto;
                opacity: .85; 
                color: white;
                //cursor:move;
            }
            table td{
                width: 200px;
                padding-left:50px ; 
            }

        </style>

        <script>
            $(function () {
                $("#draggable").draggable();
            });
        </script>
    </head>
    <body>

        <div class="ui-widget-content" id="draggable">
            <table>
                <tr><td><h3>All users Location</h3></td><td> <h3>User Location</h3></td></tr>
                <tr>
                    <td>Marker  <INPUT id="markerAll" TYPE="checkbox" NAME="radios" VALUE="markerAll" onchange="showAllGeo()"/> </td>
                    <td>Marker  <INPUT id="marker" TYPE="checkbox"  VALUE="marker"  onchange="showPerUser()"/></td>
                </tr>
                <tr>
                    <td>Line     <INPUT id="lineAll" TYPE="checkbox" NAME="radios" VALUE="lineAll" onchange="showAllGeo()"/></td>
                    <td>Line     <INPUT id="line" TYPE="checkbox" VALUE="line" onchange="showPerUser()"/><br></td>
                </tr>
                <tr><td></td><td>
                        <select id="selectList"  onchange="selectChange(this.value)">
                            <option value="">Select a user</option>
                            <c:forEach items="${allUsers}" var="user" >
                                <option value="${user.uid}">${user.mail}</option>
                            </c:forEach>
                        </select> 
                    </td>
                </tr>


            </table>
        </div>




        <div id="map">
        </div>



        
        


    </body>
</html>
