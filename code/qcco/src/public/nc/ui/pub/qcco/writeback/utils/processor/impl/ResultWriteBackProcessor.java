package nc.ui.pub.qcco.writeback.utils.processor.impl;

import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.ui.pub.qcco.writeback.utils.processor.ISecWriteBackProcessor;
import nc.vo.pub.BusinessException;

/**
 * result第一次和第二次回写
 * @author 91967
 *
 */
public class ResultWriteBackProcessor implements IFirstWriteBackProcessor, ISecWriteBackProcessor {

	@Override
	public void processSec(WriteBackProcessData data) throws BusinessException{
		// TODO 自动生成的方法存根

	}

	@Override
	public void processFirst(WriteBackProcessData data) throws BusinessException{
		// TODO 自动生成的方法存根

	}

	private CommonUtils utils;
	@Override
	public void setUtils(CommonUtils utils) {
		this.utils = utils;
	}

}
