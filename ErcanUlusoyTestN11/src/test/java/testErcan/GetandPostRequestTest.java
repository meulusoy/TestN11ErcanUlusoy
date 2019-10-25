package testErcan;

import org.testng.Assert;
import org.testng.annotations.Test; // I used test annotations

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Dictionary;

public class GetandPostRequestTest {

	
	@Test
	public void getRequestTest1() { // first get request test checks if the status code is 200 (SUCCESSFUL)
		
		given().
			get("https://archive.org/services/img/theworksofplato01platiala").
		then().
			assertThat().
		statusCode(200);
	
	}
	
	@Test
	public void getRequestTest2() { // parse a value from a response with JsonPath and then use it in another get request
		
		String path = "http://ergast.com/api/f1/2009/circuits.json";
		String[] tokens;
		
		
		Response response = given().contentType(ContentType.JSON).log().all().get(path);
		
		JsonPath pathToDatas = response.jsonPath();
		
		String circuitId = pathToDatas.getString("MRData.CircuitTable.Circuits.circuitId[1]"); // Gets second circuit id from json list
		
		if(circuitId == null) {

			System.out.println("///////////// The information is null ! //////////////");
			return;
			
		}
		tokens = path.split(".json"); // parsing the address and getting path without .json word
		
		tokens[0] += "/" + circuitId + ".json"; // adding the circuitId that we got with the response before and adding .json to the adress
		
		given().
			get(tokens[0]).
		then().
			statusCode(200); // check if the new request is valid
		
		System.out.println(":::" + tokens[0]);
		
	}
	
	@Test
	public void getRequestTest3() {        
		    given().
		    	when().
		    get("https://api.github.com/users/hadley/orgs").
		    	then().
		    body("login[0]",equalTo("ggobi")); // checks if the first login is ggobi
	}
	
	@Test
	public void getRequestTest4() {
		given().
			when().
		get("http://api.plos.org/search?q=title:DNA").
			then().
		statusCode(200);
	}
	
	@Test
	public void getRequestTest5() { // parameterless get request
		given().when().get("http://ip.jsontest.com/").then().assertThat().statusCode(200);
	}
	
	@Test
	public void postRequestTest1() {
		Response response = given().
				contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body("{\"brand\": \"samsung\",\"device\": \"i9305\","
						+ "\"token\": \"73fe4dc59774c39af20c755701d30955dea697e6f8371947\"}")
				.when()
				.post( "https://fonoapi.freshpixl.com/v1/getdevice");
		
		System.out.println("POST Response\n" + response.asString());
		
		
		Assert.assertEquals(response.getStatusCode(), 200);
		}

}
