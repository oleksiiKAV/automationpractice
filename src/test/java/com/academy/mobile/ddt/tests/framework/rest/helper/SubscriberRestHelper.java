package com.academy.mobile.ddt.tests.framework.rest.helper;

import com.academy.mobile.ddt.tests.framework.model.Entities;
import com.academy.mobile.ddt.tests.framework.model.Subscriber;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.json.simple.JSONObject;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class SubscriberRestHelper {
    private static final Logger LOG = LogManager.getLogger(SubscriberRestHelper.class);
    private boolean restMode = false;

    public void setRestMode(boolean on) {
        restMode = on;
    }

    public Entities<Subscriber> all() {
        Entities<Subscriber> subscribers = new Entities<>();
                given().log().all()
                        .contentType("application/json; charset=UTF-8")
                        .when()
                        .get("/subscribers")
                        .then()
                        .extract()
                        .body()
                        .jsonPath()
                        .getList(".", Subscriber.class)
                .forEach(subscribers::add);

        return subscribers;
    }

    public void modify(Subscriber before, Subscriber after) {
        JSONObject json = new JSONObject();
        json.put("id", after.getId()); // Cast
        json.put("firstName", after.getFirstName()); // Cast
        json.put("lastName", after.getLastName());
        json.put("age", after.getAge());
        json.put("gender", after.getGender().toString());

        given().log().all()
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .put("/subscribers/{id}", before.getId());
    }

    private boolean isPresent(Subscriber subscriber) {
        try {
            return
                    given().log().all()
                            .header("Content-Type", "application/json")
                            .when()
                            .get("/subscribers/{id}", subscriber.getId())
                            .then()
                            .assertThat()
                            .statusCode(200)
                            .and()
                            .extract()
                            .body()
                            .jsonPath()
                            .getObject(".", Subscriber.class)
                            .equals(subscriber);
        } catch (AssertionError err) {
            return false;
        }
    }

    private void create(Subscriber subscriber) {
        JSONObject json = new JSONObject();
        json.put("id", subscriber.getId());
        json.put("firstName", subscriber.getFirstName()); // Cast
        json.put("lastName", subscriber.getLastName());
        json.put("age", subscriber.getAge());
        json.put("gender", subscriber.getGender().toString());

        given().log().all()
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .post("/subscribers");
    }

    public void createIfNotPresent(Subscriber subscriber) {
        if (!isPresent(subscriber)) {
            remove(subscriber);
            create(subscriber);
        }
    }

    public void deleteIfPresent(Subscriber subscriber) {
        if (isPresent(subscriber)) {
            remove(subscriber);
        }
    }

    private void remove(Subscriber subscriber) {
        given().log().all()
                .delete("/subscribers/{id}", subscriber.getId());
    }
}
