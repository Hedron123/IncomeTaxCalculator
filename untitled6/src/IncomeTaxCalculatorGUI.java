import javax.swing.*;
import java.awt.*;

class TaxCalculator {
    private static final double TAX_RATE_10 = 0.1;
    private static final double TAX_RATE_20 = 0.2;
    private static final double TAX_RATE_30 = 0.3;
    private static final double TAX_THRESHOLD_10 = 10_000;
    private static final double TAX_THRESHOLD_20 = 20_000;

    public double calculateTax(double salary) {
        double tax = 0;
        if (salary > TAX_THRESHOLD_20) {
            tax += (salary - TAX_THRESHOLD_20) * TAX_RATE_30;
            salary = TAX_THRESHOLD_20;
        }
        if (salary > TAX_THRESHOLD_10) {
            tax += (salary - TAX_THRESHOLD_10) * TAX_RATE_20;
            salary = TAX_THRESHOLD_10;
        }
        tax += salary * TAX_RATE_10;
        return tax;
    }
}

public class IncomeTaxCalculatorGUI extends JFrame {
    private JTextField salaryField;
    private JLabel taxResultLabel;
    private TaxCalculator taxCalculator;

    public IncomeTaxCalculatorGUI() {
        setTitle("Income Tax Calculator");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        salaryField = new JTextField();
        salaryField.setBorder(BorderFactory.createTitledBorder("Enter Salary"));
        salaryField.addActionListener(e -> calculateTax());
        add(salaryField);

        taxResultLabel = new JLabel("Tax Result:");
        taxResultLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(taxResultLabel);

        taxCalculator = new TaxCalculator();
    }

    private void calculateTax() {
        try {
            String salaryText = salaryField.getText();
            if (!salaryText.isEmpty()) {
                double salary = Double.parseDouble(salaryText);
                double tax = taxCalculator.calculateTax(salary);
                taxResultLabel.setText(String.format("Tax Result: %.2f", tax));
            } else {
                taxResultLabel.setText("Please enter a salary!");
            }
        } catch (NumberFormatException ex) {
            taxResultLabel.setText("Please enter a valid salary!");
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            IncomeTaxCalculatorGUI calculator = new IncomeTaxCalculatorGUI();
            calculator.setVisible(true);
            calculator.setResizable(false);
            calculator.setLocationRelativeTo(null);
        });
    }
}
