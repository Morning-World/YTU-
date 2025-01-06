import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import java.sql.*;

public class Addstudent extends JFrame implements ActionListener {
    static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //static String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=dataschool;encrypt=true;";
    static String dbURL = "jdbc:sqlserver://LAPTOP-HGIAK4U0\\MSSQLSERVER02:1433;DatabaseName=dataschool;encrypt=true;trustServerCertificate=true";
    static String userName = "sa";
    static String userPwd = "zfk040629#";

    // 设置与数据库连接的对象、sql语句、查询的结果集
    static Connection ct = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    private JPanel panel;
    private JTextField[] textFields;
    private JLabel[] labels;
    private JButton addButton;

    public Addstudent() {
        try {
            Class.forName(driverName);
            ct = DriverManager.getConnection(dbURL, userName, userPwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setTitle("注册");
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件间距

        // Labels and TextFields
        String[] labelTexts = {"用户名:", "密码:", "学号:", "姓名:"};
        textFields = new JTextField[4];
        labels = new JLabel[4];

        for (int i = 0; i < 4; i++) {
            labels[i] = new JLabel(labelTexts[i]);
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.anchor = GridBagConstraints.EAST; // 设置文本对齐方式为右对齐
            panel.add(labels[i], gbc);

            textFields[i] = new JTextField(20); // 设置文本框宽度
            gbc.gridx = 1;
            gbc.anchor = GridBagConstraints.WEST; // 设置文本对齐方式为左对齐
            panel.add(textFields[i], gbc);
        }

        // Button
        addButton = new JButton("注册");
        addButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // 按钮跨两列
        gbc.anchor = GridBagConstraints.CENTER; // 设置按钮居中
        panel.add(addButton, gbc);

        // Add panel to the frame
        add(panel);

        // Set frame properties
       // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250); // 设置窗口大小
        setLocationRelativeTo(null); // 设置窗口居中
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        try{
            String sql="insert into Person(身份,用户名,密码,学号,姓名) values('学生',?,?,?,?)";
            ps=ct.prepareStatement(sql);
            for(int i=0;i<4;i++){
                ps.setString(i+1,textFields[i].getText());
            }
             ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "注册成功！", "提示消息", JOptionPane.WARNING_MESSAGE);
        }catch (SQLException e1) {
            e1.printStackTrace();
        }


    }

}
