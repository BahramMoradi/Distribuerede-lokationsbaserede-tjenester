
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>restricted</title>
        <style>
            body{ 
                text-align: center;
            }
            #box{
               margin:auto;
               margin-top: 15%;
                width: 600px;
                height: 285px;
                border: 1px solid;
                border-radius: 50px;
                background: black;
            }
            h1{
                  font-size: 46px;
            }
            #body-text{
                margin-bottom: 0px;
                border-bottom-left-radius: 50px;
                border-bottom-right-radius: 50px;
                height: 170px;
                width: 600px;
                background:#ffffff;
                
             
            }
            p{ 
                  padding-top: 50px;
                  font-size: 22px;
                    
            }
        </style>
    </head>
    <body>
        <div id='box'>
            <div><h1 style="color:red">ACCESS DENIED !</h1></div>
        <div id='body-text'>
        <p>
            Accessing requested page is only allowed for adminstration. <br>
            Please login from the home page.
            
        </p>
        </div>
        </div>
    </body>
</html>
