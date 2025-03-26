package spring_learning;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//API 전용 Controller
@CrossOrigin(origins="*", allowedHeaders = "*") //CORS(Spring, Spring-boot)
@RestController
public class api_controller {
	
	PrintWriter pw = null; //Front-end가 값을 가져갈수 있도록 함
	JSONArray ja = null;
	JSONObject jo = null;  //보조키
	JSONObject alldata = null;  //대표키
	
	@Resource(name="macbook_member_DTO")
	macbook_member_DTO dto;
	
	@Resource(name="user_DAO")
	user_DAO dao;
	
	//Mysql DB가져와서 API로 생성
	@GetMapping("api_data5.do")
	public String api_data5(HttpServletResponse res) throws Exception{
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		List<macbook_member_DTO> result = this.dao.all_list();
		
		JSONObject alldata = new JSONObject();
		JSONArray ja = new JSONArray();
		int w = 0;
		while(w < result.size()) {
			JSONObject jo = new JSONObject();
			jo.put("midx",result.get(w).getMidx());
			jo.put("mid",result.get(w).getMid());
			jo.put("mname",result.get(w).getMname());
			jo.put("memail",result.get(w).getMemail());
			ja.put(jo);
			w++;
		}
		alldata.put("member", ja);
		
		this.pw.print(alldata);
		this.pw.close();
		return null;
	}
	
	@GetMapping("/api_data4.do")
	public String api_data4(HttpServletResponse res) throws Exception {
		/* //CORS(servlet)
		res.addHeader("Access-Control-Allow-Origin", "*");
		res.addHeader("Access-Control-Allow-Credentials", "true");
		*/
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();	
		
		String data[][] = {
				{"모니터", "키보드", "마우스"},
				{"NEW", "BEST", "NEW"}
		};
		String sub_keys[] = {"product_name", "product_ico"};
		
		int w = 0;
		this.jo = new JSONObject();
		while(w < data.length) {
			
			this.ja = new JSONArray();
			for(int f=0; f<data[0].length; f++) {
				this.ja.put(data[w][f]);
			}
			this.jo.put(sub_keys[w], this.ja);
			w++;
		}
		
		this.alldata = new JSONObject();
		this.alldata.put("products",this.jo); //대표키 생성은 최종 반복문 다음에 코드를 작성
		
		this.pw.print(alldata);
		
		this.pw.close();
		return null;
	}		
	
	
	@GetMapping("/api_data3.do")
	public String api_data3(HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();		
		
		String db[] = {"hong","홍길동","hong@nate.com","서울","01012345678"};
		JSONObject jo = new JSONObject();
		jo.put("id", db[0]);
		jo.put("name", db[1]);
		jo.put("email", db[2]);
		jo.put("area", db[3]);
		jo.put("phone", db[4]);
		JSONArray ja = new JSONArray();
		ja.put(jo);
		JSONObject jo2 = new JSONObject();
		jo2.put("myinfo", ja);
		this.pw.print(jo2);
		
		this.pw.close();
		return null;
	}
	
	@GetMapping("/api_data2.do")
	public String api_data2(HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();		
		
		/*
		JSONArray ja = new JSONArray();
		ja.put("홍길동");
		ja.put("강감찬");
		JSONArray ja2 = new JSONArray();
		ja2.put(ja);
		this.pw.print(ja2);
		*/
		
		JSONArray ja = new JSONArray();
		ja.put("홍길동");
		ja.put("강감찬");
		JSONObject jo = new JSONObject();
		jo.put("member", ja);
		JSONArray ja2 = new JSONArray();
		ja2.put(jo);
		this.pw.print(ja2); //맨 마지막 JSON 객체를 출력
		
		this.pw.close();
		return null;
	}
	
	
	@GetMapping("/api_data1.do")
	public String api_data1(HttpServletResponse res) throws Exception {
		res.setContentType("text/html;charset=utf-8");
		this.pw = res.getWriter();
		
		/*
		 * org.json.JSONArray
		 * 
		 */
		
		/*
		 * JSONArray : []
		 * JSONObject : {} //키를 생성
		 * 
		 * org.json : put 사용
		 * org.json.simple : add 사용
		 */
		
		/* //["a","b","c","d"]
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		this.pw.print(ja);
		*/
		
		// {"data":["a","b","c","d"]}
		JSONArray ja = new JSONArray();
		ja.put("a");
		ja.put("b");
		ja.put("c");
		ja.put("d");
		JSONObject jo = new JSONObject();
		jo.put("data", ja);
		this.pw.print(jo);

		this.pw.close();
		return null;
	}
}
