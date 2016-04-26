//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.session.entityfac;

import com.kyleruss.jforum.ejb.entity.Friends;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class FriendsFacade extends AbstractFacade<Friends> 
{
    @PersistenceContext(unitName = "jforum-ejbPU")
    private EntityManager em;
    
    public FriendsFacade() 
    {
        super(Friends.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
}
