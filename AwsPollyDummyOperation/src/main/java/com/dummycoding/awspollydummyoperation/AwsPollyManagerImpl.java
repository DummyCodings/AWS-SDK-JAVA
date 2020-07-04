/*
 * This is belong to Dummy Organization
 * 
 * 
 */
package com.dummycoding.awspollydummyoperation;

import java.io.InputStream;
import java.util.List;

import com.amazonaws.services.polly.AmazonPollyClient;
import com.amazonaws.services.polly.model.DescribeVoicesRequest;
import com.amazonaws.services.polly.model.DescribeVoicesResult;
import com.amazonaws.services.polly.model.GetLexiconRequest;
import com.amazonaws.services.polly.model.GetLexiconResult;
import com.amazonaws.services.polly.model.Lexicon;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.PutLexiconRequest;
import com.amazonaws.services.polly.model.PutLexiconResult;
import com.amazonaws.services.polly.model.SynthesizeSpeechRequest;
import com.amazonaws.services.polly.model.SynthesizeSpeechResult;
import com.amazonaws.services.polly.model.Voice;

/**
 *
 * @author KarthickRaj.R at Dummy Organization
 */
public class AwsPollyManagerImpl implements AwsPollyManager {

	/**
	 * 
	 */

	private String LEXICON_CONTENT = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
			+ "<lexicon version=\"1.0\" xmlns=\"http://www.w3.org/2005/01/pronunciation-lexicon\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
			+ "xsi:schemaLocation=\"http://www.w3.org/2005/01/pronunciation-lexicon http://www.w3.org/TR/2007/CR-pronunciation-lexicon-20071212/pls.xsd\" "
			+ "alphabet=\"ipa\" xml:lang=\"en-US\">" + "<lexeme><grapheme>test1</grapheme><alias>test2</alias></lexeme>"
			+ "</lexicon>";
	private String LEXICON_NAME = "SampleLexicon";
	private AmazonPollyClient amazonPollyClient;

	public AwsPollyManagerImpl() {
		// TODO Auto-generated constructor stub
	}

	public AwsPollyManagerImpl(AmazonPollyClient amazonPollyClient) {
		this.amazonPollyClient = amazonPollyClient;
	}

	@Override
	public List<Voice> describeVoices() throws Exception {
		DescribeVoicesRequest describeVoicesRequest = new DescribeVoicesRequest();
		DescribeVoicesResult describeVoices = amazonPollyClient.describeVoices(describeVoicesRequest);
		List<Voice> voices = describeVoices.getVoices();
		return voices;
	}

	@Override
	public InputStream synthesize(String text, OutputFormat format, Voice voice) throws Exception {
		SynthesizeSpeechRequest speechRequest = new SynthesizeSpeechRequest();
		speechRequest.withText(text).withVoiceId(voice.getId()).withOutputFormat(format);

		// response
		SynthesizeSpeechResult result = amazonPollyClient.synthesizeSpeech(speechRequest);
		InputStream audioStream = result.getAudioStream();
		return audioStream;
	}

	@Override
	public void putLexicon() throws Exception {
		PutLexiconRequest lexiconRequest = new PutLexiconRequest();
		lexiconRequest.withContent(LEXICON_CONTENT);
		lexiconRequest.withName(LEXICON_NAME);
		PutLexiconResult putLexiconResult = amazonPollyClient.putLexicon(lexiconRequest);
		System.out.println("putLexiconResult:" + putLexiconResult.getSdkResponseMetadata().toString());

	}

	@Override
	public void putLexicon(String lexiconContent, String lexiconXML) throws Exception {
		PutLexiconRequest lexiconRequest = new PutLexiconRequest();
		lexiconRequest.withContent(lexiconXML);
		lexiconRequest.withName(lexiconContent);
		PutLexiconResult putLexiconResult = amazonPollyClient.putLexicon(lexiconRequest);
		System.out.println("putLexiconResult:" + putLexiconResult.getSdkResponseMetadata().toString());
	}

	@Override
	public Lexicon getLexicon() throws Exception {
		GetLexiconRequest getLexiconRequest = new GetLexiconRequest();
		getLexiconRequest.withName(LEXICON_NAME);
		GetLexiconResult lexiconResult = amazonPollyClient.getLexicon(getLexiconRequest);
		Lexicon lexicon = lexiconResult.getLexicon();
		return lexicon;
	}

	@Override
	public Lexicon getLexicon(String lexiconName) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
