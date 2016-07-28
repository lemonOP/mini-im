package com.james.im.bootloader;

import com.james.tm.taskmanage.MtmManager;

/**
 * miniTask 启动
 * 
 * @author james
 * 
 */
public class MiniTaskBootLoader implements IBootLoader {

	private  MtmManager mtmManager;

	@Override
	public void boot() {
		// TODO Auto-generated method stub

		mtmManager = new MtmManager.Builder().isOpenInfo(false).build();
		mtmManager.initialize();
		

	}

	public void setMtmManager(MtmManager mtmManager) {
		this.mtmManager = mtmManager;
	}

	public MtmManager getMtmManager() {
		if (mtmManager == null) {
			this.boot();
		}
		return mtmManager;
	}

}
