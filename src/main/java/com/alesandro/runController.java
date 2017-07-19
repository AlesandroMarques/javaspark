package com.alesandro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;


//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

//import com.fasterxml.jackson.databind.ObjectMapper;
/*import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;*/
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


       // JSONObject jsonNode = returnObj();

        HttpResponse<JsonNode> jsonResponse = null;
        String COREURL = "http://mdmubu108.torolab.ibm.com:8080/services";
        try {
            jsonResponse = Unirest.post(COREURL)
                    .body(register.returnNode())
                    .asJson();
            // .asJson converts responce , could be .asString()
        } catch (UnirestException e) {
            e.printStackTrace();
            System.out.println("Error: Invalid core URL or port $COREURL");
        }
        System.out.println();
        System.out.println(jsonResponse.getStatus());
        System.out.println(jsonResponse.getBody());

    }



    public static JSONObject returnObj() {
        JSONObject obj = new JSONObject();
        obj.put("name", "emptyJavatool");
        obj.put("version", "1.0.0");
        obj.put("url", "localhost:4567");


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
    }


}