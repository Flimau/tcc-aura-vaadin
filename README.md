# My Application README

- [ ] TODO Replace or update this README with instructions relevant to your application

To start the application in development mode, import it into your IDE and run the `Application` class. 
You can also start the application from the command line by running: 

```bash
./mvnw
```

To build the application in production mode, run:

```bash
./mvnw -Pproduction package
```

## cadastro pessoa
INSERT INTO pais (id, nome, sigla) VALUES (1, 'Brasil', 'BR');

INSERT INTO estado (id, nome, sigla, latitude, longitude, pais_id)
VALUES (1, 'Rio Grande do Sul', 'RS', -30.0346, -51.2177, 1);

INSERT INTO municipio (id, nome, latitude, longitude, estado_id)
VALUES (1, 'Porto Alegre', -30.0346, -51.2177, 1);

INSERT INTO endereco (
id, estados_id, municipios_id, logradouro, numero, bairro, cep, complemento, latitude, longitude
) VALUES (
1, 1, 1, 'Av. Ipiranga', '1234', 'Jardim Botânico', '90619-900', 'Próximo ao shopping', -30.039298, -51.223495
);

INSERT INTO pessoa (
nome, cpf, cnpj, data_nascimento, tipo_pessoa, sexo, endereco_id,
celular, telefone, email, data_registro
) VALUES (
'João da Silva', '123.456.789-00', NULL, DATE '1990-05-10', 'FISICA', 'MASCULINO', 1,
'(51) 99999-9999', '(51) 3333-3333', 'joao@email.com', CURRENT_TIMESTAMP
);

INSERT INTO usuario_sistema (
id,login, senha, pessoa_id, token_ativacao, ativo, version, tipo_usuario
) VALUES (
2,'admin', '$2a$12$1umajtGxWqcxIjcQFDum7uOnkwNGOMv7DZlvtTMK/suARerfHm53.', 2, 'ABC123XYZ', TRUE,1, 'ADMINISTRADOR'
);

INSERT INTO usuario_sistema_tipo_usuario (usuario_sistema_id, tipo_usuario)
VALUES (2, 'ADMINISTRADOR');