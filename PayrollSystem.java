import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class PayrollSystem {
    private Map<String, Employee> employeeMap;
    private JFrame frame;
    private JTextArea textArea;

    public PayrollSystem() {
        employeeMap = new HashMap<>();
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Employee Payroll System");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        textArea = new JTextArea(15, 40);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        panel.add(scrollPane);

        JButton addEmployeeButton = new JButton("Add Employee");
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
            }
        });
        panel.add(addEmployeeButton);

        JButton generatePayStubButton = new JButton("Generate Pay Stub");
        generatePayStubButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                generatePayStub();
            }
        });
        panel.add(generatePayStubButton);

        frame.add(panel);
        frame.setVisible(true);
    }

    private void addEmployee() {
        String employeeId = JOptionPane.showInputDialog(frame, "Enter Employee ID:");
        if (employeeId != null && !employeeId.isEmpty() && !employeeMap.containsKey(employeeId)) {
            String name = JOptionPane.showInputDialog(frame, "Enter Employee Name:");
            String designation = JOptionPane.showInputDialog(frame, "Enter Employee Designation:");
            double hourlyRate = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter Employee Hourly Rate:"));
            Employee employee = new Employee(name, designation, hourlyRate);
            employeeMap.put(employeeId, employee);
            textArea.append("Employee added: " + employeeId + "\n");
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid Employee ID or Employee already exists!");
        }
    }

    private void generatePayStub() {
        String employeeId = JOptionPane.showInputDialog(frame, "Enter Employee ID:");
        if (employeeId != null && !employeeId.isEmpty() && employeeMap.containsKey(employeeId)) {
            Employee employee = employeeMap.get(employeeId);
            double hoursWorked = Double.parseDouble(JOptionPane.showInputDialog(frame, "Enter Hours Worked:"));
            double salary = employee.calculateSalary(hoursWorked);
            textArea.append("Employee ID: " + employeeId + "\n");
            textArea.append("Employee Name: " + employee.getName() + "\n");
            textArea.append("Designation: " + employee.getDesignation() + "\n");
            textArea.append("Hourly Rate: " + employee.getHourlyRate() + "\n");
            textArea.append("Hours Worked: " + hoursWorked + "\n");
            textArea.append("Total Salary: " + salary + "\n\n");
        } else {
            JOptionPane.showMessageDialog(frame, "Invalid Employee ID or Employee does not exist!");
        }
    }

    private class Employee {
        private String name;
        private String designation;
        private double hourlyRate;

        public Employee(String name, String designation, double hourlyRate) {
            this.name = name;
            this.designation = designation;
            this.hourlyRate = hourlyRate;
        }

        public String getName() {
            return name;
        }

        public String getDesignation() {
            return designation;
        }

        public double getHourlyRate() {
            return hourlyRate;
        }

        public double calculateSalary(double hoursWorked) {
            return hoursWorked * hourlyRate;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PayrollSystem();
            }
        });
    }
}
