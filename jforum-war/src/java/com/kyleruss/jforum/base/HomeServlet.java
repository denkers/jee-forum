//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.base;

import com.kyleruss.jforum.ejb.entity.Categories;
import com.kyleruss.jforum.ejb.entity.Sections;
import com.kyleruss.jforum.ejb.session.entityfac.SectionsFacade;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "HomeServlet", urlPatterns = {"/home", "/error"})
public class HomeServlet extends HttpServlet 
{
    @EJB
    private SectionsFacade sectionsBean;
    
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String path     =   request.getServletPath();
        
        if(path.equals("/home"))
            getHome(request, response);
        
        else if(path.equals("/error"))
            getError(request, response);
    }

    /**
     * Displays an error page for the client
     */
    private void getError(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.getRequestDispatcher("/views/error.jsp").forward(request, response);
    }
    
    /**
     * Fetches all the sections for the forum
     * Displays the index of the application
     */
    private void getHome(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Sections> sections  =   sectionsBean.findAll();
        request.setAttribute("sections", sections);
        request.getRequestDispatcher("/views/index.jsp").forward(request, response);
    }
}
