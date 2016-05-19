//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.boards;

import com.kyleruss.jforum.ejb.entity.Categories;
import com.kyleruss.jforum.ejb.entity.Threads;
import com.kyleruss.jforum.ejb.session.entityfac.CategoriesFacade;
import com.kyleruss.jforum.ejb.session.entityfac.ThreadsFacade;
import com.kyleruss.jforum.ejb.user.ActiveUserBean;
import com.kyleruss.jforum.ejb.util.ValidationUtils;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ThreadServlet", urlPatterns = {"/boards/thread/save", "/boards/thread"})
public class ThreadServlet extends HttpServlet 
{
    @EJB
    private ThreadsFacade threadsBean;
    
    @EJB
    private CategoriesFacade categoriesBean;
    
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
        request.setAttribute("catid", request.getParameter("catid"));
        
        String path =   request.getServletPath();
        if(path.equals("/boards/thread/save"))
            getThreadCreation(request, response);
        
        else if(path.equals("/boards/thread"))
            getThread(request, response);
    }
    
    /**
     * Displays the page for the thread 
     * Redirects to the error page when given invalid 
     * thread id param or thread was not found
     */
    private void getThread(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String threadID =   request.getParameter("threadid");
        if(threadID == null || !ValidationUtils.isNumeric(threadID))
            response.sendRedirect(request.getContextPath() + "/error");
        else
        {
            Threads thread  =   threadsBean.find(Integer.parseInt(threadID));
            if(thread == null)
                response.sendRedirect(request.getContextPath() + "/error");
            else
            {
                request.setAttribute("thread", thread);
                request.getRequestDispatcher("/views/boards/thread.jsp").forward(request, response);
            }
        }
    }
    
    /**
     * Displays the page for creating threads
     * Allows reuse of the creation page for editing threads
     * If the threadid param is passed it will be considered editing
     * In which case the fetched thread for the id is passed
     */
    private void getThreadCreation(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String threadIDParam    =   request.getParameter("threadid");
        if(threadIDParam != null && !threadIDParam.equals("") && ValidationUtils.isNumeric(threadIDParam))
        {
            Threads thread   =   threadsBean.find(Integer.parseInt(threadIDParam));
            if(!activeUserBean.isActive() || thread == null || !thread.getUsers().equals(activeUserBean.getActiveUser()))
                response.sendRedirect(request.getContextPath() + "/error");
            else
                request.setAttribute("thread", threadsBean.find(Integer.parseInt(threadIDParam)));
        }
        
        request.getRequestDispatcher("/views/boards/createthread.jsp").forward(request, response);
    }

    /**
     * Handles create new threads and editing existing threads.
     * If the thread id param is passed then thread will be editited
     * otherwise a new thread entity is created
     * Redirects to error page if invalid input or not authenticated
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String title        =   request.getParameter("thread_title");
        String content      =   request.getParameter("thread_content");
        String catParam     =   request.getParameter("thread_cat_id");
        String threadParam  =   request.getParameter("thread_edit_id");
        
        if(!activeUserBean.isActive() || !ValidationUtils.isNumeric(catParam) || 
        (threadParam != null  && !threadParam.equals("") && !ValidationUtils.isNumeric(threadParam)))
            response.sendRedirect(request.getContextPath() + "/error");
        
        else
        {
            Categories category =  categoriesBean.find(Integer.parseInt(catParam));
            
            if(threadParam == null || threadParam.equals(""))
            {
                if(category == null) response.sendRedirect(request.getContextPath() + "/error");
                else
                {
                    Threads thread  =   new Threads(category, activeUserBean.getActiveUser(), title, content);
                    boolean result  =   threadsBean.addThread(thread, category);
                    
                    if(result) 
                        response.sendRedirect(request.getContextPath() + "/boards/category?catid=" + category.getId());
                    
                    else
                    {
                        request.setAttribute("saveResult", new SimpleEntry(false, "Failed to save thread"));
                        doGet(request, response);
                    }
                }
            }
            
            else
            {
                Threads thread  =   threadsBean.find(Integer.parseInt(threadParam));
                thread.setTitle(title);
                thread.setContent(content);
                threadsBean.edit(thread);
                response.sendRedirect(request.getContextPath() + "/boards/category?catid=" + category.getId());
            }
        }
    }
}
