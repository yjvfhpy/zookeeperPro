package com.iammical;

import org.I0Itec.zkclient.ZkClient;

/**
 *
 */
public class CreateSession {
	public static void main(String[] args) {
		ZkClient zk = new ZkClient("127.0.0.1:2181", 3 * 1000);

		System.out.println(zk.getChildren("/"));
	   // zk.createPersistent("/root/apps/test/test", true);
	    //zk.create("/root2","mydata", CreateMode.PERSISTENT);//创建目录并写入数据  
	    
		System.out.println(zk.getChildren("/"));

		String data = zk.readData("/root2");
		System.out.println(data);

		// zk.deleteRecursive("/root");
		System.out.println(zk.getChildren("/"));
		
		//zk.createPersistent("/root4", true);
		//zk.create("/root4/m4","m3data", CreateMode.PERSISTENT);//创建目录并写入数据  
		
		
		zk.delete("/root/apps/test1");
		
	}
}
