
/*
 * 学生界面
 *
 * 功能1：显示姓名和学号
 *
 * 功能2：查询考场信息
 *
 * 功能3：查询考试成绩
 *
 * 功能4：修改登录密码
 *
 * 作者：小木同学
 */


import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Manager extends JFrame implements ActionListener{
    public static void displayResultSet(ResultSet rs) throws SQLException {
        // 创建窗口
        JFrame frame = new JFrame("查询结果");
        frame.setSize(800, 600);

        // 创建表模型
        DefaultTableModel tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // 获取结果集的元数据
        int columnCount = rs.getMetaData().getColumnCount();

        // 设置表头
        for (int i = 1; i <= columnCount; i++) {
            tableModel.addColumn(rs.getMetaData().getColumnName(i));
        }

        // 添加数据行
        while (rs.next()) {
            Object[] row = new Object[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                row[i - 1] = rs.getObject(i);
            }
            tableModel.addRow(row);
        }

        // 显示窗口
        frame.setVisible(true);
    }
    // 数据库驱动
    //static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
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
    public Manager() {
        try {
            Class.forName(driverName);
            ct = DriverManager.getConnection(dbURL, userName, userPwd);

        } catch (Exception e) {
            e.printStackTrace();


        }
        // 设置窗口标题
        setTitle("Manager");

        // 设置布局管理器为 GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(10, 10, 10, 10);

        // 设置字体
        //Font buttonFont = new Font("Arial", Font.PLAIN, 20);

        // 创建“添加学生”按钮
        JButton addButton = new JButton("添加学生");
       // addButton.setFont(buttonFont);
        addButton.setPreferredSize(new Dimension(200, 50));
        constraints.gridx = 0;  // 第一列
        constraints.gridy = 0;  // 第一行
        add(addButton, constraints);

        // 创建“删除学生”按钮
        JButton deleteButton = new JButton("删除学生");
       // deleteButton.setFont(buttonFont);
        deleteButton.setPreferredSize(new Dimension(200, 50));
        constraints.gridx = 0;  // 第一列
        constraints.gridy = 1;  // 第二行
        add(deleteButton, constraints);

        // 创建“查看所有学生”按钮
        JButton viewButton = new JButton("查看所有学生");
        viewButton.setPreferredSize(new Dimension(200, 50));
        constraints.gridx = 0;  // 第一列
        constraints.gridy = 2;  // 第三行
        add(viewButton, constraints);
        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        viewButton.addActionListener(this);

        // 设置窗口大小
        setSize(300, 400);

        // 使窗口居中
        setLocationRelativeTo(null);

        // 使窗口可见
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("添加学生")) {
            try {
                Addstudent addstu = new Addstudent();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        else if(e.getActionCommand().equals("删除学生")){
            try{
              Deletestudent delestu= new Deletestudent();
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        }else if(e.getActionCommand().equals("查看所有学生")){
            try{
            String sql="select * from View_stu";
            ps=ct.prepareStatement(sql);
            rs=ps.executeQuery();
            displayResultSet(rs);
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}

