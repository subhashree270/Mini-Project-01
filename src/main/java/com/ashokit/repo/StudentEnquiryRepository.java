package com.ashokit.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entity.StudentEnquiry;

public interface StudentEnquiryRepository extends JpaRepository<StudentEnquiry,Integer> {
  
	public List<StudentEnquiry> findByCid(Integer cid);
}
