package com.booking.controller;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.booking.entity.FlightDetails;
import com.booking.entity.UserDetails;
import com.booking.service.UserService;
import com.booking.service.UserServiceImpl;

import io.swagger.annotations.ApiOperation;
@CrossOrigin("*")
@Component
@RestController
@RequestMapping("/user")
public class BookingController 
{
	@Autowired
	private UserService userService;
	
	@GetMapping("/getAll")
	@ApiOperation(value="Get all user details who booked their tickets")
	public List<UserDetails> getAll()
	{
		return userService.getAll();
	}
	
	@GetMapping("/get/{pnrNo}")
	@ApiOperation(value="Get user details by Pnr Number")
	public UserDetails getUserDetailsById(@PathVariable long pnrNo)
	{
		return userService.getUserDetailsById(pnrNo);
	}
	
	@PostMapping("/book/add")
	@ApiOperation(value="Book a ticket")
	public String addUserDetails(@Valid @RequestBody UserDetails userDetails)
	{
		RestTemplate restTemplate=new RestTemplate();
		userDetails.setId(UserServiceImpl.getNextSequence(userDetails.SEQUENCE_NAME));
		userDetails.setPnrNo();
		userDetails.setPayment("Pending");
		int flightNo=userDetails.getFlightNo();
		int noOfAdults=userDetails.getAdults();
		int noOfChildren=userDetails.getChildren();
		int totalPassengers=noOfAdults+noOfChildren;
		String classType=userDetails.getClassType();
		restTemplate.getForObject("http://localhost:8081/fare/updateSeats/"+flightNo +"/"+totalPassengers, FlightDetails.class);
		
		return userService.addUserBookingDetails(userDetails);	
	}
	
	@DeleteMapping("/cancel/")
	@ApiOperation(value="Cancel a ticket by PNR number")
	public String deleteUserDetailsById(@RequestParam long pnrNo)
	{
		RestTemplate restTemplate=new RestTemplate();
		restTemplate.getForObject("http://localhost:8083/pay/cancel/"+pnrNo, String.class);
		return userService.deleteUserBookingDetails(pnrNo);
	}
	
	

}