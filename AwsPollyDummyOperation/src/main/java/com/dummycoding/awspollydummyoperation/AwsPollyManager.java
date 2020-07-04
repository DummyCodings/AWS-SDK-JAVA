/**
 * 
 */
package com.dummycoding.awspollydummyoperation;

import java.io.InputStream;
import java.util.List;

import com.amazonaws.services.polly.model.Lexicon;
import com.amazonaws.services.polly.model.OutputFormat;
import com.amazonaws.services.polly.model.Voice;

/**
 * @author KarthickRaj.R
 *
 *         at Dummy Orgnaization
 */
public interface AwsPollyManager {

	public List<Voice> describeVoices() throws Exception;

	public InputStream synthesize(String text, OutputFormat format, Voice voice) throws Exception;

	public void putLexicon() throws Exception;

	public void putLexicon(String lexiconContent, String lexiconXML) throws Exception;

	public Lexicon getLexicon() throws Exception;

	public Lexicon getLexicon(String lexiconName) throws Exception;
}
