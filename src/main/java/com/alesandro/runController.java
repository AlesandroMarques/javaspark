package com.alesandro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.json.JSONArray;
import org.json.JSONObject;




import java.io.FileWriter;
import java.io.IOException;


/**
 * Created by ALESANDROMarques on 2017-07-18.
 */






public class runController {


    public static void main(String[] args) {

        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });






       registerJson register = new registerJson("practise");


        HttpResponse<String> jsonResponse = null;
        String COREURL = "http://mdmubu108.torolab.ibm.com:8080/services";
        try {
            jsonResponse = Unirest.post(COREURL)
                    .body(register.returnNode())
                    .asString();
            // for json return <JsonNode> .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
            System.out.println("Error: Invalid core URL or port $COREURL");
        }
        System.out.println();
        System.out.println(jsonResponse.getStatus());
      //  System.out.println(jsonResponse.getBody());

    }








/*
// not needed becuase created in class registerJSON
    public static JSONObject returnObj() {
        JSONObject obj = new JSONObject();
        obj.put("name", "emptyJavatool");
        obj.put("version", "1.0.0");


        JSONObject server = new JSONObject();
        server.put("url", "localhost:4567");
        server.put("threads", "1");
        obj.put("server", server);

        JSONObject input = new JSONObject();

        input.put("key", "key1");
        input.put("type", "number");
        input.put("required", "false");
        input.put("default", "40");

        JSONArray inputs = new JSONArray();
        //inputs.add(input);
        inputs.put(input);


        JSONObject output = new JSONObject();
        output.put("key", "key1");
        output.put("type", "number");
        output.put("optional", "false");

        JSONArray outputs = new JSONArray();
        outputs.put(output);
        //outputs.add(output);


        JSONObject props = new JSONObject();

        props.put("inputs", inputs);
        props.put("outputs", outputs);

        obj.put("properties", props);

        try (FileWriter file = new FileWriter("test.json")) {


            file.write(obj.toString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj.toString());
        return obj;
    }*/


}