import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import files.payLoad;
import files.resources;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

	
public class GrabResponse {
	public Properties prop;
	@BeforeTest()
	public void getData() throws IOException{
		prop= new Properties();
		FileInputStream fis=new FileInputStream("C:\\Users\\Hp\\workspace\\Selenium Project\\Rest-Assured2018\\src\\files\\env.properties");
		prop.load(fis);
		prop.getProperty("HOST");
	}
	
	@Test
	public void AddandDeletePlace(){
		
	   payLoad.getPostData();
		
		//BaseUrl
				RestAssured.baseURI=prop.getProperty("HOST");
	Response res=given().
				
				queryParam("key", prop.getProperty("KEY")).
				
				// step 1 - there is a "" inside "" , so you need to put forward slash before the each inside "".
				//step 2 - there are multiple lines , you need to concatinate them , so u need to put " at the begining of the line 
				//and " at the end of the line followed by + symbol 
				body(payLoad.getPostData()).

		when().
		post(resources.placePostData()).
		then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
		body("status",equalTo("OK")).
		
		//grab the response
		extract().response();
				
				//it is extracted with raw format
				//convert that to string
				
				String responseString=res.asString();
				System.out.println(responseString);
				
	  // grab the place id from string
				
		//convert the string to json
				
				JsonPath js = new JsonPath(responseString);
				
				//grab the place id and place in a string
				String place_id=js.get("place_id");
				System.out.println(place_id);
				
				
				//place the place id in the delete req
				
				given().
				queryParam("key", prop.getProperty("KEY")).
				body("{"+
               "\"place_id\":\""+place_id+"\""+  //place + symbo before and after variable and include " and left and right side of the varaiable before + and after +
                "}").
				when().
				post("/maps/api/place/delete/json").
				then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
				body("status",equalTo("OK"));
				

				
	

				
			}
		
}


