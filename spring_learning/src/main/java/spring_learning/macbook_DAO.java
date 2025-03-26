package spring_learning;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// macbook_mapper(인터페이스) 만들지 않아도 작동
//DAO : 데이터를 Access를 하는 역할
//@Repository : Model을 Controller에 호출하도록 함
@Repository("macbook_DAO")
public class macbook_DAO implements macbook_mapper{ //Mapper interface를 implement

	//Mybatis => DB 연결
	@Resource(name="template")
	public SqlSessionTemplate st;
	
	@Override
	public int macbook_delete(int midx) {
		int result = this.st.delete("macbook_delete",midx);
		return result;
	}
	
	//하나의 데이터만 가져오는 메소드
	@Override
	public macbook_DTO macbook_one(String midx){
		//setter형태로 DB에 있는 데이터를 이관
		//selectOne("mapper.xml에서 사용하는 id명",매개변수);
		macbook_DTO onedata = this.st.selectOne("macbook_one",midx);
		return onedata;
	}
	
	@Override
	public List<macbook_DTO> macbook_select(){
		//selectOne : 데이터 한개만 가져올 때(DTO)
		//selectList : 데이터를 여러개를 가져올때 (List배열로 가져옴)
		List<macbook_DTO> classList = this.st.selectList("macbook_select");
		
		return classList;
	}
	
	//신규 데이터 입력
	@Override
	public int macbook_insert(macbook_DTO dto) {
		int result = this.st.insert("macbook_insert",dto);
		return result;
	}
	
	//데이터 수정 메소드
	@Override
	public int macbook_update(macbook_DTO dto) {
		int result = this.st.update("macbook_update",dto);
		return result;
	}
}

/*
//macbook_mapper(인터페이스) 활용하는 방법
@Repository("macbook_DAO")
public class macbook_DAO {
	
	@Autowired
	private macbook_mapper mapper;
	
	public List<macbook_DTO> macbook_one(String midx){
		return mapper.macbook_one(midx);
	}
	
	public List<macbook_DTO> macbook_select(){
		return mapper.macbook_select();
	}
	
	public int macbook_in(macbook_DTO dto) {
		return mapper.macbook_insert(dto);
	}
}
*/