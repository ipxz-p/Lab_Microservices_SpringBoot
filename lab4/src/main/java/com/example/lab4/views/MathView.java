package com.example.lab4.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@Route("")
public class MathView extends VerticalLayout {
    private FormLayout inputForm;
    private TextField num1, num2, answer;
    private Button plus, minus, multi, divide, mod, max;
    public MathView(){
        inputForm = new FormLayout();
        num1 = new TextField();
        num1.setLabel("Number 1");
        num2 = new TextField();
        num2.setLabel("Number 2");
        inputForm.add(num1, num2);
        Span label = new Span("Operator");
        HorizontalLayout horizonLayout = new HorizontalLayout();
        plus = new Button("+");
        minus = new Button("-");
        multi = new Button("x");
        divide = new Button("/");
        mod = new Button("Mod");
        max = new Button("Max");
        plus.addClickListener(event -> sendGetReq("plus"));
        minus.addClickListener(event -> sendGetReq("minus"));
        multi.addClickListener(event -> sendGetReq("multi"));
        divide.addClickListener(event -> sendGetReq("divide"));
        mod.addClickListener(event -> sendGetReq("mod"));
        max.addClickListener(event -> sendPostReq("max"));
        horizonLayout.add(plus, minus, multi, divide, mod, max);
        answer = new TextField();
        answer.setLabel("Answer");
        answer.setWidthFull();
        answer.setReadOnly(true);
        add(inputForm, label,  horizonLayout, answer);
    }
    public void sendPostReq(String type){
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("n1", num1.getValue());
        formData.add("n2", num2.getValue());
        String res = WebClient.create()
                .post()
                .uri("http://localhost:8080/" + type)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromFormData(formData))
                .retrieve()
                .bodyToMono(String.class)
                .block();
        answer.setValue(res);
    }
    public void sendGetReq(String type){
        String res = WebClient.create()
                .get()
                .uri("http://localhost:8080/" + type + "/" + num1.getValue() + "/" + num2.getValue())
                .retrieve()
                .bodyToMono(String.class)
                .block();
        answer.setValue(res);
    }
}
