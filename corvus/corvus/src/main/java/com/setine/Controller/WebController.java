package com.setine.Controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.setine.commonfn.CrowCommonFnService;

@Controller
public class WebController {

	@Autowired
	private CrowCommonFnService CrowCommonFnService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(Model model, HttpServletRequest request) throws IOException, ParseException {
		ModelAndView result = new ModelAndView();

		// NecisData
		// String NecisRecentBoard_Url =
		// "http://necis.kma.go.kr/necis-dbf/user/earthquake/annual_earthquake.do";
		// CrowCommonFnService.getNecisData(NecisRecentBoard_Url);
		//
		// // KmaData
		// String KmaData_Url =
		// "http://www.kma.go.kr/weather/earthquake_volcano/report.jsp";
		// CrowCommonFnService.getKmaData(KmaData_Url);
		
		// TodayBoard
//		String today_Url = "http://www.todayhumor.co.kr/board/list.php?table=freeboard";
//		// get articles
//		String todayArticles_Url = "http://www.todayhumor.co.kr/board/view.php?table=freeboard&no=";
//		String todayBoard = "TodayBoard";
//
//		CrowCommonFnService.getCommunityData(todayBoard,today_Url,todayArticles_Url);
		
		// Fixed
		model.addAttribute("now", CrowCommonFnService.GetDate());
		result.setViewName("index");
		return result;
	}

	

}