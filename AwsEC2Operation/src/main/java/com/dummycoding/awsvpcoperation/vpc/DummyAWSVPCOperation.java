/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.awsvpcoperation.vpc;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateDefaultVpcRequest;
import com.amazonaws.services.ec2.model.CreateDefaultVpcResult;
import com.amazonaws.services.ec2.model.CreateVpcRequest;
import com.amazonaws.services.ec2.model.CreateVpcResult;
import com.amazonaws.services.ec2.model.DeleteVpcRequest;
import com.amazonaws.services.ec2.model.DeleteVpcResult;
import com.amazonaws.services.ec2.model.DescribeVpcsRequest;
import com.amazonaws.services.ec2.model.DescribeVpcsResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.Vpc;
import com.amazonaws.services.ec2.model.VpcCidrBlockAssociation;
import com.amazonaws.services.ec2.model.VpcCidrBlockState;
import com.amazonaws.services.ec2.model.VpcIpv6CidrBlockAssociation;
import com.dummycoding.awsvpcoperation.ec2clients.DummyEc2Client;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class DummyAWSVPCOperation {

    public static void main(String[] args) throws Exception {
        AmazonEC2 eC2Client = DummyEc2Client.getEC2Client();

        //create VPC
        CreateVpcResult createVpc = createVpc(eC2Client);
        //get VPC
        DescribeVpcsResult vpc = getVPC(eC2Client);
        List<Vpc> vpcs = vpc.getVpcs();
        for (Vpc getVPC : vpcs) {
            String cidrBlock = getVPC.getCidrBlock();
            System.out.println("cidrBlock = " + cidrBlock);
            List<VpcCidrBlockAssociation> cidrBlockAssociationSet = getVPC.getCidrBlockAssociationSet();
            String dhcpOptionsId = getVPC.getDhcpOptionsId();
            System.out.println("dhcpOptionsId = " + dhcpOptionsId);
            String instanceTenancy = getVPC.getInstanceTenancy();
            System.out.println("instanceTenancy = " + instanceTenancy);
            List<VpcIpv6CidrBlockAssociation> ipv6CidrBlockAssociationSet = getVPC.getIpv6CidrBlockAssociationSet();
            Boolean isDefault = getVPC.getIsDefault();
            System.out.println("isDefault = " + isDefault);
            String ownerId = getVPC.getOwnerId();
            System.out.println("ownerId = " + ownerId);
            String state = getVPC.getState();
            System.out.println("state = " + state);
            List<Tag> tags = getVPC.getTags();
            System.out.println("tags = " + tags);
            String vpcId = getVPC.getVpcId();
            System.out.println("vpcId = " + vpcId);
        }
        //Delete VPC
        DeleteVpcResult deleteVPC = deleteVPC(eC2Client);
        
        //create Default VPC
        CreateDefaultVpcResult createDefaultVPC = createDefaultVPC(eC2Client);

    }

    public static CreateVpcResult createVpc(AmazonEC2 eC2Client) throws Exception {
        CreateVpcRequest createVpcRequest = new CreateVpcRequest();
//        createVpcRequest.withAmazonProvidedIpv6CidrBlock(Boolean.FALSE);
        createVpcRequest.withCidrBlock("10.10.0.0/16");
//        createVpcRequest.withInstanceTenancy(Tenancy.Default);
//        createVpcRequest.withInstanceTenancy(Tenancy.Dedicated);
//        createVpcRequest.withInstanceTenancy(Tenancy.Host);
//        createVpcRequest.withIpv6CidrBlock(ipv6CidrBlock);
//        createVpcRequest.withIpv6CidrBlockNetworkBorderGroup("us-east-1-lax-1");
//        createVpcRequest.withIpv6Pool("");
        CreateVpcResult createVpc = eC2Client.createVpc(createVpcRequest);
        Vpc vpc = createVpc.getVpc();
        String cidrBlock = vpc.getCidrBlock();
        System.out.println("cidrBlock = " + cidrBlock);
        String dhcpOptionsId = vpc.getDhcpOptionsId();
        System.out.println("dhcpOptionsId = " + dhcpOptionsId);
        String instanceTenancy = vpc.getInstanceTenancy();
        System.out.println("instanceTenancy = " + instanceTenancy);
        Boolean isDefault = vpc.getIsDefault();
        System.out.println("isDefault = " + isDefault);
        String ownerId = vpc.getOwnerId();
        System.out.println("ownerId = " + ownerId);
        String state = vpc.getState();
        System.out.println("state = " + state);
        String vpcId = vpc.getVpcId();
        System.out.println("vpcId = " + vpcId);
        List<Tag> tags = vpc.getTags();
        List<VpcIpv6CidrBlockAssociation> ipv6CidrBlockAssociationSet = vpc.getIpv6CidrBlockAssociationSet();
        for (VpcIpv6CidrBlockAssociation vpcIpv6CidrBlockAssociation : ipv6CidrBlockAssociationSet) {
            String associationId = vpcIpv6CidrBlockAssociation.getAssociationId();
            System.out.println("associationId = " + associationId);
            String ipv6CidrBlock = vpcIpv6CidrBlockAssociation.getIpv6CidrBlock();
            System.out.println("ipv6CidrBlock = " + ipv6CidrBlock);
            VpcCidrBlockState ipv6CidrBlockState = vpcIpv6CidrBlockAssociation.getIpv6CidrBlockState();
            String state1 = ipv6CidrBlockState.getState();
            System.out.println("state1 = " + state1);
            String statusMessage = ipv6CidrBlockState.getStatusMessage();
            System.out.println("statusMessage = " + statusMessage);
            String ipv6Pool = vpcIpv6CidrBlockAssociation.getIpv6Pool();
            System.out.println("ipv6Pool = " + ipv6Pool);
            String networkBorderGroup = vpcIpv6CidrBlockAssociation.getNetworkBorderGroup();
            System.out.println("networkBorderGroup = " + networkBorderGroup);
        }
        List<VpcCidrBlockAssociation> cidrBlockAssociationSet = vpc.getCidrBlockAssociationSet();
        for (VpcCidrBlockAssociation vpcCidrBlockAssociation : cidrBlockAssociationSet) {
            String associationId = vpcCidrBlockAssociation.getAssociationId();
            System.out.println("associationId = " + associationId);
            String cidrBlock1 = vpcCidrBlockAssociation.getCidrBlock();
            System.out.println("cidrBlock1 = " + cidrBlock1);
            VpcCidrBlockState cidrBlockState = vpcCidrBlockAssociation.getCidrBlockState();
            String state1 = cidrBlockState.getState();
            System.out.println("state1 = " + state1);
            String statusMessage = cidrBlockState.getStatusMessage();
            System.out.println("statusMessage = " + statusMessage);
        }
        return createVpc;

    }

    public static DescribeVpcsResult getVPC(AmazonEC2 amazonEC2Client) throws Exception {
        DescribeVpcsRequest describeVpcsRequest = new DescribeVpcsRequest();
        Filter filter = new Filter();
        filter.setName("state");
        Collection<String> filterValues = new ArrayList<>();
        filterValues.add("available");//(pending | available).
        filter.setValues(filterValues);
        describeVpcsRequest.withFilters(filter);
        describeVpcsRequest.withVpcIds("vpc-049854550020695f3");
        DescribeVpcsResult describeVpcs = amazonEC2Client.describeVpcs(describeVpcsRequest);
        return describeVpcs;
    }

    public static DeleteVpcResult deleteVPC(AmazonEC2 amazonEC2Client) throws Exception {
        DeleteVpcRequest deleteVpcRequest = new DeleteVpcRequest();
        deleteVpcRequest.withVpcId("vpc-049854550020695f3");
        DeleteVpcResult deleteVpc = amazonEC2Client.deleteVpc(deleteVpcRequest);
        return deleteVpc;
    }

    public static CreateDefaultVpcResult createDefaultVPC(AmazonEC2 amazonEC2Client) throws Exception {
        CreateDefaultVpcRequest createDefaultVpcRequest = new CreateDefaultVpcRequest();
        CreateDefaultVpcResult createDefaultVpc = amazonEC2Client.createDefaultVpc(createDefaultVpcRequest);
        Vpc vpc = createDefaultVpc.getVpc();
        return createDefaultVpc;
    }
}
