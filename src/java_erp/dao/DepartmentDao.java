package java_erp.dao;

import java.util.ArrayList;

import java_erp.dto.Department;
import java_erp.dto.Title;

public interface DepartmentDao {	
	ArrayList<Department> selectDepartmentByAll();
	Title selectTitleByNo(Department department);

	int insertTitle(Department department);
	int updateTitle(Department department);
	int deleteTitle(Department department);
}
