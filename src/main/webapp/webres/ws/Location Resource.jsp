<%-- 
    Document   : Location Resource
    Created on : 04-12-2015, 15:18:17
    Author     : Bahram
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <body>
        <script src="https://google-code-prettify.googlecode.com/svn/loader/run_prettify.js"></script>
        <h1>Location Resource</h1>
        <p>
        <h4>LocationResource webservice support following methods:</h4>
        <ul>
            <li>
                <h5>Find last known location of user:</h5>
                <ul>
                    <li>
                        Application sends user mail&nbsp; as path parameter to the Location Resource.
                    </li>
                    <li>
                        Location Resource find users last known location from
                        database and send it back to the application<br>
                    </li>
        </ul>
                <code class="prettyprint">
                    <pre class="prettyprint linenums">
                    @GET<br>
                    @Path("/{mail}")<br>
                    @Produces(MediaType.APPLICATION_JSON)<br>
                    public Location getUsersLastLocation(@PathParam("mail") String mail){<br>
                        print("getUsersLastLocation : "+mail);<br>
                    return new Location(1255353L,123.12334,234.989987);<br>
                    }
                    </pre>
   
                </code>
            </li>
            <li>
                <h5>Update user location:</h5>
                <ul>
                    <li>Application sends user location by using post method <br></li></ul>
            </li>
            <li>
                <h5>Find last known location( current) of user or users:</h5>
                <ul>
                    <li>Application sends a list of users to the Location Resource</li>
                    <li>Location Resource find the location of each user and sends a list of wrapped User - Location back to the application<br></li>
                </ul>
            </li>
            <li>
                <h5>Find all users in radius R from current users last known location:</h5>
                <ul><li>Application sends the username ( mail or phone nr.) and Radius in km&nbsp; as path parameter.</li>
                <li>Location Resource find all user in that Radius and send a list of User-Location to the application.<br></li>
            </ul>
            </li>
            <li>
                <h5>Find all users in radius R from a given location:</h5>
                <ul><li>User chose a&nbsp; point on the map by clicking on it&nbsp; and define a Radius .</li>
                <li>Application sends this information to the Location Resource.</li>
                <li>Location Resource find all user in that Radius and send it back to the application.<br></li>
                </ul>
            </li>
        </ul>
    </p>
    
</body>
</html>
