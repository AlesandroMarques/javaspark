package com.alesandro;

import org.json.simple.JSONArray;

import org.json.simple.JSONObject;
import spark.Spark;
import static spark.Spark.*;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by ALESANDROMarques on 2017-07-18.
 */
public class runController {
    public static void main(String[] args) {


        // get("/hello", (req, res) -> "Hello People");
       JSONObject reg= registerService();
        String COREURL = "somthing.com";

        try
        {
            //Register the service with Core. We are ready for work
            post(COREURL + "/services/register", reg);
        }catch(Exception e)
        {
            e.printStackTrace();
            System.out.println("Error: Invalid core URL or port $COREURL");

        }
    }

    public static JSONObject registerService (){
        JSONObject obj = new JSONObject();
        obj.put("name","emptyJavatool");
        obj.put("version","1.0.0");
        obj.put("url","localhost:4567");


        JSONObject input = new JSONObject();
        input.put("key","key1");
        input.put("type","number");
        input.put("required","false");
        input.put("default","40");

        JSONArray inputs = new JSONArray();
        inputs.add(input);


        JSONObject output = new JSONObject();
        output.put("key","key1");
        output.put("type","number");
        output.put("required","false");

        JSONArray outputs = new JSONArray();
        outputs.add(output);


        JSONObject props = new JSONObject();
        props.put("inputs",inputs);
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
}
