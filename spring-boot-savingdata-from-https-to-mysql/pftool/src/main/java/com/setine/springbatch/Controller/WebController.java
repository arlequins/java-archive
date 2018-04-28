package com.setine.springbatch.Controller;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.setine.fncommon.FnCommonDAOService;
import com.setine.json.Serverdata;
import com.setine.mybatis.waitingtime.WaitingTimeDAOService;

@Controller
public class WebController {

	@Autowired
	WaitingTimeDAOService WaitingTimeDAOService;
	@Autowired
	FnCommonDAOService FnCommonDAOService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView main(Model model, HttpServletRequest request)
			throws IOException, KeyManagementException, NoSuchAlgorithmException {
		ModelAndView result = new ModelAndView();

		model.addAttribute("nowdate", Get_Time());
		result.setViewName("login");
		return result;
	}

	@RequestMapping(value = "/7days_save", method = RequestMethod.GET)
	public ModelAndView save7daysfile(Model model, HttpServletRequest request)
			throws IOException, KeyManagementException, NoSuchAlgorithmException, ParseException {
		ModelAndView result = new ModelAndView();

		// Get HTTPS URL connection
		String target_url = "https://127.0.0.1/";
		String urldownload = "_method=";
		URL url = new URL(target_url);
		HttpsURLConnection conn = FnCommonDAOService.setConnection_https(url);
		FnCommonDAOService.save_last7days_data_file(conn, urldownload, url);

		model.addAttribute("nowdate", Get_Time());
		result.setViewName("login");
		return result;
	}

	@RequestMapping(value = "/convert_hours", method = RequestMethod.GET)
	public ModelAndView converthoursfile(Model model, HttpServletRequest request)
			throws IOException, KeyManagementException, NoSuchAlgorithmException, ParseException {
		ModelAndView result = new ModelAndView();

		String request_date = request.getParameter("date");
		SimpleDateFormat transFormat_month = new SimpleDateFormat("yyyy-MM");

		@SuppressWarnings("unused")
		Date compare_table = transFormat_month.parse(request_date);
		model.addAttribute("nowdate", Get_Time());
		FnCommonDAOService.setHours(request_date);

		result.setViewName("login");
		return result;
	}

	private String Get_Time() {
		TimeZone tz;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		tz = TimeZone.getTimeZone("Asia/Seoul");
		df.setTimeZone(tz);
		String str2 = df.format(date);

		return str2;
	}

	public Serverdata Serverdata_processing(String data_list) {
		ObjectMapper mapper = new ObjectMapper(); // create once, reuse
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Serverdata value = new Serverdata();
		String item_data = data_list;
		try {
			value = mapper.readValue(item_data, Serverdata.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
}