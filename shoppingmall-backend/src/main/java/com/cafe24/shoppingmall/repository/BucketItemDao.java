package com.cafe24.shoppingmall.repository;

import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BucketItemDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	@Autowired
	private DataSource dataSource;

	public Boolean insertBucketItems(Map<String, Object> bucketMap) {
		return 0 < sqlSession.insert("bucketitem.insertItems", bucketMap);
	}

	
}
