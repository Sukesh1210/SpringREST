package com.jspiders.springrest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jspiders.springrest.pojo.User;
import com.jspiders.springrest.rsponse.ResponseStructure;
import com.jspiders.springrest.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public ResponseEntity<ResponseStructure<User>> addUser(@RequestBody User user){
		User addedUser = userService.addUser(user);
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
			if(addedUser!=null) {
				responseStructure.setMessage("User added");
				responseStructure.setData(addedUser);
				responseStructure.setStatus(HttpStatus.OK.value());
				return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
			}else {
				responseStructure.setMessage("User already exists");
				responseStructure.setData(addedUser);
				responseStructure.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
				return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_ACCEPTABLE);
			}
		
		}
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<User>>> findAllUser(){
		List<User> users = userService.findAllUser();
		ResponseStructure<List<User>> responseStructure=new ResponseStructure<List<User>>();
		if(users!=null) {
			responseStructure.setMessage("user found");
			responseStructure.setData(users);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure,HttpStatus.FOUND);
		}
		else {
			responseStructure.setMessage("user not found");
			responseStructure.setData(users);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<List<User>>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/user")
	public ResponseEntity<ResponseStructure<User>> validateUser(@RequestParam(name="userName") String userName,@RequestParam(name="password") String password){
		User user = userService.validateUser(userName,password);
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		if(user!=null) {
			responseStructure.setMessage("user found");
			responseStructure.setData(user);
			responseStructure.setStatus(HttpStatus.FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.FOUND);
		}else {
			responseStructure.setMessage("invalid userName or password");
			responseStructure.setData(user);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_FOUND);
		
			
		}
	}
	@PutMapping("/user")
	public ResponseEntity<ResponseStructure<User>> updateUser(@RequestBody User user){
		User updatedUser = userService.updateUser( user);
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		if(updatedUser!=null) {
			responseStructure.setMessage("user updated");
			responseStructure.setData(updatedUser);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setMessage("user not found");
			responseStructure.setData(updatedUser);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_FOUND);
		
		}
		
		
	}
	@DeleteMapping(path="/user")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam(name="id") int id){
		User deletedUser = userService.deleteUser(id);
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		if(deletedUser!=null) {
			responseStructure.setMessage("user deleted");
			responseStructure.setData(deletedUser);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setMessage("user not found");
			responseStructure.setData(deletedUser);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_FOUND);
		}
	}
	@PatchMapping(path="/user")
	public ResponseEntity<ResponseStructure<User>> updateCarListForUser(@RequestParam(name="userId") int userId
			,@RequestParam(name="carId") int carId){
		User updatedCarListForUser = userService.updateCarListForUser(userId,carId);
		ResponseStructure<User> responseStructure=new ResponseStructure<User>();
		if(updatedCarListForUser!=null) {
			responseStructure.setMessage("car list for user is updated");
			responseStructure.setData(updatedCarListForUser);
			responseStructure.setStatus(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.OK);
		}else {
			responseStructure.setMessage("invalid userId or carId");
			responseStructure.setData(updatedCarListForUser);
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			return new ResponseEntity<ResponseStructure<User>>(responseStructure,HttpStatus.NOT_FOUND);
		}
		
	}

}
