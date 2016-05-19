//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.user;

import com.kyleruss.jforum.ejb.session.entityfac.UsersFacade;
import com.kyleruss.jforum.ejb.user.ActiveUserBean;
import java.io.IOException;
import java.util.Map.Entry;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "RegisterServlet", urlPatterns = {"/user/register"})
public class RegisterServlet extends HttpServlet 
{
    @EJB
    private UsersFacade usersBean;
    
    @EJB
    private ActiveUserBean activeUser;
    
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        if(!activeUser.isActive())
            request.getRequestDispatcher("/views/user/register.jsp").forward(request, response);
        else
            response.sendRedirect(request.getContextPath() + "/home");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String username     =   request.getParameter("register_username");
        String password     =   request.getParameter("register_password");
        String rePassword   =   request.getParameter("register_re_password");
        String email        =   request.getParameter("register_email");
        
        Entry<Boolean, String> result   =   usersBean.createUserAccount(username, password, rePassword, email);
        request.setAttribute("registerResult", result);
        doGet(request, response);
    }
}
