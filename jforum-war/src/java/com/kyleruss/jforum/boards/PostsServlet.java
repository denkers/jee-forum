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
        
         else if(path.equals("/boards/post/remove"))
            removePost(request, response);
    }
    
    /**
     * Fetches the post for the passed post id
     * Displays post editing page and passed the fetched post as attribute
     */
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
    
    /**
     * Redirects the user to the page of the passed thread
     * @param thread The thread whose page will be redirected to
     */
    private void redirectToThread(HttpServletRequest request, HttpServletResponse response, Threads thread) throws ServletException, IOException 
    {
        response.sendRedirect(request.getContextPath() + "/boards/thread?catid=" + 
                            thread.getCategories().getId() + "&threadid=" + thread.getId());
    }
    
    /**
     * Removes the post entity that has the passed post id
     * Displays error page when given invalid post id
     */
    private void removePost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String postIDParam  =   request.getParameter("postid");
        if(!activeUserBean.isActive() || postIDParam.equals("") || !ValidationUtils.isNumeric(postIDParam))
            response.sendRedirect(request.getContextPath() + "/error");
        else
        {
            Posts post  =   postsBean.find(Integer.parseInt(postIDParam));
            Users user  =   post.getUsers();
            
            if(!user.equals(activeUserBean.getActiveUser()))
                response.sendRedirect(request.getContextPath() + "/error");
            else
            {
                postsBean.removePost(post);
                redirectToThread(request, response, post.getThreads());
            }
        }
    }
    
    /**
     * Creates or edits a post entity
     * If the post_id param is passed on request then the it will attempt to edit the found post entity
     * Otherwise a new post entity is created and properties are set to those passed
     */
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

