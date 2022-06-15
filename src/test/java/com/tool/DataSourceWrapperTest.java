package com.tool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import cn.hutool.db.ds.DataSourceWrapper;
import cn.hutool.db.ds.pooled.DbConfig;
import cn.hutool.db.ds.pooled.PooledDataSource;
import cn.hutool.db.ds.simple.SimpleDataSource;
import cn.hutool.setting.Setting;
import lombok.Data;
import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.sql.SQLException;


@Data
public class DataSourceWrapperTest {


	private Db db;

	@Before
	public void init() {
		String db = FileUtil.getUserHomePath() + "\\restfulCloud\\restfulCloud.db";
		final boolean exist = FileUtil.exist(db);
		if (!exist) {
			File touch = FileUtil.touch(db);
		}
		String osName = System.getProperty("os.name");
		if(osName.startsWith("Mac")) {
			RuntimeUtil.exec("sudo chmod -R 777 "+db);
		}

		DbConfig config = new DbConfig("jdbc:sqlite:"+db, "", "");
		config.setMaxActive(10);
		PooledDataSource pooledDataSource = new PooledDataSource(config);
	    this.setDb(Db.use(pooledDataSource));
	}


	/**
	 * 对增删改查做单元测试
	 *
	 * @throws SQLException SQL异常
	 */

	@Test
	// @Ignore
	public void crudTest() throws SQLException {

		this.init();

		// db.execute("CREATE TABLE test(a INTEGER, b BIGINT)");
		// 增
		Long id = db.insertForGeneratedKey(Entity.create("user").set("name", "unitTestUser").set("age", 66));
		Assert.assertTrue(id > 0);
		Entity result = db.get("user", "name", "unitTestUser");
		assertEquals(66, result.getInt("age"));

		// 改
		int update = db.update(Entity.create().set("age", 88), Entity.create("user").set("name", "unitTestUser"));
		Assert.assertTrue(update > 0);
		Entity result2 = db.get("user", "name", "unitTestUser");
		assertEquals(88, result2.getInt("age"));

		// 删
		int del = db.del("user", "name", "unitTestUser");
		Assert.assertTrue(del > 0);
		Entity result3 = db.get("user", "name", "unitTestUser");
		Assert.assertNull(result3);
	}


	@Test
	public void testAdd() {
		assertEquals(42, Integer.sum(19, 23));
	}
}
