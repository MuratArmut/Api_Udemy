package calisma;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;

public class C01_SoftAssert {
      /*
        http://dummy.restapiexample.com/api/v1/employee/3 url’ine
        bir GET request gonderdigimizde
        donen response’un asagidaki gibi oldugunu test edin.
            Response Body
            {
                "status":"success",
                "data":{
                        "id":3,
                        "employee_name":"Ashton Cox",
                        "employee_salary":86000,
                        "employee_age":66,
                        "profile_image":""
                        },
                "message":"Successfully! Record has been fetched."
            }
     */

    @Test
    public void test01() {

        // 1) ENDPOINT VE REQUESTBODY OLUŞTUR.

        String url = "http://dummy.restapiexample.com/api/v1/employee/3";
        //GET metodu olduğu için requestBody oluşturmaya gerek yok.


        // 2) EXPECTED DATA OLUŞTUR.
        // "aşağıdaki gibi" ifadesini kullanıp tüm responsu kastettiğinden expectedData oluşturmalıyız.

        JSONObject dataJson = new JSONObject(); // önce inner obje oluşturulur.
            dataJson.put("id",3);
            dataJson.put("employee_name","Ashton Cox");
            dataJson.put("employee_salary",86000);
            dataJson.put("employee_age",66);
            dataJson.put("profile_image","");

        JSONObject expectedDataJson = new JSONObject();
            expectedDataJson.put("status","success");
            expectedDataJson.put("data",dataJson);
            expectedDataJson.put("message","Successfully! Record has been fetched.");


        // 3) REQUEST GÖNDER VE GELEN RESPONSE KAYDET

        Response response = given().when().get(url);
            // System.out.println(response); response objesi böyle yazdırılamaz
            // response.prettyPrint(); // böyle yazırılır.Sadece bodyi yazdırır
            // response.prettyPeek(); //response özelliklerini de yazdırır


        // 4) ASSERTION YAP.

        JsonPath responseJsonPath = response.jsonPath();
        // response jsonpath objesine çevirdik böylece içindeki objelere ulaşıp bilgileri almak kolaylaştı.

        SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals
                    (responseJsonPath.get("status"),
                    expectedDataJson.get("status"));
            softAssert.assertEquals
                    (responseJsonPath.get("message"),
                    expectedDataJson.get("message"));
            softAssert.assertEquals
                    (responseJsonPath.get("data.id"),
                    expectedDataJson.getJSONObject("data").get("id"));
            softAssert.assertEquals
                    (responseJsonPath.get("data.employee_name"),
                    expectedDataJson.getJSONObject("data").get("employee_name"));
            softAssert.assertEquals
                    (responseJsonPath.get("data.employee_salary"),
                    expectedDataJson.getJSONObject("data").get("employee_salary"));
            softAssert.assertEquals
                    (responseJsonPath.get("data.employee_age"),
                     expectedDataJson.getJSONObject("data").get("employee_age"));
            softAssert.assertEquals
                    (responseJsonPath.get("data.profile_image"),
                    expectedDataJson.getJSONObject("data").get("profile_image"));

        softAssert.assertAll();

    }

}
