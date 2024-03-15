package com.ashokit.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ashokit.entity.Counsellor;
@EnableJpaRepositories
public interface CounsellorRepository extends JpaRepository<Counsellor,Integer>{
	
	public Counsellor findByCemailAndCpwd(String cemail, String cpwd);
	
	public Counsellor findByCemail(String cemail);

}
