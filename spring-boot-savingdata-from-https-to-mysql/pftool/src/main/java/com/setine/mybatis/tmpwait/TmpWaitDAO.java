package com.setine.mybatis.tmpwait;

import java.util.ArrayList;

public interface TmpWaitDAO {
	ArrayList<TmpWait> getList(String this_year, String this_month);
}
