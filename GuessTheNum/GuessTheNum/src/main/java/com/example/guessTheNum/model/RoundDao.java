/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.guessTheNum.model;

import java.util.List;

/**
 *
 * @author 69591
 */
public interface RoundDao {
    List<Round> getRoundsForGame(int gameId);
    Round createRound(int gameId, String guessNum, String result);
}
