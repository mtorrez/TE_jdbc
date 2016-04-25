/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

/**
 *
 * @author Mario Torrez
 */
@WebServlet(name = "Listador", urlPatterns = {"/Listador"})
public class Listador extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            // Driver
            String driver = "com.mysql.jdbc.Driver";
            // Canal de conexion a la base de datos
            String canal = "jdbc:mysql://localhost:3306/bd_agenda";
            // Especificar usuario autorizado
            String usuario = "root";
            // Especificar el password
            String password = "";
            
            try {
                // Leer el driver para MySQL
               Class.forName(driver);
               // Crear un canal de conexion
               Connection con = DriverManager.getConnection(canal, usuario, password);
               // Variable para crear una sentencia
               Statement sentencia = con.createStatement();
               // Consulta
               String sql = "select * from contacto";   
                // Definimos una variable para la sentencia
               ResultSet rs = sentencia.executeQuery(sql);
          
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Listador</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Listado de datos</h1>");
                out.println("<table border='1'>");
                out.println("<tr>");
                out.println("<td>Nombre</td>");
                out.println("<td>Telefono</td>");
                out.println("<td>Correo</td>");
                out.println("</tr>");
                
                while ( rs.next()){
                   out.println("<tr>"); 
                   out.println("<td>"+rs.getString("nombre")+"</td>");
                   out.println("<td>"+rs.getString("telefono")+"</td>");
                   out.println("<td>"+rs.getString("correo")+"</td>");
                   out.println("</tr>");
                }
                
                out.println("</table>");
                out.println("</body>");
                out.println("</html>");
               
            }catch(ClassNotFoundException e){
                out.println("Error en driver " + e.getMessage());
            } catch(SQLException e){
                out.println("SQLException " +e.getMessage());
            } 

        } finally {            
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
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
}
