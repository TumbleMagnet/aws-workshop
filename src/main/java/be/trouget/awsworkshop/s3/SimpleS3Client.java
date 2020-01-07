package be.trouget.awsworkshop.s3;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.glassfish.jersey.apache.connector.ApacheClientProperties;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

public class SimpleS3Client {



    public static void main(String[] args) {

        CredentialsProvider credsProvider = new BasicCredentialsProvider();
        credsProvider.setCredentials(
                new AuthScope(AuthScope.ANY),
                new NTCredentials("SPC-00479", "6GK3lIm4", null, null));

        ClientConfig cc = new ClientConfig();
        cc.property(ClientProperties.PROXY_URI, "http://proxy.post.bpgnet.net:8080");
        cc.property(ApacheClientProperties.CREDENTIALS_PROVIDER, credsProvider);
        cc.connectorProvider(new ApacheConnectorProvider());

        Client client = ClientBuilder.newBuilder().newClient(cc);

        String responseEntity = client
                .target("https://track.bpost.cloud")
                .path("btr/web/#/home")
                .queryParam("lang", "fr")
                .request().get(String.class);

        System.out.println(responseEntity);
    }
}
