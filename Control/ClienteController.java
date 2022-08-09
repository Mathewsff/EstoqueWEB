/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import App.Cliente;
import Model.ClienteDAO;
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
@WebServlet(name = "ClienteController", urlPatterns = {"/ClienteController"})
public class ClienteController extends HttpServlet {

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
        ClienteDAO clientDAO = null;
        try {
            clientDAO = new ClienteDAO();
        } catch (InstantiationException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String action = (String) request.getParameter("action");
        int id;
        ArrayList<Cliente> clientList;
        Cliente client = new Cliente();

        switch (action) {
            case "show":
                clientList = clientDAO.getAllClients();
                request.setAttribute("clientList", clientList);
                RequestDispatcher target = getServletContext().getRequestDispatcher("/tableCliente.jsp");
                target.forward(request, response);
                break;

            case "include":
                client.setNome("");
                client.setCpf("");
                client.setEndereco("");
                client.setBairro("");
                client.setCidade("");
                client.setUf("");
                client.setCep("");
                client.setTelefone("");
                client.setEmail("");
                client.setId(0);

                request.setAttribute("client", client);
                RequestDispatcher include = getServletContext().getRequestDispatcher("/cliente_add.html");
                include.forward(request, response);
                break;

            case "edit":

                id = Integer.parseInt(request.getParameter("id"));
                client = clientDAO.getClientById(id);

                if (client.getId() > 0) {
                    request.setAttribute("client", client);
                    RequestDispatcher rs = request.getRequestDispatcher("/cliente_add.jsp");
                    rs.forward(request, response);
                } else {
                    String status = "Erro ao Editar Cliente!";
                    request.setAttribute("status", status);
                    RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                    rd.forward(request, response);
                }
                break;

            case "delete":
                id = Integer.parseInt(request.getParameter("id"));
                clientDAO.removeCliente(id);

                clientList = clientDAO.getAllClients();
                request.setAttribute("clientList", clientList);
                RequestDispatcher deleteTarget = getServletContext().getRequestDispatcher("/tableCliente.jsp");
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
            Cliente client = new Cliente();
            client.setNome(request.getParameter("addNome"));
            client.setCpf(request.getParameter("addCPF"));
            client.setEndereco(request.getParameter("addEndereco"));
            client.setBairro(request.getParameter("addBairro"));
            client.setCidade(request.getParameter("addCidade"));
            client.setUf(request.getParameter("addUF"));
            client.setCep(request.getParameter("addCEP"));
            client.setTelefone(request.getParameter("addTelefone"));
            client.setEmail(request.getParameter("addEmail"));
            client.setId(Integer.parseInt(request.getParameter("id")));

            ClienteDAO clientDAO = new ClienteDAO();
            if (client.getId() > 0 && validation(client)) {
                clientDAO.updateCliente(client);
                status = "Cliente Modificado com Sucesso.";
            } else {
                if (clientDAO.addCliente(client) && validation(client)) {
                    status = "Cliente Adicionado com Sucesso.";
                } else {
                    status = "Falha em Adicionar Cliente; Cheque os dados de entrada.";
                }
            }
            request.setAttribute("status", status);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            status = "Falha em Adicionar Cliente (id);";
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

    private boolean validation(Cliente x) {
        if (x.getId() >= 0 && x.getBairro() != null && x.getCep()!= null && x.getCidade()!= null
                && x.getCpf() != null && x.getEmail() != null && x.getEndereco() != null
                && x.getNome()!= null && x.getTelefone()!= null && x.getUf() != null) {
            return true;
        }
        return false;
    }
}
