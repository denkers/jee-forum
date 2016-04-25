//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="categories", catalog="jforum")
public class Categories  implements java.io.Serializable 
{
     private Integer id;
     private Sections sections;
     private String name;
     private String description;
     private Set<Threads> threadses = new HashSet();

    public Categories() {}
	
    public Categories(Sections sections) 
    {
        this.sections = sections;
    }
    
    public Categories(Sections sections, String name, String description, Set threadses) 
    {
       this.sections        =   sections;
       this.name            =   name;
       this.description     =   description;
       this.threadses       =   threadses;
    }
   
    @Id @GeneratedValue(strategy=IDENTITY)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() 
    {
        return this.id;
    }
    
    public void setId(Integer id) 
    {
        this.id = id;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="section_id", nullable=false)
    public Sections getSections() 
    {
        return this.sections;
    }
    
    public void setSections(Sections sections)
    {
        this.sections = sections;
    }

    @Column(name="name", length=25)
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    @Column(name="description", length=40)
    public String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(String description) 
    {
        this.description = description;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="categories")
    public Set<Threads> getThreadses() 
    {
        return this.threadses;
    }
    
    public void setThreadses(Set<Threads> threadses)
    {
        this.threadses = threadses;
    }
}


