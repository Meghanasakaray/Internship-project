package com.batch.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pro.model.Member;

public class BatchDao {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/task_management_db?useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "tiger";

    // Ensure your JDBC driver is loaded
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("MySQL JDBC Driver not found");
            e.printStackTrace();
        }
    }

    public int post(Member member) {
        int result = 0;
        String sql = "INSERT INTO tasks (name) VALUES (?)";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, member.getName());
            result = pst.executeUpdate();
            if (result > 0) {
                System.out.println("Task inserted successfully: " + member.getName());
            }
        } catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace(); 
        }
        return result;
    }

    public List<Member> getAllTasks() {
        List<Member> tasks = new ArrayList<>();
        String sql = "SELECT * FROM tasks";

        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("name");
                Member member = new Member(name);
                tasks.add(member);
            }
            System.out.println("Retrieved " + tasks.size() + " tasks from the database.");
        } catch (SQLException e) {
            System.err.println("SQLState: " + e.getSQLState());
            System.err.println("Error Code: " + e.getErrorCode());
            System.err.println("Message: " + e.getMessage());
            e.printStackTrace(); 
        }
        return tasks;
    }
    
    /*public void deleteAllTasks() {
        String sql = "DELETE FROM tasks";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pst = conn.prepareStatement(sql)) {
            int rowsAffected = pst.executeUpdate();
            System.out.println(rowsAffected + " tasks deleted successfully");
        } catch (SQLException e) {
            System.err.println("Error deleting all tasks: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void deleteTask(String name) {
        String sql = "DELETE FROM tasks WHERE name=?";
        try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, name);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task deleted successfully: " + name);
            } else {
                System.out.println("Task with name " + name + " not found");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting task: " + e.getMessage());
            e.printStackTrace();
        }
    }*/
  
}