/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import App.Compra;
import java.io.IOException;
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
@WebServlet(name = "CompraDAO", urlPatterns = {"/CompraDAO"})
public class CompraDAO extends HttpServlet {

    Connection establishConnection;
    
   public CompraDAO() throws InstantiationException, IllegalAccessException{
        try {
            establishConnection = EstablishConnection.establishConnection();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public ArrayList<Compra> getAllPurchases() {
        ArrayList<Compra> purchaseList = new ArrayList<>();
        try {
            Statement stmt = establishConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("select * from compras");
            while (rs.next()) {
                Compra purchase = new Compra();
                purchase.setId(rs.getInt("id"));
                purchase.setQuantidade_compra(rs.getInt("quantidade_compra"));
                purchase.setData_compra(rs.getString("data_compra"));
                purchase.setValor_compra(rs.getFloat("valor_compra"));
                purchase.setId_fornecedor(rs.getInt("id_fornecedor"));
                purchase.setId_produto(rs.getInt("id_produto"));
                purchase.setId_funcionario(rs.getInt("id_funcionario"));
                purchaseList.add(purchase);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return purchaseList;
    }
    
    public Compra getPurchaseById(int id) {
        Compra purchase = new Compra();
        try {
            PreparedStatement pstmt = establishConnection.prepareStatement("select * from compras WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                purchase.setId(rs.getInt("id"));
                purchase.setQuantidade_compra(rs.getInt("quantidade_compra"));
                purchase.setData_compra(rs.getString("data_compra"));
                purchase.setValor_compra(rs.getFloat("valor_compra"));
                purchase.setId_fornecedor(rs.getInt("id_fornecedor"));
                purchase.setId_produto(rs.getInt("id_produto"));
                purchase.setId_funcionario(rs.getInt("id_funcionario"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return purchase;
    }
    
    public ArrayList<Compra> getPurchasesOfUser(int id) {
        ArrayList<Compra> purchaseList = new ArrayList<>();
        try {
            PreparedStatement pstmt = establishConnection.prepareStatement("select * from compras WHERE id_funcionario = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Compra purchase = new Compra();
                purchase.setId(rs.getInt("id"));
                purchase.setQuantidade_compra(rs.getInt("quantidade_compra"));
                purchase.setData_compra(rs.getString("data_compra"));
                purchase.setValor_compra(rs.getFloat("valor_compra"));
                purchase.setId_fornecedor(rs.getInt("id_fornecedor"));
                purchase.setId_produto(rs.getInt("id_produto"));
                purchase.setId_funcionario(rs.getInt("id_funcionario"));
                purchaseList.add(purchase);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return purchaseList;
    }
    
    public boolean addCompra(Compra purchase) {
        String sql = "INSERT INTO compras (quantidade_compra, data_compra, valor_compra, id_fornecedor, id_produto, id_funcionario) VALUES (?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setInt(1, purchase.getQuantidade_compra());
            pstmt.setString(2, purchase.getData_compra());
            pstmt.setFloat(3, purchase.getValor_compra());
            pstmt.setInt(4, purchase.getId_fornecedor());
            pstmt.setInt(5, purchase.getId_produto());
            pstmt.setInt(6, purchase.getId_funcionario());
            pstmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean updateCompra(Compra purchase) {
        String sql = "UPDATE compras SET quantidade_compra=?, data_compra=?, valor_compra=?, id_fornecedor=?, id_produto=?, id_funcionario=? WHERE id=?";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setInt(1, purchase.getQuantidade_compra());
            pstmt.setString(2, purchase.getData_compra());
            pstmt.setFloat(3, purchase.getValor_compra());
            pstmt.setInt(4, purchase.getId_fornecedor());
            pstmt.setInt(5, purchase.getId_produto());
            pstmt.setInt(6, purchase.getId_funcionario());
            pstmt.setInt(7, purchase.getId());
            pstmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
     public boolean removeCompra(int id) {
        try {
            String sql = "DELETE FROM compras WHERE id = ?";
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
