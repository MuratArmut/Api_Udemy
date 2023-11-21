package calisma;

import TestDatalari.TestDataJsonPlaceholder;
import baseUrl.BaseUrlJsonPlaceholder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C02_TestDataClassiKullanimi extends BaseUrlJsonPlaceholder {

     /*
        https://jsonplaceholder.typicode.com/posts/71 url'ine

        asagidaki body’e sahip bir PUT request yolladigimizda

        donen response’in
            status kodunun 200,
            content type’inin “application/json; charset=utf-8”,
            Connection header degerinin “keep-alive”
            ve response body’sinin asagida verilen ile ayni oldugunu test ediniz

         Request Body
                        {
                        "title":"Murat",
                        "body":"Çanakkale",
                        "userId":10,
                        "id":71
                        }

        Response body (Expected Data) :
                        {
                        "title":"Murat",
                        "body":"Çanakkale",
                        "userId":10,
                        "id":71
                        }
     */

    @Test
    public void test01(){

        // 1. ENDPOINT VE REQUEST BODY OLUŞTUR.
        specJsonPlaceholder.pathParams("pp1","posts","pp2",71);
        JSONObject reqBody = TestDataJsonPlaceholder.JsonBodyOlustur(10,71,"Murat","Çanakkale");

        // 2. EXPECTED DATA OLUŞTUR.
        JSONObject expectedData = TestDataJsonPlaceholder.JsonBodyOlustur(10,71,"Murat","Çanakkale");
        System.out.println("Expected Data : " + expectedData);

        // 3. REQUEST GÖNDER DÖNEN RESPONSE KAYDET
        Response response = given().contentType(ContentType.JSON).spec(specJsonPlaceholder)
                            .when().body(reqBody.toString())
                            .put("/{pp1}/{pp2}");

        JsonPath responseJsonPath = response.jsonPath();
        System.out.println("responseJsonPath : " + responseJsonPath.get().toString());

        // 4. ASSERTION
        assertEquals(TestDataJsonPlaceholder.basariliSorguStatusCode , response.statusCode());
        assertEquals(TestDataJsonPlaceholder.contentType , response.contentType());
        assertEquals(TestDataJsonPlaceholder.headerConnection , response.header("Connection"));

        assertEquals(expectedData.getInt("id") , responseJsonPath.getInt("id"));
        assertEquals(expectedData.getInt("userId") , responseJsonPath.getInt("userId"));
        assertEquals(expectedData.getString("title") , responseJsonPath.getString("title"));
        assertEquals(expectedData.getString("body") , responseJsonPath.getString("body"));


    }

}
