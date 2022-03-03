/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author 69591
 */
public class Round {
    private int roundId;
    private String guess;
    private LocalDateTime time_;
    private String result;
    Game game;

    public Round() {
    }

    public int getRoundId() {
        return roundId;
    }

    public String getGuess() {
        return guess;
    }

    public LocalDateTime getTime_() {
        return time_;
    }

    public String getResult() {
        return result;
    }

    public Game getGame() {
        return game;
    }

    public void setRoundId(int roundId) {
        this.roundId = roundId;
    }

    @Override
    public String toString() {
        return "Round{" + "roundId=" + roundId + ", guess=" + guess + ", time_=" + time_ + ", result=" + result + ", game=" + game + '}';
    }

    public void setGuess(String guess) {
        this.guess = guess;
    }

    public void setTime_(LocalDateTime time_) {
        this.time_ = time_;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setGame(Game game) {
        this.game = game;
    }


    
}
