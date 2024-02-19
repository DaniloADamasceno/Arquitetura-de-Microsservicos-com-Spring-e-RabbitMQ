CREATE TABLE payments
(
    id                 bigint(20) NOT NULL AUTO_INCREMENT,
    value_fields       decimal(19, 2) NOT NULL,
    name_card_fields   varchar(100) DEFAULT NULL,
    number_card_fields varchar(19)  DEFAULT NULL,
    expiration         varchar(7)     NOT NULL,
    cvv_code           varchar(3)   DEFAULT NULL,
    Status             varchar(255)   NOT NULL,
    form_payment       bigint(20) NOT NULL,
    order_id           bigint(20) NOT NULL,
    PRIMARY KEY (id)
);