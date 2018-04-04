package dao;

import model.Player;

import org.springframework.dao.DataAccessException;

public interface PlayerDao {
    
    void insertPlayer(Player player) throws DataAccessException;

}