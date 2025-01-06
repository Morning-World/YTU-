import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Deletestudent extends JFrame implements ActionListener{

    // 数据库连接信息
    static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    //static String dbURL = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=jsp";
    static String dbURL = "jdbc:sqlserver://LAPTOP-HGIAK4U0\\MSSQLSERVER02:1433;DatabaseName=dataschool;encrypt=true;trustServerCertificate=true";
    // 数据库用户名和密码
    static String userName = "sa";
    static String userPwd = "zfk040629#";

    // 设置与数据库连接的对象、sql语句、查询的结果集
    static Connection ct = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    JTextField textField = new JTextField(15);
    public Deletestudent() {
        try {
            Class.forName(driverName);
            ct = DriverManager.getConnection(dbURL, userName, userPwd);

        } catch (Exception e) {
            e.printStackTrace();


        }
        // 设置窗口标题
        setTitle("删除学生");

        // 设置布局管理器为 GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(10, 10, 10, 10);
        constraints.fill = GridBagConstraints.HORIZONTAL;

        // 创建“输入学号”标签
        JLabel label = new JLabel("输入学号:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        add(label, constraints);

        // 创建文本框

        constraints.gridx = 1;
        constraints.gridy = 0;
        add(textField, constraints);

        // 创建“删除”按钮
        JButton deleteButton = new JButton("删除");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 2;
        add(deleteButton, constraints);
        deleteButton.addActionListener(this);


        // 设置默认关闭操作


        // 调整窗口大小
        pack();

        // 使窗口居中
        setLocationRelativeTo(null);

        // 使窗口可见
        setVisible(true);
    }

    // 根据学号删除学生
    private void deleteStudentById(String id) {
        String sql = "DELETE FROM Person WHERE 学号 = ?";
        try {
            ps=ct.prepareStatement(sql);
            ps.setString(1,id);
            int count=ps.executeUpdate();
            String sql1 = "DELETE FROM Score WHERE 学号 = ?";
            ps=ct.prepareStatement(sql1);
            ps.setString(1,id);
            int count1=ps.executeUpdate();
            String sql2 = "DELETE FROM Room WHERE 学号 = ?";
            ps=ct.prepareStatement(sql2);
            ps.setString(1,id);
            int count2=ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "删除成功", "提示消息", JOptionPane.WARNING_MESSAGE);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void actionPerformed(ActionEvent e) {
         try{
             String sno=textField.getText();
             deleteStudentById(sno);
         }catch(Exception e5){
             e5.printStackTrace();
         }
    }

}
