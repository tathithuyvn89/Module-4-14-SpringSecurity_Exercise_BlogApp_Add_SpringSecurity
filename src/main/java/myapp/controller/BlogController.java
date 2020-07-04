package myapp.controller;

import myapp.model.Blog;
import myapp.model.Category;
import myapp.service.blog.BlogService;
import myapp.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;
import java.util.Random;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private ICategoryService categoryService;
    @ModelAttribute("categories")
    private Iterable<Category> showCategories(){
        return categoryService.findAll();
    }
    @GetMapping("/bloglist")
    private String showList(@RequestParam("s")Optional<String> s, @PageableDefault(size = 2) Pageable pageable, Model model){
        Page<Blog> blogs;
        if(s.isPresent()){
            blogs= blogService.findAllByNameContaining(s.get(),pageable);
        } else {
            blogs= blogService.findAll(pageable);

        }
        model.addAttribute("blogs",blogs);
        return "blogs/list";
    }

    @GetMapping("/create-blog")
    private ModelAndView showCreateBlogForm(){

        ModelAndView modelAndView = new ModelAndView("blogs/create");
        modelAndView.addObject("blog",new Blog());
        return modelAndView;
    }
    @PostMapping("/create-blog")
    public ModelAndView saveCustomer(@ModelAttribute("blog") Blog blog){
        blog.setLikes(0);
        Random random = new Random();
        Long id= random.nextLong();
        blog.setId(id);
        blogService.saveBlog(blog);
        ModelAndView modelAndView = new ModelAndView("blogs/create");
        modelAndView.addObject("blog", new Blog());
        modelAndView.addObject("message","New Blog create successfully");
        return modelAndView;
    }
    @GetMapping("/bloglist/{id}/delete")
    private String deleteBlog(@PathVariable long id){
        blogService.deleteBlog(id);
        return "redirect:/create-blog";
    }
    @GetMapping("/bloglist/{id}/edit")
    private String editBlog(@PathVariable Long id, Model model){
        Blog blog = blogService.findBlog(id);
        model.addAttribute("blog",blog);
        return "blogs/edit";
    }
    @PostMapping("/edit")
    public String edit(Blog blog , RedirectAttributes redirect){
        blogService.updateBlog(blog);
        redirect.addFlashAttribute("success","Updatesuccess");
        return "redirect:/bloglist";
    }
    @GetMapping("/bloglist/{id}/view")
    private String viewBlog(@PathVariable Long id, Model model){
        Blog blog = blogService.findBlog(id);
        model.addAttribute("blog",blog);
        return "blogs/view";
    }
    @GetMapping("/bloglist/sortByDay")
    private String showBlogSortByDay( @PageableDefault(size = 2)Pageable pageable, Model model){
        Page<Blog> blogs=blogService.findBlogsByOrderByDate(pageable);
        model.addAttribute("blogs",blogs);
        return "blogs/list";
    }
    @GetMapping("/bloglist/{name}")
    private ModelAndView showCategory(@PathVariable String name,@PageableDefault(size = 2)Pageable pageable){
        Page<Blog> blogs=blogService.findBlogsByCategory_NameOrderByDate(name,pageable);
        ModelAndView modelAndView = new ModelAndView("blogs/list");
        modelAndView.addObject("blogs",blogs);
        return modelAndView;

    }
    @GetMapping("/bloglist/{id}/like")
    private ModelAndView showListByLike(@PathVariable Long id,@PageableDefault(size = 2)Pageable pageable){
        ModelAndView modelAndView = new ModelAndView("blogs/list");
        Blog blog= blogService.findBlog(id);
        blog.setLikes(blog.getLikes()+1);
        blogService.saveBlog(blog);
        Page<Blog> blogs= blogService.findAll(pageable);
        modelAndView.addObject("blogs",blogs);
        return modelAndView;
    }

}
