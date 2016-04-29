//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="sections", catalog="jforum")
public class Sections  implements java.io.Serializable 
{
     private Integer id;
     private String name;
     private String description;
     private Date createdDate;
     private List<Categories> categorieses = new ArrayList<>();

    public Sections() {}

    public Sections(String name, String description, List<Categories> categorieses) 
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
    @OrderBy("createdDate ASC")
    public List<Categories> getCategorieses()
    {
        return this.categorieses;
    }
    
    public void setCategorieses(List<Categories> categorieses) 
    {
        this.categorieses = categorieses;
    }
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_date", length=19)
    public Date getCreatedDate()
    {
        return createdDate;
    }
    
    public void setCreatedDate(Date createdDate)
    {
        this.createdDate    =   createdDate;
    }
}


