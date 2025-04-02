package spring_learning;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

//Spring Session 사용방법
@Controller
//해당 세션이 생성 되었을 경우 모든 메소드에 세션값을 Model로 전송
/*@SessionAttributes : Controller에서 셋팅된 값이며, DTO가 있어야 정상적으로 핸들링됨
 * DTO 형태가 Session 형태의 DTO, @SessionAttributes => API Server
 */
//@SessionAttributes("mid") 
public class session_controller {

	//Session을 의존성 주입형태로 interface를 필드에 선언하여 모든 메소드에 세션을 적용가능
	@Autowired
	HttpSession hs;
	
	@GetMapping("/session1.do")
	public String session1(HttpSession se) { //HttpSession(Spring) = HttpServletRequest.getSession
		String userid = "kim";
		se.setAttribute("mid", userid);
		return null;
	}
	
	//HtttpSession : Controller, DAO, DTO, VO
	//해당 세션을 생성하여 model로 전달
	@GetMapping("/session2.do")
	public String session2(HttpSession se, Model m){
		String id = (String)se.getAttribute("mid");
		//System.out.println(id);
		m.addAttribute("mid", id);
		return null;
	}
	
	/*
	 * @SessionAttribute : session.getAttribute 동일한 성능을 가진 오노테이션
	 * 해당 어노테이션 사용시 주의사항은 null일 경우 400 error가 발생할 수 있으르모
	 * name 속성 그리고 required = false
	 */
	@GetMapping("/session3.do")
	public String session3(@SessionAttribute(name="mid", required = false) String mid) {
		System.out.println(mid);
		return null;
	}

	@GetMapping("/session4.do")
	public String session3() {
		String test = (String)this.hs.getAttribute("mid"); //
		System.out.println(test);
		return null;
	}
	
	@GetMapping("/session5.do")
	public String session5() {
		this.hs.invalidate(); //필드에 올려 노은 Session을 로드하여 세션 초기화
		this.hs.removeAttribute("mid"); //특정 등록된 session키만 삭제
		String test = (String)this.hs.getAttribute("mid");
		System.out.println("test : " + test);
		return null;
	}
	
}
