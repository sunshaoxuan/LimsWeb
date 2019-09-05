package nc.ui.pub.qcco.writeback.utils.mediator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.mapping.FirstWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.mapping.SecWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.processor.IFirstWriteBackProcessor;
import nc.ui.pub.qcco.writeback.utils.processor.impl.*;
import nc.vo.pub.BusinessException;

public class WriteBackMediator {
	
	
	
	
	public List<String> getLIMSSQL(String pk_commission_h) throws BusinessException {
		List<String> rsList = new ArrayList<>();
		
		if(!StringUtils.isEmpty(pk_commission_h)){
			CommonUtils utils = new CommonUtils();
			WriteBackProcessData data = new WriteBackProcessData(pk_commission_h);
			utils.setData(data);
			//ί�е�����
			IFirstWriteBackProcessor projectProcessor = new ProjectWriteBackProcessor();
			projectProcessor.setUtils(utils);
			//ί�е��ӱ�
			IFirstWriteBackProcessor sampleGroupProcessor = new SampleGroupWriteBackProcessor();
			sampleGroupProcessor.setUtils(utils);
			//ʵ��ǰ����
			IFirstWriteBackProcessor paraAProcessor = new ParaAWriteBackProcessor();
			paraAProcessor.setUtils(utils);
			//������
			IFirstWriteBackProcessor taskProcessor = new TaskWriteBackProcessor();
			taskProcessor.setUtils(utils);
			//��������
			IFirstWriteBackProcessor paraBProcessor = new ParaBWriteBackProcessor();
			paraBProcessor.setUtils(utils);
			//sample��
			SampleWriteBackProcessor sampleProcessor = new SampleWriteBackProcessor();
			sampleProcessor.setUtils(utils);
			//test��
			TestWriteBackProcessor testProcessor = new TestWriteBackProcessor();
			testProcessor.setUtils(utils);
			//result��
			ResultWriteBackProcessor resultProcessor = new ResultWriteBackProcessor();
			resultProcessor.setUtils(utils);
			
			//��̬��Դ��ʼ�� -- �����仰����ɾ��,��Ȼû���κ�����,������static������ʼ��
			@SuppressWarnings("unused")
			FirstWriteBackStaticMaping fm = new FirstWriteBackStaticMaping();
			@SuppressWarnings("unused")
			SecWriteBackStaticMaping sm = new SecWriteBackStaticMaping();
			
			
			//ί�е�������д
			projectProcessor.processFirst(data);
			// ��Ʒ���д
			sampleGroupProcessor.processFirst(data);
			// ί�е����-ʵ��ǰ������д
			paraAProcessor.processFirst(data);
			// �����ӱ�-�����д
			taskProcessor.processFirst(data);
			// �������-����������д
			paraBProcessor.processFirst(data);
			// sample����һ�λ�д
			sampleProcessor.processFirst(data);
			// test����һ�λ�д
			testProcessor.processFirst(data);
			// result����һ�λ�д
			resultProcessor.processFirst(data);

			// sample�ڶ��λ�д
			sampleProcessor.processSec(data);
			// test�ڶ��λ�д
			testProcessor.processSec(data);
			// result�ڶ��λ�д
			resultProcessor.processSec(data);
			
			rsList.addAll(utils.toLIMSSQL());
		}
		
		
		return rsList;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}