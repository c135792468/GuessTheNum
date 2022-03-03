/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.service;

import com.example.guessTheNum.model.Game;
import com.example.guessTheNum.model.Round;
import java.util.List;

/**
 *
 * @author 69591
 */
public interface ServiceLayer {
    
    public Game createGame();
    
    public Round createRound(int gameId, String guessNum);
    
    public List<Game> getAllGames();
    
    public Game getGame(int gameId);
    
    public List<Round> getRoundsForGame(int GameId);
}
