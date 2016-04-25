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
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="sections", catalog="jforum")
public class Sections  implements java.io.Serializable 
{
     private Integer id;
     private String name;
     private String description;
     private Set<Categories> categorieses = new HashSet();

    public Sections() {}

    public Sections(String name, String description, Set<Categories> categorieses) 
    {
       this.name            =   name;
       this.description     =   description;
       this.categorieses    =   categorieses;
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
    
    @Column(name="name", length=25)
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }

    @Column(name="description", length=45)
    public String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="sections")
    public Set<Categories> getCategorieses()
    {
        return this.categorieses;
    }
    
    public void setCategorieses(Set<Categories> categorieses) 
    {
        this.categorieses = categorieses;
    }
}


