/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.ec2clients;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class DummyAwsClient {

    public static EnvironmentVariableCredentialsProvider credentialsProvider = new EnvironmentVariableCredentialsProvider();

    public static AmazonEC2 getEC2Client() throws Exception {
        AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).build();
        return ec2Client;
    }

    public static AmazonS3 getS3Client() throws Exception {
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(credentialsProvider).build();
        return s3Client;
    }
}
