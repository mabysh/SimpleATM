//package simpleatm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SimpleATM {
  private JFrame frame;
  private JTextField textField;
  private JLabel label2;
  private Account acc = new Account(100.0);

  public static void main(String[] args) {
    SimpleATM atm = new SimpleATM();
    atm.setUpGui();
  }

  class AddButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          if (isValidDeposit()) {
              double d = Double.parseDouble(textField.getText());
              acc.deposit(d);
              displayBalance();
              textField.setText("");
              textField.requestFocus();

              JOptionPane.showMessageDialog(frame, "Operation successful",
                "Information", JOptionPane.INFORMATION_MESSAGE);
          }
      }
  }

  class WithdrawButtonListener implements ActionListener {
      public void actionPerformed(ActionEvent e) {
          if (isValidWithdraw()) {
              double d = Double.parseDouble(textField.getText());
              acc.withdraw(d);
              displayBalance();
              textField.setText("");
              textField.requestFocus();

              JOptionPane.showMessageDialog(frame, "Operation successful",
                "Information", JOptionPane.INFORMATION_MESSAGE);
          }
      }
  }

  private void setUpGui() {
    //Creating basic frame
    frame = new JFrame("Simple ATM");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300, 200);
    JPanel thePanel = new JPanel();
    thePanel.setLayout(new GridLayout(3,2));
    frame.setContentPane(thePanel);

    //Adding components to the frame
    JLabel label1 = new JLabel("Balance: ", JLabel.RIGHT);   //labels
    thePanel.add(label1);
    label2 = new JLabel(String.format("%.2f $", acc.getBalance()));
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
    JButton deposit = new JButton("Deposit");
    thePanel.add(deposit);

    frame.setLocationRelativeTo(null);           //appear at screen's center
    frame.setVisible(true);

    //Registrating action listeners
    deposit.addActionListener(new AddButtonListener());
    withdraw.addActionListener(new WithdrawButtonListener());
  }

  private void displayBalance() {
      label2.setText(String.format("%.2f $", acc.getBalance()));
  }

  private boolean isValidDeposit() {
      double amount;
      String message = "";
      try {
          amount = Double.parseDouble(textField.getText());
          if (amount < 1.0) {
              message = "Minimum sum to deposit is 1$";
          } else if (amount > 500.0) {
              message = "Maximum sum to deposit is 500$";
          } else {
              return true;
          }
      } catch (NumberFormatException ex) {
          message = "Invalid Amount";
      }


      JOptionPane.showMessageDialog(frame, message, "Error",
            JOptionPane.WARNING_MESSAGE);
      textField.setText("");
      textField.requestFocus();
      return false;
  }

  private boolean isValidWithdraw() {
      double amount;
      String message = "";
      try {
          amount = Double.parseDouble(textField.getText());
          if (amount < 1.0) {
              message = "Minimum sum to withdraw is 1$";
          } else if (amount > 500.0) {
              message = "Maximum sum to withdraw is 500$";
          } else if (amount > acc.getBalance()) {
              message = "Insufficient funds";
          } else {
              return true;
          }
      } catch (NumberFormatException ex) {
          message = "Invalid Amount";
      }

      JOptionPane.showMessageDialog(frame, message, "Error",
            JOptionPane.WARNING_MESSAGE);
      textField.setText("");
      textField.requestFocus();
      return false;
  }
}
