/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.awsvpcoperation.internetgateway;

import com.amazonaws.ResponseMetadata;
import com.amazonaws.http.SdkHttpMetadata;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.AttachInternetGatewayRequest;
import com.amazonaws.services.ec2.model.AttachInternetGatewayResult;
import com.amazonaws.services.ec2.model.CreateInternetGatewayRequest;
import com.amazonaws.services.ec2.model.CreateInternetGatewayResult;
import com.amazonaws.services.ec2.model.DeleteInternetGatewayRequest;
import com.amazonaws.services.ec2.model.DeleteInternetGatewayResult;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysRequest;
import com.amazonaws.services.ec2.model.DescribeInternetGatewaysResult;
import com.amazonaws.services.ec2.model.DetachInternetGatewayRequest;
import com.amazonaws.services.ec2.model.DetachInternetGatewayResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.InternetGateway;
import com.amazonaws.services.ec2.model.ResourceType;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.TagSpecification;
import com.dummycoding.awsvpcoperation.ec2clients.DummyEc2Client;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class InternetGatewayOperation {

    public static void main(String[] args) throws Exception {
        AmazonEC2 eC2Client = DummyEc2Client.getEC2Client();

        //creating internetGateway
//        CreateInternetGatewayResult createInternetGateWay = createInternetGateWay(eC2Client);
//        InternetGateway internetGateway = createInternetGateWay.getInternetGateway();
//        String internetGatewayId = internetGateway.getInternetGatewayId();
//        System.out.println("internetGatewayId = " + internetGatewayId);
        //getting internetGateway
//        DescribeInternetGatewaysResult internetGateWay = getInternetGateWay(eC2Client);
//        List<InternetGateway> internetGateways = internetGateWay.getInternetGateways();
//        for (InternetGateway internetGateway : internetGateways) {
//            String internetGatewayId = internetGateway.getInternetGatewayId();
//            System.out.println("internetGatewayId = " + internetGatewayId);
//            List<Tag> tags = internetGateway.getTags();
//            for (Tag tag : tags) {
//                String key = tag.getKey();
//                System.out.println("key = " + key);
//                String value = tag.getValue();
//                System.out.println("value = " + value);
//            }
//        }
        //attach InterngetGateway
        AttachInternetGatewayResult attachInternetGateWay = attachInternetGateWay(eC2Client);
        ResponseMetadata sdkResponseMetadata = attachInternetGateWay.getSdkResponseMetadata();
        String requestId = sdkResponseMetadata.getRequestId();
        //detach internetGateway
//        DetachInternetGatewayResult detachInternetGateWay = detachInternetGateWay(eC2Client);
        //delete internetGateway
//        DeleteInternetGatewayResult deleteInternetGateWay = deleteInternetGateWay(eC2Client);
//        ResponseMetadata sdkResponseMetadata = deleteInternetGateWay.getSdkResponseMetadata();
//        SdkHttpMetadata sdkHttpMetadata = deleteInternetGateWay.getSdkHttpMetadata();
//        int httpStatusCode = sdkHttpMetadata.getHttpStatusCode();

    }

    public static CreateInternetGatewayResult createInternetGateWay(AmazonEC2 eC2Client) throws Exception {
        CreateInternetGatewayRequest gatewayRequest = new CreateInternetGatewayRequest();
        Collection<TagSpecification> tagSpecifications = new ArrayList<>();
        TagSpecification specification = new TagSpecification();
        specification.withResourceType(ResourceType.InternetGateway);
        Tag tag = new Tag();
        tag.setKey("name");
        tag.setValue("Dummy-Internet-GateWay");
        specification.withTags(tag);
        tagSpecifications.add(specification);
        gatewayRequest.withTagSpecifications(tagSpecifications);
        return eC2Client.createInternetGateway(gatewayRequest);
    }

    public static DescribeInternetGatewaysResult getInternetGateWay(AmazonEC2 eC2Client) throws Exception {
        DescribeInternetGatewaysRequest describeInternetGatewaysRequest = new DescribeInternetGatewaysRequest();
        describeInternetGatewaysRequest.withInternetGatewayIds("igw-0338a2305ef0ba76b");
//        Filter filter = new Filter();
//        Collection<String> filterValues = new ArrayList<>();
//        filter.withName("attachment.state");
//        filterValues.add("available");
//        filter.withName("attachment.vpc-id");
//        filterValues.add("vpc-a51822df");
//        filter.withName("internet-gateway-id");
//        filterValues.add("igw-0338a2305ef0ba76b");
//        filter.withName("owner-id");
//        filterValues.add("781405961925");
//        filter.withName("tag:Name");
//        filterValues.add("ironman-gatway");
//        filter.withValues(filterValues);
//        describeInternetGatewaysRequest.withFilters(filter);
        return eC2Client.describeInternetGateways(describeInternetGatewaysRequest);
    }

    public static AttachInternetGatewayResult attachInternetGateWay(AmazonEC2 eC2Client) throws Exception {
        AttachInternetGatewayRequest attachInternetGatewayRequest = new AttachInternetGatewayRequest();
        attachInternetGatewayRequest.withInternetGatewayId("internet-gateway-id");
        attachInternetGatewayRequest.withVpcId("vpc-a51822df");
        return eC2Client.attachInternetGateway(attachInternetGatewayRequest);
    }

    public static DetachInternetGatewayResult detachInternetGateWay(AmazonEC2 eC2Client) throws Exception {
        DetachInternetGatewayRequest detachInternetGatewayRequest = new DetachInternetGatewayRequest();
        detachInternetGatewayRequest.withInternetGatewayId("internet-gateway-id");
        detachInternetGatewayRequest.withVpcId("vpc-a51822df");
        return eC2Client.detachInternetGateway(detachInternetGatewayRequest);
    }

    public static DeleteInternetGatewayResult deleteInternetGateWay(AmazonEC2 eC2Client) throws Exception {
        DeleteInternetGatewayRequest deleteInternetGatewayRequest = new DeleteInternetGatewayRequest();
        deleteInternetGatewayRequest.withInternetGatewayId("internet-gateway-id");
        return eC2Client.deleteInternetGateway(deleteInternetGatewayRequest);
    }
}
