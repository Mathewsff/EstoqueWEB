/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import App.Categoria;
import App.Funcionario;
import Model.CategoriaDAO;
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
@WebServlet(name = "CategoriaController", urlPatterns = {"/CategoriaController"})
public class CategoriaController extends HttpServlet {

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
        CategoriaDAO categoryDAO = null;
        try {
            categoryDAO = new CategoriaDAO();
        } catch (InstantiationException ex) {
            Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(VendaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String action = (String) request.getParameter("action");
        int id;
        ArrayList<Categoria> categoryList;
        Categoria category = new Categoria();

        switch (action) {
            case "show":
                categoryList = categoryDAO.getAllCategories();
                request.setAttribute("categoryList", categoryList);
                RequestDispatcher target = getServletContext().getRequestDispatcher("/tableCategoria.jsp");
                target.forward(request, response);
                break;

            case "include":

                RequestDispatcher include = getServletContext().getRequestDispatcher("/fornecedor_add.html");
                include.forward(request, response);
                break;

            case "edit":

                id = Integer.parseInt(request.getParameter("id"));
                category = categoryDAO.getClientById(id);
                if (category.getId() > 0) {
                    request.setAttribute("category", category);
                    RequestDispatcher rs = request.getRequestDispatcher("/categoria_add.jsp");
                    rs.forward(request, response);
                } else {
                    String status = "Erro ao Editar Categoria!";
                    request.setAttribute("status", status);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);

                }
                break;

            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                categoryDAO.removeCategoria(id);
                categoryList = categoryDAO.getAllCategories();
                request.setAttribute("categoryList", categoryList);
                RequestDispatcher deleteTarget = getServletContext().getRequestDispatcher("/tableCategoria.jsp");
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
            Categoria category = new Categoria();
            category.setNome_categoria(request.getParameter("addNomeCategoria"));
            category.setId(Integer.parseInt((request.getParameter("id"))));
            CategoriaDAO categoryDAO = new CategoriaDAO();
            
            if (validation(category) && category.getId() == 0) {
                if (categoryDAO.addCategoria(category)) {
                    status = "Categoria Adicionada com Sucesso.";
                } else {
                    status = "Falha em Adicionar Cliente; Cheque os dados de entrada.";
                }
            } else {
                status = "Categoria Atualizada com Sucesso.";
                categoryDAO.updateCategoria(category);
            }
            request.setAttribute("status", status);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            status = "Falha em Adicionar Categoria; Cheque os dados de entrada.";
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

    private boolean validation(Categoria categ){
        if (categ.getId() >=0 && categ.getNome_categoria() != null){
            return true;
        }
        return false;
    }
}
