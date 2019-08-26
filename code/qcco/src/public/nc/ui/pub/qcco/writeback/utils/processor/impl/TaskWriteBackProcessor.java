package nc.ui.pub.qcco.writeback.utils.processor.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Collections;
import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;

import nc.ui.pub.qcco.writeback.utils.LIMSVO.CProjTask;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.mapping.FirstWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.task.TaskBVO;

/**
 * �����л�д
 * 
 * @author 91967
 * 
 */
public class TaskWriteBackProcessor implements IFirstWriteBackProcessor {

	private CommonUtils utils;

	@Override
	public void setUtils(CommonUtils utils) {
		this.utils = utils;
	}

	@Override
	public void processFirst(WriteBackProcessData data) throws BusinessException {
		process(data);
	}

	/**
	 * ��ȡInsert���Ƭ��
	 * 
	 * @param processData
	 *            processData
	 * @throws BusinessException
	 */
	private void process(WriteBackProcessData processData) throws BusinessException {

		// NC����
		List<ISuperVO> srcDataList = new ArrayList<>();
		ISuperVO[] superVOs = processData.getAggTaskHVO().getChildren(TaskBVO.class);
		Collections.addAll(srcDataList, superVOs);
		if (null == srcDataList || srcDataList.size() <= 0) {
			return ;
		}

		//��Ҫ��д��LIMS����
		List<CProjTask> taskList = initWriteBackList(srcDataList.size());
		
		// Ԥ����pk
		List<Integer> pkList = utils.getPrePk(TaskBVO.class, srcDataList.size());

		// ����ƴ���ֶ���
		StringBuilder insertFields = new StringBuilder();
		// ������ѭ��
		for (Entry<String, String> map : FirstWriteBackStaticMaping.BODY_TASK_MAPPING.entrySet()) {
			String fieldName = map.getKey();
			String[] fields = null;
			if (map.getValue().contains(";")) {
				fields = utils.getWriteBackFields(map.getValue().split(";"));
			} else {
				fields = utils.getWriteBackFields(new String[] { map.getValue() });
			}
			// times�Ǵ���һ�Զ��ϵ��
			for (String field : fields) {
				insertFields.append(field).append(",");
			}

			if (srcDataList != null && srcDataList.size() > 0) {
				for (int i = 0 ;i < srcDataList.size();i++) {
					Object unDofieldValue = srcDataList.get(i).getAttributeValue(fieldName);

					Object realValue = utils.getRealValue(unDofieldValue, fieldName, CommissionBVO.class);

					for (String field : fields) {
						taskList.get(i).setAttributeValue(field, realValue);
					}

				}
			}
		}

		// ��д�����
		if (srcDataList != null) {
			for (int i = 0; i < srcDataList.size(); i++) {
				taskList.get(i).setAttributeValue("seq_num", pkList.get(i));

				taskList.get(i).setAttributeValue("project", processData.getProject().getAttributeValue("name"));
			}
		}
		processData.setTaskList(taskList);
	}

	private List<CProjTask> initWriteBackList(int size) {
		List<CProjTask> rsList = new ArrayList<>();
		
		for(int i = 0;i < size ;i++){
			rsList.add(new CProjTask());
		}
		return rsList;
	}
}
