
<%@page import="store.dao.LikeDao"%>
<%@page import="store.entities.User"%>
<%@page import="java.util.List"%>
<%@page import="store.entities.Post"%>
<%@page import="store.dao.PostDao"%>
<%@page import="store.helper.ConnectionProvider"%>
<div class="row">
    
    <%
        User uu= (User)session.getAttribute("currentUser");
    Thread.sleep(1000);
    PostDao d= new PostDao(ConnectionProvider.getConnection());
    List<Post> posts= null;
    int cid= Integer.parseInt(request.getParameter("cid"));
    if (cid==0){
       posts= d.getAllPosts();
        }
        else{
        posts= d.getPostByCategory(cid);
        }
      if(posts.size()==0) {
      out.println("<h3 class='display-3 text-center'>No posts in this category </h3>");
        } 
        
    
    
     
     for(Post p: posts){
     
     %>
    <div class="col-md-6">
        <div class="card">
            <img class="card-img-top" src="blog_pics/<%=p.getpPic()%>" alt="Card image cap">
            <div class="card-body">
                <b><%= p.getpTitle() %></b>
                <p><%= p.getpContent() %></p>
                
            </div>
                <%  LikeDao ld= new LikeDao(ConnectionProvider.getConnection()) ;
                    %>
                
                <div class="card-footer primary-background text-center">
                    <a href="#!" onClick="doLike(<%=p.getPid()%>,<%=uu.getId()%>)" class="btn btn-outline-light btn-sm"><i class="fa fa-thumbs-o-up"></i><span class="like-counter"><%=ld.countLikeOnPost(p.getPid()) %></span></a>
                    <a href="show_blog_page.jsp?post_id=<%=p.getPid()%>" class="btn btn-outline-light btn-sm">Read More</a>
                    <a href="#!" class="btn btn-outline-light btn-sm"><i class="fa fa-commenting-o"></i><span>20</span></a>
                </div>  
        </div>    
    </div>
    
    
    
    
    <%
    
    
        }
    
    
    
    
    
    
    %>
    
    
</div>  
    <script src="js/myjs.js" type="text/javascript"></script>