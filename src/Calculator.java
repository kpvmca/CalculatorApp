 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {
    JFrame frame;
    JTextField textfield;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[10];
    JButton addButton, subButton, mulButton, divButton;
    JButton decButton, eqButton, delButton, clrButton, negButton, dzerButton;
    JPanel panel;
    
   // Font myFont = new Font("Arial", Font.BOLD, 20);
    Font myFont = new Font("Helvetica", Font.BOLD, 20);
    double num1 = 0, num2 = 0, result = 0;
    char operator;
    
    Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 580);
        frame.setLayout(null);

        textfield = new JTextField();
        textfield.setBounds(50, 25, 300, 50);
        textfield.setFont(myFont);
        textfield.setEditable(false);
        textfield.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(textfield);
        
        addButton = new JButton("+");
        subButton = new JButton("-");
        mulButton = new JButton("*");
        divButton = new JButton("/");
        decButton = new JButton(".");
        eqButton = new JButton("=");
        delButton = new JButton("Del");
        clrButton = new JButton("C");
        negButton = new JButton("-/+");
        dzerButton = new JButton("00");

        functionButtons[0] = addButton;
        functionButtons[1] = subButton;
        functionButtons[2] = mulButton;
        functionButtons[3] = divButton;
        functionButtons[4] = decButton;
        functionButtons[5] = eqButton;
        functionButtons[6] = delButton;
        functionButtons[7] = clrButton;
        functionButtons[8] = negButton; //negative value
        functionButtons[9] = dzerButton;//double zero

        for (int i = 0; i < 10; i++) {
            functionButtons[i].addActionListener(this);
            functionButtons[i].setFont(myFont);
            functionButtons[i].setFocusable(false);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            numberButtons[i].setFont(myFont);
            numberButtons[i].setFocusable(false);
        }

        panel = new JPanel();
        panel.setBounds(50, 100, 300, 300);
        panel.setLayout(new GridLayout(4, 4, 10, 10));
        panel.setBackground(Color.LIGHT_GRAY);

        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);
        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);
        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);
        panel.add(decButton);
        panel.add(numberButtons[0]);
        panel.add(eqButton);
        panel.add(divButton);

        frame.add(panel);
        frame.add(delButton).setBounds(50, 430, 70, 50);
        frame.add(clrButton).setBounds(125, 430, 70, 50);
        frame.add(negButton).setBounds(200, 430, 70, 50);
        frame.add(dzerButton).setBounds(275, 430, 70, 50);
        
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Calculator();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                textfield.setText(textfield.getText().concat(String.valueOf(i)));
            }
        }
        if (e.getSource() == dzerButton) {
            textfield.setText(textfield.getText().concat("00"));
        }
        if (e.getSource() == decButton) {
            if (!textfield.getText().contains(".")) {
                textfield.setText(textfield.getText().concat("."));
            }
        }
        if (e.getSource() == addButton || e.getSource() == subButton || e.getSource() == mulButton || e.getSource() == divButton) {
            num1 = Double.parseDouble(textfield.getText());
            operator = e.getActionCommand().charAt(0);
            textfield.setText("");
        }
        if (e.getSource() == eqButton) {
            num2 = Double.parseDouble(textfield.getText());
            switch (operator) {
                case '+' -> result = num1 + num2;
                case '-' -> result = num1 - num2;
                case '*' -> result = num1 * num2;
                case '/' -> result = num1 / num2;
            }
            if (result % 1 == 0) {
                textfield.setText(String.valueOf((int) result));
            } else {
                textfield.setText(String.valueOf(result));
            }
            num1 = result;
        }
        if (e.getSource() == clrButton) {
            textfield.setText("");
        }
        if (e.getSource() == delButton) {
            String text = textfield.getText();
            if (!text.isEmpty()) {
                textfield.setText(text.substring(0, text.length() - 1));
            }
        }
        if (e.getSource() == negButton) {
            try {
                double value = Double.parseDouble(textfield.getText());
                textfield.setText(String.valueOf(-value));
            } catch (Exception ignored) {}
        }
    }
}


