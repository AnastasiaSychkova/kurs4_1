CREATE TABLE person
(
    id             REAL PRIMARY KEY,
    name           TEXT,
    age            INTEGER,
    driver_license BOOLEAN,
    car_id         INTEGER REFERENCES cars (id)
);

CREATE TABLE cars
(
    id    INTEGER PRIMARY KEY,
    brand TEXT,
    model TEXT,
    price INTEGER
);