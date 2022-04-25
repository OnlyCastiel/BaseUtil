package com.mfq.tree;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HFMTreeElem<T> {
    private T elem;
    private BigDecimal probability;
    private Integer parent = 0;
    private Integer left = 0;
    private Integer right = 0;

    private String codeStr;

    public HFMTreeElem() {
    }

    public HFMTreeElem(T elem, BigDecimal probability) {
        this.elem = elem;
        this.probability = probability;
    }

    @Override
    public String toString() {
        return "HFMTreeElem{" +
                "elem=" + elem +
                ", probability=" + probability +
                ", parent=" + parent +
                ", left=" + left +
                ", right=" + right +
                ", codeStr='" + codeStr + '\'' +
                '}';
    }
}
