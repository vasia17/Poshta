package springlibrary.objects;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import springlibrary.dao.interfaces.PostDao;
import springlibrary.entities.Post;
import springlibrary.enums.SearchType;
import springlibrary.dao.interfaces.PostDao;

import java.util.List;

@Component
@Scope("singleton")
public class LibraryFacade {

    @Autowired
    private PostDao postDao;

    private List<Post> posts;


    public List<Post> getPosts() {
        if (posts == null){
            posts = postDao.getPosts();
        }
        return posts;
    }




}

