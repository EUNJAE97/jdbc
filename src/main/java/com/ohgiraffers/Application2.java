package com.ohgiraffers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import static com.ohgiraffers.common.JDBCTemplate.close;
import static com.ohgiraffers.common.JDBCTemplate.getConnection;


public class Application2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("조회할 성을 입력 : ");
        String id = sc.nextLine().toUpperCase();
        Connection con = getConnection();
        PreparedStatement pstm = null;
        ResultSet rset = null;
        ActorDTO ac = null;
        List<ActorDTO> actor = null;
        Properties prob = new Properties();
        try {
            prob.loadFromXML(new FileInputStream("src/main/java/com/ohgiraffers/mapper/sakila-query.xml"));
            String query = prob.getProperty("select2");
            pstm = con.prepareStatement(query);
            pstm.setString(1,id);
            actor = new ArrayList<>();
            rset = pstm.executeQuery();
            while (rset.next()) {
                ac = new ActorDTO();
                ac.setActId(rset.getInt("actor_id"));
                ac.setLastName(rset.getString("first_name"));
                ac.setFirstName(rset.getString("last_name"));
                actor.add(ac);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(rset);
            close(pstm);
            close(con);
        }
        for (ActorDTO actorDTO : actor) {
            System.out.println(actorDTO);
        }
    }
}
