/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import App.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;

/**
 *
 * @author Nowll
 */
public class LoginDAO extends HttpServlet {

    private Connection establishConnection;

    public LoginDAO() throws SQLException, InstantiationException, IllegalAccessException {
        establishConnection = EstablishConnection.establishConnection();
    }

    /**
     *
     * @param login
     * @param senha
     * @return
     * @throws SQLException
     */
    public Funcionario getCredentials(String login, String senha) throws SQLException {
       // Statement stmt = establishConnection.createStatement();
        //String SQL = "SELECT cpf, senha FROM funcionarios  WHERE cpf = ?  and senha = ?";
        //167.740.300-41
        Funcionario current = new Funcionario();
        current.setId(0);
        PreparedStatement pstmt = establishConnection.prepareStatement("SELECT * FROM funcionarios WHERE cpf = ? AND senha = ? limit 1");
        pstmt.setString(1, login);
        pstmt.setString(2, senha);
        ResultSet rs = pstmt.executeQuery();
        
        if (rs.next()){
            current.setId(rs.getInt("id"));
            current.setNome(rs.getString("nome"));
            current.setCpf(rs.getString("cpf"));
            current.setPapel(rs.getInt("papel"));
        }
        return current;
    }
    
    
}
