//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.user;

import com.kyleruss.jforum.ejb.entity.Friends;
import com.kyleruss.jforum.ejb.entity.Users;
import com.kyleruss.jforum.ejb.session.entityfac.FriendsFacade;
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

@WebServlet(name = "FriendsServlet", urlPatterns = {"/user/profile/info/friends/remove", "/user/profile/info/friends/add"})
public class FriendsServlet extends HttpServlet 
{
    
    @EJB
    private UsersFacade usersBean;
    
    @EJB
    private FriendsFacade friendsBean;
    
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
        String path         =   request.getServletPath();
        String friendsPath  =   "/user/profile/info/friends/";
        
        if(path.equals(friendsPath + "remove"))
            processRemoveRequest(request, response);
        
        else if(path.equals(friendsPath + "add"))
            processAddRequest(request, response); 
    }
    
    private void processRemoveRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {    
        if(!activeUserBean.isActive())
                response.sendRedirect(request.getContextPath() + "/home");
        
        else
        {
            Entry<Boolean, String> result;
            
            try 
            {
                int friendID        =   Integer.parseInt(request.getParameter("friendshipid"));
                Friends friendship  =   friendsBean.find(friendID);
                Users user          =   activeUserBean.getActiveUser();

                if(friendship == null || !friendship.isFriends(user))
                    result  =   new SimpleEntry(false, "You are not friends");
                else
                    result  =   friendsBean.removeFriend(friendship);
            }

            catch(NumberFormatException e)
            {
                result  =   new SimpleEntry(false, "Invalid input");
            }

            request.setAttribute("profileResult", result);
            request.getRequestDispatcher("/user/profile/info").forward(request, response);
        }
    }
    
    private void processAddRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String username     =   request.getParameter("userid");
        if(!activeUserBean.isActive())
            response.sendRedirect(request.getContextPath() + "/home");
        else
        {
            Users friend                    =   usersBean.find(username);
            Users user                      =   activeUserBean.getActiveUser();
            Entry<Boolean, String> result   =   friendsBean.addFriend(user, friend);
            
            request.setAttribute("profileResult", result);
            request.getRequestDispatcher("/user/profile/info").forward(request, response);
        } 
    }
}
