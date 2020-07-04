package myapp.service.blog;

import myapp.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BlogService {
    void saveBlog( Blog model);
    Blog findBlog(Long id);
    Iterable<Blog> findAll();
    Iterable<Blog> search(String name);
    Page<Blog> findAll(Pageable pageable);
    void updateBlog(Blog blog);
    void deleteBlog(Long id);
    Page<Blog> findAllByNameContaining(String name, Pageable pageable);
    Page<Blog> findBlogsByOrderByDate(Pageable pageable);
    Page<Blog> findBlogsByCategory_NameOrderByDate(String categoryName,Pageable pageable);

}
