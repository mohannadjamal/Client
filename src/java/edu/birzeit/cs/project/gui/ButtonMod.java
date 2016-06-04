package edu.birzeit.cs.project.gui;


// ButtonMod is a modified version of Button with style and width changes
// it is used a lot so making a class is better and faster



import javafx.scene.control.Button;

public class ButtonMod extends Button {
	
	public ButtonMod(String text, double width) {
		
		super(text);
		
		setMinWidth(width);
		setMaxWidth(width);
		
		setMinHeight(40);
		setMaxHeight(40);
		
		setStyle(Styles.BUTTON_GREEN);
		setOnMouseEntered(e -> { // Highlighting style
			setStyle(Styles.BUTTON_GREEN_OVER);
		});
		setOnMouseExited(e -> { // Not highlighting style
			setStyle(Styles.BUTTON_GREEN);
		});
		
	}
	
}
