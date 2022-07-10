package com.egide.sms.dao;

import java.sql.*;
import java.util.ArrayList;

import com.egide.sms.db.DbConnector;
import com.egide.sms.models.Student;

public class StudentDAO extends DbConnector {
	
	public StudentDAO() {}

    public Student save(Student std){
        try{
        	
        	Student existing = findByEmail(std.getEmail());
        	if(existing != null) return null;
        	
            String sql = "INSERT INTO students(firstName,lastName,email,password) VALUES(?,?,?,?);";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1,std.getFirstName());
            stmt.setString(2,std.getLastName());
            stmt.setString(3, std.getEmail());
            stmt.setString(4,std.getPassword());
            int insertCount = stmt.executeUpdate();
            if(insertCount<1) return null;
            stmt.close();
            return findByEmail(std.getEmail());
        }catch ( Exception e){
            return null;
        }


    }

    public Student findById(int id){
        try{
            String sql = "SELECT * FROM students WHERE id=?;";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            Student std = null;
            while(rs.next()){
                std = new Student();
                std.setId(rs.getInt("id"));
                std.setFirstName(rs.getString("firstName"));
                std.setLastName(rs.getString("lastName"));
                std.setEmail(rs.getString("email"));
                std.setPassword(rs.getString("password"));
            }
            return std;
        }catch(Exception e){
            return null;
        }

    }

    public Student findByEmail(String email){
        try{
            String sql= "SELECT * FROM students WHERE email=?;";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1,email);
            ResultSet rs = stmt.executeQuery();
            Student std = null;
            while(rs.next()){
                std = new Student();
                std.setId(rs.getInt("id"));
                std.setFirstName(rs.getString("firstName"));
                std.setLastName(rs.getString("lastName"));
                std.setEmail(rs.getString("email"));
                std.setPassword(rs.getString("password"));
            }
            stmt.close();
            return std;

        }catch(Exception e){
            return null;
        }
    }

    public int updateById(int id,Student std){
        try{
            String sql = "UPDATE students SET firstName=?,lastName=?,email=? WHERE id=?;";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setString(1,std.getFirstName());
            stmt.setString(2,std.getLastName());
            stmt.setString(3, std.getEmail());
            int updateCount = stmt.executeUpdate();
            stmt.close();
            return updateCount;

        }catch(Exception e){
            return 0;
        }

    }

    public int deleteById(int id){
        try {

            String sql = " DELETE FROM students WHERE id=?;";
            PreparedStatement stmt = getConnection().prepareStatement(sql);
            stmt.setInt(1, id);
            int deleteCount = stmt.executeUpdate();
            stmt.close();
            return deleteCount;
        }catch(Exception e){
            return 0;
        }

    }
    
   public ArrayList<Student> getAll(){
	   try {
		   String sql = "SELECT * FROM students;";
		   Statement stmt = getConnection().createStatement();
		   ResultSet rs = stmt.executeQuery(sql);
		   ArrayList<Student> list = new ArrayList<Student>();
		   while (rs.next()) {
			   Student std = new Student();
               std.setId(rs.getInt("id"));
               std.setFirstName(rs.getString("firstName"));
               std.setLastName(rs.getString("lastName"));
               std.setEmail(rs.getString("email"));
               std.setPassword(rs.getString("password"));
               list.add(std);
		   }
		   stmt.close();
		   return list;
		} catch (Exception e) {
			return new ArrayList<Student>();
		}
   }
}
