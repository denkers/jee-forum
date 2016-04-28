//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.user;

import com.kyleruss.jforum.ejb.entity.Users;
import com.kyleruss.jforum.ejb.session.entityfac.UsersFacade;
import com.kyleruss.jforum.ejb.user.ActiveUserBean;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "LoginServlet", urlPatterns = {"/user/login"})
public class LoginServlet extends HttpServlet 
{
    @EJB
    private UsersFacade usersBean;
    
    @EJB
    private ActiveUserBean activeUserBean;
    
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
        if(!activeUserBean.isActive()) 
            request.getRequestDispatcher("/views/user/login.jsp").forward(request, response);
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
        String username     =   request.getParameter("login_username");
        String password     =   request.getParameter("login_password");
        
        Entry<Users, String> attempt    =   usersBean.loginUser(username, password);
        request.getSession().setAttribute("activeUser", attempt.getKey());
        activeUserBean.setActiveUser(attempt.getKey());
        
        Entry<Boolean, String> result   =   new SimpleEntry(attempt.getKey() != null, attempt.getValue());   
        request.setAttribute("loginResult", result);
        doGet(request, response);
    }
}
