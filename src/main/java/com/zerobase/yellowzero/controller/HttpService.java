package com.zerobase.yellowzero.controller;

import java.util.*;
import com.google.gson.*;
import com.zerobase.yellowzero.db.WifiData;
import okhttp3.*;

public class HttpService {
	private final String postUrl = "http://openapi.seoul.go.kr:8088/76694d657879656c3736486570706f/json/TbPublicWifiInfo/";
	private final OkHttpClient client = new OkHttpClient();
	
	//와이파이  API 정보 불러오기
	public List<WifiData> loadAPI() throws Exception {
		int totalCount = getTotalCount();
		List<WifiData> list = new ArrayList<>();
		for (int i = 1; i <= totalCount / 1000 + 1; i++) {
			String apiUrl = postUrl + (i * 1000 - 999) + "/" + (i * 1000);
			Request.Builder builder = new Request.Builder().url(apiUrl).get();
			Request request = builder.build();
			Response response = client.newCall(request).execute();
			JsonArray rowArray = null;
			
			//통신 성공시 Json 파일 파싱해오기
			if (response.isSuccessful()) {
				rowArray = getInfoArray(response.body().string());
			}
			
			for (int j = 0; j < rowArray.size(); j++) {
				Gson gson = new Gson();
				WifiData data = gson.fromJson(rowArray.get(i), WifiData.class);
				list.add(data);
			}
		}
		
		return list;
	}
	
	//Json 파일에서 와이파이 정보들 파싱
	public JsonArray getInfoArray(String response) {
		JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
		JsonObject result = jsonObject.getAsJsonObject("TbPublicWifiInfo");
		return result.get("row").getAsJsonArray();
	}
	
	//불러올 와이파이 전체 정보 양 구하기
	public int getTotalCount() throws Exception {
		String apiUrl = postUrl + "1/1";
		Request.Builder builder = new Request.Builder().url(apiUrl).get();
		Request request = builder.build();
		Response response = client.newCall(request).execute();
		
		int total = 0;
		//통신 성공시 Json 파일 파싱 해오기
		if (response.isSuccessful()) {
			String responseBody = response.body().string();
			total = getTotal(responseBody);
		}
		
		return total;
	}
	
	//Json 파일에서 전체 정보 양 파싱
	public int getTotal(String response) {
		JsonObject jsonObject = JsonParser.parseString(response).getAsJsonObject();
		JsonObject result = jsonObject.getAsJsonObject("TbPublicWifiInfo");
		return result.get("list_total_count").getAsInt();
	}	
}
