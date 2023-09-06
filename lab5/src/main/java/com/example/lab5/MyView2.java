package com.example.lab5;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;

@Route("index2")
public class MyView2 extends VerticalLayout {
    public MyView2(){
        FormLayout main = new FormLayout();
        VerticalLayout wordLayout = new VerticalLayout();
        TextField addWordField = new TextField();
        addWordField.setLabel("Add Word");
        addWordField.setWidthFull();
        Button addGoodWordButton = new Button("Add Good Word");
        addGoodWordButton.setWidthFull();

        Button addBadWordButton = new Button("Add Bad Word");
        addBadWordButton.setWidthFull();
        ComboBox<String> goodWordCombobox = new ComboBox<String>("Good Words");
        goodWordCombobox.setWidthFull();

        ComboBox<String> badWordCombobox = new ComboBox<String>("Bad Words");
        badWordCombobox.setWidthFull();
        addGoodWordButton.addClickListener(event -> {
            ArrayList out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/addGood/" + addWordField.getValue())
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();
            goodWordCombobox.setItems(out);
        });
        addBadWordButton.addClickListener(event -> {
            ArrayList out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/addBad/" + addWordField.getValue())
                    .retrieve()
                    .bodyToMono(ArrayList.class)
                    .block();

            badWordCombobox.setItems(out);
        });
        wordLayout.add(
                addWordField,
                addGoodWordButton,
                addBadWordButton,
                goodWordCombobox,
                badWordCombobox
        );
        VerticalLayout sentenceLayout = new VerticalLayout();

        TextField addSentenceField = new TextField();
        addSentenceField.setLabel("Add Sentence");
        addSentenceField.setWidthFull();

        Button addSentenceButton = new Button("Add Sentence");
        addSentenceButton.setWidthFull();

        TextArea goodSentenceTextArea = new TextArea("Good Sentences");
        goodSentenceTextArea.setWidthFull();
        goodSentenceTextArea.setEnabled(false);

        TextArea badSentenceTextArea = new TextArea("Bad Sentences");
        badSentenceTextArea.setWidthFull();
        badSentenceTextArea.setEnabled(false);

        Button showSentenceButton = new Button("Show Sentence");
        showSentenceButton.setWidthFull();
        addSentenceButton.addClickListener(event -> {
            if(!addSentenceField.getValue().equals("")){
                String out = WebClient.create()
                        .get()
                        .uri("http://127.0.0.1:8080/proof/" + addSentenceField.getValue())
                        .retrieve()
                        .bodyToMono(String.class)
                        .block();

                Notification.show(out);
            }
        });
        showSentenceButton.addClickListener(event -> {
            Sentence out = WebClient.create()
                    .get()
                    .uri("http://127.0.0.1:8080/getSentence")
                    .retrieve()
                    .bodyToMono(Sentence.class)
                    .block();

            goodSentenceTextArea.setValue(out.goodSentences.toString());
            badSentenceTextArea.setValue(out.badSentences.toString());
        });
        sentenceLayout.add(
                addSentenceField,
                addSentenceButton,
                goodSentenceTextArea,
                badSentenceTextArea,
                showSentenceButton
        );
        main.add(wordLayout, sentenceLayout);
        add(main);
    }

}
