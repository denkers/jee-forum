//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.boards;

import com.kyleruss.jforum.ejb.entity.Categories;
import com.kyleruss.jforum.ejb.session.entityfac.CategoriesFacade;
import java.io.IOException;
import java.util.Calendar;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "BoardServlet", urlPatterns = {"/boards/category"})
public class BoardServlet extends HttpServlet 
{
    @EJB
    private CategoriesFacade categoriesBean;
    

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
        String path =   request.getServletPath();
        
        if(path.equals("/boards/category"))
            getCategory(request, response);
    }
    
    private void getCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        try
        {
            int categoryID      =   Integer.parseInt(request.getParameter("catid"));
            Categories category =   categoriesBean.find(categoryID);
            
            if(category == null)
                response.sendRedirect(request.getContextPath() + "/error");
            else
            {
                System.out.println("category threads: " + category.getThreadses().size());
                request.setAttribute("category", category);
                request.getRequestDispatcher("/views/boards/category.jsp").forward(request, response);
            }
        }
        
        catch(NumberFormatException e)
        {
            response.sendRedirect(request.getContextPath() + "/error");
        }
    }
}
