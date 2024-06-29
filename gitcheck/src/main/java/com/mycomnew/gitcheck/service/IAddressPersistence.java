package com.mycomnew.gitcheck.service;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mycomnew.gitcheck.entities.address;

public interface IAddressPersistence extends JpaRepository<address, Long>{

}
