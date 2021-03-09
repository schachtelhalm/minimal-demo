CREATE TABLE animal
(
   id BIGSERIAL primary key,
   name VARCHAR (255),
   created_at TIMESTAMPTZ NOT NULL DEFAULT NOW (),
   updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW ()
);

INSERT INTO animal(name) VALUES ('squirrel');
INSERT INTO animal(name) VALUES ('marmot');
INSERT INTO animal(name) VALUES ('chipmunk');
