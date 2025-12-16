package com.diplomatics.controllers;

import com.diplomatics.entity.Applications;
import com.diplomatics.entity.Diploma;
import com.diplomatics.entity.Supervised;
import com.diplomatics.entity.User;

import com.diplomatics.services.ApplicationsService;

import com.diplomatics.services.DiplomaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Controller
public class ApplicationsController {

    @Autowired
    private UserService userService=new UserService();

    @Autowired
    private DiplomaService diplomaService = new DiplomaService();

    @Autowired
    private ApplicationsService applicationsService = new ApplicationsService();

    @Autowired
    private SupervisedService supervisedService = new SupervisedService();


    @GetMapping ("/applications")
    public String showApplications(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findExistUser(auth.getName());
        String role=user.getRole();

        List<Applications> listApplications;

        if(role.equals("STUD")) {
            listApplications = applicationsService.getApplication(user.getId());
        }else{
            listApplications = applicationsService.getApplicationStud(user.getFull_name());
        }
        model.addAttribute("listApplications", listApplications);
        model.addAttribute("role", role);

        return "applications";
    }

    @GetMapping("/applications/info/{id}")
    public String showAppInfo(@PathVariable("id")Integer id, Model model){
        Diploma diploma=diplomaService.getDiploma(id);
        List<User> listUsers;
        if(diploma.getAvailable().equals("Όχι")){
            listUsers = userService.getStudentsReady(id);
        }else {
            listUsers = userService.getAppStudents(id);
        }
        model.addAttribute("listUsers", listUsers);
        model.addAttribute("diploma", diploma);
        model.addAttribute("id", id);
        return "applications_info";
    }

    @GetMapping("/applications/apply/{id}")
    public String applyApp(@PathVariable("id")Integer id, RedirectAttributes ra) {

        Diploma diploma = diplomaService.getDiploma(id);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user=userService.findExistUser(auth.getName());
        Integer uId=user.getId();
        Applications appExists=applicationsService.getApplicationAgain(id,uId);
        if (appExists==null) {
            Applications app = new Applications();
            app.setDiploma(diploma.getTitle());
            app.setDiploma_id(diploma.getDiploma_id());
            app.setStudent(user.getFull_name());
            app.setStudent_id((user.getId()));
            app.setSelected("Σε Αναμονή");
            applicationsService.save(app);
            ra.addFlashAttribute("message", "Η αίτησή σας πραγματοποιήθηκε.");
            return "redirect:/diploma";
        }else{
            ra.addFlashAttribute("message", "Έχετε υποβάλει ήδη αίτηση για την υπόψη διπλωματική εργασία.");
            return "redirect:/diploma";
        }
    }

   @GetMapping("/applications/random/{id}")
   public String randomApp(@PathVariable("id")Integer id){

        Diploma diploma = diplomaService.getDiploma(id);
        List<User> listUsers;
        listUsers = userService.getAppStudents(id);

        Random rand = new Random();
        User randomUser = listUsers.get(rand.nextInt(listUsers.size()));
        Applications app=applicationsService.applyFinalUser(randomUser.getId(), id);
        Supervised supervised = new Supervised(app.getDiploma_id(),
                app.getDiploma(), randomUser.getId(), randomUser.getFull_name(), diploma.getUser_id(), diploma.getUser());

        supervisedService.save(supervised);

        diploma.setAvailable("Όχι");
        diplomaService.save(diploma);

        List<Applications> users = applicationsService.applyUsers(randomUser.getId(), id);
        if (!users.isEmpty()) {
           for (int i = 0; i < users.size(); i++) {
               users.get(i).setSelected("Δεν επιλεχθήκατε");
               applicationsService.save(users.get(i));
           }
        }
        // για τον επιλεγέντα μαθητή κάνω όλα τα applications "Δεν επιλεχθήκατε"
        List<Applications> applications = applicationsService.dontApplyUser(randomUser.getId());
        if (!applications.isEmpty()) {
           for (int i = 0; i < applications.size(); i++) {
               applications.get(i).setSelected("Δεν επιλεχθήκατε");
               applicationsService.save(applications.get(i));
           }
        }

       // για τον επιλεγέντα μαθητή κάνω όλα το επιλεχθεν application "Επιλεχθήκατε"
        app.setSelected("Επιλεχθήκατε");
        applicationsService.save(app);
        return "redirect:/supervised";

   }

    @GetMapping("/applications/bestgrade/{id}")
    public String bestGradeApp(@PathVariable("id")Integer id){

        Diploma diploma = diplomaService.getDiploma(id);
        List<User> listUsers;
        listUsers = userService.getAppStudents(id);
        int flag=0;
        double grade= 0;
        for(int i=0; i<listUsers.size(); i++){
            BigDecimal stu_grade=listUsers.get(i).getAvg_grade();
            if (stu_grade.doubleValue()>grade){
                grade=stu_grade.doubleValue();
                flag=i;
            }
        }

        Applications app=applicationsService.applyFinalUser(listUsers.get(flag).getId(), id);

        Supervised supervised = new Supervised(app.getDiploma_id(),
                app.getDiploma(), listUsers.get(flag).getId(), listUsers.get(flag).getFull_name(), diploma.getUser_id(), diploma.getUser());

        supervisedService.save(supervised);

        diploma.setAvailable("Όχι");
        diplomaService.save(diploma);

        List<Applications> users = applicationsService.applyUsers(listUsers.get(flag).getId(), id);
        if (!users.isEmpty()) {
            for (int i = 0; i < users.size(); i++) {
                users.get(i).setSelected("Δεν επιλεχθήκατε");
                applicationsService.save(users.get(i));
            }
        }
        // για τον επιλεγέντα μαθητή κάνω όλα τα applications "Δεν επιλεχθήκατε"
        List<Applications> applications = applicationsService.dontApplyUser(listUsers.get(flag).getId());
        if (!applications.isEmpty()) {
            for (int i = 0; i < applications.size(); i++) {
                applications.get(i).setSelected("Δεν επιλεχθήκατε");
                applicationsService.save(applications.get(i));
            }
        }
        // για τον επιλεγέντα μαθητή κάνω όλα το επιλεχθεν application "Επιλεχθήκατε"
        app.setSelected("Επιλεχθήκατε");
        applicationsService.save(app);
        int super_id=supervised.getSuper_id();
        return "redirect:/supervised";
    }

    @GetMapping("/applications/fewcourses/{id}")
    public String fewCoursesApp(@PathVariable("id")Integer id){

        Diploma diploma = diplomaService.getDiploma(id);
        List<User> listUsers;
        listUsers = userService.getAppStudents(id);
        int flag=0;
        int courses= 100;
        for(int i=0; i<listUsers.size(); i++){
            int coursesUser=listUsers.get(i).getRemain_courses();
            if (coursesUser<courses){
                courses=coursesUser;
                flag=i;
            }
        }

        Applications app=applicationsService.applyFinalUser(listUsers.get(flag).getId(), id);

        Supervised supervised = new Supervised(app.getDiploma_id(),
                app.getDiploma(), listUsers.get(flag).getId(), listUsers.get(flag).getFull_name(), diploma.getUser_id(), diploma.getUser());

        supervisedService.save(supervised);

        diploma.setAvailable("Όχι");
        diplomaService.save(diploma);

        List<Applications> users = applicationsService.applyUsers(listUsers.get(flag).getId(), id);
        if (!users.isEmpty()) {
            for (int i = 0; i < users.size(); i++) {
                users.get(i).setSelected("Δεν επιλεχθήκατε");
                applicationsService.save(users.get(i));
            }
        }
        // για τον επιλεγέντα μαθητή κάνω όλα τα applications "Δεν επιλεχθήκατε"
        List<Applications> applications = applicationsService.dontApplyUser(listUsers.get(flag).getId());
        if (!applications.isEmpty()) {
            for (int i = 0; i < applications.size(); i++) {
                applications.get(i).setSelected("Δεν επιλεχθήκατε");
                applicationsService.save(applications.get(i));
            }
        }

        // για τον επιλεγέντα μαθητή κάνω όλα το επιλεχθεν application "Επιλεχθήκατε"
        app.setSelected("Επιλεχτήκατε");
        applicationsService.save(app);

        return "redirect:/supervised";
    }

    @PostMapping("/applications/set")
    public String setApp(@RequestParam int id, @RequestParam BigDecimal grade, @RequestParam Integer courses ){

        Diploma diploma = diplomaService.getDiploma(id);
        List<User> listUsers;
        listUsers = userService.getAppStudentsSets(id, grade, courses);

        if (listUsers.isEmpty()){
            return "redirect:/applications/info/"+id;
        }
        Random rand = new Random();
        User randomUser = listUsers.get(rand.nextInt(listUsers.size()));
        Applications app=applicationsService.applyFinalUser(randomUser.getId(), id);
        Supervised supervised = new Supervised(app.getDiploma_id(),
                app.getDiploma(), randomUser.getId(), randomUser.getFull_name(), diploma.getUser_id(), diploma.getUser());

        supervisedService.save(supervised);

        diploma.setAvailable("Όχι");
        diplomaService.save(diploma);

        List<Applications> users = applicationsService.applyUsers(randomUser.getId(), id);
        if (!users.isEmpty()) {
            for (int i = 0; i < users.size(); i++) {
                users.get(i).setSelected("Δεν επιλεχθήκατε");
                applicationsService.save(users.get(i));
            }
        }
        // για τον επιλεγέντα μαθητή κάνω όλα τα applications "Δεν επιλεχθήκατε"
        List<Applications> applications = applicationsService.dontApplyUser(randomUser.getId());
        if (!applications.isEmpty()) {
            for (int i = 0; i < applications.size(); i++) {
                applications.get(i).setSelected("Δεν επιλεχθήκατε");
                applicationsService.save(applications.get(i));
            }
        }

        // για τον επιλεγέντα μαθητή κάνω όλα το επιλεχθεν application "Επιλεχθήκατε"
        app.setSelected("Επιλεχθήκατε");
        applicationsService.save(app);
        return "redirect:/supervised";
    }

}
