CREATE TABLE users
(
    id            SERIAL PRIMARY KEY,
    first_name    VARCHAR(50)              NOT NULL,
    last_name     VARCHAR(50)              NOT NULL,
    user_name     VARCHAR(50)              NOT NULL,
    email         VARCHAR(100) UNIQUE      NOT NULL,
    mobile_number VARCHAR(15) UNIQUE       NOT NULL,
    password      VARCHAR(255)             NOT NULL,
    role          VARCHAR(20)                       DEFAULT 'USER',
    created_at    timestamp with time zone NOT NULL DEFAULT NOW(),
    updated_at    timestamp with time zone NOT NULL DEFAULT NOW()
);
