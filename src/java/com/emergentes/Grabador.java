/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.emergentes;

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
@WebServlet(name = "Grabador", urlPatterns = {"/Grabador"})
public class Grabador extends HttpServlet {

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

        PrintWriter out = response.getWriter();

        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telefono");
        String correo = request.getParameter("correo");

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Servlet Listador</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Datos recibidos</h1>");
        out.println("<br>" + nombre);
        out.println("<br>" + telefono);
        out.println("<br>" + correo);

        try {
            // Driver
            String driver = "com.mysql.jdbc.Driver";
            // Canal de conexion a la base de datos
            String canal = "jdbc:mysql://localhost:3306/bd_agenda";
            // Especificar usuario autorizado
            String usuario = "root";
            // Especificar el password
            String password = "";

            // Leer el driver para MySQL
            Class.forName(driver);
            // Crear un canal de conexion
            Connection con = DriverManager.getConnection(canal, usuario, password);
            // Crear una sentencia
            Statement sentencia = con.createStatement();
            // 
            String sql = "insert into contacto (nombre,telefono,correo) values('" + nombre + "','" + telefono + "','" + correo + "')";
            // Ejecutar la sentencia
            int res = sentencia.executeUpdate(sql);

            if (res != 0) {
                out.println("<h3>Actualización exitosa<h3>");
            } else {
                out.println("<h3>Errores en la inserción</h3>");
            }

            sentencia.close();
            con.close();

            out.println("<p><a href='index.jsp'>Volver</a>");

        } catch (ClassNotFoundException e) {
            out.println("Error en driver " + e.getMessage());
        } catch (SQLException e) {
            out.println("SQLException " + e.getMessage());
        }
        out.println("</body>");
        out.println("</html>");



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
