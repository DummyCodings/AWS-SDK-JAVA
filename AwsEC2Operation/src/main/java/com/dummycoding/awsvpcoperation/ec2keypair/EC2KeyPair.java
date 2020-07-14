/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.awsvpcoperation.ec2keypair;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateKeyPairRequest;
import com.amazonaws.services.ec2.model.CreateKeyPairResult;
import com.amazonaws.services.ec2.model.DeleteKeyPairRequest;
import com.amazonaws.services.ec2.model.DeleteKeyPairResult;
import com.amazonaws.services.ec2.model.DescribeKeyPairsRequest;
import com.amazonaws.services.ec2.model.DescribeKeyPairsResult;
import com.amazonaws.services.ec2.model.KeyPair;
import com.amazonaws.services.ec2.model.KeyPairInfo;
import com.amazonaws.services.ec2.model.Tag;
import com.dummycoding.awsvpcoperation.ec2clients.DummyEc2Client;
import java.util.List;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class EC2KeyPair {

    public static void main(String[] args) throws Exception {
        AmazonEC2 eC2Client = DummyEc2Client.getEC2Client();

        //create key Pair
        CreateKeyPairResult createEc2KeyPair = createEc2KeyPair(eC2Client);
        KeyPair keyPair = createEc2KeyPair.getKeyPair();
        String keyFingerprint = keyPair.getKeyFingerprint();

        //get key pair
        DescribeKeyPairsResult ec2KeyPair = getEc2KeyPair(eC2Client);
        List<KeyPairInfo> keyPairs = ec2KeyPair.getKeyPairs();
        for (KeyPairInfo getKeypair : keyPairs) {
            String keyName = getKeypair.getKeyName();
            String keyFingerprint1 = getKeypair.getKeyFingerprint();
            String keyPairId = getKeypair.getKeyPairId();
            List<Tag> tags = getKeypair.getTags();
        }
        DeleteKeyPairResult deleteEc2KeyPair = deleteEc2KeyPair(eC2Client);
    }

    public static CreateKeyPairResult createEc2KeyPair(AmazonEC2 eC2Client) {
        CreateKeyPairRequest createKeyPairRequest = new CreateKeyPairRequest();
        createKeyPairRequest.withKeyName("dummy-keypair");
        return eC2Client.createKeyPair(createKeyPairRequest);
    }

    public static DescribeKeyPairsResult getEc2KeyPair(AmazonEC2 eC2Client) {
        DescribeKeyPairsRequest describeKeyPairsRequest = new DescribeKeyPairsRequest();
        describeKeyPairsRequest.withKeyNames("keypair-name");
        describeKeyPairsRequest.withKeyPairIds("keypair-id");
        return eC2Client.describeKeyPairs(describeKeyPairsRequest);
    }
    
    public static DeleteKeyPairResult deleteEc2KeyPair(AmazonEC2 eC2Client) {
        DeleteKeyPairRequest deleteKeyPairRequest = new DeleteKeyPairRequest();
        deleteKeyPairRequest.withKeyPairId("keypairid");
        deleteKeyPairRequest.withKeyName("keypairname");
        return eC2Client.deleteKeyPair(deleteKeyPairRequest);
    }

}
