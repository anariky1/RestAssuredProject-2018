import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.Test;


public class XmlPostRequest {
	
	@Test
	public void postData() throws IOException{
		
		String postdata=GenerateStringFromResource("E:\\postdata.xml");
		//BaseUrl
		RestAssured.baseURI="http://216.10.245.166";
		Response res=given().
		
		queryParam("key", "RESTASSURE").
		
		// step 1 - there is a "" inside "" , so you need to put forward slash before the each inside "".
		//step 2 - there are multiple lines , you need to concatinate them , so u need to put " at the begining of the line 
		//and " at the end of the line followed by + symbol 
		body(postdata).log().all().

		when().
		post("/maps/api/place/add/xml").
		then().assertThat().statusCode(200).and().contentType(ContentType.XML).log().status().
		extract().response();
		
		/*String response=res.asString();
		System.out.println(response);
		
		XmlPath xmlPath=new XmlPath(response);*/
		
		XmlPath xmlPath=ReusableMethods.rawToXML(res);
		
		System.out.println(xmlPath.get("status"));
		

		
		//CREATE A PLACE AND DELETE IT

		
	}
	
	public static String GenerateStringFromResource(String path) throws IOException{
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}
