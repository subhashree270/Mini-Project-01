package com.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ashokit.binding.DashboardResponse;
import com.ashokit.entity.Counsellor;
import com.ashokit.service.CounsellorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class CounsellorController {
	
	@Autowired
	private CounsellorService service;
	
	@GetMapping("/logout")
	public String logout(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession(false);
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/")
	public String index(Model model) { //loading login page
		model.addAttribute("counsellor", new Counsellor());
		return"loginView";
	}
	@PostMapping("/login")
	public String handleLogin(Counsellor c,HttpServletRequest req,Model model) {
		Counsellor obj = service.loginCheck(c.getCemail(),c.getCpwd());
		if(obj==null) {
		model.addAttribute("errMsg","Invalid Credentials..." );
		return "loginView";
		}
		
		HttpSession session=req.getSession(true);
		session.setAttribute("CID", obj.getCid());
		return "redirect:dashboard";
	}
	@GetMapping("/dashboard")
	public String buildDashboard(Model model,HttpServletRequest req) {
		HttpSession session=req.getSession(false);
		Object obj = session.getAttribute("CID");
		Integer cid=(Integer)obj;
		if(cid==null) {
			return "redirect:/";
		}
		
		DashboardResponse dashboardInfo = service.getDashboardInfo(cid);
		model.addAttribute("dashboard",dashboardInfo );
		return "dashboard";
	}
	
	@GetMapping("/register")
	public String regPage(Model model) { //loading registration page
		model.addAttribute("counsellor", new Counsellor());
		return "registerView";
	}
	@PostMapping("/register")
	public String handleRegistration(Counsellor c,Model model) {
		String msg = service.saveCounsellor(c);
		model.addAttribute("msg", msg);
		return "registerView";
	}
	
	@GetMapping("/forgotpwd")
	public String recoverPwd(Model model) { //loading forgot password page	
		return "recoverPwdView";
	}
	@GetMapping("/recoverpwd")
	public String handleRecoverPwd(@RequestParam String email,Model model) {
		boolean status = service.recoverPwd(email);
	    if(status) {
	    	model.addAttribute("smsg", "Password sent to your registered email...");
	    }
	    else {
	    	model.addAttribute("emsg", "Invalid email id...");
	    }
		return "recoverPwdView";
	}
	
	


}
