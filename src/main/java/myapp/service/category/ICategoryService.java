package myapp.service.category;

import myapp.model.Category;

public interface ICategoryService {
    Iterable<Category> findAll();
    Category findCategory(Long id);
    void save(Category category);
    void remove(Long id);
}
