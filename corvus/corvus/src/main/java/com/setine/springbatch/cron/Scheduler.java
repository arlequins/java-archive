package com.setine.springbatch.cron;

import java.io.IOException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.setine.commonfn.CrowCommonFnService;

@Component
public class Scheduler {

	@Autowired
	private CrowCommonFnService CrowCommonFnService;

	@Scheduled(cron = "30 * * * * ?")
	public void cronTest1() throws IOException, ParseException {

		// NecisData
		String NecisRecentBoard_Url = "http://necis.kma.go.kr/necis-dbf/user/earthquake/annual_earthquake.do";
		CrowCommonFnService.getNecisData(NecisRecentBoard_Url);
		
		// dcBoard
		String dC_Url = "http://gall.dcinside.com/board/lists/?id=jijinhee";
		// get articles
		String dCArticles_Url = "http://gall.dcinside.com/board/view/?id=jijinhee&no=";
		String dCBoard = "DCBoard";

		CrowCommonFnService.getCommunityData(dCBoard,dC_Url,dCArticles_Url);
		
		// todayBoard
		String today_Url = "http://www.todayhumor.co.kr/board/list.php?table=freeboard";
		// get articles
		String todayArticles_Url = "http://www.todayhumor.co.kr/board/view.php?table=freeboard&no=";
		String todayBoard = "TodayBoard";

		CrowCommonFnService.getCommunityData(todayBoard,today_Url,todayArticles_Url);
	}

	@Scheduled(cron = "30 0 0 */1 * ?")
	public void cronTest2() throws IOException {

	}
}