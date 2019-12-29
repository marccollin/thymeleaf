package com.laboiteaprog.thymeleaf.Controller;

import com.laboiteaprog.thymeleaf.model.User;
import com.laboiteaprog.thymeleaf.model.UserType;
import java.util.ArrayList;
import java.util.List;
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

        List<UserType> userTypes = new ArrayList<>();

        UserType userType1 = new UserType();
        userType1.setId(1l);
        userType1.setType("Admin");

        UserType userType2 = new UserType();
        userType2.setId(2l);
        userType2.setType("Standard");

        UserType userType3 = new UserType();
        userType3.setId(3l);
        userType3.setType("Invité");

        model.addAttribute("user", new User());
        model.addAttribute("userTypes", userTypes);

        userTypes.add(userType1);
        userTypes.add(userType2);
        userTypes.add(userType3);

        return "formuser";
    }

    @GetMapping(value = {"/userformrest"})
    public String getUserFormRest(Model model) {

        model.addAttribute("user", new User());

        return "formuserrest";
    }

    @PostMapping(value = {"/userform"})
    public String savUserForm(@ModelAttribute User user) {

        System.out.println(user.toString());

        //do want you want with value
        return "savingok";
    }

    @PostMapping(value = {"/userformrest"})
    @ResponseBody
    public ResponseEntity savUserFormRest(@RequestBody User user) {

        System.out.println(user.toString());

        //do want you want with value
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
