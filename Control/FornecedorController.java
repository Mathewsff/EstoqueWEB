/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import App.Fornecedor;
import Model.FornecedorDAO;
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

/**
 *
 * @author Nowll
 */
@WebServlet(name = "FornecedorController", urlPatterns = {"/FornecedorController"})
public class FornecedorController extends HttpServlet {

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

        FornecedorDAO supplierDAO = null;
        try {
            supplierDAO = new FornecedorDAO();
        } catch (InstantiationException ex) {
            Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FornecedorController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String action = (String) request.getParameter("action");
        int id;
        ArrayList<Fornecedor> supplierList;
        Fornecedor supplier = new Fornecedor();

        switch (action) {
            case "show":
                supplierList = supplierDAO.getAllSuppliers();
                request.setAttribute("supplierList", supplierList);
                RequestDispatcher target = getServletContext().getRequestDispatcher("/tableFornecedor.jsp");
                target.forward(request, response);
                break;

            case "include":
                supplier.setRazao_social("");
                supplier.setCnpj("");
                supplier.setEndereco("");
                supplier.setBairro("");
                supplier.setCidade("");
                supplier.setUf("");
                supplier.setCep("");
                supplier.setTelefone("");
                supplier.setEmail("");
                supplier.setId(0);

                request.setAttribute("supplier", supplier);
                RequestDispatcher include = getServletContext().getRequestDispatcher("/tableFornecedor.jsp");
                include.forward(request, response);
                break;

            case "edit":

                id = Integer.parseInt(request.getParameter("id"));
                supplier = supplierDAO.getSupplierById(id);

                if (supplier.getId() > 0) {
                    request.setAttribute("supplier", supplier);
                    RequestDispatcher rs = request.getRequestDispatcher("/fornecedor_add.jsp");
                    rs.forward(request, response);
                } else {
                    String status = "Erro ao Editar Fornecedor!";
                    request.setAttribute("status", status);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                supplierDAO.removeSupplier(id);

                supplierList = supplierDAO.getAllSuppliers();
                request.setAttribute("supplierList", supplierList);
                RequestDispatcher deleteTarget = getServletContext().getRequestDispatcher("/tableFornecedor.jsp");
                deleteTarget.forward(request, response);
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

        String status;
        try {
            Fornecedor supplier = new Fornecedor();
            supplier.setRazao_social(request.getParameter("addRazao"));
            supplier.setCnpj(request.getParameter("addCNPJ"));
            supplier.setEndereco(request.getParameter("addEndereco"));
            supplier.setBairro(request.getParameter("addBairro"));
            supplier.setCidade(request.getParameter("addCidade"));
            supplier.setUf(request.getParameter("addUF"));
            supplier.setCep(request.getParameter("addCEP"));
            supplier.setTelefone(request.getParameter("addTelefone"));
            supplier.setEmail(request.getParameter("addEmail"));
            supplier.setId(Integer.parseInt(request.getParameter("id")));
            
            FornecedorDAO supplierDAO = new FornecedorDAO();
            if (supplier.getId() == 0 && validation(supplier)) {
                if (supplierDAO.addFornecedor(supplier)) {
                    status = "Fornecedor Adicionado com Sucesso.";
                } else {
                    status = "Falha em Adicionar Fornecedor; Cheque os dados de entrada.";
                }
            }else{
                
                supplierDAO.updateSupplier(supplier);
                status = "Fornecedor Modificado com Sucesso.";
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
private boolean validation(Fornecedor x) {
        if (x.getId() >= 0 && x.getBairro() != null && x.getCep()!= null && x.getCidade()!= null
                && x.getCnpj() != null && x.getEmail() != null && x.getEndereco() != null
                && x.getRazao_social()!= null && x.getTelefone()!= null && x.getUf() != null) {
            return true;
        }
        return false;
    }

}
