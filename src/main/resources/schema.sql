DROP table IF EXISTS "producers" cascade;
DROP table IF EXISTS "techniques" cascade;
DROP table IF EXISTS "attributes" cascade;
DROP table IF EXISTS "products" cascade;

CREATE TABLE IF NOT EXISTS PUBLIC."producers"
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(512)                            NOT NULL UNIQUE,
    CONSTRAINT pk_producer PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS PUBLIC."techniques"
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(512)                            NOT NULL UNIQUE,
    CONSTRAINT pk_technique PRIMARY KEY (id)
);
MERGE INTO PUBLIC."techniques" KEY (id) VALUES (1, 'Настольные компьютеры');
MERGE INTO PUBLIC."techniques" KEY (id) VALUES (2, 'Ноутбуки');
MERGE INTO PUBLIC."techniques" KEY (id) VALUES (3, 'Монитор');
MERGE INTO PUBLIC."techniques" KEY (id) VALUES (4, 'Жесткие диски');

CREATE TABLE IF NOT EXISTS PUBLIC."attributes"
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(512)                            NOT NULL,
    CONSTRAINT pk_attribute PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS PUBLIC."products"
(
    id           BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    numer        VARCHAR(512)                            NOT NULL,
    producer_id  BIGINT                                  NOT NULL,
    price        FLOAT                                   NOT NULL,
    quantity     BIGINT                                  NOT NULL,
    technique_id BIGINT                                  NOT NULL,
    attribute_id BIGINT                                  NOT NULL,
    CONSTRAINT pk_catalog PRIMARY KEY (id),
    CONSTRAINT fk_producer_id_catalog FOREIGN KEY (producer_id) REFERENCES producers (id),
    CONSTRAINT fk_technique_id_catalog FOREIGN KEY (technique_id) REFERENCES techniques (id),
    CONSTRAINT fk_attribute_id_catalog FOREIGN KEY (attribute_id) REFERENCES attributes (id)
);
