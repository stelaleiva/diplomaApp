package com.diplomatics.controllers;

import com.diplomatics.entity.Applications;
import com.diplomatics.entity.User;
import com.diplomatics.services.ApplicationsService;
import com.diplomatics.services.DiplomaService;
import com.diplomatics.entity.Diploma;
import com.diplomatics.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class DiplomaController {

    @Autowired
    private DiplomaService diplomaService=new DiplomaService();

    @Autowired
    private ApplicationsService applicationsService=new ApplicationsService();

    @Autowired
    private UserService userService=new UserService();

    @GetMapping("/add-diploma")
    public String addDiploma(Model model){
        model.addAttribute("diploma", new Diploma());
        model.addAttribute("pageTitle", "Καταχώρηση Νέας Διπλωματικής");
        return "diploma_form";
    }

    @GetMapping("/diploma")
    public String showDiplomaList(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findExistUser(auth.getName());
        String role=user.getRole();
        List<Diploma> listDiplomas;

        String flag="";
        if(role.equals("STUD")) {
            Applications app=applicationsService.findUsersApp(user.getId());
            if (app!=null) {
                if (app.getSelected().equals("Επιλεχθήκατε")) {
                    flag="no";
                } else {
                    flag="yes";
                }
            }else{
                flag="yes";
            }
            listDiplomas = diplomaService.findDiplomasAvailable();
        }else{
            listDiplomas = diplomaService.findDiplomas(user.getId());
        }
        model.addAttribute("flag", flag);
        model.addAttribute("listDiplomas", listDiplomas);
        model.addAttribute("role", role);

        return "diploma";
    }

    @PostMapping("/diploma/save")
    public String saveDiploma(Diploma diploma, RedirectAttributes ra){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findExistUser(auth.getName());
        if(user!=null){
            diploma.setUser(user.getFull_name());
            diploma.setUser_id(user.getId());
            diploma.setAvailable("Ναι");
            ra.addFlashAttribute("message", "Η Διπλωματική καταχωρήθηκε επιτυχώς");
        }
        diplomaService.save(diploma);
        return "redirect:/diploma";
    }

    @GetMapping("/diploma/info/{id}")
    public String diplomaInfo(@PathVariable("id")Integer id, Model model)  {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findExistUser(auth.getName());
        String role=user.getRole();

        Diploma diploma=diplomaService.getDiploma(id);
        model.addAttribute("diploma", diploma);
        model.addAttribute("role", role);
        model.addAttribute("info", "Λεπτομέρειες Διπλωματικής Εργασίας " + diploma.getTitle() );
        return "diploma_info";

    }

    @GetMapping("/diploma/edit/{id}")
    public String diplomaEdit(@PathVariable("id") Integer id, Model model){
        Diploma diploma=diplomaService.getDiploma(id);
        model.addAttribute("diploma", diploma);
        model.addAttribute("pageTitle", "Επεξεργασία Διπλωματικής");
        return "diploma_form";
    }

    @GetMapping("/diploma/delete/{id}")
    public String diplomaDelete(@PathVariable("id") Integer id, RedirectAttributes ra){
        diplomaService.delete(id);
        List<Applications> apps=applicationsService.findByDiploma(id);
        if(apps!=null) {
            if (!apps.isEmpty()) {
                for (int i = 0; i < apps.size(); i++) {
                    applicationsService.delete(apps.get(i));
                }
            }
            ra.addFlashAttribute("message", "Η Διπλωματική διαγράφηκε επιτυχώς");
        }

        return "redirect:/diploma";
    }

    @PostMapping("/diploma/existDiploma")
    @ResponseBody
    public String checkDiploma(@RequestBody Diploma diploma) {

        String title = diploma.getTitle();

        Diploma diplomaExist=diplomaService.findDiploma(title);

        if(diplomaExist==null){
            return "{\"message\":\"" + "Όχι" + "\"}";
        }else{
            if(diploma.getDiploma_id()==diplomaExist.getDiploma_id()){
                return "{\"message\":\"" + "Όχι" + "\"}";
            }
            return "{\"message\":\"" + "Ναι" + "\"}";
        }
    }
}
