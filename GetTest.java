package TestClasses;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import Links.FilesPaths;
import Links.URLs;
import Utils.JSONUtils;
import enums.HTTPMethod;
import enums.HTTPRequestsContentTypes;
import requestHandling.RestClientHandler;

public class GetTest {
	String url = URLs.ReqResAPI;
	@Test
	public void testExistUserGet() throws IOException {
		String userID = "100";
		HttpURLConnection connection = RestClientHandler.connectServer(url+ userID, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		connection.addRequestProperty("",url);
		System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
		assertTrue("Cannot connect to webservice", connection.getResponseCode() == 200);
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
		assertTrue("There is no data!", !response.equals(""));
	}
	
	@Test
	public void testUserUpdate() throws Exception {
		String url = URLs.ReqResAPI;
		System.out.println(url);
		HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.GET,
				HTTPRequestsContentTypes.JSON);
		
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.UpdateUserJSONFile);
		System.out.println(resquestJSONObject);
		RestClientHandler.sendPut(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);

		System.out.println(connection.getResponseCode());
		String response = RestClientHandler.readResponse(connection);
		System.out.println(response);
	}

@Test (expected = Exception.class)
public void testNotExistUserGet() throws IOException {
	String userID = "110";
	HttpURLConnection connection = RestClientHandler.connectServer(url + userID, HTTPMethod.GET,
			HTTPRequestsContentTypes.JSON);
	connection.addRequestProperty("",url);
	System.out.println("connection.getResponseCode() : " + connection.getResponseCode());
	assertTrue("ERROR 404; Not Found", connection.getResponseCode() == 404);
	String response = RestClientHandler.readResponse(connection);
	assertTrue("There is no such user!", connection.getResponseCode() != 204);	
}
}