package files;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
			
		public static XmlPath rawToXML(Response res){		 
			String response=res.asString();			
			XmlPath xmlPath=new XmlPath(response);
			return xmlPath;

		}
		
		public static JsonPath rawToJson(Response res){
			String response=res.asString();			
			JsonPath jsonPath=new JsonPath(response);
			return jsonPath;			
		}

	}


