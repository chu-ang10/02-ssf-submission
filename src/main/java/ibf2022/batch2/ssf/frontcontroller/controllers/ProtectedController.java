package ibf2022.batch2.ssf.frontcontroller.controllers;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProtectedController {

	// TODO Task 5
	// Write a controller to protect resources rooted under /protected

	@GetMapping(path={"/protected/{orderId}"})
    public String getLanding(Model m , HttpSession sess, @PathVariable String orderId){
        sess.invalidate();
		if(m.getAttribute("log-in") != null) {
			return "/protected/"+orderId;
		}
        return "view0";
    }
}
