package java_erp.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java_erp.dao.DepartmentDao;
import java_erp.dto.Department;
import java_erp.dto.Title;
import java_erp.util.JdbcUtil;

/**
 * Impl은 무조건 singleton pattern 구현
 *
 */

public class DepartmentDaoImpl implements DepartmentDao {

	private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();

	public static DepartmentDaoImpl getInstance() {
		return instance;
	}

	private DepartmentDaoImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Department> selectDepartmentByAll() {
//		1.db접속(시간많이 걸림) <- Connection pool = Connection
//		2.sql을 데이터베이스에 맞는 코드로준비(스트링을 명령문으로 변경) = PreparedStatement
//		3.sql에서 ?를 입력 매개변수값으로 치환해서 sql명령문을 완성
//		4.sql명령문 실행(select : executeQuery
//						insert,update,delete : executeUpdate)
//		5.만약 executeQuery일경우 :sql결과(Result set)를 클래스로 변환
		String sql = "select deptno ,deptname ,floor from department";
		ArrayList<Department> list = null;

		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			list = new ArrayList<Department>();
			while (rs.next()) {
				// deptno ,deptname ,floor
				list.add(getDepartment(rs)); // ArrayList에 넣기
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Department selectDepartmentByNo(Department department) {// deptno가 기본키이기 때문에 결과값하나 기본키가 아니면 ArrayList에 담아야 함
//		1.db접속(시간많이 걸림) <- Connection pool = Connection
//		2.sql을 데이터베이스에 맞는 코드로준비(스트링을 명령문으로 변경) = PreparedStatement
//		3.sql에서 ?를 입력 매개변수값으로 치환해서 sql명령문을 완성
//		4.sql명령문 실행(select : executeQuery
//						insert,update,delete : executeUpdate)
//		5.만약 executeQuery일경우 :sql결과(Result set)를 클래스로 변환
		String sql = "select deptno ,deptname ,floor from department where deptno = ?";
		try (Connection con = JdbcUtil.getConnection(); 
				PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, department.getDeptNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getDepartment(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Department getDepartment(ResultSet rs) throws SQLException {
		// deptno ,deptname ,floor
		int deptNo = rs.getInt("deptno");
		String deptName = rs.getString("deptname");
		int floor = rs.getInt("floor");
		Department newDept = new Department(deptNo, deptName, floor);// 클래스 생성
		return newDept;
	}

	@Override
	public int insertDepartment(Department department) {//		1.db접속(시간많이 걸림) <- Connection pool = Connection
//		2.sql을 데이터베이스에 맞는 코드로준비(스트링을 명령문으로 변경) = PreparedStatement
//		3.sql에서 ?를 입력 매개변수값으로 치환해서 sql명령문을 완성
//		4.sql명령문 실행(select : executeQuery
//						insert,update,delete : executeUpdate)
//		5.만약 executeQuery일경우 :sql결과(Result set)를 클래스로 변환
		String sql = "insert into department values(?,?,?)";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, department.getDeptNo());
			pstmt.setString(2, department.getDeptName());
			pstmt.setInt(3, department.getFloor());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateDepartment(Department department) {
//		1.db접속(시간많이 걸림) <- Connection pool = Connection
//		2.sql을 데이터베이스에 맞는 코드로준비(스트링을 명령문으로 변경) = PreparedStatement
//		3.sql에서 ?를 입력 매개변수값으로 치환해서 sql명령문을 완성
//		4.sql명령문 실행(select : executeQuery
//						insert,update,delete : executeUpdate)
//		5.만약 executeQuery일경우 :sql결과(Result set)를 클래스로 변환
		String sql = "update department set deptname = ? where deptno = ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setString(1, department.getDeptName());
			pstmt.setInt(2, department.getDeptNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int deleteDepartment(Department department) {
//		1.db접속(시간많이 걸림) <- Connection pool = Connection
//		2.sql을 데이터베이스에 맞는 코드로준비(스트링을 명령문으로 변경) = PreparedStatement
//		3.sql에서 ?를 입력 매개변수값으로 치환해서 sql명령문을 완성
//		4.sql명령문 실행(select : executeQuery
//						insert,update,delete : executeUpdate)
//		5.만약 executeQuery일경우 :sql결과(Result set)를 클래스로 변환
		String sql = "delete from department where deptno =?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			pstmt.setInt(1, department.getDeptNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}

}
