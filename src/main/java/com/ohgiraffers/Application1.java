package com.ohgiraffers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application1 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("조회 하고싶은 배우의 ID : ");
        int acId = sc.nextInt();
        Connection con = getConnection();
        PreparedStatement pstm = null;
        ResultSet rset = null;
        Properties prop = new Properties();
        try {
            prop.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/sakila-query.xml"));
            String query = prop.getProperty("select");
            pstm = con.prepareStatement(query);
            pstm.setInt(1,acId);
            rset = pstm.executeQuery();
            if (rset.next()) {
                System.out.println(rset.getString("first_name") + ","  + rset.getString("last_name"));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstm);
            close(rset);
            close(con);
        }

    }
}
