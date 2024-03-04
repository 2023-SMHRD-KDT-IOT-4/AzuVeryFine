package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.SensorData;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorData, Integer>{

	@Query(value = "SELECT tbSensing.* "
			+ "FROM tbSensing "
			+ "JOIN tbSensor ON tbSensing.sensorIdx = tbSensor.sensorIdx "
			+ "JOIN tbMember ON tbSensor.sensorOwner = tbMember.mbEmail "
			+ "WHERE tbMember.mbEmail = :memberMbEmail "
			+ "AND tbSensing.sensingAt >= :startDateTime "
			+ "AND tbSensing.sensingAt < :endDateTime", nativeQuery = true)
List<Object[]> findByCustomQuery(@Param("memberMbEmail") String memberMbEmail, 
                                 @Param("startDateTime") LocalDateTime startDateTime, 
                                 @Param("endDateTime") LocalDateTime endDateTime);

}
