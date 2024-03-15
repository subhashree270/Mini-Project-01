package com.ashokit.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ashokit.binding.DashboardResponse;
import com.ashokit.entity.Counsellor;
import com.ashokit.entity.StudentEnquiry;
import com.ashokit.repo.CounsellorRepository;
import com.ashokit.repo.StudentEnquiryRepository;
import com.ashokit.utils.EmailUtils;
@Service
public class CounsellorImplService implements CounsellorService {
     @Autowired
	private CounsellorRepository crepo;
     
     @Autowired
     private StudentEnquiryRepository enqRepo;
     
     @Autowired
     private EmailUtils emailUtils;
	
	@Override
	public String saveCounsellor(Counsellor c) {
		//verify duplicate email
		Counsellor obj= crepo.findByCemail(c.getCemail());
		if(obj!=null) {
			return "Duplicate Email";
		}
		Counsellor savedObj = crepo.save(c);
		if(savedObj.getCid()!=null) {
			return "Registration Successful";
		}
		return "Registration Failed";
	}

	@Override
	public Counsellor loginCheck(String email,String pwd) {
		return crepo.findByCemailAndCpwd(email,pwd);
	}

	@Override
	public boolean recoverPwd(String email) {
		Counsellor c= crepo.findByCemail(email);
		if(c==null) {
		 return false;
		}
		String subject="Recover Password : AshokIT";
		String body="<h1> Your Password : "+c.getCpwd()+"<h1>";
		return emailUtils.sendEmail(subject, body, email);
	}
	@Override
	public DashboardResponse getDashboardInfo(Integer cid) {
		List<StudentEnquiry> allEnqs = enqRepo.findByCid(cid);
		int enrolledEnqs = allEnqs.stream().filter(e->e.getStatus().equals("enrolled"))
		                   .collect(Collectors.toList()).size();
		DashboardResponse resp=new DashboardResponse();
		resp.setTotalEnq(allEnqs.size());
		resp.setEnrolledEnq(enrolledEnqs);
		resp.setLostEnq(allEnqs.size()-enrolledEnqs);
		
		return resp;
		
	}

}
