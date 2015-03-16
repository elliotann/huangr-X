package com.easysoft.component.weixin.utils;

import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;

public class MyX509TrustManager implements TrustManager {
	public void checkClientTrusted(X509Certificate[] chain, String authType) {
	}

	public void checkServerTrusted(X509Certificate[] chain, String authType) {
	}

	public X509Certificate[] getAcceptedIssuers() {
		return null;
	}
}
