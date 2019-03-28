package com.zemoso.calculator.calculator;

import javax.persistence.*;

@Entity
@Table(name="calculator")
public class Calculator {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private String query;

    @Column
    private String result;

    Calculator(){
        super();
    }

    public Calculator(Integer id, String query, String result) {
        super();
        this.id = id;
        this.query = query;
        this.result = result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Calculator{" +
                "id=" + id +
                ", query='" + query + '\'' +
                ", result=" + result +
                '}';
    }
}
