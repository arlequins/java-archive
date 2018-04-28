package com.setine.fncommon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.net.ssl.HttpsURLConnection;

public interface FnCommonDAO {

	HttpsURLConnection setConnection_https(URL url)
			throws IOException, NoSuchAlgorithmException, KeyManagementException;

	void save_last7days_data_file(HttpsURLConnection conn, String urldownload, URL url)
			throws IOException, ParseException;

	void setFolder(String waitingtime_folder);

	void setHours(String request_date) throws IOException, ParseException;

	String Get_FileName();

	void setReadable(File thisMonthFile) throws IOException;


}
