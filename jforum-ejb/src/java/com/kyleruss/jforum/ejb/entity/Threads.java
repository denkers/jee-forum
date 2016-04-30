//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.entity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="threads", catalog="jforum")
public class Threads  implements java.io.Serializable
{
     private Integer id;
     private Categories categories;
     private Users users;
     private String title;
     private String content;
     private Date dateCreated     =     new Date();  
     private List<Posts> postses  =     new ArrayList();

    public Threads() {}
	
    public Threads(Categories categories, Users users, String title, String content) 
    {
        this.categories     =   categories;
        this.users          =   users;
        this.title          =   title;
        this.content        =   content;
    }
    public Threads(Categories categories, Users users, Date dateCreated, String title, String content, List<Posts> postses)
    {
       this.categories      =   categories;
       this.users           =   users;
       this.dateCreated     =   dateCreated;
       this.title           =   title;
       this.content         =   content;
       this.postses         =   postses;
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
    @JoinColumn(name="category_id", nullable=false)
    public Categories getCategories()
    {
        return this.categories;
    }
    
    public void setCategories(Categories categories) 
    {
        this.categories = categories;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="created_by", nullable=false)
    public Users getUsers()
    {
        return this.users;
    }
    
    public void setUsers(Users users) 
    {
        this.users = users;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="date_created", length=19)
    public Date getDateCreated()
    {
        return this.dateCreated;
    }
    
    public void setDateCreated(Date dateCreated) 
    {
        this.dateCreated = dateCreated;
    }
    
    @Column(name="title", nullable=false, length=50)
    public String getTitle()
    {
        return this.title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    @Column(name="content", nullable=false, length=1500)
    public String getContent() 
    {
        return this.content;
    }
    
    public void setContent(String content) 
    {
        this.content = content;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="threads", cascade=CascadeType.PERSIST)
    @OrderBy("postedDate ASC")
    public List<Posts> getPostses()
    {
        return this.postses;
    }
    
    public void setPostses(List<Posts> postses) 
    {
        this.postses = postses;
    }
    
    public int getNumPosts()
    {
        return postses.size();
    }
}


