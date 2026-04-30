package com.example.AI_summarizer.Model;

import java.util.List;

public class Response {
    private String summary;
    private List<String> actionItems;

    public Response(String summary, List<String> actionItems) {
        this.summary = summary;
        this.actionItems = actionItems;
    }

    public String getSummary() {
        return summary;
    }
    public void setSummary(String summary) {
        this.summary = summary;
    }
    public List<String> getActionItems() {
        return actionItems;
    }
    public void setActionItems(List<String> actionItems) {
        this.actionItems = actionItems;
    }
}
