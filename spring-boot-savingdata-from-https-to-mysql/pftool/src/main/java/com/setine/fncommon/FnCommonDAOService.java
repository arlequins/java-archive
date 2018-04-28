package com.setine.fncommon;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermission;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.stream.Collectors;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.setine.mybatis.waitingtime.WaitingTime;
import com.setine.mybatis.waitingtime.WaitingTimeDAOService;
import com.setine.mybatis.instancecontent.InstanceContent;
import com.setine.mybatis.instancecontent.InstanceContentDAOService;
import com.setine.mybatis.tmpwait.TmpWaitDAOService;

@Repository
public class FnCommonDAOService implements FnCommonDAO {

	@Autowired
	WaitingTimeDAOService WaitingTimeDAOService;
	@Autowired
	InstanceContentDAOService InstanceContentDAOService;
	@Autowired
	TmpWaitDAOService TmpWaitDAOService;
	String LINUX_PATH = "/var/ftp/pub/waitingtime";

	@Override
	public HttpsURLConnection setConnection_https(URL url)
			throws IOException, NoSuchAlgorithmException, KeyManagementException {
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

		// Set Hostname verification
		conn.setHostnameVerifier(new HostnameVerifier() {
			@Override
			public boolean verify(String hostname, SSLSession session) {
				// Ignore host name verification. It always returns true.
				return true;
			}
		});

		// SSL setting
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(null, null, null); // No validation for now
		conn.setSSLSocketFactory(context.getSocketFactory());
		return conn;
	}

	@Override
	public void setHours(String request_date) throws IOException, ParseException {

		String[] request_split = request_date.split("-");
		String this_month = request_split[1];
		String this_year = request_split[0];

		// 해당 달의 데이터 가져오기
		ArrayList<WaitingTime> WaitingTime_String_list = WaitingTimeDAOService.getList(this_year, this_month);
		ArrayList<String> day_list = new ArrayList<String>();

		if (WaitingTime_String_list.size() > 0) {
			for (int i = 0; i < WaitingTime_String_list.size(); i++) {
				String date = WaitingTime_String_list.get(i).get_date();
				String split_only_date = date.split(" ")[0];
				day_list.add(split_only_date);
			}
		}

		// 중복값 제거
		List<String> only_day_list = day_list.stream().distinct().collect(Collectors.toList());
		ArrayList<String> time = setHoursArrayList();

		// 인스턴스 값 가져오기
		ArrayList<InstanceContent> InstanceContent_list = InstanceContentDAOService.getList();

		String waitingtime_folder_hour = LINUX_PATH + "/summary";
		// 윈도우일 경우
		// String waitingtime_folder_hour = "c:/waitingtime";
		String for_writing = Get_writeFileName();

		String this_monthFile_text = waitingtime_folder_hour + "/wts_date_" + request_date + "_extime_" + for_writing
				+ ".csv";
		File thisMonthFile = new File(this_monthFile_text);

		// CSV 파일 UTF-8 헤더값 넣기
		OutputStream os = new FileOutputStream(thisMonthFile);
		os.write(239);
		os.write(187);
		os.write(191);

		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));

		// 특정 라인에 더미 데이터가 있다면 빼기
		String ini_data = "더미 데이터";
		bw.write(ini_data);
		bw.newLine(); // 줄바꿈
		// 특정 날을 고정
		for (int i = 0; i < only_day_list.size(); i++) {
			String this_day = only_day_list.get(i); // 2016-07-20

			// 특정 날의 시간 루프
			for (int j = 0; j < time.size(); j++) {
				String this_hour = time.get(j); // 00, 01, 02

				// 이하 루프 문에서 검색할 조건
				String target_day_hour = this_day + " " + this_hour;

				ArrayList<WaitingTime> WaitingTime_arraytimes = new ArrayList<WaitingTime>(); 

				// 인스턴스 루프
				for (int k = 0; k < InstanceContent_list.size(); k++) {
					String ins_id = InstanceContent_list.get(k).get_value(); 
					String ins_name = InstanceContent_list.get(k).get_ins_name();

					// 데이터 값을 담을 용기
					WaitingTime WaitingTime_container = new WaitingTime();
					WaitingTime_container.set_key(ins_id);
					WaitingTime_container.set_date(target_day_hour);

					// 값에 해당하는 것을 루프로 검색
					int counting_a = 0;
					int counting_b = 0;
					int counting_c = 0;
					int counting_d = 0;
					int counting_divide = 0;
					for (int l = 0; l < WaitingTime_String_list.size(); l++) {
						String this_insid = WaitingTime_String_list.get(l).get_key();
						String this_date = WaitingTime_String_list.get(l).get_date();

						if (this_insid.equals(ins_id)) {
							if (this_date.contains(target_day_hour)) {
								counting_a += Integer.parseInt(WaitingTime_String_list.get(l).get_melee());
								counting_b += Integer.parseInt(WaitingTime_String_list.get(l).get_healer());
								counting_c += Integer.parseInt(WaitingTime_String_list.get(l).get_tank());
								counting_d += Integer.parseInt(WaitingTime_String_list.get(l).get_ranged());
								counting_divide += 1;
							}
						}
					}
					float counting_a_divide = setChecking(counting_a, counting_divide);
					float counting_b_divide = setChecking(counting_b, counting_divide);
					float counting_c_divide = setChecking(counting_c, counting_divide);
					float counting_d_divide = setChecking(counting_d, counting_divide);

					WaitingTime_container.set_melee(Float.toString(counting_a_divide));
					WaitingTime_container.set_healer(Float.toString(counting_b_divide));
					WaitingTime_container.set_tank(Float.toString(counting_c_divide));
					WaitingTime_container.set_ranged(Float.toString(counting_d_divide));
					WaitingTime_arraytimes.add(WaitingTime_container);

					// 파일로 전환할 값
					String last_data = target_day_hour + "," + ins_id + "," + ins_name + "," + counting_a_divide + ","
							+ counting_b_divide + "," + counting_c_divide + "," + counting_d_divide;
					bw.write(last_data);
					bw.newLine(); // 줄바꿈
				}

			}
		}
		bw.close();
		setReadable(thisMonthFile);
	}

	private float setChecking(int counting_a, int counting_divide) {
		float return_value = 0;

		if (counting_a > 0) {
			return_value = counting_a / counting_divide;
		} else {
			return_value = counting_a;
		}
		return return_value;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void setReadable(File thisMonthFile) throws IOException {
		thisMonthFile.setReadable(true);
		thisMonthFile.setWritable(true);
		thisMonthFile.setExecutable(true);

		// 리눅스에서 권한 주기
		Set perms = new HashSet();
		perms.add(PosixFilePermission.OWNER_READ);
		perms.add(PosixFilePermission.OWNER_WRITE);
		perms.add(PosixFilePermission.GROUP_READ);
		perms.add(PosixFilePermission.GROUP_WRITE);
		perms.add(PosixFilePermission.OTHERS_READ);
		perms.add(PosixFilePermission.OTHERS_WRITE);

		Files.setPosixFilePermissions(thisMonthFile.toPath(), perms);
	}

	public ArrayList<String> setHoursArrayList() {
		ArrayList<String> time = new ArrayList<String>();

		for (int i = 0; i < 24; i++) {
			String suffix = String.format("%02d", i);
			time.add(suffix);
		}
		return time;
	}

	@Override
	public void save_last7days_data_file(HttpsURLConnection conn, String urldownload, URL url)
			throws IOException, ParseException {
		conn.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
		wr.writeBytes(urldownload);
		wr.flush();
		wr.close();

		int responseCode = conn.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urldownload);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String inputLine;
		ArrayList<String> WaitingTime_String_list = new ArrayList<String>();
		ArrayList<String> WaitingTime_String_list_check = new ArrayList<String>();
		ArrayList<String> WaitingTime_String_list_previous = new ArrayList<String>();
		ArrayList<String> WaitingTime_String_list_insertdata = new ArrayList<String>();

		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat transFormat_month = new SimpleDateFormat("yyyy-MM");
		String this_month = Get_FileName();

		while ((inputLine = in.readLine()) != null) {
			if (!inputLine.contains("date,")) {
				String file_date_tmp = inputLine.split(",")[0];
				Date compare_table = transFormat.parse(file_date_tmp);
				Date compare_today = transFormat_month.parse(this_month);
				int compare = compare_table.compareTo(compare_today);

				if (compare > 0) {
					String temp_data = inputLine;
					WaitingTime_String_list.add(temp_data);
				}
			}
		}
		in.close();

		// 일자, 시간별로 종합
		for (int i = WaitingTime_String_list.size() - 1; i >= 0; i--) {
			WaitingTime_String_list_check.add(WaitingTime_String_list.get(i));
		}

		// 폴더 생성 확인
		File theDir = new File(LINUX_PATH);
		if (!theDir.exists()) {
			try {
				theDir.mkdir();
			} catch (SecurityException se) {
				System.err.println(se);
			}
		}
		String waitingtime_folder = LINUX_PATH;

		setFolder(waitingtime_folder);
		// 파일 저장
		try {
			String this_monthFile_text = waitingtime_folder + "/waitingtime_" + this_month + ".csv";

			// 해당 파일이 있다면 파일을 읽어와서 파일의 마지막 값의 날짜를 불러오기
			File thisMonthFile = new File(this_monthFile_text);

			if (thisMonthFile.exists()) {
				try {
					BufferedReader input = new BufferedReader(new FileReader(thisMonthFile));
					String last = null, line;
					while ((line = input.readLine()) != null) {
						last = line;
						WaitingTime_String_list_previous.add(line);
					}
					input.close();

					if (last.length() > 2) {
						String[] temp_data_split = last.split(",");
						String file_date = temp_data_split[0];
						Date compare_source = transFormat.parse(file_date);

						FileWriter fw = new FileWriter(thisMonthFile); // 절대주소
						BufferedWriter bw = new BufferedWriter(fw);
						// 마지막 값의 날짜보다 과거일 경우 저장 안함
						for (int i = 0; i < WaitingTime_String_list_check.size() - 1; i++) {
							String file_date_tmp = WaitingTime_String_list_check.get(i).split(",")[0];
							Date compare_table = transFormat.parse(file_date_tmp);
							int compare = compare_table.compareTo(compare_source);

							if (compare > 0) {
								String str = WaitingTime_String_list_check.get(i);
								WaitingTime_String_list_previous.add(str);
								WaitingTime_String_list_insertdata.add(str);
							}
						}
						for (int i = 0; i < WaitingTime_String_list_previous.size(); i++) {
							String str = WaitingTime_String_list_previous.get(i);
							bw.write(str);
							bw.newLine(); // 줄바꿈
						}
						bw.close();
					} else {
						FileWriter fw = new FileWriter(thisMonthFile); // 절대주소
						BufferedWriter bw = new BufferedWriter(fw);
						// 마지막 값의 날짜보다 과거일 경우 저장 안함
						for (int i = 0; i < WaitingTime_String_list_check.size() - 1; i++) {
							String str = WaitingTime_String_list_check.get(i);
							WaitingTime_String_list_insertdata.add(str);
							bw.write(str);
							bw.newLine(); // 줄바꿈
						}
						bw.close();
					}

				} catch (SecurityException se) {
					System.err.println(se);
				}
			} else {
				FileWriter fw = new FileWriter(thisMonthFile); // 절대주소
				BufferedWriter bw = new BufferedWriter(fw);
				// 마지막 값의 날짜보다 과거일 경우 저장 안함
				for (int i = 0; i < WaitingTime_String_list_check.size() - 1; i++) {
					String str = WaitingTime_String_list_check.get(i);
					WaitingTime_String_list_insertdata.add(str);
					bw.write(str);
					bw.newLine(); // 줄바꿈
				}
				bw.close();
				setReadable(thisMonthFile);
			}

			System.out.println("file save");
		} catch (IOException e) {
			System.out.println(e); // 에러가 있다면 메시지 출력
		}

		// 넣을 데이터
		ArrayList<WaitingTime> WaitingTime_String_list_insertdata_pasing = new ArrayList<WaitingTime>();

		for (int i = 0; i < WaitingTime_String_list_insertdata.size(); i++) {
			String[] temp_data_parsing = WaitingTime_String_list_insertdata.get(i).split(",");
			WaitingTime WaitingTime = new WaitingTime();
			WaitingTime.set_date(temp_data_parsing[0]);
			// WaitingTime.set_server(temp_data_parsing[1]);
			// WaitingTime.set_world(temp_data_parsing[2]);
			// WaitingTime.set_host(temp_data_parsing[3]);
			// WaitingTime.set_instance(temp_data_parsing[4]);
			WaitingTime.set_key(temp_data_parsing[5]);
			WaitingTime.set_melee(temp_data_parsing[6]);
			WaitingTime.set_healer(temp_data_parsing[7]);
			WaitingTime.set_tank(temp_data_parsing[8]);
			WaitingTime.set_ranged(temp_data_parsing[9]);
			WaitingTime_String_list_insertdata_pasing.add(WaitingTime);
		}
		for (int i = 0; i < WaitingTime_String_list_insertdata_pasing.size(); i++) {
			WaitingTimeDAOService.insertData_one(WaitingTime_String_list_insertdata_pasing.get(i));
		}
	}

	@Override
	public void setFolder(String waitingtime_folder) {
		File theDir = new File(LINUX_PATH);
		if (!theDir.exists()) {
			try {
				theDir.mkdir();
			} catch (SecurityException se) {
				System.err.println(se);
			}
		}
	}

	@Override
	public String Get_FileName() {
		TimeZone tz;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM");

		tz = TimeZone.getTimeZone("Asia/Seoul");
		df.setTimeZone(tz);
		String str2 = df.format(date);

		return str2;
	}

	private String Get_writeFileName() {
		TimeZone tz;
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd_HHmmss");

		tz = TimeZone.getTimeZone("Asia/Seoul");
		df.setTimeZone(tz);
		String str2 = df.format(date);

		return str2;
	}
}
