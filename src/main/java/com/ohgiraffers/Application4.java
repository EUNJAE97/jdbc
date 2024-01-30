package com.ohgiraffers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Locale;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;

public class Application4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("수정할 배우의 ID를 입력하세요");
        int num = sc.nextInt();
        System.out.println("수정할 First name을 입력하세요.");
        sc.nextLine();
        String first = sc.nextLine().toUpperCase();
        System.out.println("수정할 Last name을 입력하세요.");
        String last = sc.nextLine().toUpperCase();
        Connection con = getConnection();
        PreparedStatement pstm = null;
        Properties prob = new Properties();
        int result = 0;
        ActorDTO actor = new ActorDTO();
        actor.setActId(num);
        actor.setFirstName(first);
        actor.setLastName(last);
        try {
            prob.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/sakila-query.xml"));
            String query = prob.getProperty("update");
            pstm = con.prepareStatement(query);
            pstm.setString(1,first);
            pstm.setString(2,last);
            pstm.setInt(3,num);
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
            System.out.println("수정을 완료하였습니다.");
        } else {
            System.out.println("실패하였습니다.");
        }
    }
}
