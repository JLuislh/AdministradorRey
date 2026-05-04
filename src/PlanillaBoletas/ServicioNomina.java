/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PlanillaBoletas;

/**
 *
 * @author it
 */
import java.net.*;
import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ServicioNomina {

    public static List<NominaDetalle> obtenerDatos(int idNomina) throws Exception {

        URL url = new URL("http://40.233.19.196/nominapega/nomina/detalle/" + idNomina);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
        String respuesta = br.readLine();
        br.close();

        // El JSON viene así: { ok:true, data:[...] }
        var jsonObj = new Gson().fromJson(respuesta, Map.class);

        // Extraemos solo la lista
        String dataJson = new Gson().toJson(jsonObj.get("data"));

        // Convertimos a lista de beans
        List<NominaDetalle> lista = new Gson().fromJson(
            dataJson,
            new TypeToken<List<NominaDetalle>>(){}.getType()
        );

        return lista;
    }
}
