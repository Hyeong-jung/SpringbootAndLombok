package com.springboot.lombok.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springboot.lombok.exception.ResourceNotFoundException;
import com.springboot.lombok.model.Board;
import com.springboot.lombok.model.Resident;
import com.springboot.lombok.repository.BoardRepository;

import com.springboot.lombok.util.PagingUtil;


@Service
public class BoardService {

	@Autowired
	private BoardRepository boardRepository;
	
	
	public int findAllCount() {
		return (int) boardRepository.count();
	}
	
	public ResponseEntity<Map> getPagingBoard(Integer p_num) {
		Map result = null;
		
		PagingUtil pu = new PagingUtil(p_num, 5, 5); // ($1:표시할 현재 페이지, $2:한페이지에 표시할 글 수, $3:한 페이지에 표시할 페이지 버튼의 수 )
		//List<Board> list = boardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());
		//List<Board> list = boardRepository.findFromTo(pu.getObjectStartNum(), pu.getObjectCountPerPage());

		
		final Pageable pageable = PageRequest.of(pu.getObjectStartNum(), pu.getObjectCountPerPage());
		List<Board> list = boardRepository.findAll(pageable).getContent();
		
		pu.setObjectCountTotal(findAllCount());
		pu.setCalcForPaging();
		
		System.out.println("p_num : "+p_num);
		System.out.println(pu.toString());
		
		if (list == null || list.size() == 0) {
			//return null;
		}
		
		result = new HashMap<>();
		result.put("pagingData", pu);
		result.put("list", list);
		
		return ResponseEntity.ok(result);
	}	
	
	
	public List<Board> getAllBoard() {
		return boardRepository.findAll();
	}
	
	
	public Board createBoard(Board board) {
		return boardRepository.save(board);
	}
	
	
	public ResponseEntity<Board> getBoard(Integer no) {
		Board board = boardRepository.findById(no).orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		return ResponseEntity.ok(board);
	}

	public ResponseEntity<Board> updateBoard(
			Integer no, Board updatedBoard) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		board.setType(updatedBoard.getType());
		board.setTitle(updatedBoard.getTitle());
		board.setContents(updatedBoard.getContents());
		board.setUpdatedTime(new Date());
		
		Board endUpdatedBoard = boardRepository.save(board);
		return ResponseEntity.ok(endUpdatedBoard);
	}
	
	
	public ResponseEntity<Map<String, Boolean>> deleteBoard(Integer no) {
		Board board = boardRepository.findById(no)
				.orElseThrow(() -> new ResourceNotFoundException("Not exist Board Data by no : ["+no+"]"));
		
		boardRepository.delete(board);
		Map<String, Boolean> response = new HashMap<>();
		response.put("Deleted Board Data by id : ["+no+"]", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
	
}
