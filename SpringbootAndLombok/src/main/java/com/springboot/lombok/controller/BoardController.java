package com.springboot.lombok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.springboot.lombok.model.Board;
import com.springboot.lombok.service.BoardService;

import lombok.extern.slf4j.Slf4j;



@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/reactapi")
public class BoardController {


	@Autowired
	private BoardService boardService;

	@GetMapping("/board")
	public List<Board> getAllBoards() {
		log.info(" board DB 호출");
		return boardService.getAllBoard();
	}
	
	@PostMapping("/board")
	public Board createBoard(@RequestBody Board board) {
	//public Board createBoard(Board board) {
		return boardService.createBoard(board);
	}
	
}
