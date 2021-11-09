package com.example.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
@Controller
public class UserController  {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model,RedirectAttributes redirectAttributes){
        List<User> listUsers=service.listAll();
        User user=new User();
        model.addAttribute("listUsers",listUsers);
        model.addAttribute("user",user);
        redirectAttributes.addFlashAttribute("modal","AddUserModal");
        model.addAttribute("title","Add User");
        return "users";
    }
    @PostMapping("/user/save")
    public String saveUser(User user, RedirectAttributes redirectAttributes){
        service.save(user);
        redirectAttributes.addFlashAttribute("message","User Successfully Added..");
        return "redirect:/users";
    }
    @RequestMapping("/user/delete/{id}")
    public String delete(@PathVariable("id") Integer userId,Model model,RedirectAttributes redirectAttributes){
        service.delete(userId);
        redirectAttributes.addFlashAttribute("message","User Successfully Deleted");
        return "redirect:/users";
    }

    @RequestMapping("/user/edit/{id}")
    public String edit(@PathVariable("id") Integer userId,Model model,RedirectAttributes redirectAttributes){
        try {
            User user = service.get(userId);
            model.addAttribute("user", user);
            List<User> listUsers=service.listAll();
            model.addAttribute("listUsers",listUsers);
            redirectAttributes.addAttribute("edit",1);
            model.addAttribute("title", "Edit User(Id :"+userId+")");
            return "users";
        } catch (UserNotFoundException e){
            redirectAttributes.addFlashAttribute("message","User Successfully Added..");
            return "redirect:/users";

        }

    }

}
