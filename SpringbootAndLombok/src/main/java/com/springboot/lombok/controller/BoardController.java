package com.springboot.lombok.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
//import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.springboot.lombok.model.Board;
import com.springboot.lombok.service.BoardService;
import com.springboot.lombok.service.BoardCsvService;
import com.springboot.lombok.util.BoardExcelExporter;

import lombok.extern.slf4j.Slf4j;



@CrossOrigin(origins = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/reactapi")
public class BoardController {


	@Autowired
	private BoardService boardService;
	
	@Autowired
	private BoardCsvService csvService;
	
	@GetMapping("/board")
	public ResponseEntity<Map> getAllBoards(@RequestParam(value = "p_num", required=false) Integer p_num) {
		if (p_num == null || p_num <= 0) p_num = 1;
		
		return boardService.getPagingBoard(p_num);
	}
	

	/*@GetMapping("/board")
	public List<Board> getAllBoards() {
		log.info(" board DB 호출");
		return boardService.getAllBoard();
	}*/
	
	@PostMapping("/board")
	public Board createBoard(@RequestBody Board board) {
	//public Board createBoard(Board board) {
		return boardService.createBoard(board);
	}
	
	@GetMapping("/board/{no}")
	public ResponseEntity<Board> getBoardByNo(@PathVariable Integer no) {
		
		return boardService.getBoard(no);
	}
	
	@PutMapping("/board/{no}")
	public ResponseEntity<Board> updateBoardByNo(@PathVariable Integer no, @RequestBody Board board){

		return boardService.updateBoard(no, board);

	}
	
	@DeleteMapping("/board/{no}")
	public ResponseEntity<Map<String, Boolean>> deleteBoardByNo(@PathVariable Integer no) {
		
		return boardService.deleteBoard(no);
	}
	
    @GetMapping(value = "/board/excel")
    public void exportToExcelBoardList(HttpServletResponse response) throws IOException {
        log.info(" Board DB 호출");

        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=BoardList_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
        
                
        final List<Board> boardListAll = boardService.getAllBoard();
        
        BoardExcelExporter excelExporter = new BoardExcelExporter(boardListAll);
        
        excelExporter.export(response);
    }
    
    //URL - http://localhost:8000/reactapi/board/csv
    //note - incoming request to contain the mandatory "Content-disposition" and "Content-Type" headers
    @GetMapping(value = "/board/csv")
    public ResponseEntity<InputStreamResource> exportToCsvBoardList(@RequestHeader(name = "Content-disposition") final String fileName, 
    		@RequestHeader(name = "Content-Type") final String mediaType) {

        log.info("Downloading residents csv");
        final List<Board> boardListAll = boardService.getAllBoard();
        final InputStreamResource resource = new InputStreamResource(csvService.load(boardListAll));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, fileName)
                .contentType(MediaType.parseMediaType(mediaType))
                .body(resource);
    	
    }
	
}
