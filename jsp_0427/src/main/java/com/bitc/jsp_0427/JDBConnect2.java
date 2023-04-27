package com.bitc.jsp_0427;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class JDBConnect2 {
  public Connection con;
  public Statement stmt;
  public PreparedStatement psmt;
  public ResultSet rs;

  private String dbDriver;
  private String dbUrl;
  private String dbUserId;
  private String dbUserPw;

  public JDBConnect2(){
    dbDriver = "com.mysql.cj.jdbc.Driver";
    dbUrl = "jdbc:mysql://localhost:3306/testdb?useUnicode=true&characterEncoding=utf-8&serverTimezone=UTC";
    dbUserId = "full505";
    dbUserPw = "full505";
  }

  public JDBConnect2(String dbDriver, String dbUrl, String dbUserId, String dbUserPw){
    this.dbDriver = dbDriver;
    this.dbUrl = dbUrl;
    this.dbUserId = dbUserId;
    this.dbUserPw = dbUserPw;
  }
  public void open(){
    try{
      //JDBC ����̹� �ε�
      Class.forName(dbDriver);

      // DB�� ����
      con = DriverManager.getConnection(dbUrl, dbUserId, dbUserPw);

      System.out.println("DB Connect Success �����߽��ϴ�.");
    }
    catch (Exception e){
      e.printStackTrace();
    }
  }

  public int postWrite(String title, String userId, String content){
    String sql = "INSERT INTO board (title, content, id, postdate) ";

    sql += "VALUES (?, ?, ?, NOW()) ";
    int result = 0; // �� ���� �����Ͱ� ������ Ȯ�ο�

    try{
      // ������ SQL�� �Բ� PreparedStatement ��ü�� ����
      psmt = con.prepareStatement(sql);
      // ���Ķ���Ϳ� ���� �����͸� �߰�
      psmt.setString(1, title);
      psmt.setString(2, content);
      psmt.setString(3, userId);

      // insert ������ ����
      result = psmt.executeUpdate();

      System.out.println("�Խñ��� ��� �Ǿ����ϴ�!");
    }
    catch (SQLException e){
      e.printStackTrace();
    }
    return result;
  };

  public List<Map<String, String>> postList(){
    String sql = "SELECT num, title, id, content, postdate, visitcount FROM board ";

    // ������ ������� ������ ArrayList
    List<Map<String, String >> dataList = new ArrayList<>();

    try{
      psmt = con.prepareStatement(sql);
      rs = psmt.executeQuery();

      while (rs.next()){
        int postNum = rs.getInt("num");
        String postTitle = rs.getString("title");
        String postUser = rs.getString("id");
        String postContent = rs.getString("content");
        String postDate = rs.getString("postdate");
        String postVisit = rs.getString("visitcount");

        // HashMap Ÿ�Կ� ����
        Map<String, String> data = new HashMap<>();
        data.put("postNum", String.valueOf(postNum));
        data.put("postTitle", postTitle);
        data.put("postUser", postUser);
        data.put("postContent", postContent);
        data.put("postDate", postDate);
        data.put("postVisit", postVisit);

        // ArrayList�� �Խù� 1��(�ؽø� ��ü 1��)�� ���� ������ �߰���
        dataList.add(data);
      }
    }
    catch (SQLException e){
      e.printStackTrace();
    }

    // ������� ������ ����Ʈ�� ��ȯ
    return dataList;
  }

  //�ڿ� �ݳ�
  public void close(){
    try {
      if(rs != null) rs.close();
      if(stmt != null) stmt.close();
      if(psmt != null) psmt.close();
      if(con != null) con.close();

      System.out.println("JDBC Close");
    } catch (Exception e){
      e.printStackTrace();
    }
  }
}