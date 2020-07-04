package myapp.repository;

import myapp.model.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends PagingAndSortingRepository<Blog,Long> {
Page<Blog> findAllByNameContaining(String name, Pageable pageable);

    Page<Blog> findBlogsByOrderByDate(Pageable pageable);
    Page<Blog> findBlogsByCategory_NameOrderByDate(String categoryName, Pageable pageable);
    Iterable<Blog>findBlogsByNameContaining(String name);
}