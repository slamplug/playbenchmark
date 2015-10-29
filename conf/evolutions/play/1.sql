# Users schema
 
# --- !Ups
 
CREATE TABLE World (
    id bigint(20) NOT NULL,
    randomNumber bigint(20) NOT NULL,
    PRIMARY KEY (id)
);
 
# --- !Downs
 
DROP TABLE World;