CREATE TABLE IF NOT EXISTS track(
    id INTEGER PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    author VARCHAR(30) NOT NULL,
    genre CHAR(10),
    duration INT NOT NULL,
    extension CHAR(10),
    path VARCHAR(64) NOT NULL UNIQUE
);