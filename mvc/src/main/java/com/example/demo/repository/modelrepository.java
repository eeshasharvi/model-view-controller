package com.example.demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.model;

@Repository
public interface modelrepository extends CrudRepository<model, Integer>{

}
