package springlibrary.dao.interfaces;

import org.springframework.transaction.annotation.Transactional;
import springlibrary.entities.Post;

import java.util.List;

/**
 * Created by Shon on 05.12.2015.
 */
public interface PostDao {
    @Transactional
    List<Post> getPosts();
    List<Post> getPosts(Post post);
    List<Post> getPosts(String postName);
}
