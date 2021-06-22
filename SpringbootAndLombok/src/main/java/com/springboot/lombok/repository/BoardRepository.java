package com.springboot.lombok.repository;



import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springboot.lombok.model.Board;

/*
public interface BoardRepository extends JpaRepository<Board, Integer> {

}
*/

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>, PagingAndSortingRepository<Board, Integer> {

}
