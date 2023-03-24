package ibf2022.batch2.ssf.frontcontroller.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import ibf2022.batch2.ssf.frontcontroller.models.CaptchaEntry;
import ibf2022.batch2.ssf.frontcontroller.models.Entry;
import ibf2022.batch2.ssf.frontcontroller.services.AuthenticationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class FrontController {

	// TODO: Task 2, Task 3, Task 4, Task 6

	@Autowired
    private AuthenticationService authenticationSvc;

	@GetMapping(path={"/"})
    public String getLanding(Model m , HttpSession sess){
        sess.invalidate();
        CaptchaEntry entry = new CaptchaEntry();
        m.addAttribute("login", entry);
        return "view0";
    }

	@PostMapping(path="/login")
    public String postLogin(Model model , HttpSession sess, @Valid CaptchaEntry login,
        BindingResult bindings){
        if(bindings.hasErrors())
            return "view0";
        

        List<ObjectError> errors = authenticationSvc.validateLogin(login);
        if(!errors.isEmpty()){
            for(ObjectError e: errors)
                bindings.addError(e);
            return "view0";
        }

        // before captcha do the timeout check
    boolean userDisabled = authenticationSvc.isLocked(login.getUsername());
        if (userDisabled == true) {
            return "view2";
        }

        // before check do the captcha
        if (authenticationSvc.needsCaptcha(login.getUsername())) {
            boolean didcaptchawork = login.getCaptcha().equals("4");

            if (didcaptchawork == true) {

                
            } else {
return "view0";
            }
        }

        // Write the HTTP request handler using the AuthenticationService.authenticate method
        try {
        authenticationSvc.authenticate(login.getUsername(), login.getPassword());
        } catch(Exception e) {
            System.out.printf("No authentication");
            model.addAttribute("captcha", "2 + 2");
            return "view0";
        }

        model.addAttribute("log-in", "You are logged in");
        return "view1";
    }

	//@PostMapping(path=)
	
}
