package com.springboot.app.models.service;

import java.util.List;

import com.springboot.app.models.entity.Cliente;

public interface IClienteService {
	
	public List<Cliente> listarTodos();
	public void guardar(Cliente cliente);
	public Cliente buscarPorId(Long id);
	public void eliminar(Long id);
}
