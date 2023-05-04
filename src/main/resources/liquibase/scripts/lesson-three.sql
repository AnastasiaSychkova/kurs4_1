-- liquibase formatted sql

-- changeset anastasia:1
CREATE INDEX IF NOT EXISTS student_name_index ON student (name);

--changeset anastasia:2
CREATE INDEX IF NOT EXISTS faculty_name_colour_index ON faculty (color, name);