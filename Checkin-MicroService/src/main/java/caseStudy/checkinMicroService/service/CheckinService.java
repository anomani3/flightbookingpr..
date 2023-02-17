package caseStudy.checkinMicroService.service;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import caseStudy.checkinMicroService.entity.CheckinDetails;

public interface CheckinService {
	
	public List<CheckinDetails> getAllCheckins(); 
	public void addDetails(CheckinDetails userDetails);
	public CheckinDetails findByPnrNo(long pnrNo);
}
