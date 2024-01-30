package com.ohgiraffers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("추가할 배우 First name : ");
        String fName = sc.nextLine().toUpperCase();
        System.out.println("Last name");
        String lName = sc.nextLine().toUpperCase();
        Connection con = getConnection();
        PreparedStatement pstm = null;
        Properties prob = new Properties();
        int result = 0;
        try {
            prob.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/sakila-query.xml"));
            String query = prob.getProperty("insert");
            pstm = con.prepareStatement(query);

            pstm.setString(1,fName);
            pstm.setString(2,lName);
            result = pstm.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(pstm);
            close(con);
        }
        if (result > 0) {
            System.out.println("추가 되었습니다.");
        } else {
            System.out.println("실패 하였습니다.");
        }
    }
}
