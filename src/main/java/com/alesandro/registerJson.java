package com.alesandro;

/**
 * Created by ALESANDROMarques on 2017-07-19.
 */

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.JsonNodeCreator;

import java.io.FileWriter;
import java.io.IOException;

public class registerJson {
    String name;



    public registerJson(String name) {
        this.name = name;


    }



      public ObjectNode returnNode() {

        ObjectMapper mapper = new ObjectMapper();
        ObjectNode obj = mapper.createObjectNode();
        obj.put("name", "emptyJavatoolTEST");
        obj.put("version", "1.0.0");


        ObjectNode server = mapper.createObjectNode();
        //server.put("url", "localhost:4567");
          server.put("url", "http://mdmubu107.torolab.ibm.com:8080/");
          server.put("threads", "1");

        obj.putPOJO("server", server);

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
        output.put("optional", "false");

        ArrayNode outputs = mapper.createArrayNode();
        outputs.add(output);


        ObjectNode props = mapper.createObjectNode();
        props.putArray("inputs").addAll(inputs);
        props.putArray("outputs").addAll(outputs);

        obj.putPOJO("properties", props);

try {
    System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
}catch(Exception e){
    e.printStackTrace();

}

          try (FileWriter file = new FileWriter("test.json")) {


              file.write(obj.toString());
              file.flush();

          } catch (IOException e) {
              e.printStackTrace();
          }

return obj;
    }

    public ObjectNode returnNode2() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode obj = mapper.createObjectNode();
        obj.put("name", "emptyJavatoolTEST");
        obj.put("host", "http://mdmubu107.torolab.ibm.com:8080/");

        return  obj;

    }
}
