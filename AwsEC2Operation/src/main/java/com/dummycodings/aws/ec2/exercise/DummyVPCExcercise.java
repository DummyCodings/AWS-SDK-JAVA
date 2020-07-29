/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycodings.aws.ec2.exercise;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.AssociateRouteTableRequest;
import com.amazonaws.services.ec2.model.AttachInternetGatewayRequest;
import com.amazonaws.services.ec2.model.CreateInternetGatewayRequest;
import com.amazonaws.services.ec2.model.CreateInternetGatewayResult;
import com.amazonaws.services.ec2.model.CreateRouteRequest;
import com.amazonaws.services.ec2.model.CreateRouteTableRequest;
import com.amazonaws.services.ec2.model.CreateRouteTableResult;
import com.amazonaws.services.ec2.model.CreateSubnetRequest;
import com.amazonaws.services.ec2.model.CreateSubnetResult;
import com.amazonaws.services.ec2.model.CreateVpcRequest;
import com.amazonaws.services.ec2.model.CreateVpcResult;
import com.amazonaws.services.ec2.model.InternetGateway;
import com.amazonaws.services.ec2.model.RouteTable;
import com.amazonaws.services.ec2.model.Subnet;
import com.amazonaws.services.ec2.model.Vpc;
import com.dummycoding.awsvpcoperation.ec2clients.DummyEc2Client;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class DummyVPCExcercise {

    public static void main(String[] args)throws Exception {
        AmazonEC2 eC2Client = DummyEc2Client.getEC2Client();
        CreateVpcRequest createVpcRequest=new CreateVpcRequest();
        createVpcRequest.withCidrBlock("10.10.0.0/16");
        CreateVpcResult createVpc = eC2Client.createVpc(createVpcRequest);
        Vpc vpc = createVpc.getVpc();
        String vpcId = vpc.getVpcId();
        String cidrBlock = vpc.getCidrBlock();
        
        CreateSubnetRequest publicSubnet=new CreateSubnetRequest();
        publicSubnet.withVpcId(vpcId);
        publicSubnet.withCidrBlock("10.10.1.0/24");
        CreateSubnetResult createPublicSubnet = eC2Client.createSubnet(publicSubnet);
        Subnet subnet = createPublicSubnet.getSubnet();
        String publicSubnetId = subnet.getSubnetId();
        
        CreateSubnetRequest privateSubnet=new CreateSubnetRequest();
        privateSubnet.withVpcId(vpcId);
        privateSubnet.withCidrBlock("10.10.1.0/24");
        CreateSubnetResult createPrivateSubnet = eC2Client.createSubnet(privateSubnet);
        Subnet privateSubnetResponse = createPrivateSubnet.getSubnet();
        
        CreateInternetGatewayRequest createInternetGatewayRequest=new CreateInternetGatewayRequest();
        CreateInternetGatewayResult createInternetGateway = eC2Client.createInternetGateway(createInternetGatewayRequest);
        InternetGateway internetGateway = createInternetGateway.getInternetGateway();
        String internetGatewayId = internetGateway.getInternetGatewayId();
        
        AttachInternetGatewayRequest internetGatewayRequest=new AttachInternetGatewayRequest();
        internetGatewayRequest.withInternetGatewayId(internetGatewayId);
        internetGatewayRequest.withVpcId(vpcId);
        
        CreateRouteTableRequest createRouteTableRequest=new CreateRouteTableRequest();
        createRouteTableRequest.withVpcId(vpcId);
        CreateRouteTableResult createRouteTable = eC2Client.createRouteTable(createRouteTableRequest);
        RouteTable routeTable = createRouteTable.getRouteTable();
        String routeTableId = routeTable.getRouteTableId();
        
        CreateRouteRequest createRouteRequest=new CreateRouteRequest();
        createRouteRequest.withGatewayId(internetGatewayId);
        createRouteRequest.withRouteTableId(routeTableId);
        
        AssociateRouteTableRequest associateRouteTableRequest=new AssociateRouteTableRequest();
        associateRouteTableRequest.withRouteTableId(routeTableId);
        associateRouteTableRequest.withSubnetId(publicSubnetId);
        associateRouteTableRequest.withGatewayId(internetGatewayId);
        
    }
}
