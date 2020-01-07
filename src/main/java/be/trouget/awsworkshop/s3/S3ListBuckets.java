package be.trouget.awsworkshop.s3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListBucketsRequest;
import software.amazon.awssdk.services.s3.model.ListBucketsResponse;

public class S3ListBuckets {

    private static Logger LOG = LogManager.getLogger(S3ListBuckets.class);

    public static void main(String[] args) {

        Region region = Region.EU_CENTRAL_1;

        LOG.info("Using region "+ region);

        S3Client s3 = S3Client.builder().region(region).build();

        LOG.info("S3 client ready...");

        // List buckets
        ListBucketsRequest listBucketsRequest = ListBucketsRequest.builder().build();
        ListBucketsResponse listBucketsResponse = s3.listBuckets(listBucketsRequest);

        LOG.info("Got response...");
        listBucketsResponse.buckets().stream().forEach(x -> System.out.println(x.name()));
    }
}
