CREATE TABLE notificacao (
    id BIGSERIAL PRIMARY KEY,
    nome VARCHAR(255),
    tipo VARCHAR(255),
    descricao TEXT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_tmsp TIMESTAMP
);

CREATE TABLE notificacao_usuario (
    id BIGSERIAL PRIMARY KEY,
    id_usuario BIGINT,
    id_notificacao BIGINT,
    created_at TIMESTAMP,
    updated_at TIMESTAMP,
    deleted_tmsp TIMESTAMP,
    CONSTRAINT fk_notificaca FOREIGN KEY(id_notificacao) REFERENCES notificacao(id)
);