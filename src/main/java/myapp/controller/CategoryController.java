package myapp.controller;

import myapp.model.Category;
import myapp.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping("/categories")
    private ModelAndView listProvince(){
        Iterable<Category> categories= categoryService.findAll();
        ModelAndView modelAndView = new ModelAndView("category/list");
        modelAndView.addObject("categories",categories);
        return modelAndView;
    }
    @GetMapping("/create-category")
    private ModelAndView showFromCreate(){
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category", new Category());
        return modelAndView;
    }
    @PostMapping("/create-category")
    private ModelAndView saveProvince(@ModelAttribute("category") Category province){
        categoryService.save(province);
        ModelAndView modelAndView = new ModelAndView("category/create");
        modelAndView.addObject("category",new Category());
        modelAndView.addObject("message","Create new Province successfully");
        return modelAndView;

    }
    @GetMapping("/edit-category/{id}")
    private String showFormEdit(@PathVariable("id") Long id, Model model){
        Category category = categoryService.findCategory(id);
        if(category!=null){
            model.addAttribute("category",category);
            return "category/edit";
        } else {
            return "error.404";
        }
    }
    @PostMapping("/edit-category")
    private String updateProvince(@ModelAttribute("category") Category category, Model model){
        categoryService.save(category);
        model.addAttribute("category",category);
        model.addAttribute("message","Update new infomation successfully");
        return "category/edit";

    }
    @GetMapping("/delete-category/{id}")
    private String showFormDelete(@PathVariable("id") Long id, Model model){
        Category category = categoryService.findCategory(id);
        if(category!=null){
            model.addAttribute("category",category);
            return "category/delete";
        } else {
            return "error.404";
        }
    }
    @PostMapping("/delete-category")
    public String deleteProvince(@ModelAttribute("category") Category category){
        categoryService.remove(category.getId());
        return "redirect:/categories";
    }

}
