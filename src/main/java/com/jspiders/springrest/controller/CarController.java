package com.jspiders.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.springrest.pojo.Car;
import com.jspiders.springrest.rsponse.ResponseStructure;
import com.jspiders.springrest.service.CarService;

@RestController
public class CarController {
	@Autowired
	private CarService carService;
	
	@PostMapping(path="/car")
	public ResponseEntity< ResponseStructure<Car>> addCar(@RequestBody Car car){
		Car addedCar = carService.addCar(car);
		ResponseStructure<Car> responseStructure=new ResponseStructure<>();
		responseStructure.setMessage("car added");
		responseStructure.setData(addedCar);
		responseStructure.setStatus(HttpStatus.OK.value());
		return new ResponseEntity<ResponseStructure<Car>>(responseStructure,HttpStatus.OK);
	}
	@GetMapping(path="/cars")
	public ResponseEntity<ResponseStructure<List<Car>>> getAllcars(){
		List<Car> cars = carService.findAllCars();
		ResponseStructure<List<Car>> responseStructure=new ResponseStructure<>();
		if(cars!=null) {
			responseStructure.setMessage("car found");
			responseStructure.setData(cars);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure <List<Car>>>(responseStructure,HttpStatus.FOUND);
		}else {
			responseStructure.setMessage("car not found");
			responseStructure.setData(cars);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<List<Car>>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping(path="/car")
	public ResponseEntity<ResponseStructure<Car>> deleteCar(@RequestParam( name="id") int id){
		Car car = carService.deleteCar(id);
		ResponseStructure<Car> responseStructure=new ResponseStructure<Car>();
		if(car!=null) {
			responseStructure.setMessage("car deleted");
			responseStructure.setData(car);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Car>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setMessage("car not found");
			responseStructure.setData(car);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Car>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
	@PutMapping(path="/car")
	public ResponseEntity<ResponseStructure<Car>> updateCar(@RequestBody Car car){
		Car updatedCar = carService.updateCar(car);
		ResponseStructure<Car> responseStructure=new ResponseStructure<Car>();
		if(updatedCar!=null) {
			responseStructure.setMessage("car updated");
			responseStructure.setData(updatedCar);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Car>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setMessage("car not updated");
			responseStructure.setData(updatedCar);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<Car>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}

}
