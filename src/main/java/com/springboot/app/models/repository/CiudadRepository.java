package com.springboot.app.models.repository;

import org.springframework.data.repository.CrudRepository;

import com.springboot.app.models.entity.Ciudad;

public interface CiudadRepository extends CrudRepository<Ciudad, Long> {

}
