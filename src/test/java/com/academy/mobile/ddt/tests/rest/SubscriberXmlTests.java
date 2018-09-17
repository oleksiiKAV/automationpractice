package com.academy.mobile.ddt.tests.rest;

import com.academy.mobile.ddt.tests.framework.model.Gender;
import com.academy.mobile.ddt.tests.framework.model.GenderXStreamConverter;
import com.academy.mobile.ddt.tests.framework.model.Subscriber;
import com.thoughtworks.xstream.XStream;
import io.restassured.RestAssured;
import io.restassured.config.LogConfig;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SubscriberXmlTests {
    private static final Logger LOG = LogManager.getLogger(SubscriberXmlTests.class);

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://localhost/rest/xml";
        RestAssured.port = 8081;

        config = config()
                .logConfig(new LogConfig()
                        .defaultStream(IoBuilder.forLogger(LOG).buildPrintStream()));
    }

    @Test
    public void testGet() {
        LOG.info("API-> test subscribers get by id");
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/subscribers/1");
        ResponseBody body = response.body();
        XmlPath xmlPath = body.xmlPath();
        int  id = xmlPath.getInt("subscriber.id");
        String  firstName = xmlPath.getString("subscriber.firstName");
        String  lastName = xmlPath.getString("subscriber.lastName");
        int  age = xmlPath.getInt("subscriber.age");
        Gender gender = Gender.valueOf(xmlPath.getString("subscriber.gender").charAt(0));
        int code = response.statusCode();

        Assert.assertEquals(code, 200);
        Assert.assertEquals(id, 1);

        LOG.info("First name: {}, Last name: {}, age: {}, gender: {}",
                firstName, lastName, age, gender);

        Subscriber subscriber = xmlPath.getObject(".", Subscriber.class);
        LOG.info(subscriber);
    }

    @Test
    public void testGetDsl() {
        LOG.info("API-> test subscribers get by id");
        given().log().all()
                .contentType("application/json; charset=UTF-8")
                .when()
                    .get("/subscribers/{id}", 1)
                .then()
                    .assertThat()
                        .body("subscriber.id", equalTo("1"))
//                        .body("subscriber.firstName", equalTo("Иван"))
//                        .body("subscriber.lastName", equalTo("Петров"))
//                        .body("subscriber.age", equalTo("33"))
//                        .body("subscriber.gender", equalTo("m"))
                    .and()
                        .statusCode(200);
    }

    @Test
    public void testGetAll() {
        given().log().all()
//                .header("charset", "UTF-8")
                .contentType("application/json; charset=UTF-8")
                .when()
                    .get("/subscribers")
                .then()
                    .assertThat()
                        .body("subscribers.subscriber.size()", greaterThanOrEqualTo(1))
                    .and()
                        .body("subscribers.subscriber[0].id", equalTo("1"))
                .and()
                        .statusCode(200);
    }

    @Test(dataProvider="subscriberProvider")
    public void testPost(Subscriber subscriber) {
        if (isPresent(subscriber))
            remove(subscriber);

        XStream xStream = new XStream();
        xStream.registerConverter(new GenderXStreamConverter());
        xStream.processAnnotations(Subscriber.class);
        String xml = xStream.toXML(subscriber);

        given().log().all()
                    .header("Content-Type", "application/xml")
                .with()
                    .body(xml)
                    .post("/subscribers")
                .then()
                    .assertThat()
                        .header("Location", containsString(String.format("http://localhost:%d/rest/xml/subscribers/", port)))
                        .statusCode(201);
    }

    @Test(dataProvider="subscriberProvider")
    public void testUpdate(Subscriber subscriber) {
        if (!isPresent(subscriber))
            add(subscriber);

        subscriber.setFirstName("Ivan");
        subscriber.setLastName("Ivanov");
        subscriber.setAge(19);
        subscriber.setGender(Gender.MALE);

        XStream xStream = new XStream();
        xStream.registerConverter(new GenderXStreamConverter());
        xStream.processAnnotations(Subscriber.class);
        String xml = xStream.toXML(subscriber);

        given().log().all()
                    .header("Content-Type", "application/xml")
                    .header("charset", "UTF-8")
                    .body(xml)
                    .put("/subscribers/{id}", subscriber.getId())
                .then()
                    .assertThat()
                    .body("id", equalTo((int)subscriber.getId()))
                .and()
                    .body("firstName", equalTo(subscriber.getFirstName()))
                    .body("lastName", equalTo(subscriber.getLastName()))
                    .body("age", equalTo(subscriber.getAge()))
                    .body("gender", equalTo(subscriber.getGender().toString()))
                    .statusCode(200);
    }

    @Test(dataProvider="subscriberProvider")
    public void testDelete(Subscriber subscriber) {
        if (!isPresent(subscriber))
            add(subscriber);

        given().log().all()
                    .delete("/subscribers/{id}", subscriber.getId())
                .then()
                    .assertThat()
                        .statusCode(200);
    }

    private boolean isPresent(Subscriber subscriber) {
        try {
            return
                given().log().all()
                        .when()
                            .get("/subscribers/{id}", subscriber.getId())
                        .then()
                            .assertThat()
                            .statusCode(200)
                        .and()
                            .extract()
                            .body()
                            .xmlPath()
                            .getObject(".", Subscriber.class)
                            .equals(subscriber);

        } catch (AssertionError err) {
            return false;
        }
    }

    private void add(Subscriber subscriber) {
        XStream xStream = new XStream();
        xStream.registerConverter(new GenderXStreamConverter());
        xStream.processAnnotations(Subscriber.class);
        String xml = xStream.toXML(subscriber);

        given().log().all()
                .header("Content-Type", "application/xml")
                .with()
                .body(xml)
                .post("/subscribers");
    }

    private void remove(Subscriber subscriber) {
        given().log().all()
                .delete("/subscribers/{id}", subscriber.getId());
    }

    @DataProvider
    private Object[] subscriberProvider() {
        return new Object[] {
                Subscriber.newSubscriber()
                        .id(38L)
                        .firstName("testName")
                        .lastName("lastName")
                        .age(28)
                        .gender(Gender.FEMALE)
                        .build()
        };
    }
}
