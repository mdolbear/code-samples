DROP TABLE IF EXISTS tic_tac_toe;
CREATE TABLE tic_tac_toe (
    id BIGINT not null AUTO_INCREMENT,
    version BIGINT,
    board BLOB,
    current_player INT,
    outcome VARCHAR(100),
    PRIMARY KEY (id)
 );
 
 DROP TABLE IF EXISTS player;
 CREATE TABLE player (
    id BIGINT not null AUTO_INCREMENT,
    version BIGINT,
    marker VARCHAR(100),
    game_id BIGINT,
    PRIMARY KEY (id)
 );
 
 DROP TABLE IF EXISTS game_eval;
 CREATE TABLE game_eval (
    id BIGINT not null AUTO_INCREMENT,
    version BIGINT,
    eval_type VARCHAR(100),
    game_id BIGINT,
    PRIMARY KEY (id)
 );


    