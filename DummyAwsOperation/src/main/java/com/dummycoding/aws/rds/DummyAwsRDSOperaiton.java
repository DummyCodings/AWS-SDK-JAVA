/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.aws.rds;

import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.model.AuthorizeDBSecurityGroupIngressRequest;
import com.amazonaws.services.rds.model.CreateDBClusterRequest;
import com.amazonaws.services.rds.model.CreateDBInstanceRequest;
import com.amazonaws.services.rds.model.CreateDBSecurityGroupRequest;
import com.amazonaws.services.rds.model.CreateDBSubnetGroupRequest;
import com.amazonaws.services.rds.model.DBCluster;
import com.amazonaws.services.rds.model.DBInstance;
import com.amazonaws.services.rds.model.DBSecurityGroup;
import com.amazonaws.services.rds.model.DBSubnetGroup;
import com.amazonaws.services.rds.model.DeleteDBClusterRequest;
import com.amazonaws.services.rds.model.DeleteDBInstanceRequest;
import com.amazonaws.services.rds.model.DescribeDBClustersRequest;
import com.amazonaws.services.rds.model.DescribeDBClustersResult;
import com.amazonaws.services.rds.model.DescribeDBInstancesRequest;
import com.amazonaws.services.rds.model.DescribeDBInstancesResult;
import com.amazonaws.services.rds.model.ModifyDBInstanceRequest;
import com.dummycoding.aws.clients.DummyAwsRDSClient;
import java.util.List;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class DummyAwsRDSOperaiton {

    public static void main(String[] args) throws Exception {
        AmazonRDS awsRDSClient = DummyAwsRDSClient.getAwsRDSClient();
        //createDBSubnetGroup
        DBSubnetGroup createDBSubnetGroup = createDBSubnetGroup(awsRDSClient, "subnet-05733395fb84d9805", "subnet-09970436bfeab487e");
        String dbSubnetGroupName = createDBSubnetGroup.getDBSubnetGroupName();
        System.out.println("##### Creating DB Subnet Group Created--->");
        //Creating DB Security Group
        DBSecurityGroup createDBSecurityGroup = createDBSecurityGroup(awsRDSClient, "dummyDBSecurityGroup");
        System.out.println("##### Creating DB Security Group Created--->");
        //create Db Cluster
        DBCluster createDBCluster = createDBCluster(awsRDSClient, "dummyauroracluster", "dummyaurorasubnetgroup", "sg-0789a3ad0ab4cd90c");
        System.out.println("##### Creating DBCluster Created--->");
        //creating aws Aurora Db instances
        DBInstance createRDSDbInstance = createRDSDbInstance(awsRDSClient, "dummyaurorainstance", "dummyauroracluster", "dummyaurorasubnetgroup", "sg-0789a3ad0ab4cd90c");
        System.out.println("##### Creating RDSDbInstance Created--->");
        //get aws auora db cluster details
        DescribeDBClustersResult describeDBcluster = describeDBcluster(awsRDSClient, "dummyauroracluster");
        List<DBCluster> dbClusters = describeDBcluster.getDBClusters();
        for (DBCluster dbCluster : dbClusters) {
            String endpoint = dbCluster.getEndpoint();
            System.out.println("endpoint = " + endpoint);
        }
        //get aws aurora db instances details
        DescribeDBInstancesResult describeRDSInstances = describeRDSInstances(awsRDSClient, "dummyaurorainstance");
        List<DBInstance> dbInstances = describeRDSInstances.getDBInstances();

        //crud Operation
        //Delete aws Aurora db instances
        deleteRdsInstances(awsRDSClient, "dummyaurorainstance");
        //Delete Aws Aurora DB Cluster
        deleteRdsDBCluster(awsRDSClient, "dummyauroracluster");
    }

    public static DBCluster createDBCluster(AmazonRDS awsRDSClient, String dBInstanceIdentifier, String subnetGroupName, String vpcSecurityGroupId) throws Exception {
        System.out.println("##### Creating DBCluster Creating--->");
        CreateDBClusterRequest createDBClusterRequest = new CreateDBClusterRequest();
        createDBClusterRequest.withDBClusterIdentifier(dBInstanceIdentifier);
        createDBClusterRequest.withDatabaseName("dummy");
        createDBClusterRequest.withMasterUsername("masterdummy");
        createDBClusterRequest.withMasterUserPassword("masterdummy123$$");
        createDBClusterRequest.withEngine("aurora");
        createDBClusterRequest.withEngineMode("provisioned");
        createDBClusterRequest.withEngineVersion("5.6.10a");
        createDBClusterRequest.withDBSubnetGroupName(subnetGroupName);
        createDBClusterRequest.withVpcSecurityGroupIds(vpcSecurityGroupId);
        DBCluster createDBCluster = awsRDSClient.createDBCluster(createDBClusterRequest);

        //Checking Until DB Cluster Create
        checkDBclusterCreatingStatus(awsRDSClient, dBInstanceIdentifier);
        return createDBCluster;
    }

    public static void checkDBclusterCreatingStatus(AmazonRDS awsRDSClient, String dbClusterIdentifier) throws Exception {
        System.out.println("##### Creating DBCluster Creating--->");
        DescribeDBClustersResult describeDBcluster = describeDBcluster(awsRDSClient, dbClusterIdentifier);
        String dbClusterStatus = describeDBcluster.getDBClusters().get(0).getStatus();
        System.out.println("##### Current DBCluster Creating Status = " + dbClusterStatus);
        if (!dbClusterStatus.equalsIgnoreCase("available")) {
            Thread.sleep(10000);
            checkDBclusterCreatingStatus(awsRDSClient, dbClusterIdentifier);
        }
    }

    public static DescribeDBClustersResult describeDBcluster(AmazonRDS awsRDSClient, String dbClusterIdentifier) throws Exception {
        DescribeDBClustersRequest describeDBClustersRequest = new DescribeDBClustersRequest();
        describeDBClustersRequest.withDBClusterIdentifier(dbClusterIdentifier);
        DescribeDBClustersResult describeDBClusters = awsRDSClient.describeDBClusters(describeDBClustersRequest);
        return describeDBClusters;
    }

    public static DBSubnetGroup createDBSubnetGroup(AmazonRDS awsRDSClient, String... subnetId) throws Exception {
        System.out.println("##### Creating DB Subnet Group Inprogress--->");
        CreateDBSubnetGroupRequest dBSubnetGroupRequest = new CreateDBSubnetGroupRequest();
        dBSubnetGroupRequest.withSubnetIds(subnetId);
        dBSubnetGroupRequest.withDBSubnetGroupName("dummyaurorasubnetgroup");
        dBSubnetGroupRequest.withDBSubnetGroupDescription("For Testing Purpose Only");
        DBSubnetGroup createDBSubnetGroup = awsRDSClient.createDBSubnetGroup(dBSubnetGroupRequest);
        if (!createDBSubnetGroup.getSubnetGroupStatus().equalsIgnoreCase("Complete")) {
            Thread.sleep(5000);
            createDBSubnetGroup(awsRDSClient, subnetId);
        }
        return createDBSubnetGroup;
    }

    public static DBInstance createRDSDbInstance(AmazonRDS awsRDSClient, String dBInstanceIdentifier, String dbClusterIdentifier, String subnetGroupName, String vpcSecurityGroupID) throws Exception {
        System.out.println("##### Creating RDSDbInstance Inprogress--->");
        CreateDBInstanceRequest createDBInstanceRequest = new CreateDBInstanceRequest();
        createDBInstanceRequest.withEngine("aurora");
        createDBInstanceRequest.withEngineVersion("5.6.10a");
        createDBInstanceRequest.withAutoMinorVersionUpgrade(false);
        createDBInstanceRequest.withDBClusterIdentifier(dbClusterIdentifier);
        createDBInstanceRequest.withPubliclyAccessible(Boolean.TRUE);
        createDBInstanceRequest.withDBInstanceIdentifier(dBInstanceIdentifier);
        createDBInstanceRequest.withDBInstanceClass("db.r5.large");
        createDBInstanceRequest.withDBSubnetGroupName(subnetGroupName);
//        createDBInstanceRequest.withVpcSecurityGroupIds(vpcSecurityGroupID);
        DBInstance createDBInstance = awsRDSClient.createDBInstance(createDBInstanceRequest);
        //waiting for RDS Creation
        checkDBInstanceCreatingStatus(awsRDSClient, dBInstanceIdentifier);
//        modifyingRdsDBInstance(awsRDSClient, dBInstanceIdentifier, dbSecurityGroup);
//        checkDBInstanceCreatingStatus(awsRDSClient, dBInstanceIdentifier);
        return createDBInstance;
    }

    public static DBInstance modifyingRdsDBInstance(AmazonRDS awsRDSClient, String dBInstanceIdentifier, String dbSecurityGroup) throws Exception {
        ModifyDBInstanceRequest bInstanceRequest = new ModifyDBInstanceRequest();
        bInstanceRequest.withDBInstanceIdentifier(dBInstanceIdentifier);
        bInstanceRequest.withDBSecurityGroups(dbSecurityGroup);
        DBInstance modifyDBInstance = awsRDSClient.modifyDBInstance(bInstanceRequest);
        return modifyDBInstance;
    }

    public static void checkDBInstanceCreatingStatus(AmazonRDS awsRDSClient, String dBInstanceIdentifier) throws Exception {
        System.out.println("##### Creating RDSDbInstance Inprogress--->");
        boolean isReallyCreatedDbInstances = true;
        DescribeDBInstancesResult describeRDSInstances = describeRDSInstances(awsRDSClient, dBInstanceIdentifier);
        List<DBInstance> dbInstances = describeRDSInstances.getDBInstances();
        for (DBInstance dbInstance : dbInstances) {
            String dbInstanceArn = dbInstance.getDBInstanceArn();
            String dbInstanceStatus = dbInstance.getDBInstanceStatus();
            System.out.println("##### " + dbInstanceArn + ", dbInstance Creation Status = " + dbInstanceStatus);
            if (!dbInstance.getDBInstanceStatus().equalsIgnoreCase("available")) {
                isReallyCreatedDbInstances = false;
            }
        }
        if (!isReallyCreatedDbInstances) {
            Thread.sleep(30000);
            checkDBInstanceCreatingStatus(awsRDSClient, dBInstanceIdentifier);
        }
    }

    public static DescribeDBInstancesResult describeRDSInstances(AmazonRDS awsRDSClient, String dBInstanceIdentifier) throws Exception {
        DescribeDBInstancesRequest describeDBInstancesRequest = new DescribeDBInstancesRequest();
        describeDBInstancesRequest.withDBInstanceIdentifier(dBInstanceIdentifier);
        DescribeDBInstancesResult describeDBInstances = awsRDSClient.describeDBInstances(describeDBInstancesRequest);
        return describeDBInstances;
    }

    public static DBInstance deleteRdsInstances(AmazonRDS awsRDSClient, String instanceIdentifier) throws Exception {
        DeleteDBInstanceRequest deleteDBInstanceRequest = new DeleteDBInstanceRequest();
        deleteDBInstanceRequest.withDBInstanceIdentifier(instanceIdentifier);
        DBInstance deleteDBInstance = awsRDSClient.deleteDBInstance(deleteDBInstanceRequest);
        return deleteDBInstance;
    }

    public static DBCluster deleteRdsDBCluster(AmazonRDS awsRDSClient, String clusterIdentifier) throws Exception {
        DeleteDBClusterRequest deleteDBClusterRequest = new DeleteDBClusterRequest();
        deleteDBClusterRequest.withDBClusterIdentifier(clusterIdentifier);
        deleteDBClusterRequest.withSkipFinalSnapshot(Boolean.TRUE);
        DBCluster deleteDBCluster = awsRDSClient.deleteDBCluster(deleteDBClusterRequest);
        String status = deleteDBCluster.getStatus();
        System.out.println("status = " + status);
        checkingRDSDBclusterDeletion(awsRDSClient, clusterIdentifier);
        return deleteDBCluster;
    }

    public static void checkingRDSDBclusterDeletion(AmazonRDS awsRDSClient, String clusterIdentifier) throws Exception {
        System.out.println("#####  DBCluster Deleting--->");
        DescribeDBClustersResult describeDBcluster = describeDBcluster(awsRDSClient, clusterIdentifier);
        String dbClusterStatus = describeDBcluster.getDBClusters().get(0).getStatus();
        System.out.println("##### Current DBCluster Deleting Status = " + dbClusterStatus);
        if (!dbClusterStatus.equalsIgnoreCase("Deleting")) {
            Thread.sleep(5000);
            checkDBclusterCreatingStatus(awsRDSClient, clusterIdentifier);
        }
    }

    public static DBSecurityGroup createDBSecurityGroup(AmazonRDS awsRDSClient, String dbSecurityGroupName) throws Exception {
        System.out.println("##### Creating DB Security Group--->");
        CreateDBSecurityGroupRequest bSecurityGroupRequest = new CreateDBSecurityGroupRequest();
        bSecurityGroupRequest.withDBSecurityGroupName("dummyDBSecurityGroup");
        bSecurityGroupRequest.withDBSecurityGroupDescription("for Testing Purpose only");
        DBSecurityGroup createDBSecurityGroup = awsRDSClient.createDBSecurityGroup(bSecurityGroupRequest);
        Thread.sleep(5000);
        System.out.println("##### Created DB Security Group--->");
        authorizeDBSecurityGroupIngress(awsRDSClient, dbSecurityGroupName);
        System.out.println("##### authorized DB SecurityGroupIngress--->");
        return createDBSecurityGroup;
    }

    public static DBSecurityGroup authorizeDBSecurityGroupIngress(AmazonRDS awsRDSClient, String dbSecurityGroupName) throws Exception {
        System.out.println("##### authorize DB SecurityGroupIngress--->");
        AuthorizeDBSecurityGroupIngressRequest dBSecurityGroupIngressRequest = new AuthorizeDBSecurityGroupIngressRequest();
        dBSecurityGroupIngressRequest.withCIDRIP("0.0.0.0/0");
        dBSecurityGroupIngressRequest.withDBSecurityGroupName(dbSecurityGroupName);
        DBSecurityGroup authorizeDBSecurityGroupIngress = awsRDSClient.authorizeDBSecurityGroupIngress(dBSecurityGroupIngressRequest);
        return authorizeDBSecurityGroupIngress;
    }
}
