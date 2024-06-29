package com.mycomnew.gitcheck.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.mycomnew.gitcheck.entities.Vendor;
import com.mycomnew.gitcheck.service.vendorService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class VendorController {
	@Autowired
	vendorService vendorService;
	
	@RequestMapping("/vendor")
	public List<Vendor> getVendors(){
		return vendorService.readAllVendors();
			
	}
	@RequestMapping("/vendor/{vendorCode}")
	public Vendor getVendorById(@PathVariable("vendorCode") Long code) {
		Optional<Vendor> searchResult = vendorService.getSingleVendor(code);
		if (!searchResult.isPresent()) {
			return new Vendor((long)0,"","","","", null, null, null);
		}
		return searchResult.get();
	}
	@PostMapping("/vendor")
	public Vendor createVendor(@RequestBody Vendor myPostBody) {
		return vendorService.createVendor(myPostBody);
	}
	
	@RequestMapping("/vendor/search")
	public List<Vendor> seachByCompany(@RequestParam String company){
		return vendorService.searchByCompanyName(company);
	}
	
//	@RequestMapping("/vendor/lookup")
//	public List<Vendor> searchVendorByGST(@RequestParam String GSTNo){
//		return vendorService.lookupVendorByGST(GSTNo);
//	}
	
	@RequestMapping("/vendor/lookup/{gstNo}")
	public List<Vendor> searchVendorByGST(@PathVariable("gstNo") String GSTNo){
		return vendorService.lookupVendorByGST(GSTNo);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/vendor")
	public Vendor updateVendor(@RequestBody Vendor vendor) {
		return vendorService.changeVendor(vendor);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value="/vendor/{id}")
	public String removeVendor(@PathVariable("id") Long Id) {
		return vendorService.deleteVendor(Id);
	}

}
