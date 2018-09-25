import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


public class PostRequest {
	
	@Test
	public void postData(){
		//BaseUrl
		RestAssured.baseURI="http://216.10.245.166";
		given().
		
		queryParam("key", "qaclick123").
		
		// step 1 - there is a "" inside "" , so you need to put forward slash before the each inside "".
		//step 2 - there are multiple lines , you need to concatinate them , so u need to put " at the begining of the line 
		//and " at the end of the line followed by + symbol 
		body("{"+
    "\"location\":{"+
        "\"lat\" : -38.383494,"+
        "\"lng\" : 33.427362"+
    "},"+
    "\"accuracy\":50,"+
    "\"name\":\"Frontline house\","+
    "\"phone_number\":\"(+91) 983 893 3937\","+
    "\"address\" : \"29, side layout, cohen 09\","+
    "\"types\": [\"shoe park\",\"shop\"],"+
    "\"website\" : \"http://google.com\","+
    "\"language\" : \"French-IN\""+
"}").

when().
post("/maps/api/place/add/json").
then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
body("status",equalTo("OK"));
		
		//CREATE A PLACE AND DELETE IT

		
	}

}
