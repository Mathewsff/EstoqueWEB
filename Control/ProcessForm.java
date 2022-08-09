package Control;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import App.Funcionario;
import Model.LoginDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "ProcessForm", urlPatterns = {"/ProcessForm"})
public class ProcessForm extends HttpServlet {

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
            throws ServletException, IOException, InstantiationException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");
        String userLogin = request.getParameter("login");
        String userPassword = request.getParameter("password");

        if (!userLogin.isEmpty() && !userPassword.isEmpty()) {
            try {
                //** String SQL = "SELECT cpf, senha FROM funcionarios  WHERE cpf = ?  and senha = ?";
                //pstmt = conn.prepareStatement(SQL);
                //pstmt.setString(1, userLogin);
                //pstmt.setString(1, userPassword);
                LoginDAO credentials = new LoginDAO();
                Funcionario current = credentials.getCredentials(userLogin, userPassword);
                if (current.getId() != 0) {
                    HttpSession session = request.getSession(true);
                    session.setAttribute("user", current);
                    session.setAttribute("idUser", current.getId());
                    session.setAttribute("papel", current.getPapel());
                    RequestDispatcher target;
                    switch (current.getPapel()) {
                        case 0:
                            target = request.getRequestDispatcher("/interfaceAdministrador.jsp");
                            target.forward(request, response);
                            break;
                        case 1:
                            target = request.getRequestDispatcher("/interfaceVendedor.jsp");
                            target.forward(request, response);
                            break;
                        case 2:
                            target = request.getRequestDispatcher("/interfaceComprador.jsp");
                            target.forward(request, response);
                            break;
                        
                    }
                }else{
                    response.sendRedirect("erro_login.html");
                }
                        
            } catch (SQLException ex) {
                Logger.getLogger(ProcessForm.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        try {
            processRequest(request, response);
        } catch (InstantiationException ex) {
            Logger.getLogger(ProcessForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProcessForm.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            processRequest(request, response);
        } catch (InstantiationException ex) {
            Logger.getLogger(ProcessForm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ProcessForm.class.getName()).log(Level.SEVERE, null, ex);
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

}
