package com.example.lab4.views;

import com.example.lab4.Change;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route("index2")
public class ChangeView extends VerticalLayout {
    private TextField money, b1000, b500, b100, b20, b10, b5, b1;
    private Button calcBtn;
    public ChangeView(){
        money = new TextField("เงินทอน");
        money.setPrefixComponent(new Span("$"));
        calcBtn = new Button("คำนวณเงินทอน");
        calcBtn.addClickListener(event -> getChange());
        b1000 = new TextField();
        b1000.setPrefixComponent(new Span("$1000:"));

        b500 = new TextField();
        b500.setPrefixComponent(new Span("$500:"));

        b100 = new TextField();
        b100.setPrefixComponent(new Span("$100:"));

        b20 = new TextField();
        b20.setPrefixComponent(new Span("$20:"));

        b10 = new TextField();
        b10.setPrefixComponent(new Span("$10:"));

        b5 = new TextField();
        b5.setPrefixComponent(new Span("$5:"));

        b1 = new TextField();
        b1.setPrefixComponent(new Span("$1:"));

        add(money, calcBtn, b1000, b500, b100, b20, b10, b5, b1);
    }
    public void getChange(){
        Change res = WebClient.create()
                .get()
                .uri("http://localhost:8080/getChange/" + money.getValue())
                .retrieve()
                .bodyToMono(Change.class)
                .block();
        b1000.setValue(String.valueOf(res.getB1000()));
        b500.setValue(String.valueOf(res.getB500()));
        b100.setValue(String.valueOf(res.getB100()));
        b20.setValue(String.valueOf(res.getB20()));
        b10.setValue(String.valueOf(res.getB10()));
        b5.setValue(String.valueOf(res.getB5()));
        b1.setValue(String.valueOf(res.getB1()));
    }
}
