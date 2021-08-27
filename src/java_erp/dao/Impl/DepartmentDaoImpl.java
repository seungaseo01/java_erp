package java_erp.dao.Impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import java_erp.dao.DepartmentDao;
import java_erp.dto.Department;
import java_erp.dto.Title;
import java_erp.util.JdbcUtil;

public class DepartmentDaoImpl implements DepartmentDao{

	private static final DepartmentDaoImpl instance = new DepartmentDaoImpl();
	
	
	
	public static DepartmentDaoImpl getInstance() {
		return instance;
	}
	

	private DepartmentDaoImpl() {
		// TODO Auto-generated constructor stub
	}


	@Override
	public ArrayList<Department> selectDepartmentByAll() {
		String sql = "select deptno ,deptname ,floor from department";
		ArrayList<Department> list = null;
		
		
		
		return null;
	}

	@Override
	public Title selectTitleByNo(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertTitle(Department department) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateTitle(Department department) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteTitle(Department department) {
		// TODO Auto-generated method stub
		return 0;
	}

}
