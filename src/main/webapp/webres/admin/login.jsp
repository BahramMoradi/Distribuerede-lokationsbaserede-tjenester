<%-- 
    Document   : login
    Created on : 19-02-2016, 23:18:09
    Author     : Bahram
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
        <script>
            function errorMSG(){
                //alert('error');
                 document.getElementById("outher-div").style.border = "thick solid #FF0000";
                  document.getElementById("error-div").style.visibility = "visible";
            }
        </script>
        <style>
            
            th{
                width: 100px;
            }
            td{
                width:100px;
            }
            .input{
                
                width: 70px;
            }
            
         body{ 
                text-align: center;
                background: #f0f9f9
            }
            #outher-div{
                padding-left: 5%;
                
                padding-top: 50px;
                margin:auto;
                margin-top: 15%;
                width: 400px;
                height: 200px;
                border-radius: 50px;
                background: #aaaaaa;
            }
            #error-div{
                visibility: hidden;
                margin-top: 20px;
                padding-top:20px;
                border-radius: 30px;
                width: 300px;
                height: 50px;
                font-size: 18px;
                background-color: red;
            }
          
        </style>
    </head>
    <body>
        <% 
            String login=request.getHeader("login");
            HttpSession ss=request.getSession();
            if(ss!=null){
               String log= (String)ss.getAttribute("login");
               if(log!=null&&log.equals("failed")){
              // out.print("failed.................................");
               }
            }
         
 
        %>
        
        <form action="../../login.action" method="POST">
            <div id="outher-div">
            <table>
                <thead>
                <th colspan="3">Login</th>
                </thead>
                <tbody>
                    <tr>
                        <td>User name </td><td colspan="2"><input type="text" name="username" value="" /></td>
                    </tr>
                     <tr>
                         <td>Password </td><td colspan="2"><input type="password" name="password" value="" /></td>
                    </tr>
                     <tr>
                         <td></td><td><input class="input" type="submit" value="Login" name="ok"  /></td><td><input class="input" type="reset" value="Rest" name="reset" /></td>
                    </tr>
                    
                </tbody>
            </table>
                <div id="error-div">Either user name or password is wrong.</div>
                </div>
        </form>
        
        
        
        <c:if test="${not empty sessionScope.login and sessionScope.login eq 'failed' }">
            <script>
               errorMSG(); 
            </script>
        </c:if>
       
    </body>
</html>
