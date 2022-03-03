/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author 69591
 */
@Repository
public class GameDaoImpl implements GameDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Override
    public Game createGame() {
        Random r = new Random();
        Game game = new Game();
        String stringAns = ""; 
        //user set to store unique number for answer
        Set<Integer> ans = new HashSet<Integer>();
        while (ans.size() < 4) {
            int a = r.nextInt(10);
            ans.add(a);
        }
        //covert int answer to string
        for(int num: ans){
            stringAns += Integer.toString(num);
        }
        game.setAnswer(stringAns);
        //store game info into mysql and return game id
        final String sql = "INSERT INTO game(answer) VALUES(?);";
                jdbcTemplate.update(sql,
                game.getAnswer());
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        game.setGameId(newId);
        return game;
    }

    // update game result
    @Override
    public boolean updateGame(Game game) {
        final String sql = "UPDATE game SET "
                + "isFinished = ? "
                + "WHERE gameId = ?;";

        return jdbcTemplate.update(sql,
                game.isFinished(),
                game.getGameId()) > 0;
    }
    // get all game from mysql
    @Override
    public List<Game> getAllGames() {
        final String sql = "SELECT gameId, answer, isFinished FROM game;";
        return jdbcTemplate.query(sql, new GameMapper());
    }
    // get a game from mysql by game id
    @Override
    public Game getGame(int gameId) {
        try {
            final String sql = "SELECT gameId, answer, isFinished "
                + "FROM game WHERE gameId = ?;";
            return jdbcTemplate.queryForObject(sql, new GameMapper(), gameId);
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    
    private final class GameMapper implements RowMapper<Game> {
    @Override
    public Game mapRow(ResultSet rs, int index) throws SQLException {
        Game game = new Game();
        game.setGameId(rs.getInt("GameId"));
        game.setAnswer(rs.getString("answer"));
        game.setisFinished(rs.getBoolean("isFinished"));
        return game;
        }
    }
}
