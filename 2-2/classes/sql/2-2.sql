USE SPRING;
CREATE TABLE IF NOT EXISTS PLAYER(
    PLAYER_ID MEDIUMINT PRIMARY KEY AUTO_INCREMENT,
    NAME VARCHAR(255) NOT NULL,
	TEAM_ID MEDIUMINT NOT NULL,
	FOREIGN KEY(TEAM_ID) REFERENCES TEAM(TEAM_ID)
);