/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Control;

import App.Funcionario;
import Model.FuncionarioDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
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
@WebServlet(name = "FuncionarioController", urlPatterns = {"/FuncionarioController"})
public class FuncionarioController extends HttpServlet {

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
            FuncionarioDAO employeeDAO = null;
            try {
                employeeDAO = new FuncionarioDAO();
            } catch (InstantiationException ex) {
                Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
            }
            String action = (String) request.getParameter("action");
            int id;
            ArrayList<Funcionario> employeeList;
            Funcionario employee = new Funcionario();
            HttpSession session = request.getSession();
            String role = String.valueOf(session.getAttribute("papel"));
            System.out.print(role);
            switch (action) {
                case "show":
                    if ("0".equals(role)) {
                        employeeList = employeeDAO.getAllEmployees();
                        request.setAttribute("employeeList", employeeList);
                        RequestDispatcher target = getServletContext().getRequestDispatcher("/tableFuncionario.jsp");
                        target.forward(request, response);
                    }
                    break;

                case "include":
                    if ("0".equals(role)) {
                        request.setAttribute("supplier", employee);
                        RequestDispatcher include = getServletContext().getRequestDispatcher("/tableFornecedor.jsp");
                        include.forward(request, response);
                    }
                    break;

                case "edit":
                    if ("0".equals(role)) {
                        id = Integer.parseInt(request.getParameter("id"));
                        employee = employeeDAO.getEmployeeById(id);

                        if (employee.getId() > 0) {
                            request.setAttribute("employee", employee);
                            RequestDispatcher rs = request.getRequestDispatcher("/funcionario_add.jsp");
                            rs.forward(request, response);
                        } else {
                            String status = "Erro ao Editar Funcionario!";
                            request.setAttribute("status", status);
                            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
                            rd.forward(request, response);
                        }
                    }
                    break;

                case "delete":
                    id = Integer.parseInt(request.getParameter("id"));
                    employeeDAO.removeEmployee(id);

                    employeeList = employeeDAO.getAllEmployees();
                    request.setAttribute("employeeList", employeeList);
                    RequestDispatcher deleteTarget = getServletContext().getRequestDispatcher("/tableFuncionario.jsp");
                    deleteTarget.forward(request, response);
                    break;

                case "showVen":
                    if ("0".equals(role)) {
                        employeeList = employeeDAO.getAllVen();
                        request.setAttribute("employeeList", employeeList);
                        RequestDispatcher targetVen = getServletContext().getRequestDispatcher("/tableFuncionario.jsp");
                        targetVen.forward(request, response);
                    }
                    break;

                case "showCom":
                    if ("0".equals(role)) {
                        employeeList = employeeDAO.getAllCom();
                        request.setAttribute("employeeList", employeeList);
                        RequestDispatcher targetCom = getServletContext().getRequestDispatcher("/tableFuncionario.jsp");
                        targetCom.forward(request, response);
                    }
                    break;

                case "showAdm":
                    if ("0".equals(role)) {
                        employeeList = employeeDAO.getAllADM();
                        request.setAttribute("employeeList", employeeList);
                        RequestDispatcher targetAdm = getServletContext().getRequestDispatcher("/tableFuncionario.jsp");
                        targetAdm.forward(request, response);
                    }
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
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
            String status;
            Funcionario employee = new Funcionario();

            employee.setNome(request.getParameter("addName"));
            employee.setPapel(Integer.parseInt(request.getParameter("addPapel")));
            employee.setCpf(request.getParameter("addCPF"));
            employee.setSenha(Integer.parseInt(request.getParameter("addSenha")));
            employee.setId(Integer.parseInt(request.getParameter("id")));

            FuncionarioDAO employeeDAO = new FuncionarioDAO();

            if (validation(employee)) {
                if (employee.getId() > 0) {
                    employeeDAO.updateEmployee(employee);
                    status = "Cliente Modificado com Sucesso.";
                } else {
                    if (employeeDAO.addEmployee(employee)) {
                        status = "Cliente Adicionado com Sucesso.";
                        
                    } else {
                        status = "Falha em Adicionar Cliente; Cheque os dados de entrada. (DL)";
                    }
                }

            } else {
                status = "Falha em Adicionar Cliente; Cheque os dados de entrada. (Validação)";
            }

            request.setAttribute("status", status);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/Mensagem.jsp");
            rd.forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(FuncionarioController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private boolean validation(Funcionario x) {
        if (x.getId() >= 0 && x.getNome() != null && x.getCpf() != null && x.getSenha() >= 0
                && (x.getPapel() == 0 || x.getPapel() == 1 || x.getPapel() == 2)) {
            return true;
        }
        return false;
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
