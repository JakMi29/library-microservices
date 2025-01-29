CREATE TABLE _user (
    id SERIAL   NOT NULL,
    name        VARCHAR(255) NOT NULL,
    surname     VARCHAR(255) NOT NULL,
    phone_number       VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    updated_at     TIMESTAMP WITH TIME ZONE,
    created_at    TIMESTAMP WITH TIME ZONE,
    PRIMARY KEY (id)
);
