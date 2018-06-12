package videochat.com.woocomerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.SignatureType;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Thread() {
            @Override
            public void run() {
                String RESOURCE_URL = "http://devrepublic-projects.nl/development/webshop/wp-json/wc/v2/products/categories?orderby=name&per_page=3";
                String SCOPE = "*"; //all permissions
                Response response;
                OAuthRequest request;
                String responsebody = "";
                OAuthService service = new ServiceBuilder().provider(OneLeggedApi10.class)
                        .apiKey("ck_1cfdccfc3c12d523a2668bf5e439df2490d5ddd5")
                        .apiSecret("cs_5e1a01eacd03615f7139b64e0cff53d3f8194102")
                        .signatureType(SignatureType.QueryString)
                        .debug()
                        /*.scope(SCOPE).*/
                        .build();

                request = new OAuthRequest(Verb.GET, RESOURCE_URL);
                service.signRequest(new Token("", ""), request);

                // Now let's go and ask for a protected resource!
                Log.d("scribe", "Now we're going to access a protected resource...");

                try {
                    response = request.send();
                    if (response.isSuccessful()) {
                        responsebody = response.getBody();
                        Log.e("response", responsebody);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();


    }
}
