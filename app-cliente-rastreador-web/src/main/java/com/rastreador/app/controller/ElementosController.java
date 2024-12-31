package com.rastreador.app.controller;

import com.rastreador.app.service.ElementoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;

@Controller
public class ElementosController {

  @Autowired
  ElementoServiceImpl elementoService;

@GetMapping(value = "buscaPreciosr")
  public String buscaPreciosr(@RequestParam("precio") double precioMax, Model model){
  //Encapsula objetos reactivos
    IReactiveDataDriverContextVariable reactive
      = new ReactiveDataDriverContextVariable(elementoService.elementoPorPrecio(precioMax), 1);
    model.addAttribute("resultado", reactive);
    return "listado";
  }

  @GetMapping(value = "/")
 public String inicio(){
  return "inicio";
 }
}
