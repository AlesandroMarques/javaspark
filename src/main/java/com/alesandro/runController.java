package com.alesandro;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.HttpRequest;
import com.mashape.unirest.request.HttpRequestWithBody;
import org.json.JSONArray;
import org.json.JSONObject;


import static spark.Spark.*;
import static spark.route.HttpMethod.post;

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

        // port(8080);
//run("/");


        boolean regstatus = register(getjson(), "http://mdmubu108.torolab.ibm.com:8080", "/services");
        if (regstatus == true) {

             run("/");

        }


    }


    public static ObjectNode getjson() {
        registerJson register = new registerJson("practise");
        return register.returnNode();

    }

    public static ObjectNode getjsonRes() {
        registerJson register = new registerJson("practise");
        return register.returnNode2();

    }


    public static boolean register(ObjectNode obj, String COREURL, String regEndPoint) {


// register
        HttpResponse<String> jsonResponse = null;
        // String COREURL = "http://mdmubu108.torolab.ibm.com:8080";
        // String regEndPoint = "/services"
        try {
            jsonResponse = Unirest.post(COREURL + regEndPoint)
                    .body(obj)
                    .asString();
            // for json return <JsonNode> .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();
            System.out.println("Error: Invalid core URL or port $COREURL");
        }
        System.out.println();
        System.out.println(jsonResponse.getStatus());
        System.out.println(jsonResponse.getBody());

        if (jsonResponse.getBody().equalsIgnoreCase("Opps, something went wrong :(")) {
            System.out.println("registration failed can not proceed ");
            return false;
        } else {
            return true;


        }


    }

    private static void enableCORS(final String origin, final String methods, final String headers) {

        options("/*", (request, response) -> {

            String accessControlRequestHeaders = request.headers("Access-Control-Request-Headers");
            if (accessControlRequestHeaders != null) {
                response.header("Access-Control-Allow-Headers", accessControlRequestHeaders);
            }

            String accessControlRequestMethod = request.headers("Access-Control-Request-Method");
            if (accessControlRequestMethod != null) {
                response.header("Access-Control-Allow-Methods", accessControlRequestMethod);
            }

            return "OK";
        });

        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", origin);
            response.header("Access-Control-Request-Method", methods);
            response.header("Access-Control-Allow-Headers", headers);
            // Note: this may or may not be necessary in your particular application
            response.type("application/json");
        });
    }

    public static void sendResults(String COREURL, String resEndPoint, ObjectNode obj) {
        // registerJson register = new registerJson("practise");
        // String COREURL = "http://mdmubu108.torolab.ibm.com:8080";
        // String resEndPoint= "/run/done"
        HttpResponse<String> jsonResponse2 = null;

        try {
            jsonResponse2 = Unirest.post(COREURL + resEndPoint)
                    .body(obj)
                    .asString();
            // for json return <JsonNode> .asJson();
        } catch (UnirestException e) {
            e.printStackTrace();

        }
        System.out.println("run/finish");
        System.out.println(jsonResponse2.getStatus());
        System.out.println(jsonResponse2.getBody());

    }

    public static void run(String serviceURL) {
        port(8080);
        enableCORS("*","*","*");
        // running grab from server application is being run on
        //serviceURL= "/";
        post(serviceURL, "application/json", (req, res) -> {
            System.out.println(req.body());

            Main service = new Main(15);
            service.run();

            sendResults("http://mdmubu108.torolab.ibm.com:8080", "/run/finish", getjsonRes());
            return "service complete";
        });



    }




}