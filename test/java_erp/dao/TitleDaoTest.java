package java_erp.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java_erp.dao.Impl.TitleDaoImpl;
import java_erp.dto.Title;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TitleDaoTest {
	private TitleDao dao;
	

	@Before
	public void setUp() throws Exception {
		dao = TitleDaoImpl.getInstance();
	}

	
	@After
	public void tearDown() throws Exception {
		System.out.println();
		dao = null;
	}

	@Test
	public void test01SelectTitleByAll() {
		System.out.println("test01SelectTitleByAll()");
		ArrayList<Title> list = dao.selectTitleByAll();
		Assert.assertNotEquals(0, list.size());
		
		list.stream().forEach(System.out::println);
	}

	@Test
	public void test02SelectTitleByNo() {
		System.out.println("test02SelectTitleByNo()");
		Title selectTitle = dao.selectTitleByNo(new Title(2));
		Assert.assertNotNull(selectTitle);
		System.out.println(selectTitle);
	}

	@Test
	public void test03InsertTitle() {
		System.out.println("test03InsertTitle()");
		Title newTitle = new Title(3, "과장");
		
		int res = dao.insertTitle(newTitle);
		Assert.assertEquals(1, res);
		System.out.println("res >> "+ res);
	}

	@Test
	public void test04UpdateTitle() {
		System.out.println("test04UpdateTitle()");
		Title updateTitle = new Title(3, "대리");
		
		int res = dao.updateTitle(updateTitle);
		Assert.assertEquals(1, res);
		System.out.println("res >> "+ res);
	}

	@Test
	public void test05DeleteTitle() {
		System.out.println("test05DeleteTitle()");
		Title deleteTitle = new Title(3);
		
		int res = dao.deleteTitle(deleteTitle);
		Assert.assertEquals(1, res);
		System.out.println("res >> "+ res);
	}

}
