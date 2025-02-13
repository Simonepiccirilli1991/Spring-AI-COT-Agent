package com.tsm.cot.ia.ia;

import com.tsm.cot.ia.promptUtil.Prompts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Map;

@Component
@Slf4j
@RequiredArgsConstructor
public class IaIntegration {

    private final ChatClient chatClient;

    public String simpleChat(String userRequest){
        log.info("SimpleChat integration started");

        var systemBasePrompt = """
            You are a helpful AI assistant that helps people find information and help people resolve problem. Your name is {name}
            In your first response, greet the user, quick summary of answer and then do not repeat it. 
            respod in json format with only the effective response.
            """;
        // mappo variabili sul system prompt
        var sistemPrompt = new SystemPromptTemplate(systemBasePrompt).createMessage(Map.of("name","Jarvis"));
        // setto user prompt
        var userPrompt =  new PromptTemplate(userRequest).createMessage();
        // accorpo sistem ms + user msg
        var prompt = new Prompt(List.of(sistemPrompt,userPrompt));
        // chiamata effettiva a llm
        var response = chatClient.prompt(prompt).call()
                .content();
        log.info("SimpleChat integration ended successfully");
        return response;
    }


    public String cotChat(String userRequest){
        log.info("CotChat integration started");
        // creo lista dei sistem prompt
        var systemPromptsChain = List.of(Prompts.BasePrompt,Prompts.CheckPrompt,Prompts.ValidatePrompt);
        // inizializo response vuota
        var response = "";
        // inizio il ciclo per la chain of trough
        for(var sysPrompt : systemPromptsChain){
            // se e primo giro, response sara vuota quindi prendo user request, in giri successivi checka response llm
            var chatResp = (ObjectUtils.isEmpty(response)) ? userRequest : response;
            // formatto input llm
            String input = String.format("{%s}\n {%s}", sysPrompt, chatResp);
            // Process through the LLM and capture output
            response = chatClient.prompt(input).call().content();
        }
        log.info("CotChat integration ended successfully");
        return response;
    }
}
