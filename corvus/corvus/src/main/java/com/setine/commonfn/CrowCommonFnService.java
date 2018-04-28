package com.setine.commonfn;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.setine.hibernate.model.Board;
import com.setine.hibernate.model.KmaData;
import com.setine.hibernate.model.NecisData;
import com.setine.hibernate.service.KmaDataService;
import com.setine.hibernate.service.NecisDataService;
import com.setine.mongodb.BoardDAO;

@Repository
public class CrowCommonFnService implements CrowCommonFn {

	@Autowired
	private NecisDataService NecisDataService;
	@Autowired
	private KmaDataService KmaDataService;
	@Autowired
	private BoardDAO BoardDAO;

	@Override
	public void getCommunityData(String board, String url, String articles_Url) throws IOException, ParseException {
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, InetSocketAddress.createUnresolved("127.0.0.1", 8080));
//		System.setProperty("http.proxyHost", "222.148.111.218");
//		System.setProperty("http.proxyPort", "1461");


		
		Document doc = Jsoup.connect(url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
				.ignoreHttpErrors(true).get();
		int type = 0;
		int recentCount = 1;
		if (board.contains("DC")) {
			type = 1;
			recentCount = getDCBoard(doc);
		} else if (board.contains("Today")) {
			type = 2;
			recentCount = getTodayBoard(doc);
		}

		ArrayList<Board> boardData = getBoardDetailData(articles_Url, recentCount, type);

		// getCurrentNecisData
		ArrayList<Board> getPreBoardData = new ArrayList<Board>();
		try {
			getPreBoardData = BoardDAO.getBoards(board);
		} catch (Exception e) {
			// skip getBoardData
		}
		// // Insert BoardData
		for (int i = 0; i < boardData.size(); i++) {
			int check = 1;
			for (int j = 0; j < getPreBoardData.size(); j++) {
				int preNecisData = getPreBoardData.get(j).getKey();
				int currentNecisData = boardData.get(i).getKey();
				if (preNecisData == currentNecisData) {
					check = 0;
				}
			}
			if (check == 1) {
				BoardDAO.insert(boardData.get(i), board);
			}
		}
	}

	private int getDCBoard(Document doc) {
		// get table
		Elements table = doc.select("table").select("thead.list_thead").select("tr");

		// get target elements
		Elements getRecent = table.get(6).select("td.t_notice");
		int recentCount = Integer.parseInt(getRecent.text());
		return recentCount;
	}

	private int getTodayBoard(Document doc) {
		// get table
		Elements table = doc.select("table.table_list").select("tbody").select("tr.list_tr_freeboard");

		// get target elements
		Elements getRecent = table.select("td.no").select("a");
		int recentCount = Integer.parseInt(getRecent.get(0).text());
		return recentCount;
	}
	
	// 게시물 정보 가져오
	private ArrayList<Board> getBoardDetailData(String articles_Url, int recentCount, int type)
			throws IOException, ParseException {
		ArrayList<Board> BoardData = new ArrayList<Board>();

		for (int i = recentCount-100; i <= recentCount; i++) {
			try {
				Board board = new Board();
				switch (type) {
				case 1:
					board = getDCdata(articles_Url, i);
					break;

				case 2:
					board = getTodaydata(articles_Url, i);
					break;
				}

				BoardData.add(board);
			} catch (Exception e) {
			}
		}
		return BoardData;
	}

	private Board getTodaydata(String articles_Url, int i) throws IOException, ParseException {
		Document doc = Jsoup.connect(articles_Url + i).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
				.ignoreHttpErrors(true).timeout(30000).get();

		// get div
		Elements divData = doc.select("div.table_container");
		Elements writerArea = divData.select("div.writerInfoContents").select("div");
		int key = i;
		Date date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")
				.parse(writerArea.get(7).text().replace("등록시간 : ", "").trim());

		// get top div
		String writer = writerArea.get(2).select("div").select("a").select("font").select("b").text();
		
		String title = divData.select("div.viewSubjectDiv").select("div").text();
		String content = divData.select("div.viewContent").html();

		Board Board = new Board(key, date, writer, title, content);
		return Board;
	}

	private Board getDCdata(String articles_Url, int i) throws IOException, ParseException {
		Document doc = Jsoup.connect(articles_Url + i).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36")
				.ignoreHttpErrors(true).timeout(30000).get();

		// get div
		Elements divData = doc.select("#dgn_content_de");

		int key = i;
		Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.parse(divData.select("div.w_top_right").select("ul").select("li").get(0).text());
		// get top div
		Elements topdivData = divData.select("div.w_top_left").select("dl");
		String title = topdivData.get(0).select("dd").text();
		String writer = topdivData.get(1).select("dd").select("span.user_layer").select("span").get(0).text();
		String content = doc.select("div.s_write").select("table").select("tbody").select("tr").select("td").html();

		Board Board = new Board(key, date, writer, title, content);
		return Board;
	}

	@Override
	public void getNecisData(String necisRecentBoard_Url) throws IOException, ParseException {
		Document doc = Jsoup.connect(necisRecentBoard_Url).get();

		// get table
		Elements table = doc.select("table.obsTable");

		// every elements in each tbody
		Elements gettbody = table.select("tbody");

		// Create Container
		ArrayList<NecisData> necisDataList = new ArrayList<NecisData>();
		for (int i = 0; i < gettbody.size(); i++) {

			Elements gettbody_tr_td = gettbody.get(i).select("tr").select("td");

			// Parsing Element
			int key = Integer.parseInt(gettbody_tr_td.get(0).text());
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(gettbody_tr_td.get(1).text());
			String magnitude = gettbody_tr_td.get(2).text();
			String lo1 = gettbody_tr_td.get(3).text();
			String lo2 = gettbody_tr_td.get(4).text();
			String loName = gettbody_tr_td.get(5).text();

			// Saving NecisData
			NecisData NecisData = new NecisData(key, date, magnitude, lo1, lo2, loName);
			necisDataList.add(NecisData);
		}

		// getCurrentNecisData
		ArrayList<NecisData> getPreNecisData = new ArrayList<NecisData>();
		try {
			getPreNecisData = (ArrayList<NecisData>) NecisDataService.findAllNecisData();
		} catch (Exception e) {
			// skip getPreNecisData
		}

		// Insert NecisData
		for (int i = necisDataList.size() - 1; i >= 0; i--) {
			int check = 1;
			for (int j = 0; j < getPreNecisData.size(); j++) {
				int preNecisData = getPreNecisData.get(j).getKey();
				int currentNecisData = necisDataList.get(i).getKey();
				if (preNecisData == currentNecisData) {
					check = 0;
				}
			}
			if (check == 1) {
				NecisDataService.save(necisDataList.get(i));
			}
		}
	}

	@Override
	public void getKmaData(String kmaData_Url) throws IOException, ParseException {
		Document doc = Jsoup.connect(kmaData_Url).get();

		// get SelectBox
		Elements selectBox = doc.select("#earthquake_report");

		// every elements in each option
		Elements options = selectBox.select("option");

		// create xml Container
		ArrayList<String> xmlList = new ArrayList<String>();

		for (int i = 0; i < options.size(); i++) {
			String xmlName = options.get(i).attr("value");
			xmlList.add(xmlName);
		}

		// getCurrentKmaData
		ArrayList<KmaData> getPreKmaData = new ArrayList<KmaData>();
		try {
			getPreKmaData = (ArrayList<KmaData>) KmaDataService.findAllKmaData();
		} catch (Exception e) {
			// skip getPreNecisData
		}

		// // Insert KmaData
		for (int i = xmlList.size() - 1; i >= 0; i--) {
			int check = 1;
			String xml_Url = xmlList.get(i);

			for (int j = 0; j < getPreKmaData.size(); j++) {
				int preKmaData = getPreKmaData.get(j).getKey();
				int currentKmaData = Integer.parseInt(xml_Url.split("_")[2]);
				if (preKmaData == currentKmaData)
					check = 0;
			}
			if (check == 1) {

				KmaData kmaData = getKmaDetailData(xml_Url);
				if (kmaData.getKey() != -1) {
					KmaDataService.save(kmaData);
				}
			}
		}
	}

	private KmaData getKmaDetailData(String xml_Url) throws IOException, ParseException {
		KmaData KmaData = new KmaData();

		int type = Integer.parseInt(xml_Url.split("_")[2]);

		String getprepdate = xml_Url.split("_")[3];
		Date pdate = new SimpleDateFormat("yyyyMMddHHmm").parse(getprepdate.replace(".xml", ""));

		if (type == 3 || type == 6 || type == 11) {
			// KmaData
			String KmaData_Url = "http://www.kma.go.kr/weather/earthquake_volcano/report.jsp?eqk=" + xml_Url;

			Document doc = Jsoup.connect(KmaData_Url).timeout(30000).get();
			// get SelectBox
			Elements content_table = doc.select("#content_weather").select("table");

			// every elements in each option
			Elements contents = content_table.select("tbody").select("tr");

			switch (type) {
			case 3:
			case 11:
				for (int i = 0; i < contents.size(); i++) {
					String magnitude = contents.get(0).select("td").text();
					Date date = new SimpleDateFormat("yyyy년 MM월 dd일 HH시 mm분 ss초")
							.parse(contents.get(1).select("td").text());
					String location = contents.get(2).select("td").text();
					String etc = contents.get(3).select("td").text();
					KmaData = new KmaData(type, pdate, date, magnitude, location, etc);
				}
				break;
			case 6:
				for (int i = 0; i < contents.size(); i++) {
					String etc = contents.get(0).select("td").text();
					KmaData = new KmaData(type, pdate, etc);
				}
				break;
			}
			return KmaData;
		} else {
			KmaData.setKey(-1);
			return KmaData;
		}
	}

    
	@Override
	public String GetDate() {
		TimeZone tz;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		tz = TimeZone.getTimeZone("Asia/Seoul");
		df.setTimeZone(tz);
		String str = df.format(date);

		return str;
	}
}
