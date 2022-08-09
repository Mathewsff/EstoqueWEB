/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import App.Produto;
import App.Venda;
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
@WebServlet(name = "VendaDAO", urlPatterns = {"/VendaDAO"})
public class VendaDAO extends HttpServlet {

    private Connection establishConnection;
    
    public VendaDAO() throws InstantiationException, IllegalAccessException{
        try {
            establishConnection = EstablishConnection.establishConnection();
        } catch (SQLException ex) {
            Logger.getLogger(VendaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Venda> getAllSales() {
        ArrayList<Venda> saleList = new ArrayList<>();
        try {
            Statement stmt = establishConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("select * from vendas");
            while (rs.next()) {
                Venda sale = new Venda();
                sale.setId(rs.getInt("id"));
                sale.setQuantidade_venda(rs.getInt("quantidade_venda"));
                sale.setData(rs.getString("data_venda"));
                sale.setValor_venda(rs.getFloat("valor_venda"));
                sale.setId_cliente(rs.getInt("id_cliente"));
                sale.setId_produto(rs.getInt("id_produto"));
                sale.setId_funcionario(rs.getInt("id_funcionario"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return saleList;
    }
    
     public ArrayList<Venda> generateReport() {
        ArrayList<Venda> saleList = new ArrayList<>();
        try {
            Statement stmt = establishConnection.createStatement();
            ResultSet rs;
            rs = stmt.executeQuery("SELECT data_venda, SUM(valor_venda), SUM(quantidade_venda)  FROM estoque.vendas group by data_venda");
            while (rs.next()) {
                Venda sale = new Venda();
                
                sale.setQuantidade_venda(rs.getInt("SUM(quantidade_venda)"));
                sale.setData(rs.getString("data_venda"));
                sale.setValor_venda(rs.getFloat("SUM(valor_venda)"));
                
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return saleList;
    }
    
    public Venda getSaleById(int id) {
        Venda sale = new Venda();
        try {
            PreparedStatement pstmt = establishConnection.prepareStatement("select * from vendas WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                
                sale.setId(rs.getInt("id"));
                sale.setQuantidade_venda(rs.getInt("quantidade_venda"));
                sale.setData(rs.getString("data_venda"));
                sale.setValor_venda(rs.getFloat("valor_venda"));
                 sale.setId_cliente(rs.getInt("id_cliente"));
                sale.setId_produto(rs.getInt("id_produto"));
                sale.setId_funcionario(rs.getInt("id_funcionario"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return sale;
    }
    
    public ArrayList<Venda> getSalesOfUser(int id) {
        ArrayList<Venda> saleList = new ArrayList<>();
        try {
            PreparedStatement pstmt = establishConnection.prepareStatement("select * from vendas WHERE id_funcionario = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Venda sale = new Venda();
                sale.setId(rs.getInt("id"));
                sale.setQuantidade_venda(rs.getInt("quantidade_venda"));
                sale.setData(rs.getString("data_venda"));
                sale.setValor_venda(rs.getFloat("valor_venda"));
                sale.setId_cliente(rs.getInt("id_cliente"));
                sale.setId_produto(rs.getInt("id_produto"));
                sale.setId_funcionario(rs.getInt("id_funcionario"));
                saleList.add(sale);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return saleList;
    }
    
    public boolean addVenda(Venda sale) {
        String sql = "INSERT INTO vendas (quantidade_venda, data_venda, valor_venda, id_cliente, id_produto, id_funcionario) VALUES (?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setInt(1, sale.getQuantidade_venda());
            pstmt.setString(2, sale.getData());
            pstmt.setFloat(3, sale.getValor_venda());
            pstmt.setInt(4, sale.getId_cliente());
            pstmt.setInt(5, sale.getId_produto());
            pstmt.setInt(6, sale.getId_funcionario());
            pstmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean updateVenda(Venda sale) {
        String sql = "UPDATE vendas SET quantidade_venda=?, data_venda=?, valor_venda=?, id_cliente=?, id_produto=?, id_funcionario=? WHERE id=?";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setInt(1, sale.getQuantidade_venda());
            pstmt.setString(2, sale.getData());
            pstmt.setFloat(3, sale.getValor_venda());
            pstmt.setInt(4, sale.getId_cliente());
            pstmt.setInt(5, sale.getId_produto());
            pstmt.setInt(6, sale.getId_funcionario());
            pstmt.setInt(7, sale.getId());
            pstmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
     public boolean removeVenda(int id) {
        try {
            String sql = "DELETE FROM vendas WHERE id = ?";
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
