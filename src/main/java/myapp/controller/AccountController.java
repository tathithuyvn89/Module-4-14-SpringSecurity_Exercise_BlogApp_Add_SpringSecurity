package myapp.controller;

import myapp.model.Account;
import myapp.service.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
  public class AccountController
  {
      @Autowired
      private AccountService accountService;
      @GetMapping("/register")
      private String showRegistrationForm(Model model){
          model.addAttribute("user",new Account());
          return "login/register";
      }
      @PostMapping("/register")
      private String saveUser(@Valid @ModelAttribute("user") Account account, BindingResult bindingResult){
          new Account().validate(account,bindingResult);
          if(bindingResult.hasFieldErrors()){
              return "login/register";
          } else {
              if (account.getUsername().equalsIgnoreCase("admin1234")){
                  account.setRole("ROLE_ADMIN");
              } else {
                  account.setRole("ROLE_USER");
              }
              accountService.saveAccount(account);
              return "login/registerSuccess";
          }

      }
      @GetMapping("/login")
      public String goLoginPage(){
          return "login/login";
      }

  }