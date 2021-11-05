DROP TABLE IF EXISTS "player";

CREATE TABLE "player"
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     VARCHAR(191)                       NOT NULL,
    surname  VARCHAR(191)                       NOT NULL,
    position enum ('PG', 'SG', 'SF', 'PF', 'C') NOT NULL
);

INSERT INTO "player" (name, surname, position)
VALUES ('Randy', 'Brown', 'PG'),
       ('Jud', 'Buechler', 'SF'),
       ('Jason', 'Caffey', 'PF'),
       ('James', 'Edwards', 'C'),
       ('Jack', 'Haley', 'C'),
       ('Ron', 'Harper', 'PG'),
       ('Michael', 'Jordan', 'SG'),
       ('Steve', 'Kerr', 'PG'),
       ('Toni', 'Kukoč', 'SF'),
       ('Luc', 'Longley', 'C'),
       ('Scottie', 'Pippen', 'SF'),
       ('Dennis', 'Rodman', 'PF'),
       ('John', 'Salley', 'PF'),
       ('Dickey', 'Simpkins', 'PF'),
       ('Bill', 'Wennington', 'C');