package myapp.controller;

import myapp.model.Blog;
import myapp.service.blog.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/blogs")
public class BlogAjaxController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/likes/{id}")
    public Blog createLikeForBlog(@PathVariable Long id){
        Blog blog= blogService.findBlog(id);
        int like=blog.getLikes()+1;
        blog.setLikes(like);
        blogService.saveBlog(blog);
        return blog;
    }
}
