package java_erp.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import java_erp.dao.EmployeeDao;
import java_erp.dto.Department;
import java_erp.dto.Employee;
import java_erp.dto.Title;
import java_erp.util.JdbcUtil;

public class EmployeeDaoImpl implements EmployeeDao {
	private static final EmployeeDaoImpl Instance = new EmployeeDaoImpl();

	public static EmployeeDaoImpl getInstance() {
		return Instance;
	}

	private EmployeeDaoImpl() {
	}

	@Override
	public ArrayList<Employee> selectEmployeeByAll() {
		// empno ,empname ,title ,manager ,salary ,dno
		String sql = "select empno ,empname ,title ,manager ,salary ,dno from employee";
		ArrayList<Employee> list = null;

		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if(rs.next()) {
				list = new ArrayList<Employee>();
				do {
				list.add(getEmployee(rs));
			} while (rs.next());

			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	private Employee getEmployee(ResultSet rs) throws SQLException {
		int empNo = rs.getInt("empno");
		String empName = rs.getString("empname");

		Title title = rs.getInt("title") == 0 ? null : new Title(rs.getInt("title"));
		Employee manager = rs.getInt("manager") == 0 ? null : new Employee(rs.getInt("manager"));
		int salary = rs.getInt("salary");
		Department dept = rs.getInt("dno") == 0 ? null : new Department(rs.getInt("dno"));
		return new Employee(empNo, empName, title, manager, salary, dept);
	}

	@Override
	public Employee selectEmployeeByNo(Employee emp) {
		String sql = "select empno ,empname ,title ,manager ,salary ,dno from employee where empno = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);) {
			pstmt.setInt(1, emp.getEmpNo());
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getEmployee(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int insertEmployee(Employee emp) {
//		1.db접속(시간많이 걸림) <- Connection pool = Connection
//		2.sql을 데이터베이스에 맞는 코드로준비(스트링을 명령문으로 변경) = PreparedStatement
//		3.sql에서 ?를 입력 매개변수값으로 치환해서 sql명령문을 완성
//		4.sql명령문 실행(select : executeQuery
//						insert,update,delete : executeUpdate)
//		5.만약 executeQuery일경우 :sql결과(Result set)를 클래스로 변환
		// empno ,empname ,title ,manager ,salary ,dno
		String sql = "insert into employee values(?, ?, ? , ?, ?, ?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {

			pstmt.setInt(1, emp.getEmpNo());
			pstmt.setString(2, emp.getEmpName());
			// 값이 null인것을 감안(title,manager,dno)
			try {
				pstmt.setInt(3, emp.getTitle().gettNo());
			} catch (NullPointerException e) {
				pstmt.setNull(3, Types.INTEGER);
			}

			try {
				pstmt.setInt(4, emp.getManager().getEmpNo());
			} catch (NullPointerException e) {
				pstmt.setNull(4, Types.INTEGER);
			}

			pstmt.setInt(5, emp.getSalary());
			try {
				pstmt.setInt(6, emp.getDept().getDeptNo());
			} catch (NullPointerException e) {
				pstmt.setNull(6, Types.INTEGER);
			}
			System.out.println(pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public int updateEmployee(Employee emp) {
		String sql = "update employee " + "   set empname = ?,salary =?, title = ?, manager =?,dno = ?"
				+ " where empno =?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, emp.getEmpName());
			pstmt.setInt(2, emp.getSalary());
			try {
				pstmt.setInt(3, emp.getTitle().gettNo());
			} catch (NullPointerException e) {
				pstmt.setNull(3, Types.INTEGER);
			}

			try {
				pstmt.setInt(4, emp.getManager().getEmpNo());
			} catch (NullPointerException e) {
				pstmt.setNull(4, Types.INTEGER);
			}
			try {
				pstmt.setInt(5, emp.getDept().getDeptNo());
			} catch (NullPointerException e) {
				pstmt.setNull(5, Types.INTEGER);
			}
			pstmt.setInt(6, emp.getEmpNo());
			System.out.println("update pstmt>> " + pstmt);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}

	@Override
	public int deleteEmployee(Employee emp) {
		String sql = "delete from employee where empno = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, emp.getEmpNo());
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

}
