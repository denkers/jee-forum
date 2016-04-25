//=========================================
//  KYLE RUSSELL
//  AUT UNIVERSITY 2016
//  https://github.com/denkers/jforum
//=========================================

package com.kyleruss.jforum.database.models;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="users", catalog="jforum")
public class Users  implements java.io.Serializable 
{
     private String username;
     private String password;
     private String email;
     private String picture;
     private Date registerDate;
     private Short accType;
     private Set<Messages> messagesesForFromUser    =   new HashSet<>();
     private Set<Posts> postses                     =   new HashSet<>();
     private Set<Messages> messagesesForToUser      =   new HashSet<>();
     private Set<Friends> friendsesForFriendB       =   new HashSet<>();
     private Set<Friends> friendsesForFriendA       =   new HashSet<>();
     private Set<Threads> threadses                 =   new HashSet<>();

    public Users() {}
	
    public Users(String username, String password, String email)
    {
        this.username   =   username;
        this.password   =   password;
        this.email      =   email;
    }
    public Users(String username, String password, String email, String picture, Date registerDate, Short accType, 
    Set<Messages> messagesesForFromUser, Set<Posts> postses, Set<Messages> messagesesForToUser, 
    Set<Friends> friendsesForFriendB, Set<Friends> friendsesForFriendA, Set<Threads> threadses) 
    {
       this.username                =   username;
       this.password                =   password;
       this.email                   =   email;
       this.picture                 =   picture;
       this.registerDate            =   registerDate;
       this.accType                 =   accType;
       this.messagesesForFromUser   =   messagesesForFromUser;
       this.postses                 =   postses;
       this.messagesesForToUser     =   messagesesForToUser;
       this.friendsesForFriendB     =   friendsesForFriendB;
       this.friendsesForFriendA     =   friendsesForFriendA;
       this.threadses               =   threadses;
    }
   
    @Id 
    @Column(name="username", unique=true, nullable=false, length=16)
    public String getUsername() 
    {
        return this.username;
    }
    
    public void setUsername(String username) 
    {
        this.username = username;
    }
    
    @Column(name="password", nullable=false)
    public String getPassword() 
    {
        return this.password;
    }
    
    public void setPassword(String password) 
    {
        this.password = password;
    }

    
    @Column(name="email", nullable=false, length=100)
    public String getEmail() 
    {
        return this.email;
    }
    
    public void setEmail(String email) 
    {
        this.email = email;
    }

    
    @Column(name="picture")
    public String getPicture() 
    {
        return this.picture;
    }
    
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="register_date", length=19)
    public Date getRegisterDate() 
    {
        return this.registerDate;
    }
    
    public void setRegisterDate(Date registerDate) 
    {
        this.registerDate = registerDate;
    }

    @Column(name="acc_type")
    public Short getAccType()
    {
        return this.accType;
    }
    
    public void setAccType(Short accType)
    {
        this.accType = accType;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="usersByFromUser")
    public Set<Messages> getMessagesesForFromUser()
    {
        return this.messagesesForFromUser;
    }
    
    public void setMessagesesForFromUser(Set<Messages> messagesesForFromUser) 
    {
        this.messagesesForFromUser = messagesesForFromUser;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="users")
    public Set<Posts> getPostses()
    {
        return this.postses;
    }
    
    public void setPostses(Set<Posts> postses)
    {
        this.postses = postses;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="usersByToUser")
    public Set<Messages> getMessagesesForToUser() 
    {
        return this.messagesesForToUser;
    }
    
    public void setMessagesesForToUser(Set<Messages> messagesesForToUser)
    {
        this.messagesesForToUser = messagesesForToUser;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="usersByFriendB")
    public Set<Friends> getFriendsesForFriendB() 
    {
        return this.friendsesForFriendB;
    }
    
    public void setFriendsesForFriendB(Set<Friends> friendsesForFriendB) 
    {
        this.friendsesForFriendB = friendsesForFriendB;
    }

    @OneToMany(fetch=FetchType.LAZY, mappedBy="usersByFriendA")
    public Set<Friends> getFriendsesForFriendA() 
    {
        return this.friendsesForFriendA;
    }
    
    public void setFriendsesForFriendA(Set<Friends> friendsesForFriendA)
    {
        this.friendsesForFriendA = friendsesForFriendA;
    }

@   OneToMany(fetch=FetchType.LAZY, mappedBy="users")
    public Set<Threads> getThreadses() 
    {
        return this.threadses;
    }
    
    public void setThreadses(Set<Threads> threadses) 
    {
        this.threadses = threadses;
    }
}


