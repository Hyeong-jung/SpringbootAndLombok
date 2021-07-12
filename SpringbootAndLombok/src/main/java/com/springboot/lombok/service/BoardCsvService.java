package com.springboot.lombok.service;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;
 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

import com.springboot.lombok.model.Board;

//lombok annotation
@Slf4j
//spring annotation
@Service
public class BoardCsvService {
	

    private static final String[] HEADERS = {"no", "type", "title", "contents", "likes", "counts"};
    private static final CSVFormat FORMAT = CSVFormat.DEFAULT.withHeader(HEADERS);
    
    
    //load data into csv
    public ByteArrayInputStream load(final List<Board> boardList) {
        return writeDataToCsv(boardList);
    }
    
    
    //write data to csv
    private ByteArrayInputStream writeDataToCsv(final List<Board> boardList) {
        log.info("Writing data to the csv printer");
        try (final ByteArrayOutputStream stream = new ByteArrayOutputStream();
             final CSVPrinter printer = new CSVPrinter(new PrintWriter(stream), FORMAT)) {
            for (final Board board : boardList) {
                final List<String> data = Arrays.asList(
                        String.valueOf(board.getNo()),
                        board.getType(),
                        board.getTitle(),
                        board.getContents(),
                        String.valueOf(board.getLikes()),
                        String.valueOf(board.getCounts()));
                printer.printRecord(data);
            }
 
            printer.flush();
            return new ByteArrayInputStream(stream.toByteArray());
        } catch (final IOException e) {
            throw new RuntimeException("Csv writing error: " + e.getMessage());
        }
    }

}
