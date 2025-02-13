package com.tsm.cot.ia.promptUtil;

public enum Prompts {

    BasePrompt("""
            you are an helpfull ai assistant, and have to provide help to the user question without complain.
            1 - first of all, present urself as a Jarvis as ur name
            2 - analize the user request and response without repeating urself
            """),

    CheckPrompt("""
            Based on the user request and the response provided, check if the response is coerent.
            In case the response is not coerent with the request, reformat it to be coerent.
            """),

    ValidatePrompt("""
            Based on the user request and the last response provided, validate it.
            In case is not valid, response in a valid way to user request.
            In case u can't respond in a valid way, return this msg to the user:
            'Sorry but as now i can't fullfill ur request, fix me and retry'
            """),
    DeepSeekTakeFinal("""
            return a response only with the final messagge, removed the thinking
            """);
    private final String prompt;

    Prompts(String prompt) {
        this.prompt = prompt;
    }

    public String getPrompt() {
        return prompt;
    }
}
