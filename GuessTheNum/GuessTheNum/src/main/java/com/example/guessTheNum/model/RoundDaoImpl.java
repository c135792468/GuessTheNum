/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 69591
 */
@Repository
public class RoundDaoImpl implements RoundDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    GameDao gameDao;

    
    //get rounds for game from mysql
    @Override
    public List<Round> getRoundsForGame(int gameId) {
        try{
            final String sql = "SELECT roundId, guess, time_, result, gameId "
                + "FROM round WHERE gameId = ?;";
            return jdbcTemplate.query(sql, new RoundMapper(), gameId);
        }catch(DataAccessException ex){
            return null;
        }
    }
    //get a round by round id
    public Round getRound(int roundId){
        try {
            final String sql = "SELECT roundId, guess, time_, result, gameId "
                + "FROM round WHERE roundId = ?;";
            return jdbcTemplate.queryForObject(sql, new RoundMapper(), roundId);  
        } catch(DataAccessException ex) {
            return null;
        }  
    }
    //create a round and store into mysql
    @Override
    public Round createRound(int gameId, String guessNum, String result) {
        LocalDateTime localDateTime = LocalDateTime.now();
        final String sql = "INSERT INTO round(guess, time_, result, gameId) VALUES(?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                guessNum,
                Timestamp.valueOf(localDateTime),
                result,
                gameId);
        int newId = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        return getRound(newId);
    }
    
    private final class RoundMapper implements RowMapper<Round> {
    @Override
    public Round mapRow(ResultSet rs, int index) throws SQLException {
        Round round = new Round();
        round.setRoundId(rs.getInt("roundId"));
        round.setGuess(rs.getString("guess"));
        round.setTime_(rs.getTimestamp("time_").toLocalDateTime());
        round.setResult(rs.getString("result"));
        round.setGame(gameDao.getGame(rs.getInt("gameId")));
        return round;
        }
    }
}
    

