package LoginAPI;

import POJO.PojoClass;
import api.LoginDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import org.junit.Test;
import static api.LoginDetails.BASE_URI;
import static api.LoginDetails.BASE_PATH;



public class LoginAPIClass extends LoginDetails {

    @Test
    public void CreateJsonobj() throws JsonProcessingException {

        POJO.PojoClass pc = new PojoClass();
        pc.setlogin("poojitha.bonthu@atc.xyz");
        pc.setpassword("Ammananna@1316");

        ObjectMapper Classobj = new ObjectMapper();
        String Jsonobj = Classobj.writerWithDefaultPrettyPrinter().writeValueAsString(pc);
        System.out.println(Jsonobj);
        RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body(Jsonobj)
                .when().post(BASE_PATH)
                .then().statusCode(200)
                .log().all();

//        System.out.println(" svurd ");

        Response response = requestSpecification.post(BASE_PATH);
        int statusCode = response.getStatusCode();
        System.out.println("Response Status Code: " + statusCode);
        Assert.assertEquals(200, statusCode);

//        Assert.assertEquals(200,response.getStatusCode());

        PojoClass pc1= Classobj.readValue(Jsonobj, PojoClass.class);
    }
}