package nc.ui.pub.qcco.writeback.utils.processor.impl;

import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.ui.pub.qcco.writeback.utils.processor.ISecWriteBackProcessor;
import nc.vo.pub.BusinessException;

/**
 * result��һ�κ͵ڶ��λ�д
 * @author 91967
 *
 */
public class ResultWriteBackProcessor implements IFirstWriteBackProcessor, ISecWriteBackProcessor {

	@Override
	public void processSec(WriteBackProcessData data) throws BusinessException{
		// TODO �Զ����ɵķ������

	}

	@Override
	public void processFirst(WriteBackProcessData data) throws BusinessException{
		// TODO �Զ����ɵķ������

	}

	private CommonUtils utils;
	@Override
	public void setUtils(CommonUtils utils) {
		this.utils = utils;
	}

}
