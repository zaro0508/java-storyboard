package org.openstack.storyboard;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class StoryboardClient {

	public static final String STORIES_ENDPOINT = "/api/v1/stories";
	public static final String SYS_INFO_ENDPOINT = "/api/v1/systeminfo";
	public static final String TASKS_ENDPOINT = "/api/v1/tasks";

	OkHttpClient httpClient = new OkHttpClient();
	public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

	private String url;
	private String token;

	public StoryboardClient(String url, String token) {
		this.setUrl(url);
		this.setToken(token);
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	String get(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();

		try (Response response = httpClient.newCall(request).execute()) {
			return response.body().string();
		}
	}

	String put(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).addHeader("Authorization", "Bearer " + token).put(body)
				.build();
		try (Response response = httpClient.newCall(request).execute()) {
			return response.body().string();
		}
	}

	String post(String url, String json) throws IOException {
		RequestBody body = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url).addHeader("Authorization", "Bearer " + token).post(body)
				.build();
		try (Response response = httpClient.newCall(request).execute()) {
			return response.body().string();
		}
	}

	Map<String, String> jsonToMap(String json) {
		Type stringStringMap = new TypeToken<Map<String, String>>() {
		}.getType();
		Map<String, String> map = new Gson().fromJson(json, stringStringMap);
		return map;
	}
}
