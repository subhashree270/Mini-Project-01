package com.ashokit.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ashokit.binding.SearchCriteria;
import com.ashokit.entity.StudentEnquiry;

@Service
public interface EnquiryService {
	
	public boolean addEnquiry(StudentEnquiry enq);
	
	public List<StudentEnquiry> getEnquiries(Integer cid,SearchCriteria sc);
	
	

}
