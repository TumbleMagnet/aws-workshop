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

public class SimpleRestClient {

    private static boolean useProxy = false;

    public static void main(String[] args) {

        Client client = createClient();

        String hostUrl = "http://public.trouget.be.s3-website.eu-central-1.amazonaws.com/";
        String path = "index.html";
        String responseEntity = client
                .target(hostUrl)
                .path(path)
                .request().get(String.class);

        System.out.println(responseEntity);
    }

    private static Client createClient() {

        ClientConfig cc = new ClientConfig();
        if (useProxy) {
            cc.property(ClientProperties.PROXY_URI, "http://proxy.post.bpgnet.net:8080");
            CredentialsProvider credsProvider = new BasicCredentialsProvider();
            credsProvider.setCredentials(
                    new AuthScope(AuthScope.ANY),
                    new NTCredentials("SPC-00479", "6GK3lIm4", null, null));
            cc.property(ApacheClientProperties.CREDENTIALS_PROVIDER, credsProvider);
            cc.connectorProvider(new ApacheConnectorProvider());
        }

        return ClientBuilder.newBuilder().newClient(cc);
    }
}
