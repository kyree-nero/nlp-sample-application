package sample.mvc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import edu.stanford.nlp.ling.CoreAnnotations.LemmaAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.SentencesAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TextAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;
import sample.domain.SentenceInput;

@RestController
public class InstaLemmaController {
	@PostMapping(path = "/instalemma", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public List<List<String[]>> instaLemma(@RequestBody SentenceInput sample, Model model){
		Properties properties = new Properties();
		properties.put("annotators", "tokenize, ssplit, pos, lemma");
		StanfordCoreNLP pipeLine = new StanfordCoreNLP(properties);
		Annotation document = pipeLine.process(sample.getText());
		
		List<List<String[]>> result = new ArrayList<List<String[]>>();
		
		for(CoreMap sentence : document.get(SentencesAnnotation.class)) {
			List<String[]> ls = new ArrayList<String[]>();
			for(CoreLabel token : sentence.get(TokensAnnotation.class)) {
				String word = token.get(TextAnnotation.class);
				String lemma = token.get(LemmaAnnotation.class);
				
				ls.add(new String[] {word, lemma});
				
			}
			result.add(ls);
		}
		
		return result;
	
	
		
	}
}
