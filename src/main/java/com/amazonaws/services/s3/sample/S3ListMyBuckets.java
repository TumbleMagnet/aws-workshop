package com.amazonaws.services.s3.sample;

import com.amazonaws.services.s3.sample.auth.AWS4SignerBase;
import com.amazonaws.services.s3.sample.auth.AWS4SignerForAuthorizationHeader;
import com.amazonaws.services.s3.sample.util.HttpUtils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Samples showing how to GET an object from Amazon S3 using Signature V4
 * authorization.
 */
public class S3ListMyBuckets {
    
    /**
     * Request the content of the object '/ExampleObject.txt' from the given
     * bucket in the given region using virtual hosted-style object addressing.
     */
    public static void listMyBuckets(String regionName, String awsAccessKey, String awsSecretKey) {
        System.out.println("*******************************************************");
        System.out.println("*  Executing sample 'List my buckets'  *");
        System.out.println("*******************************************************");
        
        // the region-specific endpoint to the target object expressed in path style
        URL endpointUrl;
        try {
            endpointUrl = new URL("https://s3.eu-central-1.amazonaws.com/");
        } catch (MalformedURLException e) {
            throw new RuntimeException("Unable to parse service endpoint: " + e.getMessage());
        }
        
        // for a simple GET, we have no body so supply the precomputed 'empty' hash
        Map<String, String> headers = new HashMap<String, String>();
        headers.put("x-amz-content-sha256", AWS4SignerBase.EMPTY_BODY_SHA256);
        
        AWS4SignerForAuthorizationHeader signer = new AWS4SignerForAuthorizationHeader(
                endpointUrl, "GET", "s3", regionName);
        String authorization = signer.computeSignature(headers, 
                                                       null, // no query parameters
                                                       AWS4SignerBase.EMPTY_BODY_SHA256, 
                                                       awsAccessKey, 
                                                       awsSecretKey);
                
        // place the computed signature into a formatted 'Authorization' header
        // and call S3
        headers.put("Authorization", authorization);
        String response = HttpUtils.invokeHttpRequest(endpointUrl, "GET", headers, null);
        System.out.println("--------- Response content ---------");
        System.out.println(response);
        System.out.println("------------------------------------");
    }
}