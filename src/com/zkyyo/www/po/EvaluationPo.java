package com.zkyyo.www.po;

public class EvaluationPo {
    private String judgedId;
    private String evaluatorId;
    private String content;

    public EvaluationPo() {

    }

    public EvaluationPo(String judgedId, String evaluatorId, String content) {
        this.judgedId = judgedId;
        this.evaluatorId = evaluatorId;
        this.content = content;
    }

    public String getJudgedId() { return judgedId; }
    public void setJudgedId(String judgedId) { this.judgedId = judgedId; }
    public String getEvaluatorId() { return evaluatorId; }
    public void setEvaluatorId(String evaluatorId) { this.evaluatorId = evaluatorId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return "EvaluationPo{" +
                "judgedId='" + judgedId + '\'' +
                ", evaluatorId='" + evaluatorId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
