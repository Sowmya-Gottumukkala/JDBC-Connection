package com.day7.Employee.Dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.day7.Model.Employee;


public class EmployeeDao 
{
       public int registerEmployee(Employee employee) throws
    ClassNotFoundException {
           String INSERT_USERS_SQL = "INSERT INTO employee" +
            "  ( first_name, last_name, username, password, address, contact) VALUES " +
                " ( ?, ?, ?, ?,?,?);";
     int result = 0;
          Class.forName("com.mysql.cj.jdbc.Driver");
     try (Connection connection = DriverManager
             .getConnection("jdbc:mysql://localhost:3306/ICT?useSSL=false&allowPublicKeyRetrieval=true", "root", "password");
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
       
         preparedStatement.setString(1, employee.getFirstName());
         preparedStatement.setString(2, employee.getLastName());
         preparedStatement.setString(3, employee.getUsername());
         preparedStatement.setString(4, employee.getPassword());
         preparedStatement.setString(5, employee.getAddress());
         preparedStatement.setString(6, employee.getContact());

         System.out.println(preparedStatement);
         result = preparedStatement.executeUpdate();

     } catch (SQLException e) {
         
         printSQLException(e);
     }
     return result;
 }

 private void printSQLException(SQLException ex) {
     for (Throwable e: ex) {
         if (e instanceof SQLException) {
             e.printStackTrace(System.err);
             System.err.println("SQLState: " + ((SQLException) e).getSQLState());
             System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
             System.err.println("Message: " + e.getMessage());
             Throwable t = ex.getCause();
             while (t != null) {
                 System.out.println("Cause: " + t);
                 t = t.getCause();
             }
         }
     }
 }
}
