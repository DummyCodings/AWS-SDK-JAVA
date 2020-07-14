/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.awsvpcoperation.subnet;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateSubnetRequest;
import com.amazonaws.services.ec2.model.CreateSubnetResult;
import com.dummycoding.awsvpcoperation.ec2clients.DummyEc2Client;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class DummyAWSSubnetOperation {

    public static void main(String[] args) throws Exception {
        AmazonEC2 eC2Client = DummyEc2Client.getEC2Client();
    }

    public CreateSubnetResult createSubnet(AmazonEC2 eC2Client) throws Exception {
        CreateSubnetRequest createSubnetRequest = new CreateSubnetRequest();
        createSubnetRequest.withVpcId("vpcId");
        CreateSubnetResult createSubnet = eC2Client.createSubnet(createSubnetRequest);
        return createSubnet;
    }
}
