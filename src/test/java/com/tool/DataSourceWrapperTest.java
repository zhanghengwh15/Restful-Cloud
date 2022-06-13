package com.tool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.db.ds.DataSourceWrapper;
import cn.hutool.db.ds.simple.SimpleDataSource;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;


public class DataSourceWrapperTest {

	@Test
	@SneakyThrows
	public void cloneTest(){

		String db = FileUtil.getUserHomePath() + "\\restfulCloud\\restfulCloud.db";
		final boolean exist = FileUtil.exist(db);
		if (!exist) {
			File touch = FileUtil.touch(db);
		}
		String osName = System.getProperty("os.name");
		if(osName.startsWith("Mac")) {
			RuntimeUtil.exec("sudo chmod -R 777 "+db);
		}

		final SimpleDataSource simpleDataSource = new SimpleDataSource("jdbc:sqlite:"+db, "", "");
		final DataSourceWrapper wrapper = new DataSourceWrapper(simpleDataSource, "test.driver");

		// final DataSourceWrapper clone = wrapper.clone();
		assertEquals("test.driver", wrapper.getDriver());
		assertEquals(simpleDataSource, wrapper.getRaw());
	}


	@Test
	public void testAdd() {
		assertEquals(42, Integer.sum(19, 23));
	}
}
