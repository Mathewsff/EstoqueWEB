/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import App.Funcionario;
import App.Produto;
import App.Venda;
import Model.LoginDAO;
import Model.ProdutoDAO;
import Model.VendaDAO;
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

/**
 *
 * @author Nowll
 */
@WebServlet(name = "VendaController", urlPatterns = {"/VendaController"})
public class VendaController extends HttpServlet {

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
        String status;
        try {
            Venda sale = new Venda();
            sale.setQuantidade_venda(Integer.parseInt(request.getParameter("addQuantidade")));
            sale.setData(request.getParameter("addData"));
            sale.setValor_venda(Integer.parseInt(request.getParameter("addValor")));
            sale.setId_cliente(Integer.parseInt(request.getParameter("iDCliente")));
            sale.setId_produto(Integer.parseInt(request.getParameter("iDProduto")));
            sale.setId_funcionario(Integer.parseInt(request.getParameter("iDVendedor")));
            sale.setId(Integer.parseInt(request.getParameter("id")));

            VendaDAO saleDAO = new VendaDAO();
            ProdutoDAO productDAO = new ProdutoDAO();
            Produto product = productDAO.getProductById(sale.getId_produto());;
            if (sale.getId() == 0) {
                if ((product.getQuantidade_disponivel() >= sale.getQuantidade_venda()) && validation(sale) && "S".equals(product.getLiberado_venda())) {
                    if (saleDAO.addVenda(sale)) {
                        status = "Venda Adicionada com Sucesso.";
                        product.setQuantidade_disponivel(product.getQuantidade_disponivel() - sale.getQuantidade_venda());
                        productDAO.updateProduto(product);
                    } else {
                        status = "Falha em Adicionar Venda; Cheque os dados de entrada.";
                    }
                } else {
                    status = "Falha em Adicionar Venda; Quantidade Insuficiênte ou Produto não liberado para a venda.";
                }
            } else {
                saleDAO.updateVenda(sale);
                status = "Venda atualizada com sucesso";
            }
            request.setAttribute("status", status);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            status = "Falha em Adicionar Venda (tc); Cheque os dados de entrada.";
            request.setAttribute("status", status);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
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
        VendaDAO saleDAO = null;
        try {
            saleDAO = new VendaDAO();
        } catch (InstantiationException ex) {
            Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String action = (String) request.getParameter("action");
        int id;
        ArrayList<Venda> saleList;
        Venda sale = new Venda();
        Funcionario user;
        int userId;
        HttpSession session = request.getSession();

        switch (action) {
            case "show":
                saleList = saleDAO.getAllSales();
                request.setAttribute("saleList", saleList);
                RequestDispatcher target = getServletContext().getRequestDispatcher("/tableVenda.jsp");
                target.forward(request, response);
                break;

            case "include":
                sale.setQuantidade_venda(0);
                sale.setData("");
                sale.setValor_venda(0);
                sale.setId_cliente(0);
                sale.setId_produto(0);
                sale.setId_funcionario(0);
                sale.setId(0);

                request.setAttribute("sale", sale);
                RequestDispatcher include = getServletContext().getRequestDispatcher("/fornecedor_add.html");
                include.forward(request, response);
                break;

            case "edit":

                id = Integer.parseInt(request.getParameter("id"));
                sale = saleDAO.getSaleById(id);
                userId = (int) session.getAttribute("idUser");
                if (userId == Integer.parseInt(request.getParameter("id_funcionario"))) {
                    if (sale.getId() > 0) {
                        request.setAttribute("sale", sale);
                        RequestDispatcher rs = request.getRequestDispatcher("/vendaAdd.jsp");
                        rs.forward(request, response);
                    } else {
                        String status = "Erro ao Editar Venda!";
                        request.setAttribute("status", status);
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                        rd.forward(request, response);
                    }
                } else {
                    String status = "Venda realizada por outro vendedor";
                    request.setAttribute("status", status);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                user = (Funcionario) session.getAttribute("user");
                userId = user.getId();
                System.out.print("userId = " + userId);

                if (userId == Integer.parseInt(request.getParameter("id_funcionario"))) {
                    saleDAO.removeVenda(id);
                    saleList = saleDAO.getAllSales();
                    request.setAttribute("saleList", saleList);
                    RequestDispatcher deleteTarget = getServletContext().getRequestDispatcher("/tableVenda.jsp");
                    deleteTarget.forward(request, response);
                    break;
                } else {
                    String status = "Venda realizada por outro vendedor";
                    request.setAttribute("status", status);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;

            case "report":
                String role = String.valueOf(session.getAttribute("papel"));
                if ("0".equals(role)) {
                    saleList = saleDAO.generateReport();
                    request.setAttribute("saleList", saleList);
                    RequestDispatcher targetR = getServletContext().getRequestDispatcher("/tableReport.jsp");
                    targetR.forward(request, response);
                }
                break;
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

    private boolean validation(Venda x) {
        if (x.getId() >= 0 && x.getData() != null && x.getId_cliente() > 0 && x.getId_funcionario() > 0
                && x.getId_produto() > 0 && x.getQuantidade_venda() >= 0 && x.getValor_venda() >= 0) {
            return true;
        }
        return false;
    }
}
