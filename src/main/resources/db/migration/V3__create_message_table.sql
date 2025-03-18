CREATE TABLE messages
(
    id         SERIAL PRIMARY KEY,
    queue_id   BIGINT,
    message    VARCHAR                  NOT NULL,
    message_id varchar                  not null,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_user FOREIGN KEY (queue_id) REFERENCES queues (id) ON DELETE CASCADE
);