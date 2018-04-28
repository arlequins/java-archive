package com.setine.commonfn;

import java.io.IOException;
import java.text.ParseException;

public interface CrowCommonFn {

	void getNecisData(String necisRecentBoard_Url) throws IOException, ParseException;

	String GetDate();

	void getKmaData(String kmaData_Url) throws IOException, ParseException;

	void getCommunityData(String board, String url, String articles_Url) throws IOException, ParseException;

}
