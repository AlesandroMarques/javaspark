package com.alesandro;

import com.mashape.unirest.http.JsonNode;


import com.mashape.unirest.http.HttpResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;


import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
/*import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;*/



import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import static spark.Spark.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
/**
 * Created by ALESANDROMarques on 2017-07-18.
 */






public class runController {
    public static void main(String[] args) {


        ObjectNode jsonNode = returnNode();

        HttpResponse<JsonNode> jsonResponse = null;
        String COREURL = "";
        try {
            jsonResponse = Unirest.post(COREURL + "/services/register")
                    .body(jsonNode)
                    .asJson();
            // .asJson converts responce , could be .asString()
        } catch (UnirestException e) {
            e.printStackTrace();
            System.out.println("Error: Invalid core URL or port $COREURL");
        }
        System.out.println(jsonResponse.getBody());

    }




    public static void temp (){





        // get("/hello", (req, res) -> "Hello People");
        JSONObject reg = registerService();
        String regString = reg.toString();
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode jsonNode = null;
        try {
            jsonNode = mapper.readValue(regString, ObjectNode.class);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        System.out.println(jsonNode.toString());
        String COREURL = "somthing.com";

       /* try
        {
            //Register the service with Core. We are ready for work

            post(COREURL + "/services/register", data=reg );
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: Invalid core URL or port $COREURL");

        }*/

        //  HttpResponse<String> jsonResponse = null;
        HttpResponse<JsonNode> jsonResponse = null;
        try {
            jsonResponse = Unirest.post(COREURL + "/services/register")
                    .body(jsonNode)
                    .asJson();
            // .asJson converts responce , could be .asString()
        } catch (UnirestException e) {
            e.printStackTrace();
            System.out.println("Error: Invalid core URL or port $COREURL");
        }
        System.out.println(jsonResponse.getBody());
    }


    public static JSONObject registerService() {
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
        inputs.add(input);


        JSONObject output = new JSONObject();
        output.put("key", "key1");
        output.put("type", "number");
        output.put("required", "false");

        JSONArray outputs = new JSONArray();
        outputs.add(output);


        JSONObject props = new JSONObject();
        props.put("inputs", inputs);
        props.put("outputs", outputs);

        obj.put("Properties", props);

        try (FileWriter file = new FileWriter("test.json")) {

            file.write(obj.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.print(obj);
        return obj;
    }

    public static ObjectNode returnNode() {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode obj = mapper.createObjectNode();
        obj.put("name", "emptyJavatool");
        obj.put("version", "1.0.0");
        obj.put("url", "localhost:4567");


        ObjectNode input = mapper.createObjectNode();
        input.put("key", "key1");
        input.put("type", "number");
        input.put("required", "false");
        input.put("default", "40");

        ArrayNode inputs = mapper.createArrayNode();
        inputs.add(input);



        ObjectNode output = mapper.createObjectNode();
        output.put("key", "key1");
        output.put("type", "number");
        output.put("required", "false");

        ArrayNode outputs = mapper.createArrayNode();
        outputs.add(output);


        ObjectNode props = mapper.createObjectNode();
        props.putArray("inputs").addAll(inputs);
        props.putArray("outputs").addAll(outputs);

        obj.putPOJO("Properties", props);

try {
    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
}catch(Exception e){
    e.printStackTrace();

}
return obj;
    }
}