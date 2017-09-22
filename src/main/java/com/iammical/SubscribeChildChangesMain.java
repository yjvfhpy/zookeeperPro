/**
 * 
 */
package com.iammical;

import java.util.Random;

import org.I0Itec.zkclient.ZkClient;

/**
 * @Description:
 * @author micalliu
 * @date 2017年9月22日
 */
public class SubscribeChildChangesMain {
	static String server = "127.0.0.1:2181";

	static String rootPath = "/root/apps";

	public static void main(String[] args) throws Exception {
		ZkClient zkClient = new ZkClient(server, 3 * 1000);
		zkClient.subscribeChildChanges(rootPath, new MyZkChildListener());

		// System.in.read();
		
		
		try {
			int rd = new Random().nextInt(100);
			int next = rd + 1;
			zkClient.createPersistent(rootPath + "/test" + rd);
			Thread.sleep(2000);

			zkClient.createPersistent(rootPath + "/test" + next);
			Thread.sleep(2000);

			zkClient.delete(rootPath + "/test" + rd);
			Thread.sleep(2000);

			zkClient.delete(rootPath + "/test" + next);
			Thread.sleep(2000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
