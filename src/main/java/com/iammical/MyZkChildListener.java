/**
 * 
 */
package com.iammical;

import java.util.List;

import org.I0Itec.zkclient.IZkChildListener;

/**
 * @Description:
 * @author micalliu
 * @date 2017年9月22日
 */
public class MyZkChildListener implements IZkChildListener {

	/*
	 */
	@Override
	public void handleChildChange(String parentPath, List<String> currentChilds) throws Exception {
		System.out.println("parentPath:" + parentPath + "----->currentChilds:" + currentChilds);
		
	}

}
