package spring_learning;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class banner_controller {
	List<String> listdata = null;
	Map<String, String> mapdata = null;
	PrintWriter pw = null;
	String result = null;
	int callback = 0;
	ModelAndView mv = null;
	
	@Resource(name="banner_DTO")
	banner_DTO dto;
	
	@Resource(name="banner_DAO")
	banner_DAO dao;
	
	@Resource(name="file_nename")
	file_nename fname; //파일명을 개발자가 원하는 형태로 변경
	
	//Field에 있는 dto와 매개변수에 있는 dto는 다름
	//this.dto는 필드에 있는 dto
	
	//@ModelAttribute : 1:1매칭 => name과 DTO자료형 변수가 같은거 있으면 무조건 값을 setter 발동
	@PostMapping("/banner/bannerok")
	public String bannerok(@ModelAttribute(name="dto") banner_DTO dto,
			MultipartFile bfile,
			HttpServletRequest req
			) throws Exception {
		
		String file_new = null;
		System.out.println(bfile.getSize());
		if(bfile.getSize() > 0) {
			file_new = this.fname.rename(bfile.getOriginalFilename());
			//웹 디렉토리에 개발자가 생성한 파일명으로 저장하는코드
			String url = req.getServletContext().getRealPath("/upload/");
			FileCopyUtils.copy(bfile.getBytes(), new File(url + file_new));
			
			dto.setFile_url("/upload/" +file_new); //웹디렉토리 경로 및 파일명
			dto.setFile_new(file_new);  //개발자가 원하는 방식으로 파일명을 변경값
			dto.setFile_ori(bfile.getOriginalFilename()); //사용자가 적용한 파일명
		}
		
		this.callback = this.dao.new_banner(dto);
		System.out.println(this.callback);

		return null;
	}
	
	//search 검색에 관한사항은 필수조건은 아니며, 또한 null 처리가 되었을 경우 defaultValue=""
	@GetMapping("/banner/bannerlist")
	public String bannerlist(Model m, 
			@RequestParam(defaultValue="", required=false) String search,
			@RequestParam(defaultValue="1", required=false) Integer pageno
			){
		
		int total = this.dao.banner_total();
		
		int userpage = 0;  //사용자가 클릭한 페이지 번호에 맞는 순차번호 계산값
		if(pageno == 1) {
			userpage = 0;
		}else {  //1외 페이지 번호를 클릭시
			userpage = (pageno-1) * 5;
		}
		
		List<banner_DTO> all = null;
		if(search.equals("")) { //검색어가 없을 경우
			all = this.dao.banner_all(pageno); //인자값 : 사용자가 페이지 번호를 클릭한 값
		}
		else { //갬색어가 있을 경우
			all = this.dao.banner_search(search);
		}
		m.addAttribute("userpage", userpage);
		m.addAttribute("total", total);  //데이터 전체 갯수
		m.addAttribute("search",search); //검색어를 JSP로 전달
		m.addAttribute("all",all);
		return null;
	}
}
