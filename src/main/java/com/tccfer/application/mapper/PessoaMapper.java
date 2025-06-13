package com.tccfer.application.mapper;

import com.tccfer.application.controller.dto.usuario.PessoaCadastroDTO;
import com.tccfer.application.mapper.ContatoMapper;
import com.tccfer.application.mapper.EnderecoMapper;
import com.tccfer.application.model.entity.enuns.TipoPessoa;
import com.tccfer.application.model.entity.pessoa.Pessoa;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PessoaMapper {

    private final EnderecoMapper enderecoMapper;
    private final ContatoMapper contatoMapper;

    public PessoaMapper(EnderecoMapper enderecoMapper, ContatoMapper contatoMapper) {
        this.enderecoMapper = enderecoMapper;
        this.contatoMapper = contatoMapper;
    }

    public Pessoa toEntity(PessoaCadastroDTO dto) {
        if (dto == null) {
            return null;
        }

        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setSexo(dto.getSexo());
        pessoa.setTipoPessoa(dto.getTipoPessoa());
        pessoa.setDataNascimento(dto.getDataNascimento());
        pessoa.setEndereco(enderecoMapper.toEntity(dto.getEndereco()));
        pessoa.setContato(contatoMapper.toEntity(dto.getContato()));

        preencherCamposCondicionais(dto, pessoa);

        return pessoa;
    }

    public PessoaCadastroDTO toDTO(Pessoa pessoa) {
        if (pessoa == null) {
            return null;
        }

        PessoaCadastroDTO dto = new PessoaCadastroDTO();
        dto.setNome(pessoa.getNome());
        dto.setSexo(pessoa.getSexo());
        dto.setTipoPessoa(pessoa.getTipoPessoa());
        dto.setDataNascimento(pessoa.getDataNascimento());
        dto.setEndereco(enderecoMapper.toDTO(pessoa.getEndereco()));
        dto.setContato(contatoMapper.toDTO(pessoa.getContato()));

        if (pessoa.getTipoPessoa() == TipoPessoa.FISICA) {
            dto.setCpf(pessoa.getCpf());
        } else {
            dto.setCnpj(pessoa.getCnpj());
        }

        return dto;
    }

    public void atualizarEntidade(PessoaCadastroDTO dto, Pessoa entidade) {
        if (dto == null || entidade == null) {
            return;
        }

        if (dto.getNome() != null) entidade.setNome(dto.getNome());
        if (dto.getSexo() != null) entidade.setSexo(dto.getSexo());
        if (dto.getTipoPessoa() != null) entidade.setTipoPessoa(dto.getTipoPessoa());
        if (dto.getDataNascimento() != null) entidade.setDataNascimento(dto.getDataNascimento());
        if (dto.getEndereco() != null) entidade.setEndereco(enderecoMapper.toEntity(dto.getEndereco()));
        if (dto.getContato() != null) entidade.setContato(contatoMapper.toEntity(dto.getContato()));

        preencherCamposCondicionais(dto, entidade);
    }

    public void preencherCamposCondicionais(PessoaCadastroDTO dto, Pessoa pessoa) {
        if (dto.getTipoPessoa() == TipoPessoa.FISICA) {
            pessoa.setCpf(dto.getCpf());
        } else {
            pessoa.setCnpj(dto.getCnpj());
        }

        pessoa.setDataRegistro(LocalDateTime.now());
    }
}
