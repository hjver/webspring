package spring_learning;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class mainpage2 {
	
	//WEB-INF : Controller, Model이 접근할 수 있는 디렉토리
	//return 사용시 WEB-INF/디렉토리명/파일명 형태로 구성
	
	/*
	 * DTO로 프론트앤드의 값을 받을 수 있습니다. (lombok)
	 * 별도의 값을 받아서 처리할 경우는 Servlet 형태의 request로 받으면 됨
	 *   ** 프론트의 name과 동일해야 함
	 *   
	 * DTO활용 : Front-end값 이관, Model에 값을 이관, 데이타베이스에 저장
	 */
	@GetMapping("/login.do")
	public String login(user_DTO dto, HttpServletRequest req, Model m) {
		String ck = req.getParameter("mcheck");
		System.out.println(ck);
		//Model로 해당 jsp에 변수를 이관함. 출력은 jstl 변수선언으로 출력
		m.addAttribute("mid", dto.getMid());

		System.out.println(dto.getMemail());
		return "WEB-INF/view/login";	
	}
	
	/*
	 * Autowired : java에서 사용하는 class 또는 interface의 값을 
	 * xml에 있는 id 기준으로 대체 하는 형태 (의존성 주입)
	*/
	@Autowired
	BasicDataSource dbinfo;
	
	//DB Query문 작성 및 데이터를 가져오기 위한 interface
	Connection con = null;
	PreparedStatement ps = null;
	ResultSet rs = null;
	
	//Database + XNL + Connection + Controller
	@GetMapping("/event_list.do")
	public String event_list(HttpServletRequest req) {
		
		try {
			//db_config.xml에 있는 정보를 Connetion으로 이관
			this.con = this.dbinfo.getConnection();
			String sql = "select * from event order by eidx desc";
			this.ps = this.con.prepareStatement(sql);
			//this.rs = this.ps.executeQuery();
			req.setAttribute("ps", this.ps);
			//req.setAttribute("rs", this.rs); //REsultSet을 JSP 전송
			//단점 : this.ps, this.rs close를 하지 못함
			
			//this.rs.close();
			//this.ps.close();
		}catch (Exception e) {
			
		}finally {
			
		}
		return null;
	}
	
	//RequestMapping : GET,POST,PUT.... 모든 통신을 다 받을 수 있음(기본)
	/*
	 * value 속성 : 가상의 경로
	 * method 속성 : 통신방법(Front-end 데이터 이관방법)
	 * 
	 */
	@RequestMapping(value="/event_infook.do",method=RequestMethod.POST) //보안상 method 지정해야 함
	public String eventok(event_DTO dto) {
		System.out.println(dto.getEname());
		return "eventok";
	}
	
	/*
	@RequestMapping("/event_infookdo")  //GET도 열리므로 보안상 매우 취약
	public String eventok() {
	
		return "eventok";
	}
	*/
}
