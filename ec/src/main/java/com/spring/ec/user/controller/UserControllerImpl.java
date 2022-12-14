package com.spring.ec.user.controller;

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

import com.spring.ec.user.service.UserService;
import com.spring.ec.user.vo.MemberVO;

@Controller("userController")
public class UserControllerImpl implements UserController  {
	
	@Autowired
	private UserService userService;
	@Autowired
	MemberVO memberVO;


	@Override
	@RequestMapping(value = "/main.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
/*interceptor에서 gitviewname()메서드 잘라낸 도메인(viewName)을 setAttribute한걸 getAttribute로받음 */
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		System.out.println("controller : main.do");
		return mav;
	}
	@Override
	@RequestMapping(value = "/user/u_regadmin.do", method = RequestMethod.GET)
	public ModelAndView regadmin(@RequestParam(value = "result", required = false) String result,
			/*가져올데이터이름(value="result") 가져올데이터를담을변수(String result) */	
		@RequestParam(value = "action", required = false) String action, 
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//String viewName = getViewName(request); interceptor이용안하고 아래에 getviewName 메서드 추가시 사용하는 코드
		String viewName = (String)request.getAttribute("viewName"); /*intercepotor getviewName메서드이용 그리고 getattribute로 setattribute받음*/
		HttpSession session = request.getSession();  /*session객체 이용하여 viewName받음 */
		session.setAttribute("action", action); /*session객체 이용하여 action키 값 받아 session에 저장 */
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result); /*ModelAndView객체 이용하여 값 추가*/
		mav.setViewName(viewName);
		System.out.println("controller:" + viewName);
		return mav;	 /* ModelAndView객체 이용하여 값을 전달 */
	}
	
	@Override
	@RequestMapping(value = "/user/find_id.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView find_id(HttpServletRequest request, HttpServletResponse response) throws Exception {
/*interceptor에서 gitviewname()메서드 잘라낸 도메인(viewName)을 setAttribute한걸 getAttribute로받음 */
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		System.out.println("controller : main.do");
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/user/find_pwd.do" , method = {RequestMethod.GET,RequestMethod.POST})
	public ModelAndView find_pwd(HttpServletRequest request, HttpServletResponse response) throws Exception {
/*interceptor에서 gitviewname()메서드 잘라낸 도메인(viewName)을 setAttribute한걸 getAttribute로받음 */
		String viewName = (String) request.getAttribute("viewName");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(viewName);
		System.out.println("controller : main.do");
		return mav;
	}
	
	
	@Override
	@RequestMapping(value = "/user/u_board", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listBoards(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List boardsList = userService.listBoards();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("boardsList", boardsList);
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/user/u_board/eatpl", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listEatBoards(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List boardsList = userService.eatListBoards();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("boardsList", boardsList);
		return mav;
	}
	
	@Override
	@RequestMapping(value = "/user/u_board/seepl", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView listSeeBoards(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String) request.getAttribute("viewName");
		List boardsList = userService.seeListBoards();
		ModelAndView mav = new ModelAndView(viewName);
		mav.addObject("boardsList", boardsList);
		return mav;
	}
	
	@RequestMapping(value = "/user/loginForm.do", method = RequestMethod.GET)
	public ModelAndView loginform(@RequestParam(value = "result", required = false) String result,
			@RequestParam(value = "action", required = false) String action, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		return mav;
	}
	
	
	@RequestMapping(value = "/user/memberForm_main.do", method = RequestMethod.GET)
	public ModelAndView memberform_main(@RequestParam(value = "result", required = false) String result,
			@RequestParam(value = "action", required = false) String action, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String viewName = (String)request.getAttribute("viewName");
		HttpSession session = request.getSession();
		session.setAttribute("action", action);
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result);
		mav.setViewName(viewName);
		System.out.println("controller:" + viewName);
		return mav;
		
	}
	@RequestMapping(value = "/user/memberForm_udetail.do", method = RequestMethod.GET)
	public ModelAndView memberform_udetail(@RequestParam(value = "result", required = false) String result,
			/*가져올데이터이름(value="result") 가져올데이터를담을변수(String result) */	
		@RequestParam(value = "action", required = false) String action, 
		HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//String viewName = getViewName(request); interceptor이용하기에 아래 getviewName주석처리
		String viewName = (String)request.getAttribute("viewName"); /*intercepotor getviewName메서드이용*/
		HttpSession session = request.getSession();  /*session객체 이용하여 viewName받음 */
		session.setAttribute("action", action); /*session객체 이용하여 action키 값 받음 */
		ModelAndView mav = new ModelAndView();
		mav.addObject("result", result); /*ModelAndView객체 이용하여 값 추가*/
		mav.setViewName(viewName);
		System.out.println("controller:" + viewName);
		return mav;	 /* ModelAndView객체 이용하여 값을 전달 */
	}
	@Override
	@RequestMapping(value="/user/login.do",method=RequestMethod.POST)
	public ModelAndView login(@ModelAttribute("member") MemberVO member, 
			RedirectAttributes rAttr, 
			HttpServletRequest request, 
			HttpServletResponse response)throws Exception{
		ModelAndView mav= new ModelAndView();
		memberVO = userService.login(member);
		if(memberVO != null) { /*DB에 있는 값과 일치하지않으면 */
			HttpSession session = request.getSession();
			session.setAttribute("member",memberVO);
			session.setAttribute("isLogOn", true);
			String action =(String) session.getAttribute("action");
			session.removeAttribute("action");
			if(action!=null) {
				mav.setViewName("redirect:"+ action); /* action도메인 비어있음 */
			}else {
				mav.setViewName("redirect:/main.do");  /*로그인 성공시*/
			}
		}else {
			rAttr.addFlashAttribute("result", "loginFailed"); /*login.jsp의 loginFailed <choose>구문 실행 */
			mav.setViewName("redirect:/user/loginForm.do");   /* login.jsp redirect한다 */
		}
		return mav;
	}
	
	@Override
	@RequestMapping(value="/user/logout.do",method=RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		session.removeAttribute("member");
		session.removeAttribute("isLogOn");
		ModelAndView mav= new ModelAndView();
		mav.setViewName("redirect:/main.do");
		return mav;
	}

	@Override
	@RequestMapping(value="/user/addMember.do",method=RequestMethod.POST)
	public ModelAndView addMember(@ModelAttribute("member") MemberVO member, HttpServletRequest request, HttpServletResponse response) throws Exception{
		request.setCharacterEncoding("utf-8");
		response.setContentType("html/text;charset=utf-8");
		int result =0;
		result= userService.addMember(member);
		ModelAndView mav= new ModelAndView("redirect:/main.do");
		return mav;
	}
	
	
	
//	@Override
//	@RequestMapping(value = "/category.do" , method = RequestMethod.GET)
//	public ModelAndView category(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		String viewName = "/category/categorymain";
//		System.out.println("viewName::" + viewName);
//		List StoreList = userService.selectAllStores();
//		System.out.println("StoreList::" + StoreList);
//		ModelAndView mav = new ModelAndView(viewName);
//		mav.addObject("StoreList",StoreList);
//		return mav;
//	}
	
	

	
}
