-- liquibase formatted sql

-- changeset anastasia:1
CREATE INDEX student_name_index ON student (name);

--changeset anastasia:2
CREATE INDEX faculty_name_colour_index ON faculty (color, name);