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


public class Application5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("삭제할 배우 ID를 입력 : ");
        int num = sc.nextInt();
        sc.nextLine();
        Connection con = getConnection();
        PreparedStatement pstm = null;
        Properties prob = new Properties();
        ActorDTO actor = new ActorDTO();
        actor.setActId(num);
        int result = 0;
        try {
            prob.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/sakila-query.xml"));
            String query = prob.getProperty("delete");
            pstm = con.prepareStatement(query);
            pstm.setInt(1,num);
            result = pstm.executeUpdate();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close(pstm);
            close(con);
        }
        if (result > 0) {
            System.out.println("삭제 완료");
        } else {
            System.out.println("삭제 실패");
        }
    }
}
