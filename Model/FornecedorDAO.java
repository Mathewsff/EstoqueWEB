/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import App.Cliente;
import App.Fornecedor;
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
@WebServlet(name = "FornecedorDAO", urlPatterns = {"/FornecedorDAO"})
public class FornecedorDAO extends HttpServlet {

    Connection establishConnection;
    
    public FornecedorDAO() throws InstantiationException, IllegalAccessException{
        try {
            establishConnection = EstablishConnection.establishConnection();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public ArrayList<Fornecedor> getAllSuppliers() {
        ArrayList<Fornecedor> supplierList = new ArrayList<>();
        try {
            Statement stmt = establishConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("select * from fornecedores");
            while (rs.next()) {
                Fornecedor supplier = new Fornecedor();
                supplier.setId(rs.getInt("id"));
                supplier.setRazao_social(rs.getString("razao_social"));
                supplier.setCnpj(rs.getString("cnpj"));
                supplier.setEndereco(rs.getString("endereco"));
                supplier.setBairro(rs.getString("bairro"));
                supplier.setCidade(rs.getString("cidade"));
                supplier.setUf(rs.getString("uf"));
                supplier.setCep(rs.getString("cep"));
                supplier.setTelefone(rs.getString("telefone"));
                supplier.setEmail(rs.getString("email"));
                supplierList.add(supplier);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return supplierList;
    }
    
     public Fornecedor getSupplierById(int id) {
        Fornecedor supplier = new Fornecedor();
        try {
            PreparedStatement pstmt = establishConnection.prepareStatement("select * from fornecedores WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                supplier.setId(rs.getInt("id"));
                supplier.setRazao_social(rs.getString("razao_social"));
                supplier.setCnpj(rs.getString("cnpj"));
                supplier.setEndereco(rs.getString("endereco"));
                supplier.setBairro(rs.getString("bairro"));
                supplier.setCidade(rs.getString("cidade"));
                supplier.setUf(rs.getString("uf"));
                supplier.setCep(rs.getString("cep"));
                supplier.setTelefone(rs.getString("telefone"));
                supplier.setEmail(rs.getString("email"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return supplier;
    }
     
     public boolean addFornecedor(Fornecedor supplier) {
        String sql = "INSERT INTO fornecedores (razao_social, cnpj, endereco, bairro, cidade, uf, cep, telefone, email) VALUES (?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setString(1, supplier.getRazao_social());
            pstmt.setString(2, supplier.getCnpj());
            pstmt.setString(3, supplier.getEndereco());
            pstmt.setString(4, supplier.getBairro());
            pstmt.setString(5, supplier.getCidade());
            pstmt.setString(6, supplier.getUf());
            pstmt.setString(7, supplier.getCep());
            pstmt.setString(8, supplier.getTelefone());
            pstmt.setString(9, supplier.getEmail());
            pstmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
     public boolean updateSupplier(Fornecedor supplier) {
        String sql = "UPDATE fornecedores SET razao_social=?, cnpj=?, endereco=?, bairro=?, cidade=?, uf=?, cep=?, telefone=?, email=? WHERE id=?";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setString(1, supplier.getRazao_social());
            pstmt.setString(2, supplier.getCnpj());
            pstmt.setString(3, supplier.getEndereco());
            pstmt.setString(4, supplier.getBairro());
            pstmt.setString(5, supplier.getCidade());
            pstmt.setString(6, supplier.getUf());
            pstmt.setString(7, supplier.getCep());
            pstmt.setString(8, supplier.getTelefone());
            pstmt.setString(9, supplier.getEmail());
            pstmt.setInt(10, supplier.getId());
            pstmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
     
    public boolean removeSupplier(int id) {
        try {
            String sql = "DELETE FROM fornecedores WHERE id = ?";
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
