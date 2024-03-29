package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Member;
import com.example.demo.model.Valve;

import jakarta.transaction.Transactional;

@Repository
public interface ValveRepository extends JpaRepository<Valve, Integer> {
	
	Valve findByvalveStatusAndMemberMbEmail(@Param("valveStatus") String valveStatus, @Param("mbEmail") String email);

	Valve findBySensorSensorIdx(int sensorIdx); 

	@Query(value = "SELECT valveStatus FROM tbValve WHERE valveOwner = :memberMbEmail", nativeQuery = true)
	List<String> findByCustomQuery(@Param("memberMbEmail") String memberMbEmail);
	
	@Modifying
	@Transactional
	@Query("UPDATE Valve v SET v.valveStatus = :valveStatus WHERE v.member.mbEmail = :email")
	Integer updateValveStatusByEmail(@Param("valveStatus") String valveStatus, @Param("email") String email);
}
