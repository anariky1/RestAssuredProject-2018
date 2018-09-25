import org.testng.annotations.Test;

import files.ReusableMethods;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Basics_Author_extractbulkValues {

	@Test
	public void Test1() {
		
		//BaseUrl
		RestAssured.baseURI="http://216.10.245.166";
		
		//given
		//parameters and resources
		
		Response res=given().
		       param("AuthorName","Rahul").
		   /*    header("","").
		       cookie("","").
		       body()*/
		       
	     when().
	       get("/Library/GetBook.php").
	     then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().    //content type - header
	     //body("array[0].book_name",equalTo("Selenium automation using Java"));
	     body("book_name[0]",equalTo("Selenium automation using Java")).and().
	     body("aisle[5]",equalTo("22712344")).and().
	     header("Server","Apache").
	     extract().response();
		
		JsonPath jsonPath=ReusableMethods.rawToJson(res);
		int size=jsonPath.get("book_name.size()");
		System.out.println(size);
		
		for(int i=0;i<=size-1;i++){
			System.out.println(jsonPath.get("book_name["+i+"]"));
		}
		//System.out.println(jsonPath.get("book_name[23]"));
		
	     

	}

}
