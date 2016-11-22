//package simpleatm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleATM {
  private float balance = 0;
  private JTextField textField;
  private JLabel label2;

  public static void main(String[] args) {
    SimpleATM atm = new SimpleATM();
    atm.work();
  }

  class AddButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          float amount;
          try {
              amount = Float.parseFloat(textField.getText());
              balance += amount;
              displayBalance();
              textField.setText("");
          } catch (NumberFormatException ex) {
              dialogWindow("Invalid Amount");
              textField.setText("");
          } catch (NullPointerException ex) {
              dialogWindow("Invalid Amount");
              textField.setText("");
          }
      }
  }

  class WithdrawButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          float amount;
          try {
              amount = Float.parseFloat(textField.getText());
              if (amount > balance)
                dialogWindow("Insufficient funds");
              else {
                  balance = balance - amount;
                  displayBalance();
              }
              textField.setText("");
          } catch (NumberFormatException ex) {
              dialogWindow("Invalid Amount");
              textField.setText("");
          } catch (NullPointerException ex) {
              dialogWindow("Invalid Amount");
              textField.setText("");
          }
      }
  }

  private void work() {
    //Creating basic frame
    JFrame frame = new JFrame("Simple ATM");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    JPanel thePanel = new JPanel();
    thePanel.setLayout(new GridLayout(0,2));
    frame.setContentPane(thePanel);

    //Adding content to the frame
    JLabel label1 = new JLabel("Balance: ", JLabel.RIGHT);
    thePanel.add(label1);                                   //labels
    label2 = new JLabel(String.format("%.2f $", balance));
    label2.setOpaque(true);
    label2.setForeground(Color.GREEN);
    label2.setBackground(Color.DARK_GRAY);
    thePanel.add(label2);
    JLabel label3 = new JLabel("Value: ", JLabel.RIGHT);
    thePanel.add(label3);

    textField = new JTextField();                //text field
    thePanel.add(textField);

    JButton withdraw = new JButton("Withdraw");             //buttons
    thePanel.add(withdraw);
    JButton add = new JButton("Add");
    thePanel.add(add);

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    //Adding action listeners
    add.addActionListener(new AddButtonListener());
    withdraw.addActionListener(new WithdrawButtonListener());
  }

  private void dialogWindow(String text) {
      JFrame fr = new JFrame("ATM Notification");
      fr.setSize(400, 100);
      JLabel l = new JLabel(text, JLabel.CENTER);
      fr.getContentPane().add(BorderLayout.CENTER, l);
      fr.setLocationRelativeTo(null);
      fr.setVisible(true);
  }

  private void displayBalance() {
      label2.setText(String.format("%.2f $", balance));
  }
}
