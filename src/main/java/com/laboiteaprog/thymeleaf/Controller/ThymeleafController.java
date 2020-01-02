package com.laboiteaprog.thymeleaf.Controller;

import com.laboiteaprog.thymeleaf.model.User;
import com.laboiteaprog.thymeleaf.model.UserType;
import com.laboiteaprog.thymeleaf.service.PdfGeneratorUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

    @Autowired
    private PdfGeneratorUtil pdfGeneratorUtil;

    @GetMapping(value = {"/", "/index"})
    public String getRoot() {

        return "index";
    }

    @GetMapping(value = {"/ajaxfragment"})
    public String getAjaxFragment(Model model) {

        List<User> users = new ArrayList<>();

        users.add(new User("Yvan", "Dubois"));
        users.add(new User("Yvon", "Couler"));
        users.add(new User("Ytord", "Lamope"));

        model.addAttribute("users", users);

        return "fragments/ajax::Ajax";
    }

    @GetMapping(value = {"generatepdfreport"})
    public ResponseEntity<byte[]> getPdfReport(Model model) throws Exception {

        List<User> users = new ArrayList<>();

        users.add(new User("Yvan", "Dubois"));
        users.add(new User("Yvon", "Couler"));
        users.add(new User("Ytord", "Lamope"));

        byte[] content = pdfGeneratorUtil.process("usersReport", "html", users, "users");

        return preparePdfReport(content);
    }

    private ResponseEntity<byte[]> preparePdfReport(byte[] content) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String fileName = "report.pdf";

        headers.add("Content-Disposition", "inline;filename=" + fileName);
        headers.setCacheControl("no-cache, must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<>(content, headers, HttpStatus.OK);
        return response;
    }

    @GetMapping(value = {"generatereportdemo"})
    public String getReport(Model model) {

        List<User> users = new ArrayList<>();

        users.add(new User("Yvan", "Dubois"));
        users.add(new User("Yvon", "Couler"));
        users.add(new User("Ytord", "Lamope"));

        model.addAttribute("users", users);

        return "generatereportdemo";
    }

    @GetMapping(value = {"/ajaxdemo"})
    public String getAjaxDemo() {
        return "ajaxdemo";
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
