package com.alesandro; /**
 * Created by ALESANDROMarques on 2017-07-18.
 */

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static spark.Spark.*;

import java.io.FileWriter;
import java.io.IOException;
public class Main {

    private int id;

    public Main (int id){
        this.id=id;

    }

     static String url = "http://mdmubu107.torolab.ibm.com:8080/";
    static String name = "emptyJavaToolTEST";
    static String threads ="1";
     static String version = "1.0.0";


    public void run (){
        System.out.println("service is running ");


    }


     static ArrayNode getInputs(){
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
     static ArrayNode getOutputs(){
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
