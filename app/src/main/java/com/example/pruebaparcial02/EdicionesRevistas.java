package com.example.pruebaparcial02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import com.example.pruebaparcial02.WebServices.Asynchtask;
import com.example.pruebaparcial02.WebServices.WebService;
import com.example.pruebaparcial02.adaptador.AdapterEdiciones;
import com.example.pruebaparcial02.adaptador.AdapterRevistas;
import com.example.pruebaparcial02.fragments.InicioFragment;
import com.example.pruebaparcial02.modelo.ModelEdiciones;
import com.example.pruebaparcial02.modelo.ModelRevistas;

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

public class EdicionesRevistas extends AppCompatActivity implements Asynchtask {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager manager;
    Fragment fragmentInicio;
    //private TextView journal_id;
    private ModelRevistas modelRevistas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ediciones_revistas);

        handleSSLHandshake();
        //journal_id=findViewById(R.id.tvJournalID);
        modelRevistas= (ModelRevistas) getIntent().getExtras().getSerializable("itemDetail");
        //ivbandera.setImageResource(itemDeail.getImagen());
        //journal_id.setText(modelRevistas.getJournal_id());
        //tvcapital.setText(itemDeail.getCapital());
        Map<String, String> datos = new HashMap<String, String>();
        WebService ws= new WebService("https://revistas.uteq.edu.ec/ws/issues.php?j_id="+modelRevistas.getJournal_id(), datos, EdicionesRevistas.this,EdicionesRevistas.this);
        ws.execute("POST");
        //setTitle(getClass().getSimpleName() );
        fragmentInicio=new InicioFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedorFragmentsEdiciones,fragmentInicio).commit();
    }

    @Override
    public void processFinish(String result) throws JSONException {

        final ArrayList<ModelEdiciones> ListItems= new ArrayList<>();
        String issue_id="";
        String cover="";
        String title="";
        String volume="";
        String number="";
        String year="";
        String doi="";

        JSONArray JSONlista = new JSONArray(result);
        for(int i=0; i< JSONlista.length();i++) {
            JSONObject banco = JSONlista.getJSONObject(i);
            //bandera="http://www.geognos.com/api/en/countries/flag/"+banco.getString("alpha2Code").toString()+".png";

            issue_id=banco.getString("issue_id").toString();
            cover=banco.getString("cover").toString();
            title=banco.getString("title").toString();// .getString("JournalThumbnail");
            volume=banco.getString("volume").toString();
            number=banco.getString("number").toString();
            year=banco.getString("year").toString();
            doi=banco.getString("doi").toString();

            //AGREGAR A LA LISTA
            ListItems.add(new ModelEdiciones(issue_id, cover, title, volume,number,year,doi));

        }

        recyclerView = (RecyclerView) findViewById(R.id.rvListaRevistas);
        manager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        adapter = new AdapterEdiciones(ListItems, this);
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