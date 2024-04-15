package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.MySQLConnector;
import dto.MemberDTO;

/**
 * member DAO
 * @author ljy
 * @since 2024-04-08
 * **/
public class MemberDAO {
	private Connection connector = null;
	private MySQLConnector datasource = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public MemberDAO() {
		datasource = new MySQLConnector();
	} // memberDAO() END

	/**
	 * member signup
	 * @param MemberDTO member
	 * @return boolean
	 * **/
	public boolean signup(MemberDTO member) {
		boolean state = false;
		
		try {
			connector = datasource.connection();
			String query = "insert into member (id, pwd, name, join_date, birthday, email, job, interest, image)"
					+ "value (?, ?, ?, now(), ?, ?, ?, ?, ?)";
			
			pstmt = connector.prepareStatement(query);
			pstmt.setString(1, member.getId());
			pstmt.setString(2, member.getPwd());
			pstmt.setString(3, member.getName());
			pstmt.setDate(4, member.getBirthday());
			pstmt.setString(5, member.getEmail());
			pstmt.setString(6, member.getJob());
			pstmt.setString(7, member.getInterest());
			pstmt.setString(8, member.getImage());
			
			int n = pstmt.executeUpdate();
			
			if (n >= 1) {
				state = true;
			}
		} catch (SQLException e) {
			System.err.println("signup(): " + e.getMessage());
		} finally {
			datasource.close(connector, pstmt, rs);
		}
		
		return state;
	} // signup() END
	
	/**
	 * member login
	 * @param String id, String pwd
	 * @return boolean
	 * **/
	public boolean login(String id, String pwd) {
		boolean state = false;
		try {
			connector = datasource.connection();
			String query = "select count(*) from member where id = ? and pwd = ?";
			
			pstmt = connector.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			if (rs.next()) {
				count = rs.getInt("count(*)");
			}
			System.out.println(count);
			System.out.println(id);
			System.out.println(pwd);
			if (count == 1) {
				state = true;
			}
		} catch (SQLException e) {
			System.err.println("login(): " + e.getMessage());
		} finally {
			datasource.close(connector, pstmt, rs);
		}
		
		return state;
	} // login() END
	
	/**
	 * check id duplicate
	 * @param String id
	 * @return boolean
	 * **/
	public boolean confirmDuplicate(String id) {
		boolean state = false;
		try {
			connector = datasource.connection();
			String query = "select count(*) from member where id = ?";
			
			pstmt = connector.prepareStatement(query);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			int count = 0;
			if (rs.next()) {
				count = rs.getInt("count(*)");
			}
			
			if (count == 1) {
				state = true;
			}
		} catch (SQLException e) {
			System.err.println("login(): " + e.getMessage());
		} finally {
			datasource.close(connector, pstmt, rs);
		}
		
		return state;
	} // confirmDuplicate END
	
	/**
	 * get member's list
	 * @param
	 * @return List<MemberDTO>
	 * **/
	public List<MemberDTO> selectList() {
		List<MemberDTO> memberList = new ArrayList<MemberDTO>();
		
		try {
			connector = datasource.connection();
			String query = "select * from member order by join_date";
			
			pstmt = connector.prepareStatement(query);
			rs = pstmt.executeQuery();
			
			MemberDTO member = null;
			while(rs.next()) {
				member = new MemberDTO();
				member.setId(rs.getString("id"));
				member.setPwd(rs.getString("pwd"));
				member.setName(rs.getString("name"));
				member.setJoinDate(rs.getDate("join_date"));
				member.setBirthday(rs.getDate("birthday"));
				member.setEmail(rs.getString("email"));
				member.setJob(rs.getString("job"));
				member.setInterest(rs.getString("interest"));
				member.setImage(rs.getString("image"));
				
				memberList.add(member);
			}	
		} catch (SQLException e) {
			System.err.println("selectList(): " + e.getMessage());
		} finally {
			datasource.close(connector, pstmt, rs);
		}
		
		return memberList;
	} // selectList() END
	
	/**
	 * delete member
	 * @param String id
	 * @return boolean
	 * **/
	public boolean delete(String id, String pwd) {
		boolean state = false;
		
		try {
			connector = datasource.connection();
			String query = "delete from member where id = ? and pwd = ?";
			
			pstmt = connector.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			
			int n = pstmt.executeUpdate();
			
			if (n >= 1) {
				state = true;
			}
		} catch (SQLException e) {
			System.err.println("login(): " + e.getMessage());
		} finally {
			datasource.close(connector, pstmt, rs);
		}
		
		return state;
	} // delete() END 
	
	/**
	 * get member's detail
	 * @param String id
	 * @return MemberDTO
	 * **/
	public MemberDTO detail(String id) {
		MemberDTO member = new MemberDTO();
		
		try {
			connector = datasource.connection();
			String query = "select * from member where id=?";
			
			pstmt = connector.prepareStatement(query);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				member = new MemberDTO();
				member.setId(rs.getString("id"));
				member.setPwd(rs.getString("pwd"));
				member.setName(rs.getString("name"));
				member.setJoinDate(rs.getDate("join_date"));
				member.setBirthday(rs.getDate("birthday"));
				member.setEmail(rs.getString("email"));
				member.setJob(rs.getString("job"));
				member.setInterest(rs.getString("interest"));
				member.setImage(rs.getString("image"));
			}	
		} catch (SQLException e) {
			System.err.println("detail(): " + e.getMessage());
		} finally {
			datasource.close(connector, pstmt, rs);
		}
		
		return member;
	} // detail END
	
	/**
	 * update member's info
	 * @param MemberDTO member
	 * @return boolean
	 * **/
	public boolean update(MemberDTO member) {
		boolean state = false;
		
		try {
			connector = datasource.connection();
			String query = "update member set pwd = ?, name = ?, birthday = ?, email = ?, job = ?, interest = ?, image = ? where id = ?";
			
			pstmt = connector.prepareStatement(query);
			
			pstmt = connector.prepareStatement(query);
			pstmt.setString(1, member.getPwd());
			pstmt.setString(2, member.getName());
			pstmt.setDate(3, member.getBirthday());
			pstmt.setString(4, member.getEmail());
			pstmt.setString(5, member.getJob());
			pstmt.setString(6, member.getInterest());
			pstmt.setString(7, member.getImage());
			pstmt.setString(8, member.getId());
			int n = pstmt.executeUpdate();
			
			if (n >= 1) {
				state = true;
			}
		} catch (SQLException e) {
			System.err.println("update(): " + e.getMessage());
		} finally {
			datasource.close(connector, pstmt, rs);
		}
		
		return state;
	} // update END
}
