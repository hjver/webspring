package spring_learning;

import java.sql.Connection;
import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/* macbook_mapper(인터페이스) 만들지 않아도 작동
//DAO : 데이터를 Access를 하는 역할
//@Repository : Model을 Controller에 호출하도록 함
@Repository("macbook_DAO")
public class macbook_DAO {

	//Mybatis => DB 연결
	@Resource(name="template")
	public SqlSessionTemplate st;
	
	public List<macbook_DTO> macbook_select(){
		
		//selectOne : 데이터 한개만 가져올 때
		//selectList : 데이터를 여러개를 가져올때 (List배열로 가져옴)
		List<macbook_DTO> classList = this.st.selectList("macbook_select");
		
		return classList;
	}
	
	public int macbook_in(macbook_DTO dto) {
		int result = this.st.insert("macbook_insert",dto);
		return result;
	}
}
*/

//macbook_mapper(인터페이스) 활용하는 방법
@Repository("macbook_DAO")
public class macbook_DAO {
	
	@Autowired
	private macbook_mapper mapper;
	
	public List<macbook_DTO> macbook_select(){
		return mapper.macbook_select();
	}
	
	public int macbook_in(macbook_DTO dto) {
		return mapper.macbook_insert(dto);
	}
}