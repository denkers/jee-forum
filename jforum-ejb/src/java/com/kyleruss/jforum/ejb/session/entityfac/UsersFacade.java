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
    
    public void saveSettings(String email, String password, String picture, Users user)
    {
        user.setEmail(email);
        user.setPassword(password);
        user.setPicture(picture);
        edit(user);
    }
    
    public boolean isValidInput(String username, String password, String email)
    {
        return  ValidationUtils.isNotNull(username, password, email)
                && ValidationUtils.isInRange(username, 4, 16)
                && ValidationUtils.isInRange(password, 6, 16);
    }
    
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
