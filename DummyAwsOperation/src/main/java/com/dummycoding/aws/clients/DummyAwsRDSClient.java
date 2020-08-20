/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.aws.clients;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class DummyAwsRDSClient {

    public static AmazonRDS getAwsRDSClient() throws Exception {
        EnvironmentVariableCredentialsProvider credentialsProvider = new EnvironmentVariableCredentialsProvider();
        AmazonRDS ec2Client = AmazonRDSClientBuilder.standard().withCredentials(credentialsProvider).build();
        return ec2Client;
    }
}
