package springlibrary.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import springlibrary.dao.interfaces.PostDao;
import springlibrary.entities.Post;

import java.util.List;

/**
 * Created by Shon on 05.12.2015.
 */
@Component
public class PostDaoImpl implements PostDao {

    @Autowired
    private SessionFactory sessionFactory;

    private ProjectionList postProjection;

    public PostDaoImpl() {
        postProjection = Projections.projectionList();
        postProjection.add(Projections.property("id"), "id");
        postProjection.add(Projections.property("customername"), "customername");
        postProjection.add(Projections.property("customernumber"), "customernumber");
        postProjection.add(Projections.property("productname"), "productname");
    }


    @Transactional
    @Override
    public List<Post> getPosts() {
        List<Post> post = createPostList(createPostCriteria());
        return post;
    }

    @Transactional
    @Override
    public List<Post> getPosts(Post post){
        List<Post> posts = createPostList(createPostCriteria().add(Restrictions.ilike("customernumber", post.getCustomernumber(), MatchMode.ANYWHERE)));
        return posts;

    }

    @Transactional
    @Override
    public List<Post> getPosts(String postName) {
        List<Post> posts = createPostList(createPostCriteria().add(Restrictions.ilike("customernumber", postName, MatchMode.ANYWHERE)));
        return posts;
    }

    private DetachedCriteria createPostCriteria(){
        DetachedCriteria postListCriteria = DetachedCriteria.forClass(Post.class, "p");
        return postListCriteria;
    }

    private List<Post> createPostList(DetachedCriteria bookListCriteria) {
        Criteria criteria = bookListCriteria.getExecutableCriteria(sessionFactory.getCurrentSession());
        criteria.addOrder(Order.asc("p.id")).setProjection(postProjection).setResultTransformer(Transformers.aliasToBean(Post.class));
        return criteria.list();
    }

}
