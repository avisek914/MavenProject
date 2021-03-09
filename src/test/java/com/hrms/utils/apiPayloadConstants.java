package com.hrms.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minidev.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class apiPayloadConstants {
    public static String createEmployeeBody() {

        File input = new File("C:/Users/Owner/IdeaProjects/CucumberFrameworkBatch8/src/test/resources/JsonData/createUser.json");
        JsonObject jsonObj=null;
        try {

            JsonElement jsonElement = JsonParser.parseReader(new FileReader(input));
                jsonObj = jsonElement.getAsJsonObject();




        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jsonObj.toString();
    }
}
