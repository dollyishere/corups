package dao;

import java.util.ArrayList;

import dto.MemberDTO;

/**
 * member DAO
 * @author ljy
 * @since 2024-04-08
 * **/
public class MemberDAO {

	public MemberDAO() {
		
	} // memberDAO() END

	/**
	 * member signup
	 * @param MemberDTO member
	 * @return boolean
	 * **/
	public boolean signup(MemberDTO member) {
		return false;
	} // signup() END
	
	/**
	 * member login
	 * @param String id, String pw
	 * @return boolean
	 * **/
	public boolean login(String id, String pw) {
		return false;
	} // login() END
	
	/**
	 * check id duplicate
	 * @param String id
	 * @return boolean
	 * **/
	public boolean confirmDuplicate(String id) {
		return false;
	} // confirmDuplicate END
	
	/**
	 * get member's list
	 * @param
	 * @return ArrayList<MemberDTO>
	 * **/
	public ArrayList<MemberDTO> selectList() {
		return null;
	} // selectList() END
	
	/**
	 * delete member
	 * @param String id
	 * @return boolean
	 * **/
	public boolean delete(String id) {
		return false;
	} // delete() END 
	
	/**
	 * get member's detail
	 * @param String id
	 * @return MemberDTO
	 * **/
	public MemberDTO detail(String id) {
		return null;
	} // detail END
	
	/**
	 * update member's info
	 * @param MemberDTO member
	 * @return boolean
	 * **/
	public boolean update(MemberDTO member) {
		return false;
	} // update END
}
