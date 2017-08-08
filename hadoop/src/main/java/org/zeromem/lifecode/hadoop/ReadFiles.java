package org.zeromem.lifecode.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * Created by zeromem on 2017/8/8.
 */
public class ReadFiles {
//	public static final String fsUri = "hdfs://192.168.1.1:9000";
	public static final String fsUri = "file:///";
	public static void main(String[] args) throws URISyntaxException, IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		// new a configuration.
		Configuration conf = new Configuration();
		// get a filesystem with target namenode uri.
		FileSystem fs = FileSystem.get(new URI(fsUri), conf);
		Path path = new Path("/");
		// fetch file meta info from fs.
		FileStatus[] statuses = fs.listStatus(path, (p) -> p.getName().startsWith("C"));
		for (FileStatus status : statuses) {
			System.out.println(status);
		}

		System.out.println("================================");

		// listStatus return a FileStatus[]
		// use reflect to call FileSystem's private method listStatus(ArrayList, Path, PathFilter)
		Method method = FileSystem.class.getDeclaredMethod("listStatus", ArrayList.class, Path.class, PathFilter.class);
		method.setAccessible(true);

		// third parameter lambda expression must convert to PathFilter
		ArrayList<FileStatus> arg = new ArrayList<>();
		method.invoke(fs, arg, path, (PathFilter)(Path p) -> p.getName().startsWith("C"));
		System.out.println(arg);
	}
}
