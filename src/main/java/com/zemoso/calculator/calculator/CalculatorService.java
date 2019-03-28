package com.zemoso.calculator.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculatorService {

    @Autowired
    CalculatorRepository calculatorRepository;
    public void deleteResult(Integer id) {
        calculatorRepository.deleteById(id);
    }

    public List<Calculator> getResults() {
        return calculatorRepository.findAll();
    }

    public void addResult(Calculator calculator) {
        calculatorRepository.save(calculator);
    }

    public String calculateExpression(String statement){
        // TODO add formatting of the result
        // add spaces to the statement
        String statementSpaces = "";
        // add spaces to the statement
        boolean isSymbol = false;
        boolean isSymbolNext = false;

        for (int i = 0; i < statement.length(); i++) {
            if (statement.charAt(i) == '+' | statement.charAt(i) == '-' | statement.charAt(i) == '*' | statement.charAt(i) == '/' | statement.charAt(i) == '(' | statement.charAt(i) == ')') {
                isSymbol = true;
            }
            if (((i + 1) < statement.length()) && statement.charAt(i + 1) == '+' | statement.charAt(i + 1) == '-' | statement.charAt(i + 1) == '*' | statement.charAt(i + 1) == '/' | statement.charAt(i + 1) == '(' | statement.charAt(i + 1) == ')') {
                isSymbolNext = true;
            }
            if (((i + 1) < statement.length()) && isSymbol && isSymbolNext) {
                statementSpaces = statementSpaces.concat(" " + statement.charAt(i));
            } else if (isSymbol && !isSymbolNext) {
                statementSpaces = statementSpaces.concat(" " + statement.charAt(i) + " ");
            } else if (!isSymbol) {
                statementSpaces = statementSpaces.concat("" + statement.charAt(i));
            }
            isSymbol = false;
            isSymbolNext = false;
        }
        statement = statementSpaces;

        // splitted statement
        String[] tempSplittedStatement = statement.split(" ");
        String[] splStatement = new String[tempSplittedStatement.length - 1];

        if (tempSplittedStatement[0].equals("")) {
            for (int i = 0; i < splStatement.length; i++) {
                splStatement[i] = tempSplittedStatement[i + 1];
            }
        } else {
            splStatement = statement.split(" ");
        }
        String[] splittedStatement = splStatement;

        // main logic
        Double resultDouble = 0.0;
        String result = "";
        String newStatement = statement;
        try{
            for (; ; ) {
                if (newStatement.contains("(")) {
                    int openBracketIndex = 0;
                    int closeBracketIndex = 0;
                    for (int i = 0; i < splittedStatement.length; i++) {
                        if (splittedStatement[i].equals("(")) {
                            openBracketIndex = i;
                            int countOpenBrackets = 0;
                            for (int j = openBracketIndex; j < splittedStatement.length; j++) {
                                if (splittedStatement[j].equals("(")) {
                                    countOpenBrackets++;
                                }
                                if (splittedStatement[j].equals(")")) {
                                    countOpenBrackets--;
                                }
                                if ((countOpenBrackets == 0) && splittedStatement[j].equals(")")) {
                                    closeBracketIndex = j;
                                    break;
                                }
                            }
                        }
                        if (openBracketIndex > closeBracketIndex)
                            return null;
                        if ((openBracketIndex != 0) && (closeBracketIndex != 0))
                            break;
                    }

                    String subStatement = "";
                    for (int i = (openBracketIndex + 1); i < closeBracketIndex; i++) {
                        subStatement = subStatement.concat(splittedStatement[i]);
                    }
                    newStatement = "";
                    for (int i = 0; i < splittedStatement.length; i++) {
                        if (((i < openBracketIndex) || (i > closeBracketIndex)) && i != (splittedStatement.length - 1)) {
                            newStatement = newStatement.concat(splittedStatement[i] + " ");
                        }
                        if (i == openBracketIndex) {
                            String subResult = calculateExpression(subStatement);
                            newStatement = newStatement.concat(subResult + " ");
                        }
                        if ((i == (splittedStatement.length - 1)) && (i != closeBracketIndex)) {
                            newStatement = newStatement.concat(splittedStatement[i]);
                        }
                    }
                    splittedStatement = newStatement.split(" ");
                } else {
                    Double resultTemp = 0.0;
                    for (int i = 0; i < splittedStatement.length; i++) {
                        if (splittedStatement[i].equals("*") || splittedStatement[i].equals("/")) {
                            if (splittedStatement[i].equals("*")) {
                                resultTemp = (Double.valueOf(splittedStatement[i - 1])) * (Double.valueOf(splittedStatement[i + 1]));
                            }
                            if (splittedStatement[i].equals("/")) {
                                resultTemp = (Double.valueOf(splittedStatement[i - 1])) / (Double.valueOf(splittedStatement[i + 1]));
                            }
                            splittedStatement[i - 1] = "0";
                            splittedStatement[i] = "0";
                            splittedStatement[i + 1] = resultTemp.toString();
                        }
                    }
                    // fill the final array
                    String[] finalArray = new String[splittedStatement.length];
                    int countSkip = 0;
                    for (int i = 0; i < finalArray.length; i++) {
                        if (splittedStatement[i].equals("") || splittedStatement[i].equals("0")) {
                            countSkip++;
                        }
                        if (!splittedStatement[i].equals("") && !splittedStatement[i].equals("0")) {
                            finalArray[i - countSkip] = splittedStatement[i];
                        }
                    }
                    // get the result
                    for (int i = 0; i < finalArray.length; i++) {
                        if (finalArray[i] == null) {
                            break;
                        }
                        if (!finalArray[i].equals("+") && !finalArray[i].equals("-")) {
                            if ((i > 0) && finalArray[i - 1].equals("-")) {
                                resultDouble -= Double.valueOf(finalArray[i]);
                            } else {
                                resultDouble += Double.valueOf(finalArray[i]);
                            }
                            resultDouble = (double) Math.round(resultDouble*1000)/1000;
                            result = resultDouble.toString();
                        }
                    }
                    break;
                }
            }
        } catch (Exception e)
        {
            return null;
        }
        return result;
    }

}
