//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.session.entityfac;

import com.kyleruss.jforum.ejb.entity.Sections;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class SectionsFacade extends AbstractFacade<Sections>
{
    @PersistenceContext(unitName = "jforum-ejbPU")
    private EntityManager em;
    
    public SectionsFacade() 
    {
        super(Sections.class);
    }

    @Override
    protected EntityManager getEntityManager() 
    {
        return em;
    }
}
