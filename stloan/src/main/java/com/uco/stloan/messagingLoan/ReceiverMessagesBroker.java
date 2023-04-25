package com.uco.stloan.messagingLoan;

import com.uco.stloan.dto.LoanDTO;
import com.uco.stloan.utils.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Component
public class ReceiverMessagesBroker {

    private final MapperJsonObjeto mapperJsonObjeto;

    public ReceiverMessagesBroker(MapperJsonObjeto mapperJsonObjeto) {
        this.mapperJsonObjeto = mapperJsonObjeto;
    }


    @RabbitListener ( queues = "${loan.procesar.queue-name}")
    public void receiveMessageProcessClient(String message) {
        try {
            System.out.println(obtenerObjetoDeMensaje(message).get().getArticle());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Optional<LoanDTO> obtenerObjetoDeMensaje( String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, LoanDTO.class);
    }
}
