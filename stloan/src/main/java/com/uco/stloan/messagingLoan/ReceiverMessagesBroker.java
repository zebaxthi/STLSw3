package com.uco.stloan.messagingLoan;

import com.uco.stloan.dto.LoanDTO;
import com.uco.stloan.utils.gson.MapperJsonObjeto;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ReceiverMessagesBroker {

    private final MapperJsonObjeto mapperJsonObjeto;

    public ReceiverMessagesBroker(MapperJsonObjeto mapperJsonObjeto) {
        this.mapperJsonObjeto = mapperJsonObjeto;
    }


    @RabbitListener ( queues = "${Loan.recibir.queue-name}")
    public void receiveMessageProcessClient(String message) {
        try {
            System.out.println(obtenerObjetoDeMensaje(message).get());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private Optional<LoanDTO> obtenerObjetoDeMensaje( String mensaje) {
        return mapperJsonObjeto.ejecutar(mensaje, LoanDTO.class);
    }
}
