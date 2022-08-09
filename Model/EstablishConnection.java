/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

/**
 *jdbc:mysql://localhost:3306/?user=root
 * @author Nowll
 */
@WebServlet(name = "Conexão", urlPatterns = {"/Conexão"})
public class EstablishConnection extends HttpServlet {

    private static Connection establishConnection = null;
    
    public static Connection establishConnection() throws SQLException, InstantiationException, IllegalAccessException{
        if (establishConnection == null){
            try{
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                establishConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/estoque", "root", "");
                
            } catch(ClassNotFoundException e){
            }
        }
        return establishConnection;
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

