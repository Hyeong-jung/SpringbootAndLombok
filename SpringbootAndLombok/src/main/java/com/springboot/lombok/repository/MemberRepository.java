package com.springboot.lombok.repository;



import com.springboot.lombok.model.MemberVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface MemberRepository extends JpaRepository<MemberVo, Long> {

	// 비워있어도 잘 작동함. 
	// long X Long O ex) int => Integer 같이 primitive형식 사용못함 
	// findBy ColumName 
	public List<MemberVo> findById(String id); 
	public List<MemberVo> findByName(String name); 
	//like검색 
	public List<MemberVo> findByNameLike(String keyword);
	
	
	
}
