CREATE TABLE ah_user
(
    id           UUID    NOT NULL,
    version      BIGINT  NOT NULL,
    password     VARCHAR NOT NULL,
    first_name   VARCHAR NOT NULL,
    last_name    VARCHAR NOT NULL,
    gender       VARCHAR NOT NULL,
    email        VARCHAR NOT NULL,
    phone_number VARCHAR NOT NULL,
    avatar       BYTEA,
    PRIMARY KEY (id)
);

ALTER TABLE ah_user
    ADD CONSTRAINT UK_user_email UNIQUE (email);

CREATE TABLE user_roles
(
    user_id UUID    NOT NULL,
    role    VARCHAR NOT NULL
);

ALTER TABLE user_roles
    ADD CONSTRAINT FK_user_roles_to_user FOREIGN KEY (user_id) REFERENCES ah_user;

CREATE TABLE ah_customer
(
    id           UUID    NOT NULL,
    version      BIGINT  NOT NULL,
    first_name   VARCHAR NOT NULL,
    last_name    VARCHAR NOT NULL,
    gender       VARCHAR NOT NULL,
    email        VARCHAR NOT NULL,
    phone_number VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE ah_customer
    ADD CONSTRAINT UK_customer_email UNIQUE (email);

CREATE TABLE ah_employee
(
    id           UUID    NOT NULL,
    version      BIGINT  NOT NULL,
    first_name   VARCHAR NOT NULL,
    last_name    VARCHAR NOT NULL,
    gender       VARCHAR NOT NULL,
    email        VARCHAR NOT NULL,
    phone_number VARCHAR NOT NULL,
    nic          VARCHAR NOT NULL,
    PRIMARY KEY (id)
);

ALTER TABLE ah_employee
    ADD CONSTRAINT UK_employee_email UNIQUE (email);

CREATE TABLE ah_reservation(
    id UUID NOT NULL,
    version BIGINT NOT NULL,
    customer_id UUID NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    room_type VARCHAR NOT NULL,
    reservation_type VARCHAR NOT NULL,
    amount VARCHAR NOT NULL,
    payment_method VARCHAR NOT NULL,
    PRIMARY KEY(id)
);

ALTER TABLE ah_reservation
    ADD CONSTRAINT FK_reservations_to_customer FOREIGN KEY (customer_id) REFERENCES ah_customer;
