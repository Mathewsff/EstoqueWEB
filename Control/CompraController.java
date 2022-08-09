/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import App.Compra;
import App.Funcionario;
import App.Produto;
import Model.CompraDAO;
import Model.ProdutoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import static javax.ws.rs.core.Response.status;

/**
 *
 * @author Nowll
 */
@WebServlet(name = "CompraController", urlPatterns = {"/CompraController"})
public class CompraController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
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
        CompraDAO purchaseDAO = null;
        try {
            purchaseDAO = new CompraDAO();
        } catch (InstantiationException ex) {
            Logger.getLogger(CompraController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(CompraController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String action = (String) request.getParameter("action");
        int id;
        ArrayList<Compra> purchaseList;
        Compra purchase = new Compra();
        Funcionario user;
        HttpSession session = request.getSession();
        int userId;

        switch (action) {
            case "show":
                purchaseList = purchaseDAO.getAllPurchases();
                request.setAttribute("purchaseList", purchaseList);
                RequestDispatcher target = getServletContext().getRequestDispatcher("/tableCompra.jsp");
                target.forward(request, response);
                break;

            case "include":
                purchase.setQuantidade_compra(0);
                purchase.setData_compra("");
                purchase.setValor_compra(0);
                purchase.setId_fornecedor(0);
                purchase.setId_produto(0);
                purchase.setId_funcionario(0);
                purchase.setId(0);

                request.setAttribute("purchase", purchase);
                RequestDispatcher include = getServletContext().getRequestDispatcher("/fornecedor_add.html");
                include.forward(request, response);
                break;

            case "edit":

                id = Integer.parseInt(request.getParameter("id"));
                purchase = purchaseDAO.getPurchaseById(id);
                user = (Funcionario) request.getAttribute("user");
                userId = (int) session.getAttribute("idUser");

                if (userId == Integer.parseInt(request.getParameter("id_funcionario"))) {
                    if (purchase.getId() > 0) {
                        request.setAttribute("purchase", purchase);
                        RequestDispatcher rs = request.getRequestDispatcher("/compra_add.jsp");
                        rs.forward(request, response);
                    } else {
                        String status = "Erro ao Editar Compra!";
                        request.setAttribute("status", status);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                        rd.forward(request, response);
                    }
                } else {
                    String status = "Compra realizada por outro Funcionário";
                    request.setAttribute("status", status);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                user = (Funcionario) session.getAttribute("user");
                userId = user.getId();

                if (userId == Integer.parseInt(request.getParameter("id_funcionario"))) {
                    purchaseDAO.removeCompra(id);
                    purchaseList = purchaseDAO.getAllPurchases();
                    request.setAttribute("purchaseList", purchaseList);
                    RequestDispatcher deleteTarget = getServletContext().getRequestDispatcher("/tableCompra.jsp");
                    deleteTarget.forward(request, response);
                    
                } else {
                    String status = "Compra realizada por outro Funcionário";
                    request.setAttribute("status", status);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }break;
        }
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
        String status;
        try {
            Compra purchase = new Compra();
            purchase.setQuantidade_compra(Integer.parseInt(request.getParameter("addQuantidade")));
            purchase.setData_compra(request.getParameter("addData"));
            purchase.setValor_compra(Integer.parseInt(request.getParameter("addValor")));
            purchase.setId_fornecedor(Integer.parseInt(request.getParameter("iDFornecedor")));
            purchase.setId_produto(Integer.parseInt(request.getParameter("iDProduto")));
            purchase.setId_funcionario(Integer.parseInt(request.getParameter("iDFuncionario")));
            purchase.setId(Integer.parseInt(request.getParameter("id")));
            CompraDAO purchaseDAO = new CompraDAO();
            
            if (purchase.getId() == 0 && validation(purchase)) {
                if (purchaseDAO.addCompra(purchase)) {
                    status = "Compra Adicionada com Sucesso.";
                    ProdutoDAO productDAO = new ProdutoDAO();
                    Produto product = productDAO.getProductById(purchase.getId_produto());
                    product.setPreco_compra(purchase.getValor_compra());
                    product.setQuantidade_disponivel(purchase.getQuantidade_compra() + product.getQuantidade_disponivel());
                    productDAO.updateProduto(product);
                } else {
                    status = "Falha em Adicionar Compra; Cheque os dados de entrada.";
                }
            } else {
                purchaseDAO.updateCompra(purchase);
                status = "Compra modificada com Sucesso.";
            }
            request.setAttribute("status", status);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            status = "Falha em Adicionar Fornecedor; Cheque os dados de entrada.";
            request.setAttribute("status", status);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
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

    private boolean validation(Compra x) {
        if (x.getId() >= 0 && x.getData_compra() != null && x.getId_fornecedor()> 0 && x.getId_funcionario() > 0
                && x.getId_produto() > 0 && x.getQuantidade_compra() >= 0 && x.getValor_compra() >= 0) {
            return true;
        }
        return false;
    }
}
