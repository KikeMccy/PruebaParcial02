package com.example.pruebaparcial02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.pruebaparcial02.WebServices.Asynchtask;
import com.example.pruebaparcial02.WebServices.WebService;
import com.example.pruebaparcial02.adaptador.AdapterRevistas;
import com.example.pruebaparcial02.fragments.InicioFragment;
import com.example.pruebaparcial02.interfaces.ComunicarFragments;
import com.example.pruebaparcial02.modelo.ModelRevistas;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class MainActivity extends AppCompatActivity implements Asynchtask,ComunicarFragments{
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    //private static final String URL="https://revistas.uteq.edu.ec/public/site/pageHeaderTitleImage_es_ES.png";
    private ImageView imagen;
    Fragment fragmentInicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // imagen=findViewById(R.id.imagen);
        //setupView();
        //loadPicasso();

        handleSSLHandshake();

        //imagen=findViewById(R.id.ivImagen);
       //Picasso.get().load("https://s3-us-west-2.amazonaws.com/lasaga-blog/media/images/grupo_imagen.original.jpg").into(imagen);

        //CARGA DATOS WS
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/journals.php", datos, MainActivity.this,MainActivity.this);
        ws.execute("POST");


        fragmentInicio=new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragments,fragmentInicio).commit();
        //imagen=findViewById(R.id.imagen);

    }


    @Override
    public void processFinish(String result) throws JSONException {
        final ArrayList<ModelRevistas> ListItems= new ArrayList<>();
        String journal_id="";
        String portada="";
        String  name="";
        String description="";
        //String titulo="";
        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++) {
            JSONObject banco = JSONlista.getJSONObject(i);
            //bandera="http://www.geognos.com/api/en/countries/flag/"+banco.getString("alpha2Code").toString()+".png";

            journal_id=banco.getString("journal_id").toString();
            portada=banco.getString("portada").toString();
            name=banco.getString("name").toString();
            description=banco.getString("description");

            //AGREGAR A LA LISTA
            ListItems.add(new ModelRevistas(journal_id, portada, name, description));

        }

        recyclerView = (RecyclerView) findViewById(R.id.rvListaRevistas);
        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new AdapterRevistas(ListItems, this);
        recyclerView.setAdapter(adapter);
    }

    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String arg0, SSLSession arg1) {
                    return true;
                }
            });
        } catch (Exception ignored) {
        }
    }
}