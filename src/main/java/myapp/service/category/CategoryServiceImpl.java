package myapp.service.category;

import myapp.model.Category;
import myapp.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryServiceImpl  implements ICategoryService{

    @Autowired
    private CategoryRepository categoryRepo;
    @Override
    public Iterable<Category> findAll() {
      return categoryRepo.findAll();
    }

    @Override
    public Category findCategory(Long id) {
        return  categoryRepo.findOne(id);
    }

    @Override
    public void save(Category category) {
        categoryRepo.save(category);

    }

    @Override
    public void remove(Long id) {
        categoryRepo.delete(id);

    }
}
