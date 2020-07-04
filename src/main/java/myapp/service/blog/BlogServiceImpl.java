package myapp.service.blog;

import myapp.model.Blog;
import myapp.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class BlogServiceImpl implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Override
    public void saveBlog(Blog model) {
        blogRepository.save(model);

    }

    @Override
    public Blog findBlog(Long id) {
       Blog blog= blogRepository.findOne(id);
       return blog;
    }

    @Override
    public Iterable<Blog> findAll() {
        return blogRepository.findAll();
    }

    @Override
    public Iterable<Blog> search(String name) {
        return blogRepository.findBlogsByNameContaining(name);
    }

    @Override
    public Page<Blog> findAll(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }

//    @Override
//    public Iterable<Blog> findAll() {
//        return blogRepository.findAll();
//    }

    @Override
    public void updateBlog(Blog blog) {
        blogRepository.save(blog);

    }

    @Override
    public void deleteBlog(Long id) {
        blogRepository.delete(id);

    }

    @Override
    public Page<Blog> findAllByNameContaining(String name, Pageable pageable) {
        return blogRepository.findAllByNameContaining(name,pageable);
    }

    @Override
    public Page<Blog> findBlogsByOrderByDate(Pageable pageable) {
        return blogRepository.findBlogsByOrderByDate(pageable);
    }

    @Override
    public Page<Blog> findBlogsByCategory_NameOrderByDate(String categoryName, Pageable pageable) {
        return blogRepository.findBlogsByCategory_NameOrderByDate(categoryName,pageable);
    }

//    @Override
//    public Page<Blog> findAllBlogSortByName( Pageable pageable,Sort sort) {
//       return blogRepository.findAllByName(pageable,sort);
//    }
}
