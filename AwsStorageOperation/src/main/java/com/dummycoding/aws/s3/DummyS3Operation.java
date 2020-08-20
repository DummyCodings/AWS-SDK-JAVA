/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.aws.s3;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.AccessControlList;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.CreateBucketRequest;
import com.amazonaws.services.s3.model.DeleteBucketRequest;
import com.amazonaws.services.s3.model.Grantee;
import com.amazonaws.services.s3.model.GroupGrantee;
import com.amazonaws.services.s3.model.ListBucketsRequest;
import com.amazonaws.services.s3.model.Owner;
import com.amazonaws.services.s3.model.Permission;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.dummycoding.ec2clients.DummyAwsClient;
import java.util.Date;
import java.util.List;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class DummyS3Operation {

    public static void main(String[] args) throws Exception {
        AmazonS3 s3Client = DummyAwsClient.getS3Client();

        //creating Bucket
        Bucket createBucket = createBucket(s3Client);
        String name = createBucket.getName();
        System.out.println("name = " + name);
        Owner owner = createBucket.getOwner();
        String id = owner.getId();
        System.out.println("id = " + id);
        String displayName = owner.getDisplayName();
        System.out.println("displayName = " + displayName);
        Date creationDate = createBucket.getCreationDate();
        System.out.println("creationDate = " + creationDate);

        //listofBuckets
//        List<Bucket> listBucket = listBucket(s3Client);
        //Delete Buckets
//        deleteBucket(s3Client);
    }

    public static Bucket createBucket(AmazonS3 s3Client) throws Exception {
        CreateBucketRequest bucketRequest = new CreateBucketRequest("dummyBucket");
//        AccessControlList accessControlList = new AccessControlList();
//        Owner owner=new Owner();
//        owner.setId("ownderId");
//        owner.setDisplayName("ownderDisplayname");
//        accessControlList.setOwner(owner);
//        accessControlList.grantPermission(GroupGrantee.LogDelivery, Permission.WriteAcp);
//        accessControlList.revokeAllPermissions(GroupGrantee.AllUsers);
//        bucketRequest.withAccessControlList(accessControlList);
        bucketRequest.withCannedAcl(CannedAccessControlList.PublicReadWrite);
        Bucket createBucket = s3Client.createBucket(bucketRequest);
        return createBucket;
    }

    public static List<Bucket> listBucket(AmazonS3 s3Client) throws Exception {
        ListBucketsRequest listBucketsRequest = new ListBucketsRequest();
        List<Bucket> listBuckets = s3Client.listBuckets(listBucketsRequest);
        return listBuckets;
    }

    public static void deleteBucket(AmazonS3 s3Client) throws Exception {
        DeleteBucketRequest deleteBucketRequest = new DeleteBucketRequest("dummyBucket");
        s3Client.deleteBucket(deleteBucketRequest);
    }
}
