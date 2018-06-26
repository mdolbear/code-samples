package com.oracle.tictactoe.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracle.tictactoe.gamemodel.TicTacToeGame;

/**
 *
 *
 */
public interface TicTacToeGameJpaRepository extends JpaRepository<TicTacToeGame, Long> {

}
