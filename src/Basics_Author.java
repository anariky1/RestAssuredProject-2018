import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class Basics_Author {

	@Test
	public void Test1() {
		
		//BaseUrl
		RestAssured.baseURI="http://216.10.245.166";
		
		//given
		//parameters and resources
		
		given().
		       param("AuthorName","Rahul").log().all().
		   /*    header("","").
		       cookie("","").
		       body()*/
		       
	     when().
	       get("/Library/GetBook.php").
	     then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().    //content type - header
	     //body("array[0].book_name",equalTo("Selenium automation using Java"));
	     body("book_name[0]",equalTo("Selenium automation using Java")).and().
	     body("aisle[5]",equalTo("22712344")).and().
	     header("Server","Apache");
	     

	}

}
