CREATE TABLE chess_game (
    piece_type VARCHAR(255) NOT NULL,
    piece_rank TINYINT(10) NOT NULL,
    piece_file VARCHAR(255) NOT NULL,
    team VARCHAR(255) NOT NULL,
    turn VARCHAR(255) NOT NULL
)

CREATE TABLE chess_room (
    id INT AUTO_INCREMENT,
    turn VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
)
