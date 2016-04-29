//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.ejb.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="friends", catalog="jforum")
public class Friends  implements java.io.Serializable
{
     private Integer id;
     private Users usersByFriendB;
     private Users usersByFriendA;
     private Date friendshipDate;
     private Boolean confirmed;

    public Friends() {}
    
    public Friends(Users usersByFriendB, Users usersByFriendA)
    {
        this.usersByFriendB     =   usersByFriendB;
        this.usersByFriendA     =   usersByFriendA;
    }
    
    public Friends(Users usersByFriendB, Users usersByFriendA, Date friendshipDate) 
    {
       this.usersByFriendB      =   usersByFriendB;
       this.usersByFriendA      =   usersByFriendA;
       this.friendshipDate      =   friendshipDate;
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
    @JoinColumn(name="friend_b", nullable=false)
    public Users getUsersByFriendB() 
    {
        return this.usersByFriendB;
    }
    
    public void setUsersByFriendB(Users usersByFriendB) 
    {
        this.usersByFriendB = usersByFriendB;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="friend_a", nullable=false)
    public Users getUsersByFriendA() 
    {
        return this.usersByFriendA;
    }
    
    public void setUsersByFriendA(Users usersByFriendA)
    {
        this.usersByFriendA = usersByFriendA;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="friendship_date", length=19)
    public Date getFriendshipDate()
    {
        return this.friendshipDate;
    }
    
    public void setFriendshipDate(Date friendshipDate) 
    {
        this.friendshipDate = friendshipDate;
    }

    public boolean getConfirmed()
    {
        return confirmed;
    }

    public void setConfirmed(Boolean confirmed)
    {
        this.confirmed  =   confirmed;
    }
    
    public boolean isFriends(Users user)
    {
        return usersByFriendA.equals(user) || usersByFriendB.equals(user);
    }
    
    public Users getFriend(Users user)
    {
        if(usersByFriendA.equals(user))
            return usersByFriendB;
        
        return usersByFriendA;
    }
}