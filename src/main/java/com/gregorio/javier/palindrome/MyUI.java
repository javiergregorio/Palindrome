package com.gregorio.javier.palindrome;

import javax.servlet.annotation.WebServlet;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;


@Theme("mytheme")
public class MyUI extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        
        final VerticalLayout layout = new VerticalLayout();
        
        final TextField palindrome = new TextField();
        palindrome.setPlaceholder("Example: Racecar");
        palindrome.setCaption("Type the word or phrase you want to test here:");
        
        Data data = new Data();
    	
        Button button = new Button("Palindrome?");
        button.addClickListener( e -> {
        	
        	data.setPalindrome(palindrome.getValue());
        	String testWord = data.getPalindrome();
    		
        	layout.addComponent(new Label("Hi, the word or phrase \"" + palindrome.getValue() + "\", " + isPalindrome(testWord) + ", thank you"));
        });
        
        layout.addComponents(palindrome, button);
        
        setContent(layout);
    }


	private String isPalindrome(String testW) {
		
		String testWordLowCase = testW.replace(" ", "").toLowerCase();
		
		int i=0;
		int j=testWordLowCase.length()-1;
		boolean res = false;

		while((i<j) && (!res)){
			
			if(testWordLowCase.charAt(i) == testWordLowCase.charAt(j)){
				i++;
				j--;
			}else{
				res = true;
			}
		}        	
		
		if(!res)
			return "it is a palindrome";
		else
			return "it's NOT a palindrome";
		
	}
    

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
