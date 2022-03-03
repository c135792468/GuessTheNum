/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.service;

import com.example.guessTheNum.model.Game;
import com.example.guessTheNum.model.GameDao;
import com.example.guessTheNum.model.GameDaoImpl;
import com.example.guessTheNum.model.Round;
import com.example.guessTheNum.model.RoundDao;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author 69591
 */
@Repository
public class ServiceLayerImpl implements ServiceLayer{
    @Autowired
    GameDao gameDao;
    @Autowired
    RoundDao roundDao;

    //create game
    @Override
    public Game createGame() {
        return gameDao.createGame();
    }
    //create round and check if guessNum equal answer
    @Override
    public Round createRound(int gameId, String guessNum) {
        Game game = gameDao.getGame(gameId);
        if(game == null){
            return null;
        }
        else{
            String answer = game.getAnswer();
            int e = 0;
            int p = 0;
            String result = "";
            if(game.getAnswer().equals(guessNum)){
                game.setisFinished(true);
                gameDao.updateGame(game);
                result = "e:4p:0";
                return roundDao.createRound(gameId, guessNum, result);
            }
            else{
                for(int i = 3; i >= 0; i--){
                    for(int j = 3; j >= 0; j--){
                        if((i == j) && (answer.charAt(i) == guessNum.charAt(i)) ){
                            e++;
                        }
                        else if(answer.charAt(i) == guessNum.charAt(j)){
                            p++;
                        }
                    }
                }
                result = "e:"+e+"p:"+p;
                Round round = roundDao.createRound(gameId, guessNum, result);
                game = getGame(round.getGame().getGameId());
                round.setGame(game);
                return round;
            }
        }
    }
    //get all game and set answer to null if not finish
    @Override
    public List<Game> getAllGames() {
        List <Game> allGame = gameDao.getAllGames();
        for(Game game: allGame){
           if(!game.isFinished()){
               game.setAnswer(null);
           } 
        }
        return allGame;
    }
    //get a game and set answer to null if not finish
    @Override
    public Game getGame(int gameId) {
        Game game = gameDao.getGame(gameId);
        if(game == null){
            return null;
        }
        else{
            if(game.isFinished()){
                return game;
            }
            else{
                game.setAnswer(null);
                return game;
            }
        }
    }
    //get rounds for game and set answer to null for not finish game
    @Override
    public List<Round> getRoundsForGame(int GameId) {
        List <Round> gameRounds = roundDao.getRoundsForGame(GameId);
        for(Round round: gameRounds){
           if(!round.getGame().isFinished()){
               round.getGame().setAnswer(null);
           } 
        }
        return gameRounds;
    }
    
}
