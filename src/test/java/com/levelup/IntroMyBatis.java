package com.levelup;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.levelup.domain.Agency;

public class IntroMyBatis {

	private SqlSession session;

	@Before
	public void setup() throws IOException, SQLException {

		String resource = "mybatis-config.xml";
		InputStream inputStream = Resources.getResourceAsStream(resource);
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder()
				.build(inputStream);

		session = sqlSessionFactory.openSession();

		seedData();

	}

	private void seedData() {

		int createTable = session.update("com.levelup.Agency.createTable");

		int seedAgency = session.update("com.levelup.Agency.seedAgency");

		System.out.println(createTable);
		System.out.println(seedAgency);
	}
	
	@Test
	public void test() {

		Agency agency = session
				.selectOne("com.levelup.Agency.selectAgency", 10);

		System.out.println(agency);
	}
	
	@After
	public void tearDown() {
		session.close();
	}
	
}
