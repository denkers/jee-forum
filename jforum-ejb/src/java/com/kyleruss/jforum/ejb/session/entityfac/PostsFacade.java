//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.session.entityfac;

import com.kyleruss.jforum.ejb.entity.Posts;
import com.kyleruss.jforum.ejb.entity.Threads;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@LocalBean
public class PostsFacade extends AbstractFacade<Posts> 
{
    @PersistenceContext(unitName = "jforum-ejbPU")
    private EntityManager em;

    public PostsFacade()
    {
        super(Posts.class);
    }
    
    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
    
    public boolean addPost(Posts post, Threads thread)
    {
        post.setThreads(thread);
        thread.getPostses().add(post);
        em.persist(post);
        em.merge(thread);
        return em.contains(post);
    }
    
    public void editPost(Posts post, String content)
    {
        post.setContent(content);
        edit(post);
    }
}
