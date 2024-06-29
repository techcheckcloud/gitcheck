package com.mycomnew.gitcheck.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mycomnew.gitcheck.entities.address;
import com.mycomnew.gitcheck.service.addressService;

@RestController
public class addressController {
	@Autowired
	addressService addSrv;
	
@RequestMapping("/address")
public List<address> readAllAddress(){
	return addSrv.getAddress();
}

@PostMapping("/address")
public address createAddress(@RequestBody address payload) {
	return addSrv.createAddress(payload);
}

}
