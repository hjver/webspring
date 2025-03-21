package spring_learning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class macbook {

	//@Autowird, @Inject : 의존성 주입 XML=>Java, Java=>XML
	
	/* @Resource : new 클래스명 호출과 동일하게 작동을 하며.
	 * @Repository의 이름을 가져오는 역할
	 */
	@Resource(name="macbook_DAO")
	private macbook_DAO dao;

	//과정 리스트 출력
	@GetMapping("/macbook_list.do")
	public String macbook_list(Model m) {
		//List<macbook_DTO> : DTO 형태의 배열로 생성하여 JSP에 전달
		List<macbook_DTO> classList = this.dao.macbook_select();
		m.addAttribute("ea", classList.size());
		m.addAttribute("classList", classList);
		return null;
	}
	
	//과정개설 메소드
	@PostMapping("/macbook_ok.do")
	public String macbook_ok(macbook_DTO dto, Model m) throws Exception {
		try {
			int result = this.dao.macbook_in(dto);
			String msg = "";
			if(result > 0) {
				msg = "alert('과정 개설이 올바르게 생성 되었습니다.');"
						+ "location.href='./macbook_list.do';";
			}
			m.addAttribute("msg", msg);
		}catch (Exception e) {
			
		}finally {
			
		}
		
		return "load";
	}
}
