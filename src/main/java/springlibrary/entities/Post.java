package springlibrary.entities;

import sun.invoke.empty.Empty;

import javax.annotation.Resource;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shon on 05.12.2015.
 */
@ManagedBean(name="post")
@RequestScoped


@Entity
public class Post {
    private int id;
    private String customername;
    private String customernumber;
    private String productname;


    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "customername")
    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    @Basic
    @Column(name = "customernumber")
    public String getCustomernumber() {
        return customernumber;
    }

    public void setCustomernumber(String customernumber) {
        this.customernumber = customernumber;
    }

    @Basic
    @Column(name = "productname")
    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (customername != null ? !customername.equals(post.customername) : post.customername != null) return false;
        if (customernumber != null ? !customernumber.equals(post.customernumber) : post.customernumber != null)
            return false;
        if (productname != null ? !productname.equals(post.productname) : post.productname != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (customername != null ? customername.hashCode() : 0);
        result = 31 * result + (customernumber != null ? customernumber.hashCode() : 0);
        result = 31 * result + (productname != null ? productname.hashCode() : 0);
        return result;
    }

    public void add()
    {
            PreparedStatement ps = null;
            Connection con = null;
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post", "root", "root");
                String sql = "INSERT INTO post.post (id, customername, customernumber, productname) VALUES(?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setLong(1, id);
                ps.setString(2, customername);
                ps.setString(3, customernumber);
                ps.setString(4, productname);
                ps.executeUpdate();
                System.out.println("Data Added Successfully");
            }catch(Exception e) {
                System.out.println(e);
            }
            finally {
                try {
                    con.close();
                    ps.close();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }
    }
    public void delete() {
        PreparedStatement ps = null;
        Connection con = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post", "root", "root");
            String sql = "DELETE FROM post WHERE id = ?";
            ps = con.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
            System.out.println("Data Deleted Successfully");
        }catch(Exception e) {
            System.out.println(e);
        }
        finally {
            try {
                con.close();
                ps.close();
            }catch(Exception e) {
                e.printStackTrace();
            }
        }
    }

    public List<Post> getPostList(){
        PreparedStatement ps;
        Connection con;
        List<Post> list = new ArrayList<>();
        ResultSet result;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/post", "root", "root");
            ps = con.prepareStatement("select id, customername, address, created_date from post");
            result = ps.executeQuery();

            while (result.next()) {
                Post post = new Post();
                post.setId(result.getInt("id"));
                post.setCustomername(result.getString("customername"));
                post.setCustomernumber(result.getString("customernumber"));
                post.setProductname(result.getString("productname"));
                list.add(post);
            }

        } catch (ClassNotFoundException| SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
