package org.openstack.storyboard;

import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class MyApp {

	public static void main(String[] args) throws IOException {
		StoryboardClient client = new StoryboardClient("https://storyboard-dev.openstack.org",
				"aaaaaaaa-bbbb-cccc-dddd-eeeeeeeeeeee");
		getExample(client);
		//putExample(client);
		//postExample(client);
	}
	
	public static void getExample(StoryboardClient client) {
		// get task
		int id = 22;
		String requestUrl = client.getUrl() + StoryboardClient.TASKS_ENDPOINT + "/" + id;
		try {
			String response = client.get(requestUrl);
		    JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
		    String notes = jobj.get("link").getAsString();
			System.out.println(notes);
		} catch (Exception e) {
			System.out.println("Failed to GET data");
		}
	}

	public static void putExample(StoryboardClient client) {
		// set task notes
		int id = 22;
		String requestUrl = client.getUrl() + StoryboardClient.TASKS_ENDPOINT + "/" + id;
		try {
			String body = "{\"task_id\":22,\"link\":\"green dogs\"}";
			String response = client.put(requestUrl, body);
			System.out.println(response);
		} catch (Exception e) {
			System.out.println("Failed to PUT data");
		}
	}
	
	public static void postExample(StoryboardClient client) {
		// set task notes
		int id = 7;
		String requestUrl = client.getUrl() + StoryboardClient.STORIES_ENDPOINT + "/" + id + "/comments";
		try {
			String body = "{\"story_id\":7,\"content\":\"testing comment\"}";
			String response = client.post(requestUrl, body);
			System.out.println(response);
		} catch (Exception e) {
			System.out.println("Failed to PUT data");
		}
	}
}
