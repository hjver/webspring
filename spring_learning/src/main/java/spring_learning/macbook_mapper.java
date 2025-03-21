package spring_learning;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

//@Mapper : mapper.xml과 연동하는 interface
//** mapper.xml에서 사용하는 id기준으로 메소드 이름을 설정하게 됩니다.
@Mapper
public interface macbook_mapper {
	public int macbook_insert(macbook_DTO dto);
	public List<macbook_DTO> macbook_select();
	public int macbook_delete(int midx);
}
