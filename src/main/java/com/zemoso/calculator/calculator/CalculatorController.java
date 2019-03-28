package com.zemoso.calculator.calculator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api")
public class CalculatorController {

    @Autowired
    private CalculatorService calculatorService;

    @PostMapping(value="/results")
    public String addResult(@RequestBody Calculator calculator){
        String query = calculator.getQuery();
        String result = calculatorService.calculateExpression(query);
        calculator.setResult(result);
        calculatorService.addResult(calculator);
        return result;
    }

    @GetMapping(value = "/results")
    public List<Calculator> getResults(){
        return calculatorService.getResults();
    }

    @DeleteMapping(value = "/results/{id}")
    public void deleteResult(@PathVariable Integer id){
        calculatorService.deleteResult(id);
    }
}
