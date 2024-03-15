package com.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.ashokit.binding.SearchCriteria;
import com.ashokit.entity.StudentEnquiry;
import com.ashokit.service.EnquiryService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class StudentEnquiryController {
	@Autowired
	private EnquiryService enqService;
	
	@GetMapping("/enq")
	public String enqPage(Model model) { //loading enquiry page
		model.addAttribute("se", new StudentEnquiry());
		return "addEnqView";
	}
	@PostMapping("/enq")
	public String addEnquiry(@ModelAttribute("se") StudentEnquiry se,HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		Integer cid=(Integer)session.getAttribute("CID");
		
		se.setCid(cid);
		
		boolean addEnq = enqService.addEnquiry(se);
		if(addEnq) {
			model.addAttribute("smsg", "Enquiry added...");
		}
		else {
			model.addAttribute("emsg", "Failed to add...");
		}
		return "addEnqView";
	}
	
	@GetMapping("/enqs")
	public String viewEnquiries(HttpServletRequest req,Model model) {
		HttpSession session = req.getSession(false);
		Integer cid=(Integer)session.getAttribute("CID");
		if(cid==null) {
			return "redirect:logout";
		}
		
		model.addAttribute("sc", new SearchCriteria());
		
		List<StudentEnquiry> enquiries = enqService.getEnquiries(cid, new SearchCriteria());
		model.addAttribute("enquiries", enquiries);
		return"displayEnqView";
	}
	@PostMapping("/filter-enqs")
	public String filterEnquiries(@ModelAttribute("sc") SearchCriteria sc,HttpServletRequest req,Model model) {
		HttpSession session=req.getSession(false);
		Integer cid=(Integer)session.getAttribute("CID");
		
		if(cid==null) {
			return "redirect:/";
		}
		
		
		List<StudentEnquiry> enquiries = enqService.getEnquiries(cid, sc);
		model.addAttribute("enquiries", enquiries);
		return "filterEnqView";
	}
	
}
