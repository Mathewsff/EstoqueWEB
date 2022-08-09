/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import App.Cliente;
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
@WebServlet(name = "ClienteDAO", urlPatterns = {"/ClienteDAO"})
public class ClienteDAO extends HttpServlet {

    Connection establishConnection;

    public ClienteDAO() throws InstantiationException, IllegalAccessException {
        try {
            establishConnection = EstablishConnection.establishConnection();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Cliente> getAllClients() {
        ArrayList<Cliente> clientList = new ArrayList<>();
        try {
            Statement stmt = establishConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("select * from clientes");
            while (rs.next()) {
                Cliente client = new Cliente();
                client.setId(rs.getInt("id"));
                client.setNome(rs.getString("nome"));
                client.setCpf(rs.getString("cpf"));
                client.setEndereco(rs.getString("endereco"));
                client.setBairro(rs.getString("bairro"));
                client.setCidade(rs.getString("cidade"));
                client.setUf(rs.getString("uf"));
                client.setCep(rs.getString("cep"));
                client.setTelefone(rs.getString("telefone"));
                client.setEmail(rs.getString("email"));
                clientList.add(client);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientList;
    }

    public Cliente getClientById(int id) {
        Cliente client = new Cliente();
        try {
            PreparedStatement pstmt = establishConnection.prepareStatement("select * from clientes WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                client.setId(rs.getInt("id"));
                client.setNome(rs.getString("nome"));
                client.setCpf(rs.getString("cpf"));
                client.setEndereco(rs.getString("endereco"));
                client.setBairro(rs.getString("bairro"));
                client.setCidade(rs.getString("cidade"));
                client.setUf(rs.getString("uf"));
                client.setCep(rs.getString("cep"));
                client.setTelefone(rs.getString("telefone"));
                client.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return client;
    }

    public boolean addCliente(Cliente client) {
        String sql = "INSERT INTO clientes (nome, cpf, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setString(1, client.getNome());
            pstmt.setString(2, client.getCpf());
            pstmt.setString(3, client.getEndereco());
            pstmt.setString(4, client.getBairro());
            pstmt.setString(5, client.getCidade());
            pstmt.setString(6, client.getUf());
            pstmt.setString(7, client.getCep());
            pstmt.setString(8, client.getTelefone());
            pstmt.setString(9, client.getEmail());

            System.out.print(pstmt);
            pstmt.execute();

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean updateCliente(Cliente client) {
        String sql = "UPDATE clientes SET nome=?, cpf=?, endereco=?, bairro=?, cidade=?, uf=?, cep=?, telefone=?, email=? WHERE id=?";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setString(1, client.getNome());
            pstmt.setString(2, client.getCpf());
            pstmt.setString(3, client.getEndereco());
            pstmt.setString(4, client.getBairro());
            pstmt.setString(5, client.getCidade());
            pstmt.setString(6, client.getUf());
            pstmt.setString(7, client.getCep());
            pstmt.setString(8, client.getTelefone());
            pstmt.setString(9, client.getEmail());
            pstmt.setInt(10, client.getId());
            pstmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean removeCliente(int id) {
        try {
            String sql = "DELETE FROM clientes WHERE id = ?";
            PreparedStatement pstmt = establishConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

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
        processRequest(request, response);
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

}
