/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import App.Funcionario;
import static Model.EstablishConnection.establishConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nowll
 */
@WebServlet(name = "FuncionarioDAO", urlPatterns = {"/FuncionarioDAO"})
public class FuncionarioDAO extends HttpServlet {

    private Connection establishConnection;

    public FuncionarioDAO() throws SQLException, InstantiationException, IllegalAccessException {
        establishConnection = EstablishConnection.establishConnection();
    }

    public ArrayList<Funcionario> getAllEmployees() throws SQLException {

        ArrayList<Funcionario> employeeList = new ArrayList<>();
        Statement stmt = establishConnection.createStatement();

        ResultSet rs = stmt.executeQuery("select * from funcionarios");

        while (rs.next()) {
            Funcionario current = new Funcionario();
            current.setId(rs.getInt("id"));
            current.setNome(rs.getString("nome"));
            current.setCpf(rs.getString("cpf"));
            current.setSenha(rs.getInt("senha"));
            current.setPapel(rs.getInt("papel"));
            employeeList.add(current);
        }
        return employeeList;
    }
    
    public ArrayList<Funcionario> getAllADM() throws SQLException {

        ArrayList<Funcionario> employeeList = new ArrayList<>();
        Statement stmt = establishConnection.createStatement();

        ResultSet rs = stmt.executeQuery("select * from funcionarios WHERE papel=0");

        while (rs.next()) {
            Funcionario current = new Funcionario();
            current.setId(rs.getInt("id"));
            current.setNome(rs.getString("nome"));
            current.setCpf(rs.getString("cpf"));
            current.setSenha(rs.getInt("senha"));
            current.setPapel(rs.getInt("papel"));
            employeeList.add(current);
        }
        return employeeList;
    }
    
    public ArrayList<Funcionario> getAllVen() throws SQLException {

        ArrayList<Funcionario> employeeList = new ArrayList<>();
        Statement stmt = establishConnection.createStatement();

        ResultSet rs = stmt.executeQuery("select * from funcionarios WHERE papel=1");

        while (rs.next()) {
            Funcionario current = new Funcionario();
            current.setId(rs.getInt("id"));
            current.setNome(rs.getString("nome"));
            current.setCpf(rs.getString("cpf"));
            current.setSenha(rs.getInt("senha"));
            current.setPapel(rs.getInt("papel"));
            employeeList.add(current);
        }
        return employeeList;
    }
    
    public ArrayList<Funcionario> getAllCom() throws SQLException {

        ArrayList<Funcionario> employeeList = new ArrayList<>();
        Statement stmt = establishConnection.createStatement();

        ResultSet rs = stmt.executeQuery("select * from funcionarios WHERE papel=2");

        while (rs.next()) {
            Funcionario current = new Funcionario();
            current.setId(rs.getInt("id"));
            current.setNome(rs.getString("nome"));
            current.setCpf(rs.getString("cpf"));
            current.setSenha(rs.getInt("senha"));
            current.setPapel(rs.getInt("papel"));
            employeeList.add(current);
        }
        return employeeList;
    }

    public Funcionario getEmployeeById(int id) {
        Funcionario current = new Funcionario();
        try {
            PreparedStatement pstmt = establishConnection.prepareStatement("select * from funcionarios WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                current.setId(rs.getInt("id"));
                current.setNome(rs.getString("nome"));
                current.setCpf(rs.getString("cpf"));
                current.setSenha(rs.getInt("senha"));
                current.setPapel(rs.getInt("papel"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return current;
    }

    public boolean removeEmployee(int id) {
        try {
            String sql = "DELETE FROM funcionarios WHERE id = ?";
            PreparedStatement pstmt = establishConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }
    
    public boolean addEmployee(Funcionario employee) {
        String sql = "INSERT INTO funcionarios (nome, cpf, senha, papel) VALUES (?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setString(1, employee.getNome());
            pstmt.setString(2, employee.getCpf());
            pstmt.setInt(3, employee.getSenha());
            pstmt.setInt(4, employee.getPapel());
           

            System.out.print(pstmt);
            pstmt.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    
    public boolean updateEmployee(Funcionario employee) {
        String sql = "UPDATE funcionarios SET nome=?, cpf=?, senha=?, papel=? WHERE id=?";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setString(1, employee.getNome());
            pstmt.setString(2, employee.getCpf());
            pstmt.setInt(3, employee.getSenha());
            pstmt.setInt(4, employee.getPapel());
            pstmt.setInt(5, employee.getId());
           

            System.out.print(pstmt);
            pstmt.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
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
