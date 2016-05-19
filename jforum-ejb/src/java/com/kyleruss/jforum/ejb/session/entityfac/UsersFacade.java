//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.session.entityfac;

import com.kyleruss.jforum.ejb.entity.Users;
import com.kyleruss.jforum.ejb.util.ValidationUtils;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class UsersFacade extends AbstractFacade<Users> 
{
    @PersistenceContext(unitName = "jforum-ejbPU")
    private EntityManager em;
    
    public UsersFacade() 
    {
        super(Users.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
    
    /**
     * Creates a new user entity with the passed param details
     * @param username The users unique name
     * @param password The password the user will use to login
     * @param rePassword A repeat of the password param that must match
     * @param email The email address of the user
     * @return An entry where key -> status and value -> a response message (error etc.)
     */
    public Entry<Boolean, String> createUserAccount(String username, String password, String rePassword, String email)
    {
        boolean result  =   false;
        String response;
        
        if(!isValidInput(username, password, email) || rePassword == null)
        {
            response   =    "Invalid input";
        }
        
        else if(find(username) != null)
            response    =   "Account already exists";
        
        
        else if(!password.equals(rePassword))
            response    =   "Passwords don't match";
        
        else
        {
            Users user  =   new Users(username, password, email);
            create(user);
            result      =   em.contains(user);
            response    =   result? "Successfully created account" : "Failed to create account";
        }
        
        return new SimpleEntry<>(result, response);
    }
    
    /**
     * Updates the passed user entity with 
     * the new email, password and picture
     * @param user The user entity will be updated
     */
    public void saveSettings(String email, String password, String picture, Users user)
    {
        user.setEmail(email);
        user.setPassword(password);
        user.setPicture(picture);
        edit(user);
    }
    
    /**
     * Checks that the params username, password, email
     * are not null and whose string lengths are to be expected
     * @return true if the inputs are not null and have valid length
     */
    public boolean isValidInput(String username, String password, String email)
    {
        return  ValidationUtils.isNotNull(username, password, email)
                && ValidationUtils.isInRange(username, 4, 16)
                && ValidationUtils.isInRange(password, 6, 16);
    }
    
    /**
     * Authenticates the username and password
     * Returns a user entity if authenticated successfully
     * @param username The username of the login
     * @param password The password of the passed user
     * @return An entry where key -> the authenticated User and value -> a response message (error etc.)
     */
    public Entry<Users, String> loginUser(String username, String password)
    {
        Users user = null;
        String response;
        
        
        if(!ValidationUtils.isNotNull(username, password)||  !ValidationUtils.isInRange(username, 4, 16) ||  !ValidationUtils.isInRange(password, 6, 16)   ||  !ValidationUtils.isAlphanumeric(username, password))
            response    =   "Invalid input";
        else
        {
            user  =   find(username);
            if(user == null)
                response = "Account not found";
            
            else if(!user.getPassword().equals(password))
            {
                response    =   "Invalid password";
                user        =   null;
            }
            
            else
                response    =   "Successfully logged in";
        }
        
        return new SimpleEntry<>(user, response);
    }
}
