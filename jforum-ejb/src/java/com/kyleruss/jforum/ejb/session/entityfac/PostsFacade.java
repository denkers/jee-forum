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
    
    /**
     * Adds a new Posts entity
     * Adds the newely created post to the threads post list
     * @param post The post to add
     * @param thread The thread whose post will be saved in and list updated
     * @return true if the post was successfully added; false otherwise
     */
    public boolean addPost(Posts post, Threads thread)
    {
        post.setThreads(thread);
        thread.getPostses().add(post);
        em.persist(post);
        em.merge(thread);
        return em.contains(post);
    }
    
    /**
     * Changes the content and saves the Post
     * @param post The post whose content will be changed
     * @param content The content string to be changed
     */
    public void editPost(Posts post, String content)
    {
        post.setContent(content);
        edit(post);
    }
    
    /**
     * Removes a post entity
     * Updates the parent thread and merges both
     * @param post The post entity to remove
     * @return true if the post was successfully removed; false otherwise
     */
    public boolean removePost(Posts post)
    {
        Threads thread  =   post.getThreads();
        thread.getPostses().remove(post);
        remove(post);
        em.merge(thread);
        return !em.contains(post);
    }
}
