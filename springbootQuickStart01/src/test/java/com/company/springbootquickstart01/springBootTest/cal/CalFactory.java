package com.company.springbootquickstart01.springBootTest.cal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CalFactory {

    private Map<String, Function> funcMap;

    @Autowired
    private List<Function> funcList;

    @PostConstruct
    public void init() {
        this.funcMap = funcList.stream().collect(Collectors.toMap(e -> e.getCode(), e -> e, (e1, e2) -> e1));
    }

    public Function get(String code){
        Function func = this.funcMap.get(code);
        if (func == null) {
            // TODO throw
        }
        return func;
    }
}
