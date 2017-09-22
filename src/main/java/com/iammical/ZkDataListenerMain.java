/**
 * 
 */
package com.iammical;

import java.io.Serializable;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.zookeeper.CreateMode;

/**
 * @Description:获取与更新数据
 * @author micalliu
 * @date 2017年9月22日
 */
public class ZkDataListenerMain {
	static String server = "127.0.0.1:2181";

	static String rootPath = "/root3";

	public static void main(String[] args) throws Exception {
		ZkClient zkClient = new ZkClient(server, 3 * 1000);
		zkClient.subscribeDataChanges(rootPath, new IZkDataListener() {

			@Override
			public void handleDataDeleted(String dataPath) throws Exception {
				System.out.println("handleDataDeleted dataPath : " + dataPath);
			}

			@Override
			public void handleDataChange(String dataPath, Object data) throws Exception {
				System.out.println("handleDataChange dataPath : " + dataPath + "---->data:" + data);
			}
		});

		User user = new User(200, "小明");

		// PERSISTENT:The znode will not be automatically deleted upon client's  disconnect
		// EPHEMERAL: The znode will be deleted upon the client's disconnect.
		zkClient.create(rootPath, user, CreateMode.EPHEMERAL);
		User data = zkClient.readData(rootPath);
		System.out.println(data);
		Thread.sleep(2000);
		
		
		
		User user2 = new User(100, "小红");
		zkClient.writeData(rootPath, user2);
		Thread.sleep(2000);
		zkClient.delete(rootPath);
		Thread.sleep(1000);

	}

}

class User implements Serializable {
	/**
	 * 必须序列化
	 */
	private static final long serialVersionUID = 1L;
	private int code;
	private String name;

	public User(int code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "User [code=" + code + ", name=" + name + "]";
	}

}
