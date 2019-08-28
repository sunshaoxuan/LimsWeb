package nc.ui.pub.qcco.writeback.utils.processor.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.mozilla.javascript.edu.emory.mathcs.backport.java.util.Collections;

import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.CProjTask;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Result;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Sample;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Test;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.mapping.FirstWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.ui.pub.qcco.writeback.utils.processor.ISecWriteBackProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.qcco.task.AggTaskHVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskSVO;

/**
 * result第一次和第二次回写
 * @author 91967
 *
 */
public class ResultWriteBackProcessor implements IFirstWriteBackProcessor, ISecWriteBackProcessor {

	private CommonUtils utils;
	@Override
	public void setUtils(CommonUtils utils) {
		this.utils = utils;
	}
	
	@Override
	public void processSec(WriteBackProcessData data) throws BusinessException{
		// TODO 自动生成的方法存根

	}

	@Override
	public void processFirst(WriteBackProcessData data) throws BusinessException{
		process(data);
	}


	
	/**
     * 获取Insert语句片断
     *
     * @param processData processData
     * @throws BusinessException
     */
    private void process(WriteBackProcessData processData)
            throws BusinessException {
		// NC数据
		List<ISuperVO> srcDataList = new ArrayList<>();
		// 获取所有的实验条件参数VO
		ISuperVO[] superVOs = getAllTaskSVO(processData);
		Collections.addAll(srcDataList, superVOs);
		if (null == srcDataList || srcDataList.size() <= 0) {
			return ;
		}

		//相关数据
		List<Sample> firstSampleList = processData.getFirstSampleList();
		List<Test> firstTestList = processData.getFirstTestList();
		List<CProjTask> taskList = processData.getTaskList();
		
		//要回写的LIMS 数据--任务单孙表试验条件
		List<Result> allResultList = initWriteBackList(srcDataList.size());
		
		// 预申请PK
		List<Integer> pkList = utils.getPrePk(TaskSVO.class, srcDataList.size());
		processData.setMaxResult(pkList.get(srcDataList.size()-1));

		// 进行列循环
		for (Entry<String, String> map : FirstWriteBackStaticMaping.GRAND_CONDITION_MAPPING.entrySet()) {
			String fieldName = map.getKey();
			String[] fields = null;
			if (map.getValue().contains(";")) {
				fields = utils.getWriteBackFields(map.getValue().split(";"));
			} else {
				fields = utils.getWriteBackFields(new String[] { map.getValue() });
			}
			if (srcDataList != null && srcDataList.size() > 0) {
				for (int i = 0; i < srcDataList.size(); i++) {
					Object unDofieldValue = srcDataList.get(i).getAttributeValue(fieldName);
					Object realValue = utils.getRealValue(unDofieldValue, fieldName, TaskSVO.class);
					for (String field : fields) {
						allResultList.get(i).setAttributeValue(field, realValue);
					}
				}
			}
		}

		for (int i = 0; i < srcDataList.size(); i++) {
			// 写入主键和父主键(只支持单主键,要多主键,需要参考上面的字段添加逻辑)
			// 获取上层的主键:
			String fatherPk = (String) (((ISuperVO) srcDataList.get(i)).getAttributeValue("pk_task_b"));
			//上次obj的index
			int fatherIndex = utils.getNCObjIndexByPK(fatherPk, TaskSVO.class);
			// 主键
			allResultList.get(i).setAttributeValue("result_number", pkList.get(i));
			//外键们
			allResultList.get(i).setAttributeValue("sample_number", firstSampleList.get(fatherIndex).getAttributeValue("sample_number"));
			allResultList.get(i).setAttributeValue("test_number", firstTestList.get(fatherIndex).getAttributeValue("test_number"));
			
			//flag标记,会在sort完之后清除
			allResultList.get(i).setAttributeValue("task_seq_num", taskList.get(fatherIndex).getAttributeValue("seq_num"));
		}

		processData.setFirstResultListMap(sortResult(allResultList));
	}

	private Map<Integer, List<Result>> sortResult(List<Result> allResultList) {
		Map<Integer, List<Result>> rsListMap = new HashMap<>();
		for (Result result : allResultList) {
			Integer taskid = (Integer) result.getAttributeValue("task_seq_num");
			//此字段不回写只是flag
			result.removeAttributeValue("task_seq_num");
			if (rsListMap.containsKey(taskid)) {
				rsListMap.get(taskid).add(result);
			} else {
				List<Result> resultList = new ArrayList<>();
				resultList.add(result);
				rsListMap.put(taskid, resultList);
			}
		}
		return rsListMap;
	}

	private List<Result> initWriteBackList(int size) {
		List<Result> rsList = new ArrayList<>();
		for(int i = 0;i < size ;i++){
			rsList.add(new Result());
		}
		return rsList;
	}

	private ISuperVO[] getAllTaskSVO(WriteBackProcessData processData) {
		List<ISuperVO> rsList = new ArrayList<>();
		AggTaskHVO aggvo = processData.getAggTaskHVO();
		ISuperVO[] bvos = aggvo.getChildren(TaskBVO.class);
		if (bvos != null && bvos.length > 0) {
			for (ISuperVO bvo : bvos) {
				TaskBVO cbvo = (TaskBVO) bvo;
				if (cbvo.getPk_task_s() != null && cbvo.getPk_task_s().length > 0) {
					Collections.addAll(rsList, cbvo.getPk_task_s());
				}
			}
		}
		return rsList.toArray(new ISuperVO[0]);
	}



}
