package com.academy.demo;

import com.eclipsesource.json.Json;
import com.eclipsesource.json.JsonObject;
import com.eclipsesource.json.JsonValue;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ReadFromJsonXML {
    public static void main(String[] args) {
        String path = System.getProperty("user.dir") + "/data/login";

        readFromJson(path+".json")
                .forEach(System.out::println);

        readFromXML(path+".xml")
                .forEach(System.out::println);

        readUsingMinimalJsonLib(path+".json")
                .forEach(System.out::println);

    }

    // Читаем из XML
    private static List<LoginData> readFromXML(String path) {
        System.out.println(String.format("Read xml from %s: ", path));

        String xml = readFileAsString(path);
        XStream xStream = new XStream();
        initXstreamSecurity(xStream);
        xStream.processAnnotations(LoginData.class);
        List<LoginData> data = (List<LoginData>)xStream.fromXML(xml);
        return data;
    }

    // Читаем из JSON
    private static List<LoginData> readFromJson(String path) {
        System.out.println(String.format("Read json from %s: ", path));

        String json = readFileAsString(path);
        Type listType = new TypeToken<List<LoginData>>() {}.getType();
        List<LoginData> data = new Gson().fromJson(json, listType);
        return data;
    }

    private static List<LoginData> readUsingMinimalJsonLib(String path) {
        System.out.println(String.format("Read using minimal-json lib from %s: ", path));

        String json = readFileAsString(path);
        JsonValue jsonValue = Json.parse(json);
        List<LoginData> data = new ArrayList<>();
        jsonValue.asArray().iterator().forEachRemaining(v-> data.add(parseJsonValueToLoginData(v)));

        return data;
    }

    private static LoginData parseJsonValueToLoginData(JsonValue jsonValue) {
        JsonObject jsonObject = jsonValue.asObject();
        return new LoginData(
                jsonObject.get("login").toString(),
                jsonObject.get("password").toString(),
                jsonObject.get("msg").toString());
    }

    // Читаем из файла в строку как есть
    private static String readFileAsString(String path) {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(new File(path)))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
        }
        return null;
    }

    // prevent security warning
    private static void initXstreamSecurity(XStream xStream) {
        Class<?>[] classes = new Class[] { LoginData.class };
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(classes);
    }
}

