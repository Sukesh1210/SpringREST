package com.jspiders.springrest.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.jspiders.springrest.pojo.Car;

@Repository
public class CarRepository {
	private EntityManagerFactory entityManagerFactory;
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	
	public void openConnection() {
		entityManagerFactory=Persistence.createEntityManagerFactory("car");
		entityManager=entityManagerFactory.createEntityManager();
		entityTransaction=entityManager.getTransaction();
	}
	public void closeConnection() {
		if(entityManagerFactory!=null) {
			entityManagerFactory.close();
		}
		if(entityManager!=null) {
			entityManager.close();
		}
		if(entityTransaction!=null) {
			if(entityTransaction.isActive()) {
				entityTransaction.rollback();
			}
		}
	}
	public Car addCar( Car car) {
		openConnection();
		entityTransaction.begin();
		entityManager.persist(car);
		entityTransaction.commit();
		closeConnection();
		return car;
	}
	public List<Car> findAllCars(){
		openConnection();
		entityTransaction.begin();
		Query query=entityManager.createQuery("SELECT car FROM Car car");
		@SuppressWarnings("unchecked")
		List<Car> cars=query.getResultList();
		entityTransaction.commit();
		closeConnection();
		return cars;
	}
	public Car deleteCar(int id) {
		openConnection();
		entityTransaction.begin();
		Car car = entityManager.find(Car.class, id);
		if(car!=null) {
			
			entityManager.remove(car);
		}
		entityTransaction.commit();
		closeConnection();
		return car;
	}

	public Car updateCar(Car car) {
		openConnection();
		Car carTobeUpdated = entityManager.find(Car.class, car.getId());
		entityTransaction.begin();
		if(carTobeUpdated!=null) {
			carTobeUpdated.setName(car.getName());
			carTobeUpdated.setBrand(car.getBrand());
			carTobeUpdated.setPrice(car.getPrice());
			entityManager.persist(carTobeUpdated);
		}
		entityTransaction.commit();
		closeConnection();
		return carTobeUpdated;
	}
}
