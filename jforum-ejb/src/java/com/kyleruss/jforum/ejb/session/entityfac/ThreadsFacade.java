//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.session.entityfac;

import com.kyleruss.jforum.ejb.entity.Categories;
import com.kyleruss.jforum.ejb.entity.Threads;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class ThreadsFacade extends AbstractFacade<Threads>
{
    @PersistenceContext(unitName = "jforum-ejbPU")
    private EntityManager em;
    
    public ThreadsFacade()
    {
        super(Threads.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
    
    /**
     * Adds the passed thread entity to the category
     * persists the thread and updates the categories thread list
     * @param thread The thread to be added
     * @param category The category to add the thread in
     * @return true if the thread was added; false otherwise
     */
    public boolean addThread(Threads thread, Categories category)
    {
        thread.setCategories(category);
        category.getThreadses().add(thread);
        em.persist(thread);
        em.merge(category);
        return em.contains(thread);
    }
        
}
