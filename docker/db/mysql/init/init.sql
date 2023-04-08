CREATE TABLE chess_room (
    id TINYINT AUTO_INCREMENT,
    turn VARCHAR(255) NOT NULL,
    state VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE chess_game (
    game_id TINYINT,
    piece_type VARCHAR(255) NOT NULL,
    piece_rank TINYINT NOT NULL,
    piece_file VARCHAR(255) NOT NULL,
    team VARCHAR(255) NOT NULL,
    FOREIGN KEY (game_id) REFERENCES chess_room(id)
);
