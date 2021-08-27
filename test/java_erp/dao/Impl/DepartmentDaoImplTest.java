package java_erp.dao.Impl;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java_erp.dao.DepartmentDao;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DepartmentDaoImplTest {
	private DepartmentDao dao;
	
	@Before
	public void setUp() throws Exception {
		dao = DepartmentDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		System.out.println();
		dao = null;
	}

	@Test
	public void test01SelectDepartmentByAll() {
		System.out.println("test01SelectDepartmentByAll()");
		
	}

	@Test
	public void test02SelectTitleByNo() {
		System.out.println("test02SelectTitleByNo()");
	}

	@Test
	public void test03InsertTitle() {
		System.out.println("test03InsertTitle()");
	}

	@Test
	public void test04UpdateTitle() {
		System.out.println("test04UpdateTitle()");
	}

	@Test
	public void test05DeleteTitle() {
		System.out.println("test05DeleteTitle()");
	}

}
