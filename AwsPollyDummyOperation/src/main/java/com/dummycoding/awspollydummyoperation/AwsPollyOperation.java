/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.awspollydummyoperation;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.Lexicon;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.Voice;
import java.io.InputStream;
import javazoom.jl.player.advanced.AdvancedPlayer;
import javazoom.jl.player.advanced.PlaybackEvent;
import javazoom.jl.player.advanced.PlaybackListener;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class AwsPollyOperation {

    public static String SAMPLE = "Congratulations. You have successfully built this working demo \r\n"
            + "	of Amazon Polly in Java. Have fun building voice enabled apps with Amazon Polly (that's me!), and always \r\n"
            + "	look at the AWS website for tips and tricks on using Amazon Polly and other great services from AWS";

    public static void main(String args[]) throws Exception {
//        AmazonPollyClient amazonPollyClient = new AmazonPollyClient();
//        BasicAWSCredentials awsCreds = new BasicAWSCredentials("xxxxxxxacceskey", "yyyyySecretkey");
//        AmazonPollyClient amazonPollyClient = new AmazonPollyClient(new AWSStaticCredentialsProvider(awsCreds));
//        Region.getRegion(Regions.US_EAST_2);
        EnvironmentVariableCredentialsProvider credentialsProvider = new EnvironmentVariableCredentialsProvider();
        AmazonPollyClient amazonPollyClient = new AmazonPollyClient(new AWSStaticCredentialsProvider(credentialsProvider.getCredentials()));
        AwsPollyManager awsPollyManager = new AwsPollyManagerImpl(amazonPollyClient);
        awsPollyManager.putLexicon();
        Lexicon lexicon = awsPollyManager.getLexicon();
        String content = lexicon.getContent();
        System.out.println("Content:" + content);
//        synthesisExecute(awsPollyManager);
    }

    public static void synthesisExecute(AwsPollyManager awsPollyManager) throws Exception {
        Voice voice = new Voice();
        voice.setId("Salli");
        voice.setLanguageCode("en-US");
        InputStream speechStream = awsPollyManager.synthesize(SAMPLE, OutputFormat.Mp3, voice);

        // create an MP3 player
        AdvancedPlayer player = new AdvancedPlayer(speechStream,
                javazoom.jl.player.FactoryRegistry.systemRegistry().createAudioDevice());

        player.setPlayBackListener(new PlaybackListener() {
            @Override
            public void playbackStarted(PlaybackEvent evt) {
                System.out.println("Playback started");
                System.out.println(SAMPLE);
            }

            @Override
            public void playbackFinished(PlaybackEvent evt) {
                System.out.println("Playback finished");
            }
        });

        // play it!
        player.play();

    }
}
