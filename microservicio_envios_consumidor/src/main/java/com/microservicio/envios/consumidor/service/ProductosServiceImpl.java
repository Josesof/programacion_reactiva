package com.microservicio.envios.consumidor.service;

import com.microservicio.envios.consumidor.model.Envio;
import com.microservicio.envios.consumidor.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
public class ProductosServiceImpl implements ProductosService{

  @Autowired
  ProductoRepository productoRepository;

  @Override
  public Flux<Envio> findByEstadoEnvio(String estado) {
    return productoRepository.findByEstado(estado);
  }

  @KafkaListener(topics = "pedidosTopic", groupId = "myGroup2")
  public void gestionEnvios(Envio envio) {

         envio.setFechaEnvio(LocalDateTime.now());
         envio.setEstado("pendiente");
         productoRepository.save(envio).subscribe();
  }


}
