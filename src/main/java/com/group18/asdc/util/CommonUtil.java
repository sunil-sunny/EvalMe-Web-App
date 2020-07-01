package com.group18.asdc.util;

import java.util.HashMap;

public class CommonUtil {

	private static CommonUtil commonUtilObj = null;
	public static final HashMap<String, String> roleVsLandingPage = new HashMap<String, String>();

	public static enum userRoles {
		ADMIN, GUEST
	};

	static {
		roleVsLandingPage.put(userRoles.ADMIN.name(), "/adminhome");
		roleVsLandingPage.put(userRoles.GUEST.name(), "/coursepage");
	}

	private CommonUtil() {
	}

	public static CommonUtil getInstance() {
		if (null == commonUtilObj) {
			commonUtilObj = new CommonUtil();
		}
		return commonUtilObj;
	}
}