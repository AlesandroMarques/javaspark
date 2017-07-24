package com.alesandro; /**
 * Created by ALESANDROMarques on 2017-07-18.
 */

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import static spark.Spark.*;

import java.io.FileWriter;
import java.io.IOException;
public class Main {

    private int id;

    public Main (int id){
        this.id=id;

    }

     final String url = "http://mdmubu107.torolab.ibm.com:8080/";
     final String name = "emptyJavaToolTEST";
    final  String threads ="1";
      final String version = "1.0.0";


    public ObjectNode run (){
        System.out.println("service is running ");
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode obj = mapper.createObjectNode();
        obj.put("name", name);
        obj.put("host", url);



        return obj;

    }



     public ArrayNode getInputs(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode input = mapper.createObjectNode();
        input.put("key", "key1");
        input.put("type", "number");
        input.put("required", "false");
        input.put("default", "40");

        ArrayNode inputs = mapper.createArrayNode();
        inputs.add(input);


return inputs;



    }
     public ArrayNode getOutputs(){
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode output = mapper.createObjectNode();
        output.put("key", "key1");
        output.put("type", "number");
        output.put("optional", "false");

        ArrayNode outputs = mapper.createArrayNode();
        outputs.add(output);

        return outputs;
    }


}
