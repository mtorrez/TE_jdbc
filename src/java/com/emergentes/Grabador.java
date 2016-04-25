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
        
        out.println("Datos recibidos");
        out.println(nombre);
        out.println(telefono);
        out.println(correo);
        
        try{
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
            String sql = "insert into contacto (nombre,telefono,correo) values('"+nombre+"','"+telefono+"','"+correo+"')";
            // Ejecutar la sentencia
            int res = sentencia.executeUpdate(sql);
            
            if (res != 0) out.println("Actualización exitosa");
            else out.println("Errores en la inserción");
            
            sentencia.close();
            con.close();
           
        } catch(ClassNotFoundException e){
            out.println("Error en driver " + e.getMessage());
        } catch(SQLException e){
            out.println("SQLException " +e.getMessage());
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
