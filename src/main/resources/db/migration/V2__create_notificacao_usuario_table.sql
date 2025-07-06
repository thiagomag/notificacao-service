CREATE TABLE notificacao_usuario (
    id                  BIGSERIAL PRIMARY KEY,
    id_usuario          BIGINT,
    id_notificacao      BIGINT,
    created_at          TIMESTAMP,
    updated_at          TIMESTAMP,
    deleted_tmsp        TIMESTAMP,
    CONSTRAINT fk_notificacao FOREIGN KEY(id_notificacao) REFERENCES notificacao(id)
);