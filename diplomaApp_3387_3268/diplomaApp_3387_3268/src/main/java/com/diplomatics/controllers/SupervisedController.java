package com.diplomatics.controllers;

import com.diplomatics.entity.Supervised;
import com.diplomatics.entity.User;
import com.diplomatics.dao.UserDao;
import com.diplomatics.services.SupervisedService;
import com.diplomatics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SupervisedController {

    @Autowired
    private SupervisedService supervisedService=new SupervisedService();

    @Autowired
    private UserService userService;

    @GetMapping("/supervised")
    public String showSupervised(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findExistUser(auth.getName());
        String role=user.getRole();
        List<Supervised> listSupervised;

        if(role.equals("STUD")) {
            listSupervised=supervisedService.findSupervised(user.getId());
        }else{
            listSupervised=supervisedService.findSupervisedStu(user.getId());
        }

        model.addAttribute("listSupervised", listSupervised);
        model.addAttribute("role", role);

        return "supervised";
    }

    @GetMapping("/supervised/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {

        Supervised supervised= supervisedService.findSupervisedById(id);
        model.addAttribute("supervised", supervised);
        model.addAttribute("pageTitle", "Βαθμολόγηση Διπλωματικής : " + supervised.getDiploma());

        return "supervised_form";
    }

    @PostMapping("/supervised/save")
    public String saveForm(Supervised supervised, RedirectAttributes ra) {
        supervisedService.save(supervised);
        if(ra!=null) {
            ra.addAttribute("message", "Επιτυχής βαθμολόγηση");
        }
        return "redirect:/supervised";
    }

}
