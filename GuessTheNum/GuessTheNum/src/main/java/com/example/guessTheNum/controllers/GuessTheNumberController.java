/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.controllers;

import com.example.guessTheNum.model.Game;
import com.example.guessTheNum.model.Round;
import com.example.guessTheNum.service.ServiceLayer;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author 69591
 */
@RestController
@RequestMapping("/api")
public class GuessTheNumberController {
    @Autowired
    ServiceLayer myService;
    
    @GetMapping
    public int all() {
        return 1;
    }
    
    //create new game
    @PostMapping("/begin")
    @ResponseStatus(HttpStatus.CREATED)
    public int createGame(){
       Game newGame = myService.createGame();
       return newGame.getGameId();
    }
    
    //ask user for game id and guess number
    @PostMapping("/guess")
    public ResponseEntity<Round>createRound(@RequestBody Round guess){
        Round newRound = myService.createRound(guess.getGame().getGameId(), guess.getGuess());
        if (newRound == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(newRound);
    }
    // print all games
    @GetMapping("/game")
    public List<Game> getAllGames(){
        return myService.getAllGames();
    }
    
    //print a game by id
    @GetMapping("/game/{id}")
    public ResponseEntity<Game> getGame(@PathVariable int id) {
        Game result = myService.getGame(id);
        if (result == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(result);
    }
    //print all rounds of a game
    @GetMapping("/round/{id}")
    public ResponseEntity<List<Round>> getGameRounds(@PathVariable int id) {
        List<Round> rounds = myService.getRoundsForGame(id);
        if (rounds == null) {
            return new ResponseEntity(null, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(rounds);
    }
}
