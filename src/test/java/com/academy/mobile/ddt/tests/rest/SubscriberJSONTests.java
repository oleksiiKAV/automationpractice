package com.academy.mobile.ddt.tests.rest;

import com.academy.mobile.ddt.tests.framework.model.Gender;
import com.academy.mobile.ddt.tests.framework.model.Subscriber;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.academy.automation.framework.util.MatcherAssertExt.assertThat;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SubscriberJSONTests {
    private static final Logger LOG = LogManager.getLogger(SubscriberJSONTests.class);

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost/rest/json";
        RestAssured.port = 8081;
//        RestAssured.proxy = host("127.0.0.1").withPort(8888);

        config = config()
                .logConfig(new LogConfig()
                        .defaultStream(IoBuilder.forLogger(LOG).buildPrintStream()));
    }

    @Test(dataProvider = "subscriberFirstProvider")
    public void testGet(Subscriber subscriberExpected) {
        LOG.info("API-> test subscribers get by id");
        if (!isPresent(subscriberExpected))
            add(subscriberExpected);

        RequestSpecification request = RestAssured.given();
        Response response = request.get("/subscribers/1");
        ResponseBody body = response.body();
        JsonPath jsonPath = body.jsonPath();
        long id = jsonPath.getLong("id");
        String  firstName = jsonPath.getString("firstName");
        String  lastName = jsonPath.getString("lastName");
        int  age = jsonPath.getInt("age");
        Gender gender = Gender.valueOf(jsonPath.getString("gender").charAt(0));
        int code = response.getStatusCode();

        Assert.assertEquals(code, 200);
        Assert.assertEquals(id, subscriberExpected.getId());

        LOG.info("First name: {}, Last name: {}, age: {}, gender: {}",
                firstName, lastName, age, gender);

        Subscriber subscriberActual = jsonPath.getObject(".", Subscriber.class);
        LOG.info(subscriberActual);
    }

    @Test(dataProvider = "subscriberFirstProvider")
    public void testGetDsl(Subscriber subscriberExpected) {
        if (!isPresent(subscriberExpected))
            add(subscriberExpected);

        LOG.info("API-> test subscribers get by id");
        given().log().all()
                .when()
                .get("/subscribers/{id}", subscriberExpected.getId())
                .then()
                .assertThat()
                .body("id", equalTo((int)subscriberExpected.getId()))
                .and()
                .statusCode(200);
    }

    @Test(dataProvider = "subscriberFirstProvider")
    public void testGetAll(Subscriber subscriber) {
        LOG.info("API-> test subscribers get All");
        if (!isPresent(subscriber))
            add(subscriber);

        given().log().all()
                .when()
                .get("/subscribers")
                .then()
                .assertThat()
                .body("size()", greaterThanOrEqualTo(1))
                .and()
                .body("[0].id", equalTo(1))
                .and()
                .statusCode(200);
    }

    // TODO
    @Test(dataProvider="subscriberProvider")
    public void testPost(Subscriber subscriber) {
        if (isPresent(subscriber))
            remove(subscriber);

        JSONObject json = new JSONObject();
        json.put("firstName", subscriber.getFirstName()); // Cast
        json.put("lastName", subscriber.getLastName());
        json.put("age", subscriber.getAge());
        json.put("gender", subscriber.getGender().toString());

        given().log().all()
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .post("/subscribers")
                .then()
                .assertThat()
                .header("Location", containsString(String.format("http://localhost:%d/rest/json/subscribers", port)))
                .statusCode(201);
    }

    // TODO
    @Test(dataProvider="subscriberProvider")
    public void testUpdate(Subscriber subscriber) {
        if (!isPresent(subscriber))
            add(subscriber);

        JSONObject json = new JSONObject();
        json.put("id", subscriber.getId()); // Cast
        json.put("firstName", "Vasya"); // Cast
        json.put("lastName", "Pupkin");
        json.put("age", "25");
        json.put("gender", "m");

        given().log().all()
                .header("Content-Type", "application/json")
                .body(json.toJSONString())
                .put("/subscribers/{id}", subscriber.getId())
                .then()
                .assertThat()
                .body("id", equalTo((int)subscriber.getId()))
                .and()
                .body("lastName", equalTo("Pupkin"))
                .statusCode(200);
    }

    @Test(dataProvider="subscriberProvider")
    public void testDelete(Subscriber subscriber) {
        LOG.info("API-> test delete");

        // add if not exists
        if (!isPresent(subscriber))
            add(subscriber);

        // read before
        Set<Subscriber> before = new HashSet<>(
                getAll()
        );

        // delete
        given().log().all()
                .delete("/subscribers/{id}", subscriber.getId())
                .then().assertThat()
                .statusCode(200);

        // read after
        Set<Subscriber> after = new HashSet<>(
                getAll()
        );

        // assert lists
        assertThat(after.size(), equalTo(before.size()-1));
        after.add(subscriber);
        LOG.info("before: {}", before);
        LOG.info("after: {}", after);
        assertThat(after, equalTo(before));
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

    private void add(Subscriber subscriber) {
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

    private void remove(Subscriber subscriber) {
        given().log().all()
                .delete("/subscribers/{id}", subscriber.getId());
    }

    private List<Subscriber> getAll() {
        return
                given().log().all()
                        .contentType("application/json; charset=UTF-8")
                        .when()
                        .get("/subscribers")
                        .then()
                        .extract()
                        .body()
                        .jsonPath()
                        .getList(".", Subscriber.class);
    }

    @DataProvider
    private Object[] subscriberProvider() {
        return new Object[] {
                Subscriber.newSubscriber()
                        .id(28L)
                        .firstName("testName")
                        .lastName("lastName")
                        .age(25)
                        .gender(Gender.MALE)
                        .build()
        };
    }

    @DataProvider Object[] subscriberFirstProvider() {
        return new Object[] {
                Subscriber.newSubscriber()
                        .id(1L)
                        .firstName("Peter")
                        .lastName("Pechking")
                        .age(25)
                        .gender(Gender.MALE)
                        .build()
        };
    }
}
