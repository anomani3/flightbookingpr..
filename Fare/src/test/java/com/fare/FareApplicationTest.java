package com.fare;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.fare.entity.FlightDetails;
import com.fare.repository.FareRepository;
import com.fare.service.FareServiceImpl;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FareApplicationTest {
	@Autowired
	private FareServiceImpl fareService;
	@MockBean
	private FareRepository fareRepo;

	 @Test
	@DisplayName("Testing whether flight details database is empty")
	public void getAllDetailsTest1() {
	List<FlightDetails> details = fareService.getAllDetails();
	assertTrue(details.isEmpty());
	}

	 @Test
	@DisplayName("Testing whether getAllDetails method is returning the records of db")
	public void getAllDetailsTest2() { // Added flight details
	List<FlightDetails> detailsList = new ArrayList<FlightDetails>();
	FlightDetails details = new FlightDetails(123, "Mumbai", "Pune", "9:00AM", "5:00PM", "8H", 50, 1000, 750, 500,
	"1-JAN-2022");
	detailsList.add(details);
	// checking whether it returns correct values
	when(fareRepo.findAll()).thenReturn(detailsList);
	List<FlightDetails> detailsListNew = fareService.getAllDetails();
	assertEquals(1, detailsListNew.size());
	}

	// @Test
	// @DisplayName("Testing getFlightDetailsByFlightNo method")
	// public void getDetailsByflightNoTest1() {
	// // Added flight details
	// Optional<FlightDetails> details = Optional.of(
	// new FlightDetails(123, "Mumbai", "Pune", "9:00AM", "5:00PM", "8H", 50, 1000, 750, 500, "1-JAN-2022"));
	// // Checking whether they are returning correct values or not
	// when(fareRepo.findById(123)).thenReturn(details);
	// FlightDetails det = fareRepo.findByFlightNo(123);
	// assertEquals("Mumbai", det.getStartPoint());
	// assertEquals("Pune", det.getEndPoint());
	// assertEquals("9:00AM", det.getArrivalTime());
	// assertEquals("5:00PM", det.getDeptTime());
	// assertEquals("8H", det.getDuration());
	// assertEquals(50, det.getNoOfSeats());
	// assertEquals(1000, det.getEconomyClass());
	// assertEquals(750, det.getPremiumEconomy());
	// assertEquals(500, det.getBusinessClass());
	// assertEquals("1-JAN-2022", det.getFlightDate());
	// }

	 @Test
	@DisplayName("Testing addFlightDetails method")
	public void addFlightDetailsTest1() {
	// Added flight details
	FlightDetails details = new FlightDetails(123, "Mumbai", "Pune", "9:00AM", "5:00PM", "8H", 50, 1000, 750, 500,
	"1-JAN-2022");
	fareService.addFlightDetails(details);
	// checking whether flight details are added or not
	verify(fareRepo, times(1)).save(details);
	}

}
