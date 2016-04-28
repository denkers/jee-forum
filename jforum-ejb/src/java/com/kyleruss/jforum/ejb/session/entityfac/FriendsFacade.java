//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.session.entityfac;

import com.kyleruss.jforum.ejb.entity.Friends;
import com.kyleruss.jforum.ejb.entity.Users;
import java.util.Map.Entry;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
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
        
        return em.createQuery(query).getSingleResult();
    }
    
    public Entry<Boolean, String> addFriend(Users me, Users friend)
    {
        return null;
    }
}
