//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.session.entityfac;

import com.kyleruss.jforum.ejb.entity.Friends;
import com.kyleruss.jforum.ejb.entity.Users;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
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
    
    
    /**
     * Fetches the friendship that involves the users friendA and friendB
     * The param order of friendA and friendB is not important
     * @param friendA A user entity of a user in this friendship
     * @param friendB A user entity of a user in this friendship
     * @return The friendship entity that has users friendA and friendB
     */
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
    
    /**
     * Fetches all the friends of the passed user
     * @param user A Users entity whose friends you wish to fetch
     * @return A list of friendships that the user is involved in
     */
    public List<Friends> getUsersFriends(Users user)
    {
        CriteriaBuilder builder         =   em.getCriteriaBuilder();
        CriteriaQuery<Friends> query    =   builder.createQuery(entityClass);
        Root<Friends> from              =   query.from(entityClass);
        query.select(from);
        
        query.where(builder.or(builder.equal(from.get("usersByFriendA"), user), builder.equal(from.get("usersByFriendB"), user)));
        return em.createQuery(query).getResultList();
    }
    
    /**
     * Creates a friendship entity with Users user and friend
     * @param user The Entity of the client/user perspective
     * @param friend The friend the user wants to add
     * @return An entry where key -> status and value -> a response message (error etc.)
     */
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
                response    =   result? "Successfully added friend" : "Failed to add friend";   
            }
        }
        
        
        return new SimpleEntry(result, response);
    }
    
    /**
     * Removes the friendship entity
     * @param friendship The friendship entity to be removed
     * @return An entry where key -> status and value -> a response message (error etc.)
     */
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
