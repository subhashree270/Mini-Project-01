package com.ashokit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.ashokit.binding.SearchCriteria;
import com.ashokit.entity.StudentEnquiry;
import com.ashokit.repo.StudentEnquiryRepository;
@Service
public class EnquiryServiceImpl implements EnquiryService {
	@Autowired
	private StudentEnquiryRepository enqRepo;

	@Override
	public boolean addEnquiry(StudentEnquiry se) {
		StudentEnquiry enq = enqRepo.save(se);
		if(enq.getSid()!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<StudentEnquiry> getEnquiries(Integer cid, SearchCriteria sc) {
		//List<StudentEnquiry> enqs = enqRepo.findByCid(cid);
		
		StudentEnquiry enq=new StudentEnquiry();
		enq.setCid(cid);
		
		if(sc.getCourseName()!=null && !sc.getCourseName().equals("")) {
			enq.setCourseName(sc.getCourseName());
		}
		if(sc.getClassMode()!=null && !sc.getClassMode().equals("")) {
			enq.setClassMode(sc.getClassMode());
		}
		if(sc.getStatus()!=null && !sc.getStatus().equals("")) {
			enq.setStatus(sc.getStatus());
		}
		Example<StudentEnquiry> of = Example.of(enq);
		List<StudentEnquiry> enqs = enqRepo.findAll(of);
		
		return enqs;
	}

}
