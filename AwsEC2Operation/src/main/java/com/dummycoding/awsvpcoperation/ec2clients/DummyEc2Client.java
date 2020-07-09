/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.awsvpcoperation.ec2clients;

import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class DummyEc2Client {

    public static AmazonEC2 getEC2Client() throws Exception {
        EnvironmentVariableCredentialsProvider credentialsProvider = new EnvironmentVariableCredentialsProvider();
        AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard().withCredentials(credentialsProvider).build();
        return ec2Client;
    }
}
