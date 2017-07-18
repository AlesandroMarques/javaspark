/**
 * Created by ALESANDROMarques on 2017-07-18.
 */
import static spark.Spark.*;
import static spark.Spark.stop;


public class Main {
    public static void main(String[] args) {


        get("/hello", (req, res) -> "Hello People");

    }


}
