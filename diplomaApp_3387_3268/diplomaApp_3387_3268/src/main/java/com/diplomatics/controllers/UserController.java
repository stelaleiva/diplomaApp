package com.diplomatics.controllers;

import com.diplomatics.entity.User;
import com.diplomatics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Controller
public class UserController {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    @Autowired
    private UserService service=new UserService();

    @Autowired
    private UserService userService;
    
    @GetMapping("/myprofile")
    public String showUserList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User listUsers=userService.findExistUser(auth.getName());
        model.addAttribute("listUsers", listUsers);

        return "myprofile";
    }

    @GetMapping("/register")
    public String showNewForm(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("pageTitle", "Εγγραφή Χρήστη");
        return "user_form";
    }

    @GetMapping("/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();

        if (name!="anonymousUser"){
            User activeUser=userService.findExistUser(auth.getName());

            if(user.getPassword().isEmpty()){
                user.setPassword(activeUser.getPassword());
            }else{
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }

            if(!Objects.equals(activeUser.getUserName(), user.getUserName())){
                service.save(user);
                return "redirect:/login";
            }

            if(user.getEnabled()){
                service.save(user);
                return "redirect:/myprofile";
            }
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        service.save(user);
        return "redirect:/login";
    }

    @GetMapping("/users/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model, RedirectAttributes ra) {

        User user = service.get(id);
        model.addAttribute("user", user);
        model.addAttribute("pageTitle", "Επεξεργασία Προφίλ");

        return "user_form_2";
    }

    @PostMapping("/users/exist/{uname}")
    public @ResponseBody String checkUser(@PathVariable("uname") String uname) {

        User user=userService.findExistUser(uname);

        if(user==null){
            return "Όχι";
        }else{

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();

            if (name!="anonymousUser"){
                User activeUser=userService.findExistUser(auth.getName());
                if(activeUser.getUserName()==user.getUserName()){
                    return "Όχι";
                }
            }
            return "Ναι";
        }

    }

    @PostMapping("/users/existFullname/{fname}")
    public @ResponseBody String checkUserFullname(@PathVariable("fname") String fname) {

        User user=userService.findExistUserFullname(fname);
        if(user==null){
            return "Όχι";
        }else{

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            String name = auth.getName();

            if (name!="anonymousUser"){
                User activeUser=userService.findExistUser(auth.getName());
                if(activeUser.getUserName()==user.getUserName()){
                    return "Όχι";
                }
            }
            return "Ναι";
        }

    }
}
