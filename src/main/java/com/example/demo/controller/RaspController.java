package com.example.demo.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Sensor;
import com.example.demo.model.SensorData;
import com.example.demo.model.Valve;
import com.example.demo.service.SensorDataService;
import com.example.demo.service.ValveService;

import jakarta.servlet.http.HttpServletResponse;

@RestController
public class RaspController {
	
	@Autowired
	SensorDataService sensorDataService;
	
	@Autowired
	ValveService valveService;
	
	Sensor sensor = new Sensor();
	SensorData sensorData;
	int count=0;
	@PostMapping("/RaspToSpring")
	public String getSensorValue(@RequestParam("phValue") BigDecimal phValue, @RequestParam("turbidValue") BigDecimal turbidValue,
							  @RequestParam("inFlowValue") BigDecimal inFlowValue, @RequestParam("outFlowValue") BigDecimal outFlowValue, @RequestParam("identifier") int identifier) {
		count++;
		sensorData = new SensorData(phValue, turbidValue, inFlowValue, outFlowValue, new Sensor(identifier, null, null, null));
		
		if(count!=5) {
			sensorDataService.insertFlowData(identifier, inFlowValue, outFlowValue);
		}
		
		if(count==5) {
			sensorDataService.insertSensingData(identifier, phValue, turbidValue, inFlowValue, outFlowValue);
			count=0;
		}
	
		Valve valve = valveService.getValveStatus(identifier);
		
		return valve.getValveStatus() != null ? valve.getValveStatus() : "밸브 상태값 못받아옴";
	}
	
	@GetMapping("/getGraphData")
	public SensorData getGraphData() {
	    SensorData emptySensorData = sensorData; // 현재 센서데이터 인스턴스를 새로운 변수에 할당
	    sensorData = null; // 현재 센서데이터 인스턴스를 비움
	    return emptySensorData; // 비워진 센서데이터를 반환
	}
	
}
