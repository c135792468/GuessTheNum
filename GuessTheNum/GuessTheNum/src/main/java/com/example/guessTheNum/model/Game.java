/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.model;

import java.util.Objects;

/**
 *
 * @author 69591
 */
public class Game {
    private int gameId;
    private String answer;
    private boolean isFinished;

    @Override
    public String toString() {
        return "Game{" + "gameId=" + gameId + ", answer=" + answer + ", inProgress=" + isFinished + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + this.gameId;
        hash = 41 * hash + Objects.hashCode(this.answer);
        hash = 41 * hash + (this.isFinished ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.gameId != other.gameId) {
            return false;
        }
        if (this.isFinished != other.isFinished) {
            return false;
        }
        if (!Objects.equals(this.answer, other.answer)) {
            return false;
        }
        return true;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setisFinished(boolean inProgress) {
        this.isFinished = inProgress;
    }

    public int getGameId() {
        return gameId;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public Game() {
    }
}
