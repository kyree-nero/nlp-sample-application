package sample;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import sample.domain.SentenceInput;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class InstaLemmaIT {
	 @Autowired private TestRestTemplate restTemplate;
	 
	   @Test
	   public void testAbout() {
		  SentenceInput input = new SentenceInput();
		  //input.setText("I see the red dog run");
		  input.setText("In this Scene true madness comes into play. Once Ophelia meets Hamlet and speaks with him her love abandons her. Hamlet realizes that his mother and step father are aware of this love and might use this to end his threat. Hamlet must end their thoughts of using Ophelia to rid him of his condition.");
		  Assertions.assertNotNull(restTemplate);
		  List<List<List<String>>> message = this.restTemplate.postForObject("/instalemma", input, List.class);
	      //assertEquals("JUnit 5 and Spring Boot Example.", message);
	      for(List<List<String>> sentence:message) {
	    	  System.out.println("--------------START SENTENCE;-----------------");
	    	  
	    	  for(List<String> word: sentence) {
	    		  System.out.println("word: "+word.get(0)+" lemma: " + word.get(1));
	    	  }
	    	  
	    	  System.out.println("--------------END SENTENCE------------------");
	      }
	      System.out.println("x");
	      Assertions.assertEquals(4, message.size());
	      
	   }
	   
}
