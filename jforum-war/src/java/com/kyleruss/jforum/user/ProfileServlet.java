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
import com.kyleruss.jforum.ejb.util.ValidationUtils;
import java.io.IOException;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Map.Entry;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ProfileServlet", urlPatterns = 
{   "/user/profile/info", 
    "/user/profile/messages", 
    "/user/profile/settings", 
    "/user/profile/friends",
    "/user/profile/settings/save"
})
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
        Users reqUser;
        
        if(ownProfile)
            reqUser =   activeUserBean.getActiveUser();
        else
            reqUser =   usersBean.find(username);
        
        request.setAttribute("profileUser", reqUser);
        request.setAttribute("isOwnProfile", ownProfile);
        
        String path =   request.getServletPath();
        
        if(path.equals("/user/profile/info"))
            getProfileInfo(request, response, ownProfile, reqUser);
        
        else if(path.equals("/user/profile/friends"))
            getProfileFriends(request, response, reqUser);
        
        else if(path.equals("/user/profile/settings"))
            getProfileSettings(request, response, reqUser);
    }
    
    /**
     * Displays the info page of the user profile
     * @param ownProfile true if user is accessing own profile
     * @param reqUser The user entity of the accessing profile
     */
    private void getProfileInfo(HttpServletRequest request, HttpServletResponse response, boolean ownProfile, Users reqUser) 
    throws ServletException, IOException 
    {
        Friends friendship  =   null;
        
        if(!ownProfile)
            friendship  =   friendsBean.getFriendship(activeUserBean.getActiveUser(), reqUser);
        
        request.setAttribute("friendship", friendship);
        request.getRequestDispatcher("/views/user/profile/info.jsp").forward(request, response);
    }
    
    /**
     * Gets the friends page of the accessing users profile
     * Fetches the users friends and passeds them
     * @param reqUser reqUser The user entity of the accessing profile 
     */
    private void getProfileFriends(HttpServletRequest request, HttpServletResponse response, Users reqUser) 
    throws ServletException, IOException 
    {
        List<Friends> friends   =   friendsBean.getUsersFriends(reqUser);
        request.setAttribute("friendList", friends);
        request.getRequestDispatcher("/views/user/profile/friends.jsp").forward(request, response);
    }
        
    /**
     * Gets the authenticated users settings page
     * Redirects to error page if user is not authenticated
     * @param reqUser reqUser The user entity of the accessing profile
     */
    private void getProfileSettings(HttpServletRequest request, HttpServletResponse response, Users reqUser) 
    throws ServletException, IOException 
    {
        if(!activeUserBean.isActive() || !activeUserBean.getActiveUser().equals(reqUser))
            response.sendRedirect(request.getContextPath() + "/error");
        
        else
            request.getRequestDispatcher("/views/user/profile/settings.jsp").forward(request, response);
    }
    
    private void processSettingsSave(HttpServletRequest request, HttpServletResponse response) 
    throws ServletException, IOException
    {
        String email        =   request.getParameter("settings_email");
        String picture      =   request.getParameter("settings_picture");
        String pass         =   request.getParameter("settings_password");
        Entry<Boolean, String> result;
        
        if(pass != null && !ValidationUtils.isInRange(pass, 6, 16))
            result  =   new SimpleEntry(false, "Invalid input");
        
        else
        {
            usersBean.saveSettings(email, pass, picture, activeUserBean.getActiveUser());
            result  =   new SimpleEntry(true, "Account settings have been saved");
        }
        
        request.setAttribute("settingsResult", result);
        request.getRequestDispatcher("/views/user/profile/settings.jsp").forward(request, response);
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        String path =   request.getServletPath();
        if(path.equals("/user/profile/settings/save"))
            processSettingsSave(request, response);
    }
}
