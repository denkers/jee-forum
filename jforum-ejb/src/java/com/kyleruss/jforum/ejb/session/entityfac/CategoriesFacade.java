//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.session.entityfac;

import com.kyleruss.jforum.ejb.entity.Categories;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class CategoriesFacade extends AbstractFacade<Categories> 
{
    @PersistenceContext(unitName = "jforum-ejbPU")
    private EntityManager em;
    
    public CategoriesFacade() 
    {
        super(Categories.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
}
