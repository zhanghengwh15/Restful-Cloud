package com.tool;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RuntimeUtil;
import cn.hutool.db.ds.DataSourceWrapper;
import cn.hutool.db.ds.simple.SimpleDataSource;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class DataSourceWrapperTest {

	@Test
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
		Assert.assertEquals("test.driver", wrapper.getDriver());
		Assert.assertEquals(simpleDataSource, wrapper.getRaw());
	}
}
