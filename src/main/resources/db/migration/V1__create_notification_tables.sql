CREATE TABLE notificacao (
    id              BIGSERIAL PRIMARY KEY,
    nome            VARCHAR(255),
    tipo            VARCHAR(255),
    descricao       TEXT,
    created_at      TIMESTAMP,
    updated_at      TIMESTAMP,
    deleted_tmsp    TIMESTAMP
);