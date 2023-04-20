package com.uco.stloan.messagingLoan;

import com.uco.stloan.config.ClientQueueConfig;
import com.uco.stloan.dto.LoanDTO;
import com.uco.stloan.utils.MessageSender;
import com.uco.stloan.utils.gson.MapperJsonObjeto;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.MessagePropertiesBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.util.Optional;

public class MessageSenderBroker implements MessageSender<LoanDTO> {

    private final RabbitTemplate rabbitTemplate;
    private final MapperJsonObjeto mapperJsonObjeto;
    private final ClientQueueConfig clientQueueConfig;

    public MessageSenderBroker ( RabbitTemplate rabbitTemplate, MapperJsonObjeto mapperJsonObjeto, ClientQueueConfig clientQueueConfig ) {
        this.rabbitTemplate = rabbitTemplate;
        this.mapperJsonObjeto = mapperJsonObjeto;
        this.clientQueueConfig = clientQueueConfig;
    }

    @Override
    public void execute(LoanDTO message, String idMessage) {
        MessageProperties propiedadesMensaje = generarPropiedadesMensaje(idMessage);

        Optional<Message> cuerpoMensaje = obtenerCuerpoMensaje(message, propiedadesMensaje);
        if (!cuerpoMensaje.isPresent()) {
            return;
        }
        rabbitTemplate.convertAndSend(clientQueueConfig.getExchangeName(), clientQueueConfig.getRoutingKeyName(), cuerpoMensaje.get());
    }

    private MessageProperties generarPropiedadesMensaje(String idMessageSender ) {
        return MessagePropertiesBuilder.newInstance()
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setHeader("idMensaje", idMessageSender)
                .build();
    }

    private Optional<Message> obtenerCuerpoMensaje( Object mensaje, MessageProperties propiedadesMensaje) {
        Optional<String> textoMensaje = mapperJsonObjeto.ejecutarGson(mensaje);

        return textoMensaje.map(msg -> MessageBuilder
                .withBody(msg.getBytes())
                .andProperties(propiedadesMensaje)
                .build());

    }
}
