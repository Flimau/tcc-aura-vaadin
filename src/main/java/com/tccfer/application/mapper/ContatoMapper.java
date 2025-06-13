package com.tccfer.application.mapper;

import com.tccfer.application.controller.dto.contato.ContatoDTO;
import com.tccfer.application.model.entity.contato.Contato;

import org.springframework.stereotype.Component;

@Component
public class ContatoMapper {

    public Contato toEntity(ContatoDTO dto) {
        if (dto == null) {
            return null;
        }

        Contato contato = new Contato();
        contato.setTelefone(dto.getTelefone());
        contato.setEmail(dto.getEmail());
        // Adicione outros campos se existirem
        return contato;
    }

    public ContatoDTO toDTO(Contato contato) {
        if (contato == null) {
            return null;
        }

        ContatoDTO dto = new ContatoDTO();
        dto.setTelefone(contato.getTelefone());
        dto.setEmail(contato.getEmail());
        // Adicione outros campos se existirem
        return dto;
    }
}
