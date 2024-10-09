package com.productor.pedidos.controller;

import com.productor.pedidos.model.Pedido;
import com.productor.pedidos.service.PedidoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(
  value =  "pedido/",
  produces = {
    MediaType.APPLICATION_JSON_VALUE })
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE,
  RequestMethod.PUT })
public class PedidoController {


  private PedidoService pedidoService;

  public PedidoController(PedidoService pedidoService) {
    this.pedidoService = pedidoService;
  }

  @PostMapping(value = "alta", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> nuevoPedido(@RequestBody Pedido pedido) {

   try {
         pedidoService.registrarPedido(pedido);
         return  new ResponseEntity<>(HttpStatus.OK);
       }catch (Exception e) {

     return  new ResponseEntity<>(HttpStatus.BAD_REQUEST);

   }

  }

}
