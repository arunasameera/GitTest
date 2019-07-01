package com.aruna.dbservices.repository;



import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aruna.dbservices.model.Shift;

@Repository
public interface ShiftRepository extends JpaRepository<Shift,UUID>{

	Shift findByNameAndIsDeprecated(String name, Boolean isDeprecated);
	Shift findByName(String name);
}
