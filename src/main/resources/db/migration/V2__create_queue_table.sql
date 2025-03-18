CREATE TABLE queues
(
    id                 SERIAL PRIMARY KEY,
    queue_name         VARCHAR(50) UNIQUE       NOT NULL,
    visibility_timeout INT                      NOT NULL,
    user_id            BIGINT,
    created_at         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at         TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),

    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);
