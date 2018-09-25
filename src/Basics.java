import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import static io.restassured.RestAssured.given;



public class Basics {

	public static void main(String[] args) {
		
		//BaseUrl
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//given
		//parameters nd resources
		
		given().
		       param("location","-33.8670522,151.1957362").
		       param("radius","500").
		       param("key","AIzaSyDWlNEK8GNddgeeUrJ6ZzmqTmZGBmQgDwM").
		   /*    header("","").
		       cookie("","").
		       body()*/
		       
	     when().
	       get("/maps/api/place/nearbysearch/json").
	     then().assertThat().statusCode(200).and().contentType(ContentType.XML);
	     

	}

}
