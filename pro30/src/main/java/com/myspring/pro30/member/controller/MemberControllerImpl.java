package com.myspring.pro30.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myspring.pro30.member.service.MemberService;
import com.myspring.pro30.member.vo.MemberVO;


@Controller("memberController")
public class MemberControllerImpl implements MemberController {
	
	@Autowired
	private MemberService memberService;
	@Autowired
	MemberVO memberVO;
	
	@Override
	@RequestMapping(value="/member/listMembers.do", method=RequestMethod.GET)
	public ModelAndView listMembers(HttpServletRequest request, 
									HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		//String viewName = getViewName(request); interceptor를 사용하지고않고 아래의 getview사용시에는 이 구문을 사용한다.
		String viewName =(String)request.getAttribute("viewName"); //interceptor를 사용했기에 브라우저에서 요청받은 도메인을 request.setAttribute와 getAttribute를 이용해 저장 호출한다.
		List membersList = memberService.listMembers();
		ModelAndView mav= new ModelAndView(viewName);
		mav.addObject("memberList",membersList);
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/addMember.do",method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		int result =0;
		result= memberService.addMember(member);
		ModelAndView mav= new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	  
	@Override
	@RequestMapping(value="/member/removeMember.do", method= RequestMethod.GET)
	public ModelAndView removeMember(@RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		memberService.removeMember(id);
		ModelAndView mav= new ModelAndView("redirect:/member/listMembers.do");
		return mav;
	}
	
	@RequestMapping(value="/member/*Form.do",method=RequestMethod.GET)
	public ModelAndView form(@RequestParam(value="result", required=false) String result, 
						/*가져올데이터이름(value="result") 가져올데이터를담을변수(String result) */
							@RequestParam(value="action",required=false) String action,
			HttpServletRequest request , HttpServletResponse response) throws Exception{
		//String viewName = getViewName(request);
		String viewName =(String)request.getAttribute("viewName"); /*intercepotor getviewName메서드이용*/
		HttpSession session = request.getSession(); /*session객체 이용하여 viewName받음 */
		session.setAttribute("action", action); /*session객체 이용하여 action키 값 받음 */
		ModelAndView mav = new ModelAndView(); 
		mav.addObject("result",result); /*ModelAndView객체 이용하여 값 추가*/
		mav.setViewName(viewName);
		return mav; /* ModelAndView객체 이용하여 값을 전달 */
	}
	
	private String getViewName(HttpServletRequest request) throws Exception{
		String contextPath = request.getContextPath();
		String uri = (String) request.getAttribute("javax.servlet.include.request_uri");
		if(uri==null || uri.trim().equals("")) {
			uri=request.getRequestURI();
		}
		int begin = 0;
		if(!((contextPath == null)||("".equals(contextPath)))) {
			begin = contextPath.length();
		}
		int end;
		if(uri.indexOf(";")!=-1) {
			end = uri.indexOf(";");
		}else if (uri.indexOf("?")!=-1) {
			end= uri.indexOf("?");
		}else {
			end= uri.length();
		}
		String viewName = uri.substring(begin,end);
		if(viewName.indexOf(".")!=-1) {
			viewName = viewName.substring(0,viewName.lastIndexOf("."));
		}
		if(viewName.lastIndexOf("/")!=-1) {
			viewName = viewName.substring(viewName.lastIndexOf("/",1),viewName.length());
		}
		return viewName;
	}
	
	@Override
	@RequestMapping(value="/member/login.do",method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, 
			RedirectAttributes rAttr, 
			HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		ModelAndView mav= new ModelAndView();
		memberVO = memberService.login(member);
		if(memberVO != null) { 
			HttpSession session = request.getSession();
			session.setAttribute("member",memberVO); /*header.jsp의 jstl표현언어 ${member.name}참고 */
			session.setAttribute("isLogOn", true); /*header.jsp참고 */
			String action =(String) session.getAttribute("action");
			session.removeAttribute("action");
			if(action!=null) {
				mav.setViewName("redirect:"+action); /* action도메인 비어있음 */
			}else {
				mav.setViewName("redirect:/member/listMembers.do"); /*로그인 성공시*/
			}
		}else {
			rAttr.addFlashAttribute("result", "loginFailed");
			mav.setViewName("redirect:/member/loginForm.do");   
		}
		return mav;
	}
	
	@Override
	@RequestMapping(value="/member/logout.do",method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav= new ModelAndView();
		mav.setViewName("redirect:/member/listMembers.do");
		return mav;
	}
	
	@RequestMapping(value= "/main.do",method=RequestMethod.GET)
	private ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String viewName =(String)request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		return mav;
	}

}