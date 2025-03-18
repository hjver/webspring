package spring_learning;

import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

//Spring Controller + View 기초
//@Controller : 해당 일반 class를 web에서 작동할 수 있도록 변경하도록 함
@Controller
public class mainpage {
	
	PrintWriter pw = null;
	//@GetMapping == doGet, @PostMapping == doPost, @RequestMapping == doService
	//throws + HttpServletRequest + HttpServletResponse (View 사용 X) => 바로 값 전달
	@GetMapping("/abc.do")
	public void abc(HttpServletRequest req, HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8"); // 이건 넣어줘야 안깨짐.. 프론트 값 req.~ 는 안해도됨..
		this.pw = res.getWriter();
		this.pw.write("<script>alert('테스트 페이지 입니다.');</script>");
		this.pw.close();
		System.out.println("ABC페이지");
	}
	
	@PostMapping("/bbb.do")//아무것도 안붙임 -> 무조건 뷰로 전달(bbb.jsp가 없으면 404)
	public void bbb(HttpServletRequest req) {//void는 같은 이름으로 사용해야함
		//FE값을 받음
		String pdnm = req.getParameter("pdnm");
		//View(bbb.jsp)로 값을 보냄
		req.setAttribute("pdnm", pdnm);
		System.out.println("BBB페이지");
	}
	
	//return형태의 메서드는 view파일명을 다르게 사용할 수 있다.
	//기본은 return null(컨트롤(do) 이름과 동일한 jsp를 찾게됨.)
	//return "" (컨트롤(do)이름과 다른 jsp를 사용 가능)
	@PostMapping("/ccc.do")
	public String ccc(HttpServletRequest req) {
		String pdnm = req.getParameter("pdnm");
		req.setAttribute("pdnm", pdnm);
		return "/product_list";
	}
	
	//request로 view(jsp)로 전달방식 아님
	@PostMapping("/ddd.do")
	public ModelAndView ddd(HttpServletRequest req) {
		
		String pdnm = req.getParameter("pdnm");
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		//ModelAndView(Object자료형) : 배열
		ModelAndView mv = new ModelAndView();
		mv.addObject("pdnm",pdnm); //addObject : 키 배열 형태로 값을 저장시킴
		mv.addObject("pcode",pcode);
		mv.addObject("pmoney",pmoney);
		//setView : null은 Mapping이름과 동일한 jsp를 찾게 됩니다.
		mv.setView(null);
		
		//Mapping과 다른이름을 사용하고 싶은 경우
		//mv.setViewName("bbb");
		
		return mv; //무조건 ModelAndView 객체명을 사용해야 함
	}
	
	@PostMapping("/eee.do")
	public String eee(HttpServletRequest req, Model m) {
		String pdnm = req.getParameter("pdnm");
		String pcode = req.getParameter("pcode");
		String pmoney = req.getParameter("pmoney");
		
		//Model(interface)를 이용하여 JST로 값을 전달 (JSTL형태로 값 출력)
		m.addAttribute("pdnm", pdnm);
		m.addAttribute("pcode", pcode);
		m.addAttribute("pmoney", pmoney);
		
		return "ddd";
	}
}
