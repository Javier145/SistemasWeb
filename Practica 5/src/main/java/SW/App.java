package mx.uv.sw145;

import static spark.Spark.*;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
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
        before((request, response) -> response.header("Access-Control-Allow-Origin", "*"));

        get("/hello/:name", (request, response) -> {
            return "Hello: " + request.params(":name");
        });

        get("/queryparms", (request, response) -> {
            return "Hello: " + request.queryParams("Prtemail");
        });

        post("/json", (request, response) -> {
            // Ojo: el nombre de los atributos enviados en json es diferente al nombre
            // enviado con URLSearchParams, es decir los parámetros son sensibles al caso
            JsonParser parser = new JsonParser();
            JsonElement arbol = parser.parse(request.body());
            JsonObject peticion = arbol.getAsJsonObject();

            System.out.println("Parametro: " + peticion.get("PrtEmail"));
            System.out.println("Parametro: " + peticion.get("PrtPassword"));
            return "algo";
        });

        post("/formdata", (request, response) -> {
            // JsonParser parser = new JsonParser();
            // JsonElement arbol = parser.parse(request.body());
            // JsonObject peticion = arbol.getAsJsonObject();

            /* 
            *  tener cuidado de usar request.body antes de request.queryParams o viceversa
            *  debido a que el framework consume el contenido al momento de usarlo
            */
            System.out.println("Parametro1: " + request.queryParams("Prtemail"));
            System.out.println("Parametro2: " + request.queryParams("Prtpassword"));

            System.out.println("Parametro: " + request.body() );
            System.out.println("Parametro: " + request.contentType() );

            return "algo";
        });
    }
}