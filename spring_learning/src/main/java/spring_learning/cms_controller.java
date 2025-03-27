package spring_learning;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class cms_controller {
	
	@Resource(name="template")
	public SqlSessionTemplate st;

	@PostMapping("/cms/cmsok.do")
	public String cmsok(@RequestParam String csubject,
			@RequestParam String cuser,
			@RequestParam(name="cate", required=false) ArrayList<String> cate
			) throws Exception{
		/* 
		 * ArrayList<String> 클래스 배열로 동일한 checkbox 처리함
		 * checkbox가 동일한 이름에 여러개가 있을 경우 배열로 닶을 받으며, DB에 저장시
		 * String 변환하여 String.join() 클래스를 이용하여 DB set이라는 자료형으로 저장됨
		 */
		String catein = String.join(",", cate);
		Map<String, String> data = new HashMap<>();
		data.put("csubject", csubject);
		data.put("cuser", cuser);
		data.put("cate", catein);
		
		/*
		 * mapper.xml에 다른 Table을 사용하더라도 문제가 되지 않습니다.
		 * 단점 : 유지보수할 경우 해당 쿼리문을 찾아야 함
		 */
		int result = this.st.insert("macbook_user.cms_in", data);
		System.out.println(result);
		return null;
	}
	
	//CMS 상담신청 내역 상세페이지
	@GetMapping("/cms/cmsview.do")
	public String cmsview(Model m) {
		//데이터 그룹 한개만 가져옴
		/*
		 * mapper에서 resultType="String"으로 처리했을 경우는 하나의 컬럼 값에 대해서만
		 * 처리할때 사용할수 있습니다. 단, 여러개의 컬럼으로 처리시에는 첫번째 컬럼외에 모두 
		 * loss 처리 합니다. (예시: count, avg, sum, max, min, midx, mname 등)
		 */
		Map<String, Object> result = this.st.selectOne("macbook_user.cms_views");
		m.addAttribute("csubject", result.get("csubject"));
		m.addAttribute("cuser", result.get("cuser"));
		m.addAttribute("cate", result.get("cate"));
		return null;
	}
}
