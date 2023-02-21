/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package store.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import store.entities.Category;
import store.entities.Post;

/**
 *
 * @author HIMANSHU MEHRA
 */
public class PostDao {
    Connection con;
    
  public  PostDao(Connection con){
      this.con= con;
  }
  public ArrayList<Category> getAllCategory(){
      ArrayList<Category> list= new ArrayList<>();
      
      try{
          String q= "select * from categories";
          Statement st= this.con.createStatement();
          ResultSet set= st.executeQuery(q);
          while(set.next()){
              int cid= set.getInt("cid");
              String name=set.getString("name");
              String description=set.getString("description");
              Category c= new Category(cid,name,description);
              list.add(c);
          }
      }
      catch(SQLException e){
      }
      
      return list;
  }
  
  public boolean savePost(Post p){
      boolean f=false;
      try{
        
          String q= "insert into Posts(pTitle,pContent,pCode,pPic,catId,userId) values(?,?,?,?,?,?)";
          PreparedStatement pstmt= con.prepareStatement(q);
          pstmt.setString(1,p.getpTitle());
          pstmt.setString(2,p.getpContent());
          pstmt.setString(3,p.getpCode());
          pstmt.setString(4,p.getpPic());
          pstmt.setInt(5,p.getCatId());
          pstmt.setInt(6,p.getUserId());
          
         
          pstmt.executeUpdate();
          f=true;
      }
      catch(SQLException e){
       
      }
        return f;
  }
  
  //get all posts
  public List<Post> getAllPosts(){
      List<Post> l1= new ArrayList<>();
      //fetch all the post
      try{
          PreparedStatement ps= con.prepareStatement("select * from posts order by pid desc");
          ResultSet set= ps.executeQuery();
          
          while(set.next()){
              int pid= set.getInt("pid");
              String pTitle= set.getString("pTitle");
              String pContent= set.getString("pContent");
              String pCode= set.getString("pCode");
              String pPic= set.getString("pPic");
              Timestamp pDate= set.getTimestamp("pDate");
              int catId= set.getInt("catId");
              int userId= set.getInt("userId");
              
              Post p= new Post(pid,pTitle,pContent,pCode,pPic, pDate,catId,userId);
              l1.add(p);
          }
          
      }
      catch(SQLException e){
      }
      return l1;
  }
  //get posts by category
  public List<Post> getPostByCategory(int catId){
      List<Post> l1= new ArrayList<>();
      //fetch posts by id
      try{
          PreparedStatement ps= con.prepareStatement("select * from posts where catId=?");
           ps.setInt(1, catId);
          ResultSet set= ps.executeQuery();
          
          while(set.next()){
              int pid= set.getInt("pid");
              String pTitle= set.getString("pTitle");
              String pContent= set.getString("pContent");
              String pCode= set.getString("pCode");
              String pPic= set.getString("pPic");
              Timestamp pDate= set.getTimestamp("pDate");
              int userId= set.getInt("userId");
              
              Post p= new Post(pid,pTitle,pContent,pCode,pPic, pDate,catId,userId);
              l1.add(p);
          }
          
      }
      catch(SQLException e){
      }
      return l1;
  }
  
  public Post getPostByPostId(int postId){
      Post post=null;
      String q= "select * from posts where pid=?";
      
      try{
          PreparedStatement p = this.con.prepareStatement(q);
          p.setInt(1,postId);
          ResultSet set= p.executeQuery();
          
          while(set.next()){
              int pid= set.getInt("pid");
              String pTitle= set.getString("pTitle");
              String pContent= set.getString("pContent");
              String pCode= set.getString("pCode");
              String pPic= set.getString("pPic");
              Timestamp pDate= set.getTimestamp("pDate");
              int userId= set.getInt("userId");
              int cId= set.getInt("catId");
              
              post= new Post(pid,pTitle,pContent,pCode,pPic, pDate,cId,userId);
           }
      }
      catch(SQLException e){
          
      }
      return post;
  }
}
