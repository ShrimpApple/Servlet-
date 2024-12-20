package com.company.ex03;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
	
	
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	public MemberDAO() {
		try {
			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:/comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/mysql");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<MemberVO> listMembers(){
		
		List<MemberVO> list = new ArrayList<>();  //메모리 할당
		
			try {
				con = dataFactory.getConnection();
				String query = "select * from t_member";
				System.out.println("prepareStatement:" + query);
				pstmt = con.prepareStatement(query);
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					String id = rs.getString("id");
					String pwd = rs.getString("pwd");
					String name = rs.getString("name");
					String email = rs.getString("email");
					Date joinDate = rs.getDate("joinDate");
					
					MemberVO vo = new MemberVO();
					
					vo.setId(id);
					vo.setPwd(pwd);
					vo.setName(name);
					vo.setEmail(email);
					vo.setJoinDate(joinDate);
					
					list.add(vo);
				}
				rs.close();
				pstmt.close();
				con.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			return list;
	}
	
	public void addMember(MemberVO memberVo){
		try {
			
			con = dataFactory.getConnection();
			String id = memberVo.getId();
			String pwd = memberVo.getPwd();
			String name = memberVo.getName();
			String email = memberVo.getEmail();
			
			String query = "insert into t_member";
					query += " (id, pwd, name, email)";
					query += " values(?,?,?,?)";
					
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			pstmt.setString(4, email);
			pstmt.executeUpdate();
			pstmt.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void delMember(String id){
		try 
			{
			con = dataFactory.getConnection();
			String query = "delete from t_member " + "where id = ?";
			System.out.println("prepareStatement: " + query);
			pstmt = con.prepareStatement(query);
			
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			pstmt.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
