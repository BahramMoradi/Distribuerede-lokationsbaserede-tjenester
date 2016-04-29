<%-- 
    Document   : screen
    Created on : 25-02-2016, 22:38:56
    Author     : Bahram
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>screen shot</title>
        <link href="../../css/appcss.css" rel="stylesheet" type="text/css"/>
        <link href="../../css/verticalmenu.css" rel="stylesheet" type="text/css"/>
        <script type="text/javascript">
            function imageSwitcher(id) {
                switch (id) {
                    case 'splash':
                        document.getElementById("img").src = "../image/app/splash.png";
                        break;
                    case 'profile':
                        document.getElementById("img").src = "../image/app/profile.png";
                        break;
                    case 'home':
                        document.getElementById("img").src = "../image/app/record.png";
                        break;
                    case 'mylocation':
                        document.getElementById("img").src = "../image/app/mylocation.png";
                        break;
                    case 'recordh':
                        document.getElementById("img").src = "../image/app/recordh.png";
                        break;
                    case 'help':
                        document.getElementById("img").src = "../image/app/help.png";
                        break;
                    default:
                        break;


                }

            }

        </script>

    </head>
    <body>
        <h1>Screen shots</h1>
        <nav>
            <ul>
                <li> <a href="../../index.jsp">Home</a></li>
                <li  id="splash" onmouseover="imageSwitcher(this.id)"> <a href="">Splash</a></li>
                <li  id="profile" onmouseover="imageSwitcher(this.id)"><a href="">Profile</a></li>
                <li  id="home" onmouseover="imageSwitcher(this.id)"><a href="">Home</a></li>
                <li  id="mylocation" onmouseover="imageSwitcher(this.id)"><a href="">My Location</a></li>
                <li  id="recordh" onmouseover="imageSwitcher(this.id)"><a href="">Record History</a></li>
                <li  id="help" onmouseover="imageSwitcher(this.id)"><a href="">Help</a></li>

            </ul>
        </nav>


        <img id="img" src="" alt="" style="width:30%; height:30%; "/>
    </body>
</html>
