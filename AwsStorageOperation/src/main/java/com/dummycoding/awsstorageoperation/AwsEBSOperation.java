/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.awsstorageoperation;

import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.CreateVolumeRequest;
import com.amazonaws.services.ec2.model.CreateVolumeResult;
import com.amazonaws.services.ec2.model.DescribeVolumesRequest;
import com.amazonaws.services.ec2.model.DescribeVolumesResult;
import com.amazonaws.services.ec2.model.Filter;
import com.amazonaws.services.ec2.model.Tag;
import com.amazonaws.services.ec2.model.Volume;
import com.amazonaws.services.ec2.model.VolumeAttachment;
import com.amazonaws.services.ec2.model.VolumeType;
import com.dummycoding.ec2clients.DummyAwsClient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class AwsEBSOperation {

    public static void main(String[] args) throws Exception {
        AmazonEC2 eC2Client = DummyAwsClient.getEC2Client();
        CreateVolumeResult createVolume = createVolume(eC2Client);
        Volume volume = createVolume.getVolume();
        String volumeId = volume.getVolumeId();
        System.out.println("volumeId = " + volumeId);
        String volumeType = volume.getVolumeType();
        System.out.println("volumeType = " + volumeType);
        String state = volume.getState();
        System.out.println("state = " + state);
        String snapshotId = volume.getSnapshotId();
        System.out.println("snapshotId = " + snapshotId);
        Integer size = volume.getSize();
        System.out.println("size = " + size);
        String outpostArn = volume.getOutpostArn();
        System.out.println("outpostArn = " + outpostArn);
        Boolean multiAttachEnabled = volume.getMultiAttachEnabled();
        System.out.println("multiAttachEnabled = " + multiAttachEnabled);
        String kmsKeyId = volume.getKmsKeyId();
        System.out.println("kmsKeyId = " + kmsKeyId);
        Integer iops = volume.getIops();
        System.out.println("iops = " + iops);
        Boolean fastRestored = volume.getFastRestored();
        System.out.println("fastRestored = " + fastRestored);
        Boolean encrypted = volume.getEncrypted();
        System.out.println("encrypted = " + encrypted);
        Date createTime = volume.getCreateTime();
        System.out.println("createTime = " + createTime);
        String availabilityZone = volume.getAvailabilityZone();
        System.out.println("availabilityZone = " + availabilityZone);
        List<Tag> tags = volume.getTags();
        List<VolumeAttachment> attachments = volume.getAttachments();
        for (VolumeAttachment attachment : attachments) {
            String state1 = attachment.getState();
            System.out.println("state1 = " + state1);
            String instanceId = attachment.getInstanceId();
            System.out.println("instanceId = " + instanceId);
            String device = attachment.getDevice();
            System.out.println("device = " + device);
            Boolean deleteOnTermination = attachment.getDeleteOnTermination();
            System.out.println("deleteOnTermination = " + deleteOnTermination);
            Date attachTime = attachment.getAttachTime();
            System.out.println("attachTime = " + attachTime);
        }

    }

    public static CreateVolumeResult createVolume(AmazonEC2 eC2Client) {
        CreateVolumeRequest createVolumeRequest = new CreateVolumeRequest();
        createVolumeRequest.withAvailabilityZone("us-east-1");
//        createVolumeRequest.withEncrypted(Boolean.TRUE);
//        createVolumeRequest.withKmsKeyId(kmsKeyId);

//        createVolumeRequest.withIops(3200);
//        createVolumeRequest.withVolumeType(VolumeType.Io1);
//        createVolumeRequest.withVolumeType(VolumeType.Gp2);
//        createVolumeRequest.withVolumeType(VolumeType.Sc1);
//        createVolumeRequest.withVolumeType(VolumeType.St1);
//        createVolumeRequest.withVolumeType(VolumeType.Standard);
//        createVolumeRequest.withSnapshotId("snapshotId");
        createVolumeRequest.withMultiAttachEnabled(Boolean.TRUE);

//        TagSpecifications  tagSpecifications=new TagSpecifications();
//        createVolumeRequest.withTagSpecifications(tagSpecifications);
        CreateVolumeResult createVolume = eC2Client.createVolume(createVolumeRequest);
        return createVolume;
    }

    public static DescribeVolumesResult getVolumes(AmazonEC2 eC2Client) throws Exception {
        DescribeVolumesRequest describeVolumesRequest = new DescribeVolumesRequest();
        describeVolumesRequest.withVolumeIds("");
        Collection<Filter> filters = new ArrayList<>();

        Filter attachMentTimeFilter = new Filter();
        attachMentTimeFilter.withName("attachment.attach-time");
        String attachMentTimeFilterValues[] = new String[]{"YYYY-MM-DDTHH:MM:SS.SSSZ"};
        attachMentTimeFilter.withValues(attachMentTimeFilterValues);

        Filter delete_on_termination = new Filter();
        delete_on_termination.withName("attachment.delete-on-termination");
        String delete_on_terminationFilterValues[] = new String[]{"true"};//false
        delete_on_termination.withValues(delete_on_terminationFilterValues);

        describeVolumesRequest.withFilters(filters);
        DescribeVolumesResult describeVolumes = eC2Client.describeVolumes(describeVolumesRequest);
        return describeVolumes;
    }
}
