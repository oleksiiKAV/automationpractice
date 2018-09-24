package com.academy.demo;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class PrepareJsonXML {

    private static List<LoginData> data = new ArrayList<>();

    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/data/login";

        data.add(new LoginData("dimabologov@gmail.com", "456456Qwerty", "Invalid password."));
        data.add(new LoginData("123", "456456Qwerty", "Invalid email address."));
        data.add(new LoginData("", "123", "An email address required."));

        prepareJson(path+".json", data);
        prepareXML(path + ".xml", data);
    }


    private static void prepareJson(String path, List<LoginData> data) {
        Gson gson = new Gson();
        String json = gson.toJson(data);

        try (Writer writer = new FileWriter(
                new File(path))) {
            writer.write(json);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    private static void prepareXML(String path, List<LoginData> data) {
        XStream xStream = new XStream();
        xStream.processAnnotations(LoginData.class);
        String xml = xStream.toXML(data);

        try (Writer writer = new FileWriter(new File(path))) {
            writer.write(xml);
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
