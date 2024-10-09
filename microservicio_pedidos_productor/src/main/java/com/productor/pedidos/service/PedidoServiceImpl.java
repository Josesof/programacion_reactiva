package com.productor.pedidos.service;

import com.productor.pedidos.model.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;


@Service
public class PedidoServiceImpl implements PedidoService {

  @Value("${topico}")
  String topico;

   @Autowired
   KafkaTemplate<String, Pedido> kafkaTemplate;



  @Override
  public void registrarPedido(Pedido pedido) {

    CompletableFuture<SendResult<String, Pedido>> future = kafkaTemplate.send(topico, pedido);

    //Cuando se cumpla el envio del mensaje se envia mensaje de confirmacion
    //whenCompleteAsync((r, t) r es el resultado t es si ocurre una excepcion
    future.whenCompleteAsync((r, t)->{
       if(t!=null){
         throw new RuntimeException();
       }
       System.out.println("Pedido registrada con exito" +
                                 r.getProducerRecord().value().getNombre()
                                      + "En el topico " + r.getRecordMetadata().topic());
    });

  }
}
