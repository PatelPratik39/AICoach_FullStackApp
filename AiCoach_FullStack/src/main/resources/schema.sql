-- CREATE TABLE vector_store (
--                               id SERIAL PRIMARY KEY,
--                               vector_data BYTEA NOT NULL
-- );


CREATE TABLE persona (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         role VARCHAR(255) NOT NULL
);

CREATE TABLE document_metadata (
                                   id SERIAL PRIMARY KEY,
                                   file_name VARCHAR(255) NOT NULL,
                                   file_type VARCHAR(50) NOT NULL,
                                   file_size BIGINT NOT NULL
);

CREATE TABLE conversation (
                              id SERIAL PRIMARY KEY,
                              user_message TEXT NOT NULL,
                              bot_response TEXT NOT NULL
);
