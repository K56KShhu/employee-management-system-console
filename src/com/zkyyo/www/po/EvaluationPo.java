package com.zkyyo.www.po;

public class EvaluationPo {
    private int beEvaluatedId;
    private int evaluatorId;
    private String content;

    public EvaluationPo() {

    }

    public EvaluationPo(int beEvaluatedId, int evaluatorId, String content) {
        this.beEvaluatedId = beEvaluatedId;
        this.evaluatorId = evaluatorId;
        this.content = content;
    }

    public int getBeEvaluatedId() { return beEvaluatedId; }
    public void setBeEvaluatedId(int beEvaluatedId) { this.beEvaluatedId = beEvaluatedId; }
    public int getEvaluatorId() { return evaluatorId; }
    public void setEvaluatorId(int evaluatorId) { this.evaluatorId = evaluatorId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    @Override
    public String toString() {
        return "EvaluationPo{" +
                "beEvaluatedId='" + beEvaluatedId + '\'' +
                ", evaluatorId='" + evaluatorId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
