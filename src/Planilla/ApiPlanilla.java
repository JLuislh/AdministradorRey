/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Planilla;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author it
 */
public class ApiPlanilla {

    public static ArrayList<ClassNomina> ListarEmpleados() throws Exception {
        URL url = new URL("http://40.233.19.196/nominapega/empleados/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("Accept", "application/json");

        int responseCode = con.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("Error al conectar: Código de respuesta " + responseCode);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = in.readLine()) != null) {
                respuesta.append(linea);
            }

            Gson gson = new Gson();
            Type tipoLista = new TypeToken<ArrayList<ClassNomina>>() {
            }.getType();

            return gson.fromJson(respuesta.toString(), tipoLista);
        }
    }

  /*  public static ArrayList<ClassNomina> cargarNominas() throws Exception {

        URL url = new URL("http://40.233.19.196/nominapega/nomina");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        con.setRequestProperty("Accept", "application/json");

        int responseCode = con.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new Exception("Error al conectar: Código de respuesta " + responseCode);
        }

        try (BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = in.readLine()) != null) {
                respuesta.append(linea);
            }

            Gson gson = new Gson();
            Type tipoLista = new TypeToken<ArrayList<ClassNomina>>() {
            }.getType();

            return gson.fromJson(respuesta.toString(), tipoLista);
        }
    }*/
    
    
    public static ArrayList<ClassNomina> cargarNominas(int estado) throws Exception {

    URL url = new URL("http://40.233.19.196/nominapega/nomina/" + estado);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");
    con.setRequestProperty("Accept", "application/json");

    int responseCode = con.getResponseCode();
    if (responseCode != HttpURLConnection.HTTP_OK) {
        throw new Exception("Error al conectar: Código de respuesta " + responseCode);
    }

    try (BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream(), "UTF-8"))) {

        StringBuilder respuesta = new StringBuilder();
        String linea;
        while ((linea = in.readLine()) != null) {
            respuesta.append(linea);
        }

        Gson gson = new Gson();
        Type tipoLista = new TypeToken<ArrayList<ClassNomina>>() {}.getType();

        return gson.fromJson(respuesta.toString(), tipoLista);
    }
}


    public static String crearNomina(String fechainicio, String fechafin, int dias, int estado) throws Exception {

        URL url = new URL("http://40.233.19.196/nominapega/nomina");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
        conn.setDoOutput(true);

        JSONObject json = new JSONObject();
        json.put("FECHAINICIO", fechainicio);
        json.put("FECHAFIN", fechafin);
        json.put("DIAS", dias);
        json.put("ESTADO", estado);

        // Enviar JSON
        try (OutputStream os = conn.getOutputStream()) {
            os.write(json.toString().getBytes(StandardCharsets.UTF_8));
            os.flush();
        }

        int status = conn.getResponseCode();

        BufferedReader br = new BufferedReader(
                new InputStreamReader(
                        status >= 200 && status < 300 ? conn.getInputStream() : conn.getErrorStream(),
                        StandardCharsets.UTF_8
                )
        );

        StringBuilder response = new StringBuilder();
        String line;

        while ((line = br.readLine()) != null) {
            response.append(line);
        }

        return response.toString();
    }
    
    public static String CerrarNomina(int id, String jsonBody) throws Exception {
    URL url = new URL("http://40.233.19.196/nominapega/nomina/" + id);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("PUT");
    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    conn.setRequestProperty("Accept", "application/json");
    conn.setDoOutput(true);

    System.out.println("JSON ENVIADO DESDE JAVA: " + jsonBody);

    try (OutputStream os = conn.getOutputStream()) {
        os.write(jsonBody.getBytes(StandardCharsets.UTF_8));
    }

    int status = conn.getResponseCode();
    BufferedReader br = new BufferedReader(new InputStreamReader(
            (status >= 200 && status < 300) ? conn.getInputStream() : conn.getErrorStream(),
            StandardCharsets.UTF_8
    ));

    StringBuilder response = new StringBuilder();
    String line;

    while ((line = br.readLine()) != null) {
        response.append(line);
    }

    return response.toString();
}
    
    
    public static List<EmpleadoPago> obtenerPagoNomina(int idNomina) throws Exception {
    
    String urlStr = "http://40.233.19.196/nominapega/pago-nomina/" + idNomina;
    URL url = new URL(urlStr);
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("GET");
    conn.setRequestProperty("Accept", "application/json");

    BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "UTF-8")
    );

    StringBuilder sb = new StringBuilder();
    String line;
    while ((line = br.readLine()) != null) sb.append(line);

    List<EmpleadoPago> lista = new ArrayList<>();

    JSONArray array = new JSONArray(sb.toString());
    for (int i = 0; i < array.length(); i++) {
        JSONObject o = array.getJSONObject(i);

        EmpleadoPago ep = new EmpleadoPago();
        ep.CODIGO = o.getInt("CODIGO");
        ep.NOMBRES = o.getString("NOMBRES");
        ep.PUESTO = o.getString("PUESTO");
        ep.LIQUIDO = o.getDouble("LIQUIDO");
        lista.add(ep);
    }

    return lista;
}
    
///////////////////////////inicio descuentos////////////////////////////////

public static String crearDescuento(int idPago, String descripcion, double monto) throws Exception {

    URL url = new URL("http://40.233.19.196/nominapega/descuentos");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    conn.setDoOutput(true);

    JSONObject json = new JSONObject();
    json.put("ID_PAGO", idPago);
    json.put("DESCRIPCION", descripcion);
    json.put("MONTO", monto);

    // Enviar JSON
    try (OutputStream os = conn.getOutputStream()) {
        os.write(json.toString().getBytes(StandardCharsets.UTF_8));
        os.flush();
    }

    int status = conn.getResponseCode();

    BufferedReader br = new BufferedReader(
            new InputStreamReader(
                    status >= 200 && status < 300 ? conn.getInputStream() : conn.getErrorStream(),
                    StandardCharsets.UTF_8
            )
    );

    StringBuilder response = new StringBuilder();
    String line;

    while ((line = br.readLine()) != null) {
        response.append(line);
    }

    return response.toString();
}
///////////////////////fin descuentos///////////////////

///////////////////////////////inicio extras////////////
public static String insertarExtras(int idPago, String descripcion, double monto) throws Exception {

    URL url = new URL("http://40.233.19.196/nominapega/horas-extras");
    HttpURLConnection conn = (HttpURLConnection) url.openConnection();

    conn.setRequestMethod("POST");
    conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
    conn.setDoOutput(true);

    JSONObject json = new JSONObject();
    json.put("ID_PAGO", idPago);
    json.put("DESCRIPCION", descripcion);
    json.put("MONTO", monto);

    // Enviar JSON
    try (OutputStream os = conn.getOutputStream()) {
        os.write(json.toString().getBytes(StandardCharsets.UTF_8));
        os.flush();
    }

    int status = conn.getResponseCode();

    BufferedReader br = new BufferedReader(
            new InputStreamReader(
                    status >= 200 && status < 300 ? conn.getInputStream() : conn.getErrorStream(),
                    StandardCharsets.UTF_8
            )
    );

    StringBuilder response = new StringBuilder();
    String line;

    while ((line = br.readLine()) != null) {
        response.append(line);
    }

    return response.toString();
}    

}//FIN
