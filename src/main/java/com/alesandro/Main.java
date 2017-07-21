package com.alesandro; /**
 * Created by ALESANDROMarques on 2017-07-18.
 */

import static spark.Spark.*;

import java.io.FileWriter;
import java.io.IOException;
public class Main {

    private int id;

    public Main (int id){
        this.id=id;

    }


    public void run (){
        System.out.println("service is running ");


    }

}
