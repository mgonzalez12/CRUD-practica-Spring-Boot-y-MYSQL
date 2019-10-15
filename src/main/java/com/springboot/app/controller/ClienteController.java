package com.springboot.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springboot.app.models.entity.Ciudad;
import com.springboot.app.models.entity.Cliente;
import com.springboot.app.models.service.ICiudadService;
import com.springboot.app.models.service.IClienteService;

@Controller
@RequestMapping("views/clientes")
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	@Autowired
	private ICiudadService ciudadService;
	
	@GetMapping("/")
	public String listarClientes(Model model) {
		List<Cliente> listadoCliente = clienteService.listarTodos();
		model.addAttribute("titulo", "Lista de clientes");
		model.addAttribute("clientes",listadoCliente);
		return "views/clientes/listar";
	}
	
	@GetMapping("/create")
	 public String crear(Model model) {
		 Cliente cliente = new Cliente();
		  List<Ciudad> listCiudades = ciudadService.ListaCiudades();
		 
		 model.addAttribute("titulo", "Formulario: Nuevo cliente");
		 model.addAttribute("cliente", cliente);
		 model.addAttribute("ciudades", listCiudades);
		 return "views/clientes/frmCrear";
	 }
	
	 @PostMapping("/save")	
	 public String guardar(@Valid @ModelAttribute Cliente cliente, BindingResult result,
			 Model model, RedirectAttributes attribute) {
		 List<Ciudad> listCiudades = ciudadService.ListaCiudades();
		 if(result.hasErrors()) {
			 model.addAttribute("titulo", "Formulario: Nuevo cliente");
			 model.addAttribute("cliente", cliente);
			 model.addAttribute("ciudades", listCiudades);
			 
			 System.out.println("Existen errores");
			 return "views/clientes/frmCrear"; 
		 }
		 
		 clienteService.guardar(cliente);
		  attribute.addFlashAttribute("success","Cliente Guardado Con exito");
		 return "redirect:/views/clientes/"; 
	 }
	 
	 @GetMapping("/edit/{id}")
	 public String editar(@PathVariable("id") Long idCliente, Model model, RedirectAttributes attribute) {
		 Cliente cliente = null;
		 if(idCliente > 0) {
			  cliente = clienteService.buscarPorId(idCliente);
			  
			  if(cliente == null) {
				  attribute.addFlashAttribute("danger","Error: el Id no existe");
				  return "redirect:/views/clientes/";	
			  }
		 }else {
			 attribute.addFlashAttribute("danger","Error: con el ID del cliente");
			  return "redirect:/views/clientes/"; 
		 }
		 
		
		 List<Ciudad> listCiudades = ciudadService.ListaCiudades();
		 
		 model.addAttribute("titulo", "Formulario: Editar");
		 model.addAttribute("cliente", cliente);
		 model.addAttribute("ciudades", listCiudades);
		 return "views/clientes/frmCrear";
	 }
	
	 @GetMapping("/delete/{id}")
	 public String delete(@PathVariable("id") Long idCliente, Model model,RedirectAttributes attribute) {
		
		 Cliente cliente = null;
		 if(idCliente > 0) {
			  cliente = clienteService.buscarPorId(idCliente);
			  
			  if(cliente == null) {
				  attribute.addFlashAttribute("danger","Error: el Id no existe");
				  return "redirect:/views/clientes/";	
			  }
		 }else {
			 attribute.addFlashAttribute("danger","Error: con el ID del cliente");
			  return "redirect:/views/clientes/"; 
		 }
		 
		 
		clienteService.eliminar(idCliente);
		attribute.addFlashAttribute("warning","Eliminado Con Exito");
		 return "redirect:/views/clientes/";
	 }
	 
}
