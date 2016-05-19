//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.user;

import com.kyleruss.jforum.ejb.entity.Users;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * A singleton bean that's used to maintain 
 * the active user entity of the client
 */

@Singleton
@LocalBean
public class ActiveUserBean
{
    private Users activeUser;
    
    public void setActiveUser(Users activeUser)
    {
        this.activeUser =   activeUser;
    }
    
    public Users getActiveUser()
    {
        return activeUser;
    }
    
    public boolean isActive()
    {
        return activeUser != null;
    }
}
