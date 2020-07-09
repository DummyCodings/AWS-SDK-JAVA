/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.awsvpcoperation.internetgateway;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateEgressOnlyInternetGatewayRequest;
import com.amazonaws.services.ec2.model.CreateEgressOnlyInternetGatewayResult;
import com.amazonaws.services.ec2.model.DeleteEgressOnlyInternetGatewayRequest;
import com.amazonaws.services.ec2.model.DeleteEgressOnlyInternetGatewayResult;
import com.amazonaws.services.ec2.model.DescribeEgressOnlyInternetGatewaysRequest;
import com.amazonaws.services.ec2.model.DescribeEgressOnlyInternetGatewaysResult;
import com.amazonaws.services.ec2.model.Filter;
import com.dummycoding.awsvpcoperation.ec2clients.DummyEc2Client;
import java.util.List;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class EgressOnlyInternetGateway {

    public static void main(String[] args) throws Exception {
        AmazonEC2 eC2Client = DummyEc2Client.getEC2Client();

        //create egress only internetgateway
        CreateEgressOnlyInternetGatewayResult createEgressOnlyInternetGateway = createEgressOnlyInternetGateway(eC2Client);
        com.amazonaws.services.ec2.model.EgressOnlyInternetGateway egressOnlyInternetGateway = createEgressOnlyInternetGateway.getEgressOnlyInternetGateway();
        String egressOnlyInternetGatewayId = egressOnlyInternetGateway.getEgressOnlyInternetGatewayId();
        System.out.println("egressOnlyInternetGatewayId = " + egressOnlyInternetGatewayId);

        //get egress only internetgateway
        DescribeEgressOnlyInternetGatewaysResult egressOnlyInternetGateway1 = getEgressOnlyInternetGateway(eC2Client);
        List<com.amazonaws.services.ec2.model.EgressOnlyInternetGateway> egressOnlyInternetGateways = egressOnlyInternetGateway1.getEgressOnlyInternetGateways();
        for (com.amazonaws.services.ec2.model.EgressOnlyInternetGateway egressOnlyInternetGateway2 : egressOnlyInternetGateways) {
            String egressOnlyInternetGatewayId1 = egressOnlyInternetGateway2.getEgressOnlyInternetGatewayId();
        }
        
        //delete egress only internetgateway
        DeleteEgressOnlyInternetGatewayResult deleteEgressOnlyInternetGateway = deleteEgressOnlyInternetGateway(eC2Client);

    }

    public static CreateEgressOnlyInternetGatewayResult createEgressOnlyInternetGateway(AmazonEC2 eC2Client) {
        CreateEgressOnlyInternetGatewayRequest createEgressOnlyInternetGatewayRequest = new CreateEgressOnlyInternetGatewayRequest();
        createEgressOnlyInternetGatewayRequest.withVpcId("vpc-id");
        return eC2Client.createEgressOnlyInternetGateway(createEgressOnlyInternetGatewayRequest);
    }

    public static DescribeEgressOnlyInternetGatewaysResult getEgressOnlyInternetGateway(AmazonEC2 eC2Client) {
        DescribeEgressOnlyInternetGatewaysRequest describeEgressOnlyInternetGatewaysRequest = new DescribeEgressOnlyInternetGatewaysRequest();
        describeEgressOnlyInternetGatewaysRequest.withEgressOnlyInternetGatewayIds("egonlygateway-id");
        Filter filter = new Filter();
        filter.withName("tag:team");
        filter.withValues("devteam");
        describeEgressOnlyInternetGatewaysRequest.withFilters(filter);
        return eC2Client.describeEgressOnlyInternetGateways(describeEgressOnlyInternetGatewaysRequest);
    }

    public static DeleteEgressOnlyInternetGatewayResult deleteEgressOnlyInternetGateway(AmazonEC2 eC2Client) {
        DeleteEgressOnlyInternetGatewayRequest deleteEgressOnlyInternetGatewayRequest = new DeleteEgressOnlyInternetGatewayRequest();
        deleteEgressOnlyInternetGatewayRequest.withEgressOnlyInternetGatewayId("eg-gateway-id");
        return eC2Client.deleteEgressOnlyInternetGateway(deleteEgressOnlyInternetGatewayRequest);
    }

}
