package kr.or.bit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.bit.model.EmpDAO;
import kr.or.bit.model.EmpDTO;

@Controller
public class EmpController {
	@Autowired
	private SqlSession sqlsession;
	
	@RequestMapping("/emp.htm")
	public String emp(Model model) {
		EmpDAO empdao = sqlsession.getMapper(EmpDAO.class);
		List<EmpDTO> list = empdao.getEmpList();
		model.addAttribute("list",list);
		/*JSONArray jsonlist = JSONArray.fromObject(list);
		model.addAttribute("jsonlist",jsonlist);*/
		return "joinus.list";
	}
	@RequestMapping("/delete.htm")
	public String delete(String empno) {
		EmpDAO empdao = sqlsession.getMapper(EmpDAO.class);
		empdao.deleteEmp(Integer.parseInt(empno));
		return "redirect:emp.htm";
	}
	@RequestMapping(value="/select.htm" , method= RequestMethod.GET)
	  public String select(int empno , Model model) {
	      EmpDAO empdao = sqlsession.getMapper(EmpDAO.class);
	      List<EmpDTO> list = empdao.getEmpList();
	      for(EmpDTO a : list) {
	          if(a.getEmpno()== empno) {
	              Map<String, Object> map = new HashMap<String, Object>();
	              map.put("empno", empno);
	             
	              empdao.getEmp(map);
	              list = (ArrayList<EmpDTO>)map.get("empList");
	             
	              model.addAttribute("employ" , list.get(0));
	              return "joinus.select";
	          }else {
	             
	          }
	      }
	      return "joinus.list";
	  }
	

	@RequestMapping("/insert.htm")
	public String insert(EmpDTO dto) {
	    EmpDAO empdao = sqlsession.getMapper(EmpDAO.class);
	    empdao.insertEmp(dto);
	    System.out.println("메시지: " + dto.getOutmsg());
	    return "redirect:emp.htm";
	    
	}


	@RequestMapping(value="/update.htm" , method= RequestMethod.POST)
    public String updateok(EmpDTO dto) {
        EmpDAO empdao = sqlsession.getMapper(EmpDAO.class);
        String date;
        try {
            date = dto.getHiredate().substring(0, 10);
            if(date.trim().indexOf("-")!=4) {
            	date = "1996-02-20";
            }else if(date.trim().indexOf("-", 1) != 7) {
            	date = "1996-02-20";
            }
        }catch (Exception e) {
            date = "1996-02-20";
        }
        dto.setHiredate(date);
        empdao.updateEmp(dto);
        return "redirect:emp.htm";
    }
}
