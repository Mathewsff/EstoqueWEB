/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import App.Produto;
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
@WebServlet(name = "ProdutoDAO", urlPatterns = {"/ProdutoDAO"})
public class ProdutoDAO extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    private Connection establishConnection;

    public ProdutoDAO() throws InstantiationException, IllegalAccessException {
        try {
            establishConnection = EstablishConnection.establishConnection();
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public ArrayList<Produto> getAllProducts() {
        ArrayList<Produto> productList = new ArrayList<>();
        try {
            Statement stmt = establishConnection.createStatement();
            ResultSet rs;
            //rs = stmt.executeQuery("select * from produtos");
            rs = stmt.executeQuery("SELECT * FROM estoque.produtos INNER JOIN categorias ON produtos.id_categoria=categorias.id");
            while (rs.next()) {
                Produto product = new Produto();
                product.setId(rs.getInt("id"));
                product.setNome_produto(rs.getString("nome_produto"));
                product.setDescricao(rs.getString("descricao"));
                product.setPreco_compra(rs.getFloat("preco_compra"));
                product.setPreco_venda(rs.getFloat("preco_venda"));
                product.setQuantidade_disponivel(rs.getInt("quantidade_disponível"));
                product.setLiberado_venda(rs.getString("liberado_venda"));
                product.setId_categoria(rs.getInt("id_categoria"));
                product.setFk(rs.getString("nome_categoria"));
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productList;
    }
    
    public ArrayList<Produto> getAllSelableProducts() {
        ArrayList<Produto> productList = new ArrayList<>();
        try {
            Statement stmt = establishConnection.createStatement();
            ResultSet rs;
            //rs = stmt.executeQuery("select * from produtos WHERE liberado_venda='S' and quantidade_disponível>0");
            rs = stmt.executeQuery("SELECT * FROM estoque.produtos INNER JOIN categorias ON produtos.id_categoria=categorias.id AND liberado_venda='S' AND quantidade_disponível>0");
            while (rs.next()) {
                Produto product = new Produto();
                product.setId(rs.getInt("id"));
                product.setNome_produto(rs.getString("nome_produto"));
                product.setDescricao(rs.getString("descricao"));
                product.setPreco_compra(rs.getFloat("preco_compra"));
                product.setPreco_venda(rs.getFloat("preco_venda"));
                product.setQuantidade_disponivel(rs.getInt("quantidade_disponível"));
                product.setLiberado_venda(rs.getString("liberado_venda"));
                product.setId_categoria(rs.getInt("id_categoria"));
                product.setFk(rs.getString("nome_categoria"));
                productList.add(product);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return productList;
    }
    
    public Produto getProductById(int id){
        Produto product = new Produto();
        try {
            PreparedStatement pstmt = establishConnection.prepareStatement("select * from produtos WHERE id = ?");
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setNome_produto(rs.getString("nome_produto"));
                product.setDescricao(rs.getString("descricao"));
                product.setPreco_compra(rs.getFloat("preco_compra"));
                product.setPreco_venda(rs.getFloat("preco_venda"));
                product.setQuantidade_disponivel(rs.getInt("quantidade_disponível"));
                product.setLiberado_venda(rs.getString("liberado_venda"));
                product.setId_categoria(rs.getInt("id_categoria"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return product;
    }
    //String nome, String desc, float precoCompra, float precoVenda, int qtd, char status, int idCat        //String nome, String desc, float precoCompra, float precoVenda, int qtd, char status, int idCat

    public boolean addProduto(Produto prod) {
        String sql = "INSERT INTO produtos (nome_produto, descricao, preco_compra, preco_venda, quantidade_disponivel, liberado_venda, id_categoria) VALUES (?,?,?,?,?,?,?)";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setString(1, prod.getNome_produto());
            pstmt.setString(2, prod.getDescricao());
            pstmt.setFloat(3, prod.getPreco_compra());
            pstmt.setFloat(4, prod.getPreco_venda());
            pstmt.setInt(5, prod.getQuantidade_disponivel());
            pstmt.setString(6, prod.getLiberado_venda());
            pstmt.setInt(7, prod.getId_categoria());
            pstmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
    public boolean updateProduto(Produto prod) {
        String sql = "UPDATE produtos SET nome_produto =?, descricao=?, preco_compra=?, preco_venda=?, quantidade_disponível=?, liberado_venda=?, id_categoria=? WHERE id=?";
        PreparedStatement pstmt;
        try {
            pstmt = establishConnection.prepareStatement(sql);
            pstmt.setString(1, prod.getNome_produto());
            pstmt.setString(2, prod.getDescricao());
            pstmt.setFloat(3, prod.getPreco_compra());
            pstmt.setFloat(4, prod.getPreco_venda());
            pstmt.setInt(5, prod.getQuantidade_disponivel());
            pstmt.setString(6, prod.getLiberado_venda());
            pstmt.setInt(7, prod.getId_categoria());
            pstmt.setInt(8, prod.getId());
            pstmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public boolean removeProduto(int id) {
        try {
            String sql = "DELETE FROM produtos WHERE id = ?";
            PreparedStatement pstmt = establishConnection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("Erro de SQL: " + e.getMessage());
            return false;
        }
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
