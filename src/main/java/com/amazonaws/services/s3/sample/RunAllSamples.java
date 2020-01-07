package com.amazonaws.services.s3.sample;

public class RunAllSamples {

    /** Put your access key here **/
    private static final String awsAccessKey = "ACCESS_KEY";
    
    /** Put your secret key here **/
    private static final String awsSecretKey = "SECRET_KEY";
    
    /** Put your bucket name here **/
    private static final String bucketName = "testbucket.trouget.be";
    
    /** The name of the region where the bucket is created. (e.g. us-west-1) **/
    private static final String regionName = "eu-central-1";
    
    
    /**
     * Run all the included samples. Before running the samples, you need to
     * specify the bucket name, region name and your credentials.
     */
    public static void main(String[] args) {

        S3ListMyBuckets.listMyBuckets(regionName, awsAccessKey, awsSecretKey);
        PutS3ObjectSample.putS3Object(bucketName, regionName, awsAccessKey, awsSecretKey);
        GetS3ObjectSample.getS3Object(bucketName, regionName, awsAccessKey, awsSecretKey);
        PresignedUrlSample.getPresignedUrlToS3Object(bucketName, regionName, awsAccessKey, awsSecretKey);
        PutS3ObjectChunkedSample.putS3ObjectChunked(bucketName, regionName, awsAccessKey, awsSecretKey);

    }

}
