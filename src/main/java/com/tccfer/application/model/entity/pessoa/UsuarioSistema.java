package com.tccfer.application.model.entity.pessoa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tccfer.application.model.entity.enuns.TipoUsuario;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
public class UsuarioSistema extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String login;

    private String username;

    @JsonIgnore
    private String hashedPassword;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<TipoUsuario> tipoUsuario;


    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pessoa_id", nullable = false)
    private Pessoa pessoa;

    private String tokenAtivacao;

    private boolean ativo = false;


}
