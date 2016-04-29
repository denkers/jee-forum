//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.session.entityfac;

import com.kyleruss.jforum.ejb.entity.Friends;
import com.kyleruss.jforum.ejb.entity.Users;
import java.util.AbstractMap.SimpleEntry;
import java.util.Map.Entry;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
    
    public Friends getFriendship(Users friendA, Users friendB)
    {
        CriteriaBuilder builder         =   em.getCriteriaBuilder();
        CriteriaQuery<Friends> query    =   builder.createQuery(entityClass);
        Root<Friends> from              =   query.from(entityClass);
        query.select(from);
        
        query.where(builder.or
                (builder.and(builder.equal(from.get("usersByFriendA"), friendA), builder.equal(from.get("usersByFriendB"), friendB)),
                builder.and(builder.equal(from.get("usersByFriendA"), friendB), builder.equal(from.get("usersByFriendB"), friendA)))); 
        
        try { return em.createQuery(query).getSingleResult(); }
        catch(NoResultException e)
        {
            return null;
        }
    }
    
    public Entry<Boolean, String> addFriend(Users user, Users friend)
    {
        String response;
        boolean result  =   false;
        
        if(user == null || friend == null)
            response    =   "Invalid input";
        
        else
        {
            Friends friendship =   getFriendship(user, friend);
            
            if(friendship != null)
                response    =   "You are already friends";
            
            else
            {
                Friends newFriendship   =   new Friends(user, friend);
                create(newFriendship);
                result      =   em.contains(newFriendship);
                System.out.println(result);
                response    =   result? "Successfully added friend" : "Failed to add friend";   
            }
        }
        
        
        return new SimpleEntry(result, response);
    }
    
    public Entry<Boolean, String> removeFriend(Friends friendship)
    {
        String response;
        boolean result  =   false;
        
        if(friendship == null)
            response    =   "Friendship not found";
        else
        {
            remove(friendship);
            result      =   !em.contains(friendship);
            response    =   result? "Successfully removed friend" : "Failed to remove friend";   
        }
        
        return new SimpleEntry(result, response);
    }
}
