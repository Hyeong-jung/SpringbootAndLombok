package com.springboot.lombok.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.lombok.model.Board;
import com.springboot.lombok.repository.BoardRepository;


@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}
	
	
	public Board createBoard(Board board) {
		return boardRepository.save(board);
	}
	
}
