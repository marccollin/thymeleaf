package com.laboiteaprog.thymeleaf.Controller;

import com.laboiteaprog.thymeleaf.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Marc Collin
 */
@Controller
public class ThymeleafController {

    @GetMapping(value = {"/", "/index"})
    public String getRoot() {

        return "index";
    }

    @GetMapping(value = {"/userform"})
    public String getUserForm(Model model) {

        model.addAttribute("user", new User());

        return "formuser";
    }
    
    @GetMapping(value = {"/userformrest"})
    public String getUserFormRest(Model model) {

        model.addAttribute("user", new User());

        return "formuserrest";
    }

    @PostMapping(value = {"/userform"})
    public String savUserForm(@ModelAttribute User user) {

        //do want you want with value
        return "savingok";
    }

    @PostMapping(value = {"/userformrest"})
    @ResponseBody
    public ResponseEntity savUserFormRest(@RequestBody User user) {

        //do want you want with value
       return new ResponseEntity<>(HttpStatus.OK);
    }

}
