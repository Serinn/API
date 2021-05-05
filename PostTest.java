package TestClasses;
import static org.junit.Assert.*;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
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

public class PostTest {
	String url = URLs.ReqResAPI;
	@Test
	public void TestPostData() throws Exception
{
	HttpURLConnection connection= RestClientHandler.connectServer(URLs.BOOKING, HTTPMethod.POST , HTTPRequestsContentTypes.JSON);
	String json=JSONUtils.readJSONObjectFromFile(FilesPaths.CreatBookingJSONFile);
	RestClientHandler.sendPost(connection, json, HTTPRequestsContentTypes.JSON);
	String response=RestClientHandler.readResponse(connection);
	System.out.println(response);
      JSONObject obj= (JSONObject) JSONUtils.convertStringToJSON(response);
	   System.out.println(obj.get("bookingid"));
	}
	@Test
	public void testAouthorizedUser() throws Exception {
		String userID="211";
		HttpURLConnection connection = RestClientHandler.connectServer(url+userID, HTTPMethod.POST,
				HTTPRequestsContentTypes.JSON);
		String resquestJSONObject = JSONUtils.readJSONObjectFromFile(FilesPaths.ExistingUser);
		
		RestClientHandler.sendPost(connection, resquestJSONObject, HTTPRequestsContentTypes.JSON);
		assertTrue( connection.getResponseCode() != 201);	
		String response = RestClientHandler.readResponse(connection);
		assertFalse(response.equals("")); 
	}
@Test
public void testUnaouthorizedUser() throws Exception
{
	String url = URLs.ReqResAPI;;
	HttpURLConnection connection = RestClientHandler.connectServer(url, HTTPMethod.DELETE,
			HTTPRequestsContentTypes.JSON);
	assertFalse(connection.getResponseCode()==401);

}
}