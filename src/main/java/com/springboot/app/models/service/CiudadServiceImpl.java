package com.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.app.models.entity.Ciudad;
import com.springboot.app.models.repository.CiudadRepository;

@Service
public class CiudadServiceImpl implements ICiudadService {

	@Autowired
	public CiudadRepository ciudadRepository;
	
	@Override
	public List<Ciudad> ListaCiudades() {
		return  (List<Ciudad>) ciudadRepository.findAll();
	}

}
