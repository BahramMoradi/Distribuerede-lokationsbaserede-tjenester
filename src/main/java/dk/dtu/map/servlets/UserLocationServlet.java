/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.dtu.map.servlets;

import dk.dtu.locationservice.dto.UserLocations;
import dk.dtu.locationservice.dto.GeoJson;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import dk.dtu.locationservice.dao.DaoFactory;
import dk.dtu.locationservice.dao.LocationDaoImpl;
import dk.dtu.locationservice.dao.UserDaoImpl;
import dk.dtu.locationservice.dto.Location;
import dk.dtu.locationservice.dto.User;
import dk.dtu.locationservice.idao.ILocationDao;
import dk.dtu.locationservice.idao.IUserDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Thiis class is created for fetching location data in jsp page 
 *  an Ajax method calls this servelt and put the table in the web page
 * @author Bahram Moradi
 */
public class UserLocationServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String userUid=request.getParameter("uid");
        ILocationDao ld=new LocationDaoImpl();
        
        try{
            long uid=Long.valueOf(userUid);
            List<Location> locations=ld.getUserLocation(uid);     
        }catch(Exception e){}
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet getUsrLocations</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet getUsrLocations at " + request.getContextPath() + "</h1>");
            out.println("UID"+request.getParameter("uid"));
            out.println("</body>");
            out.println("</html>");
        }
    }
    public void getUserLocationsHtml(long uid, HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        ILocationDao ld=new LocationDaoImpl();
        
        try{
            
            List<Location> locations=ld.getUserLocation(uid);
            System.err.println("Servlet: size of array : "+locations.size());
            DateFormat format=new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
           
            //List<Location> locations=new ArrayList<Location>();
            //llocations.add(new Location(1,150.644,-34.397));
               try (PrintWriter out = response.getWriter()) {
                   out.println(" <table id=\"latlon\" >");
                    out.println("<tr>");
                    out.println("<th>Date</th>");
                       out.println("<th>Latitude</th>");
                       out.println("<th>Longitude</th>");
                     out.println("</tr>");
                   for(Location location:locations){
                       out.println("<tr>");
                       out.println("<td>"+format.format(new Date(location.getTime()))+"</td>");
                       out.println("<td>"+location.getLatitude()+"</td>");
                       out.println("<td>"+location.getLongitude()+"</td>");
                       out.println("</tr>");
                   }
                    out.println("</table>");
               }
            
            
            
            
            
        }catch(Exception e){}
        
        
    
    }
    public void getAllLocationJson(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        ILocationDao locationDao=DaoFactory.getLocationDao();
        IUserDao userDao=DaoFactory.getUserDao();
        GeoJson geoJson=new GeoJson();
        List<UserLocations> allUsersAndLocations=new ArrayList<>();
        
        try {
            List<User> users=userDao.getAllUser();
            for(User user:users){
            List<Location> locations=locationDao.getUserLocation(user.getUid());
            UserLocations ul=new UserLocations(user,locations);
            allUsersAndLocations.add(ul);
            }
            geoJson.setAllUsersAndLocations(allUsersAndLocations);
        } catch (Exception ex) {
            Logger.getLogger(UserLocationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        Gson gson=new Gson();
        try (PrintWriter out = response.getWriter()) {
           String json=gson.toJson(geoJson);
            System.err.println("GeoJson : "+json);
           out.println(json);
          }
    }
    
    
    
    public void getUserLocationsJson(long uid,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        ILocationDao locationDao=DaoFactory.getLocationDao();
        IUserDao userDao=DaoFactory.getUserDao();
        UserLocations userLocation=null;
        
       
        
        try {
            User user=userDao.getUserById(uid);
            List<Location> locations=locationDao.getUserLocation(user.getUid());
            userLocation=new UserLocations(user,locations);
            
        } catch (Exception ex) {
            Logger.getLogger(UserLocationServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        Gson gson=new Gson();
        try (PrintWriter out = response.getWriter()) {
           if(userLocation!=null){ 
            String json=gson.toJson(userLocation);
               System.err.println("User location : "+json);
            out.println(json);
          }
        }
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userUid=request.getParameter("uid");
        String format=request.getParameter("format");
        if(userUid!=null&&format!=null){
            if(format.equalsIgnoreCase("html")){
                System.err.println("Format HTML");
                getUserLocationsHtml(Long.valueOf(userUid),request, response);
            }else{
                System.err.println("Format JSON");
                getUserLocationsJson(Long.valueOf(userUid),request, response);
            }
        }else{
            getAllLocationJson(request,response);
        }
        
       
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }
    
}
