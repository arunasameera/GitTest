package com.aruna.dbservices.services;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import com.aruna.dbservices.model.Shift;
import com.aruna.dbservices.model.ShiftModel;
import com.aruna.dbservices.repository.ShiftRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/rest/db")
@EnableJpaRepositories("com.aruna.dbservices.repository")
@ComponentScan(basePackages = { "com.aruna.dbservices.model" })
@EntityScan(basePackages = "com.aruna.dbservices.model")
public class DBServiceResource {

	@Autowired
	private ShiftRepository shiftRepository;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private static final Logger LOGGER = LoggerFactory.getLogger(DBServiceResource.class);

	
	
	
	private  ArrayList<String> shiftList=new ArrayList<String>();

    
   

	@GetMapping("/{shiftname")
	//@GetMapping
    public String getShifts(@PathVariable("shiftname") final String shiftname){
    	LOGGER.info("Starting getShifts");    
    	LOGGER.info("Starting getShifts "+getShiftsByShiftName(shiftname));  
    	return getShiftsByShiftName(shiftname);       

    }

    @PostMapping("/add")
    public String add(@RequestBody final String shift){    	
     Shift shiftObj=readStringToPojo(shift);
     	LOGGER.info("shiftObj "+shiftObj.isDeprecated());  
    	Shift savedShift=shiftRepository.saveAndFlush(shiftObj);
    	return savedShift.getName();
    }

    
    /*@PostMapping("/delete/{shiftname}")
    public List<String> delete(@PathVariable("shiftname") final String shiftname) {
    	LOGGER.info("delete "); 
        List<Shift> shifts = shiftRepository.findByNameAndIsDeprecated(shiftname,false);
        shiftRepository.delete(shifts);

        return getShiftsByShiftName(shiftname);
    }*/
    
    
    @GetMapping("/{shiftname}")
    public String getShiftsByShiftName(@PathVariable("shiftname") String shiftname) {
    	LOGGER.info("getShiftsByShiftName "+shiftname);  
    	Shift list=shiftRepository.findByName(shiftname);
    	LOGGER.info("shiftRepository "+shiftRepository);
    	LOGGER.info("list "+shiftRepository.findByName(shiftname));
    	LOGGER.info("list "+shiftRepository.findAll());
    	//LOGGER.info("list "+list.getName());
    	
    	return shiftRepository.findByName(shiftname).getName();
       /* return   shiftRepository.findByNameAndIsDeprecated(shiftname, false)
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toList());*/
    }

    
    private Shift readStringToPojo(String name) {
    	LOGGER.info("readStringToPojo ");  
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Shift shift = new Shift();
		ShiftModel shiftModel = new ShiftModel();

		try {
			shiftModel = mapper.readValue(name,ShiftModel.class);
			shift.setName(shiftModel.getShift());
			shift.setTimeCreated(timestamp);
			shift.setDeprecated(false);
			
			
		} catch (Exception ex) {
			LOGGER.error("err: " + ex.toString() + "; msg: " + ex.getMessage());

		}

		return shift;
	}
    
    
}
