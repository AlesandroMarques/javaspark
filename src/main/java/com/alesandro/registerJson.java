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
    String url;
    String thread;
    String version;
    ArrayNode inputs;
    ArrayNode outputs;

    public registerJson(String name, String url, String thread, String version, ArrayNode inputs, ArrayNode outputs) {
        this.name = name;
        this.url = url;
        this.thread = thread;
        this.version = version;
        this.inputs = inputs;
        this.outputs = outputs;
    }


    public ObjectNode returnNode() {

        ObjectMapper mapper = new ObjectMapper();

        ObjectNode obj = mapper.createObjectNode();

        obj.put("name", name);
        obj.put("version", version);

        ObjectNode server = mapper.createObjectNode();
        server.put("url", url);
        server.put("threads", thread);

        obj.putPOJO("server", server);

        ObjectNode props = mapper.createObjectNode();
        props.putArray("inputs").addAll(inputs);
        props.putArray("outputs").addAll(outputs);

        obj.putPOJO("properties", props);

        try {
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj));
        } catch (Exception e) {
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
        obj.put("name", name);
        obj.put("host", url);

        return obj;
    }
}