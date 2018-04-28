package com.setine.springbatch.cron;

import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import javax.net.ssl.HttpsURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.setine.fncommon.FnCommonDAOService;

@Component
public class Scheduler {

	@Autowired
	FnCommonDAOService FnCommonDAOService;
	String disconnect_time;

	// 2일 마다 오전 6시에 실행
	@Scheduled(cron = "0 0 6 */2 * ?")
	public void cronTest_logging3() throws IOException, KeyManagementException, NoSuchAlgorithmException, ParseException {
		// Get HTTPS URL connection
		String target_url = "https://127.0.0.1/";
		String urldownload = "_method=";
		URL url = new URL(target_url);
		HttpsURLConnection conn = FnCommonDAOService.setConnection_https(url);
		FnCommonDAOService.save_last7days_data_file(conn, urldownload, url);
	}
	
	// 15일 마다 오전 6시에 실행
	@Scheduled(cron = "0 0 6 */15 * ?")
	public void cronTest_logging2() throws IOException, KeyManagementException, NoSuchAlgorithmException, ParseException {
		String request_date = FnCommonDAOService.Get_FileName();
		FnCommonDAOService.setHours(request_date);
	}
}