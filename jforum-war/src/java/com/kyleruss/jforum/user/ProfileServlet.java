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
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfileServlet", urlPatterns = {"/user/profile/info", "/user/profile/messages", "/user/profile/settings", "/user/profile/friends"})
public class ProfileServlet extends HttpServlet 
{
    @EJB
    private ActiveUserBean activeUserBean;
            
    @EJB
    private UsersFacade usersBean;
    
    @EJB
    private FriendsFacade friendsBean;
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
        String username     =   request.getParameter("userid");
        boolean ownProfile  =   activeUserBean.isActive() && activeUserBean.getActiveUser().getUsername().equals(username);   
        Friends friendship  =   null;
        Users reqUser;
        
        if(ownProfile)
            reqUser =   activeUserBean.getActiveUser();
        else
            reqUser =   usersBean.find(username);
        
        if(!ownProfile)
            friendship  =   friendsBean.getFriendship(activeUserBean.getActiveUser(), reqUser);
        
        request.setAttribute("profileUser", reqUser);
        request.setAttribute("isOwnProfile", ownProfile);
        request.setAttribute("friendship", friendship);
        request.getRequestDispatcher("/views/user/profile/info.jsp").forward(request, response);
    }
}
