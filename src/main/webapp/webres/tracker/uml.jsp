<%-- 
    Document   : tracker
    Created on : 25-02-2016, 21:30:47
    Author     : Bahram
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>uml</title>
        <link href="../../css/menucss.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/appcss.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            function myfunc(id){
                if(id==='ui'){
                document.getElementById("img").src="../image/uml/tracker_ui.PNG";}
                if(id==='trcl'){
                     document.getElementById("img").src="../image/uml/tracker_class.jpg";
                }
            }
            
        </script>
        
       
    </head>
    <body>
        <nav>
            <ul>
                <li id='ui' onmouseover="myfunc(this.id)">
                    <a  href="">UI Class Diagram</a>
                    
                </li>
                <li id='trcl' onmouseover="myfunc(this.id)">
                    <a id="c" href="" >Class Diagram</a>
                </li>
                <li>
                    <a href="../../index.jsp" >Home</a>
                </li>
                
                
                
                
            </ul>
        </nav>
        <img class="roundCorner" id="img" src="" alt="">
        
         
    </body>
</html>
