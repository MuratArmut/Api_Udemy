package TestDatalari;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class TestDataHerokuapp {

    /*
    {
                        "firstname" : "Murat",
                        "lastname" : “Armut",
                        "totalprice" : 500,
                        "depositpaid" : false,
                        "bookingdates" : {
                                "checkin" : "2023-11-21",
                                "checkout" : "2023-11-24"
                                },
                        "additionalneeds" : "wi-fi"
                        }
     */

    public static JSONObject jsonRequestBodyOlustur(){

        JSONObject requestBody= new JSONObject();
        JSONObject bookingdatesBody= new JSONObject();

        bookingdatesBody.put("checkin","2023-11-21");
        bookingdatesBody.put("checkout","2023-11-24");

        requestBody.put("firstname","Murat");
        requestBody.put("lastname","Armut");
        requestBody.put("totalprice",500);
        requestBody.put("depositpaid",false);
        requestBody.put("bookingdates",bookingdatesBody);
        requestBody.put("additionalneeds","wi-fi");

        return requestBody;

    }

    /*
                     {
                         "bookingid":24,
                         "booking":{
                            "firstname":"Murat",
                            "lastname":"Armut",
                            "totalprice":500,
                            "depositpaid":false,
                            "bookingdates":{
                                    "checkin":"2023-11-21",
                                    "checkout":"2023-11-24"
                                            },
                            "additionalneeds":"wi-fi"
                                    }
                          }
     */

    public static JSONObject jsonResponseBodyOlustur(){

        JSONObject responseBody= new JSONObject();
        JSONObject bookingBody= jsonRequestBodyOlustur();

        responseBody.put("bookingid",24);
        responseBody.put("booking",bookingBody);

        return responseBody;
    }

    /*
     {
                        "firstname" : "Murat",
                        "lastname" : “Armut",
                        "totalprice" : 500,
                        "depositpaid" : false,
                        "bookingdates" : {
                                "checkin" : "2023-11-21",
                                "checkout" : "2023-11-24"
                                },
                        "additionalneeds" : "wi-fi"
                        }
     */

    public static Map<String,Object> requestBodyMapOlustur(){

        Map<String,Object> requestBodyMap= new HashMap<>();
        requestBodyMap.put("firstname","Murat");
        requestBodyMap.put("lastname","Armut");
        requestBodyMap.put("totalprice",500.0);
        requestBodyMap.put("depositpaid",false);
        requestBodyMap.put("bookingdates",bookingdatesMapOlustur());
        requestBodyMap.put("additionalneeds","wi-fi");

        return requestBodyMap;
    }

    public static Map<String,String> bookingdatesMapOlustur(){

        Map<String,String > bookingdatesMap= new HashMap<>();
        bookingdatesMap.put("checkin","2023-11-21");
        bookingdatesMap.put("checkout","2023-11-24");

        return bookingdatesMap;
    }

    /*
    Response Body // expected data
                           {
                         "bookingid":24,
                         "booking":{
                            "firstname":"Murat",
                            "lastname":"Armut",
                            "totalprice":500,
                            "depositpaid":false,
                            "bookingdates":{
                                    "checkin":"2023-11-21",
                                    "checkout":"2023-11-24"
                                            },
                            "additionalneeds":"wi-fi"

                          }
     */

    public static Map<String,Object> responseBodyMapOlustur(){

        Map<String,Object> responseBodyMap= new HashMap<>();

        responseBodyMap.put("bookingid",24);
        responseBodyMap.put("booking",requestBodyMapOlustur());

        return responseBodyMap;
    }
}
