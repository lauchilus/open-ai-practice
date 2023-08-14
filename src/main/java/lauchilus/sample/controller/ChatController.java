package lauchilus.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import lauchilus.sample.service.ChatGPTClientService;

@RestController
@RequestMapping(value = "openai")
public class ChatController {
  
  @Value("${openai.api.chat.default.role}")
  private String defaultRole;
  
  @Autowired
  private ChatGPTClientService chatGPTClientService;
    
  @GetMapping("chat/generate")
    public String chatGptRequest(@RequestBody RequestPrompt request) {
      
	  String prompt = generatePrompt(request);
      final OpenAiService service = chatGPTClientService.getOpenAiService();
      final ChatCompletionRequest chatRequest = chatGPTClientService.getChatCompletionRequest(List.of(new ChatMessage(defaultRole, prompt)));
      
    return service.createChatCompletion(chatRequest).getChoices().get(0).getMessage().getContent();
    }
  
  private String generatePrompt(RequestPrompt request) {
	  String prompt = "Generate data to populate a table in the "
	  		+ request.type()
	  		+ " database,with "
	  		+ request.rows()
	  		+ "rows, named "
	  		+ request.name()
	  		+ "which contains the following columns: "
	  		+ request.columns();
	  System.out.println(prompt);
	return prompt;
	  
  }
  
}