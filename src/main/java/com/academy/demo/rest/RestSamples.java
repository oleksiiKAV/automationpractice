package com.academy.demo.rest;


import io.restassured.RestAssured;
import org.json.simple.JSONObject;

public class RestSamples {
    public static void main(String[] args) {
        insertSubscriberByRest();
        deleteSubscriberByRest();
    }

    private static void deleteSubscriberByRest() {

    }

    private static void insertSubscriberByRest() {
        RestAssured.baseURI = "http://localhost/rest/json";
        RestAssured.port = 8081;

//        config = config()
//                .logConfig(new LogConfig()
//                        .defaultStream(System.out::printf);

        JSONObject json = new JSONObject();
        json.put("id", 153);
        json.put("firstName", "Santa"); // Cast
        json.put("lastName", "Barbara");
        json.put("age", 25);
        json.put("gender", "m");
        System.out.println("JSON: " + json.toJSONString());

        RestAssured.given().log().all()
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .post("/subscribers");
    }
}
