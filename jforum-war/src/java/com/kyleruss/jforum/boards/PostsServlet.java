//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.boards;

import com.kyleruss.jforum.ejb.entity.Posts;
import com.kyleruss.jforum.ejb.entity.Threads;
import com.kyleruss.jforum.ejb.entity.Users;
import com.kyleruss.jforum.ejb.session.entityfac.PostsFacade;
import com.kyleruss.jforum.ejb.session.entityfac.ThreadsFacade;
import com.kyleruss.jforum.ejb.user.ActiveUserBean;
import com.kyleruss.jforum.ejb.util.ValidationUtils;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PostsServlet", urlPatterns = {"/boards/post/save", "/boards/post/remove", "/boards/post/edit"})
public class PostsServlet extends HttpServlet 
{
    @EJB
    private ActiveUserBean activeUserBean;
    
    @EJB
    private ThreadsFacade threadsBean;
    
    @EJB
    private PostsFacade postsBean;

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
        if(path.equals("/boards/post/edit"))
            getPostEdit(request, response);
    }
    
    private void getPostEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String postIDParam  =   request.getParameter("postid");
        if(postIDParam.equals("") || !ValidationUtils.isNumeric(postIDParam))
            response.sendRedirect(request.getContextPath() + "/error");
        else
        {
            Posts post  =       postsBean.find(Integer.parseInt(postIDParam));
            request.setAttribute("post", post);
            request.getRequestDispatcher("/views/boards/editpost.jsp").forward(request, response);
        }
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
        if(!activeUserBean.isActive())
            response.sendRedirect(request.getContextPath() + "/error");
        
        String path =   request.getServletPath();
        
        if(path.equals("/boards/post/save"))
            savePost(request, response);
    }
    
    private void redirectToThread(HttpServletRequest request, HttpServletResponse response, Threads thread) throws ServletException, IOException 
    {
        response.sendRedirect(request.getContextPath() + "/boards/thread?catid=" + 
                            thread.getCategories().getId() + "&threadid=" + thread.getId());
    }
    
    private void savePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String postIDParam  =   request.getParameter("post_id");
        int threadID        =   Integer.parseInt(request.getParameter("thread_id"));
        String postContent  =   request.getParameter("post_content");
        Threads postThread  =   threadsBean.find(threadID);
        
        if(postThread == null || (postIDParam.length() > 0 && !ValidationUtils.isNumeric(postIDParam)))
            response.sendRedirect(request.getContextPath() + "/error");
        else
        {
            if(!postIDParam.equals(""))
            {
                Posts post  =   postsBean.find(Integer.parseInt(postIDParam));
                if(post == null) response.sendRedirect(request.getContextPath() + "/error");
                else
                {
                    postsBean.editPost(post, postContent);
                    redirectToThread(request, response, postThread);
                }
            }
            
            else
            {
                Users user      =   activeUserBean.getActiveUser();
                Posts post      =   new Posts(postThread, user, postContent);
                if(postsBean.addPost(post, postThread))
                    redirectToThread(request, response, postThread);
                else
                    response.sendRedirect(request.getContextPath() + "/error");
            }
        }
    }
}

