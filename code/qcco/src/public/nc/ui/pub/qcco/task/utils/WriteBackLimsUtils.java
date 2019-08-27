package nc.ui.pub.qcco.task.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.qcco.writeback.utils.common.CommonUtils;
import nc.ui.pub.qcco.writeback.utils.mapping.FirstWriteBackStaticMaping;
import nc.ui.pub.qcco.writeback.utils.mapping.SecWriteBackStaticMaping;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.commission.CommissionHVO;
import nc.vo.qcco.commission.CommissionRVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskHVO;
import nc.vo.qcco.task.TaskRVO;
import nc.vo.qcco.task.TaskSVO;

import org.apache.commons.lang.StringUtils;

/**
 * XXX ������ʵ���̲�ס��,���ع������
 * 
 * @author 91967\
 * 
 * ��һ�����ڵ���
 * 2019��8��25��16:45:30
 *
 */
public class WriteBackLimsUtils {
    private BaseDAO baseDao = new BaseDAO();
    private CommonUtils utils = new CommonUtils();

    // NC ���
    public static Map<Class<?>, String> NC_FK_MAP;
    {
        NC_FK_MAP = new HashMap<>();
        NC_FK_MAP.put(CommissionHVO.class, null);
        NC_FK_MAP.put(CommissionBVO.class, "qc_commission_b.pk_commission_h");
        NC_FK_MAP.put(CommissionRVO.class, "qc_commission_r.pk_commission_b");

        NC_FK_MAP.put(TaskBVO.class, "qc_task_b.pk_task_h");
        NC_FK_MAP.put(TaskSVO.class, "qc_task_s.pk_task_b");
        NC_FK_MAP.put(TaskRVO.class, "qc_task_r.pk_task_b");
    }

    // LIMS ���
    public static Map<Class<?>, String> LIMS_FK_MAP;
    {
        LIMS_FK_MAP = new HashMap<>();
        LIMS_FK_MAP.put(CommissionHVO.class, null);
        LIMS_FK_MAP.put(CommissionBVO.class, "C_PROJ_LOGIN_SAMPLE.PROJECT");
        LIMS_FK_MAP.put(CommissionRVO.class, "C_PROJ_PARA_A.proj_logsamp_seqnum");

        LIMS_FK_MAP.put(TaskBVO.class, "c_proj_task.PROJECT");
        LIMS_FK_MAP.put(TaskSVO.class, "result.SAMPLE_NUMBER");
        LIMS_FK_MAP.put(TaskRVO.class, "c_proj_task_para_b.TASK_SEQ_NUM");
    }

    // LIMS ���-�����д�ı�
    public static Map<Class<?>, String> LIMS_FK_MAP_EXTEND;
    {
        LIMS_FK_MAP_EXTEND = new HashMap<>();
        LIMS_FK_MAP_EXTEND.put(CommissionBVO.class, "SAMPLE.PROJECT");

    }
    // ��¼ÿ��vo���ϲ�����
    public static Map<Class<?>, String> FATHER_PK_KEY_MAP;
    {
        FATHER_PK_KEY_MAP = new HashMap<>();

        FATHER_PK_KEY_MAP.put(CommissionHVO.class, null);
        FATHER_PK_KEY_MAP.put(CommissionBVO.class, "pk_commission_h");
        FATHER_PK_KEY_MAP.put(CommissionRVO.class, "pk_commission_b");
        FATHER_PK_KEY_MAP.put(TaskBVO.class, "pk_task_h");
        FATHER_PK_KEY_MAP.put(TaskSVO.class, "pk_task_b");
        FATHER_PK_KEY_MAP.put(TaskRVO.class, "pk_task_b");
    }

    /**
     * ע��: ʷ������������ϼ�,�������?�����ڵ�,�������?�а�,��������?�����
     * ȡί�е�/���񵥻�дLIMS�Ĳ���SQL
     *
     * @param pk_commission_h
     * @return
     * @throws BusinessException
     */
    public String[] getInsertLIMSSQL(String pk_commission_h)
            throws BusinessException {
        List<String> sqlList = new ArrayList<String>();
        // �������Ӧ��������--����У��
        Map<Class<?>, Integer> class2NumMap = new HashMap<>();
        // �������񵥵�ί�е���ӳ���
        Map<String, String> taskPk2CommissionPkMap = initRelated(pk_commission_h);
        Map<String, Object> ncPK2NCObjMap = new HashMap<>();
        String[] lists = null;
        List<String> projectList = new ArrayList<>();
        //sample���һ�λ�д
        //String[] sampleFirstExtends = null;
        //test���һ�λ�д
        /*String[] testFirstExtendArray = null;*/
        List<String> testFirstExtendList = new ArrayList<>();
        //
        //���ڴ洢����ʱ����     pkFirstSample:��һ�λ�д��sample����
        Map<String,Object> boxMap = new HashMap<>();
        //Integer pkFirstSample = null;

        // �洢nc�е�pk��limsϵͳ��pk�Ķ�Ӧ��ϵ
        Map<String, Object> ncPK2LimsPkMap = new HashMap<>();
        String headCond = "pk_commission_h = '" + pk_commission_h
                + "' and dr = 0 ";
        // ί�е���ͷ
        /*lists = getInsertSQLByMap(FirstWriteBackStaticMaping.HEAD_MAPPING, CommissionHVO.class,
                headCond, ncPK2LimsPkMap, ncPK2NCObjMap, taskPk2CommissionPkMap,projectList,testFirstExtendList);
        if (lists == null || lists.length < 1) {
            throw new BusinessException("ί�е�������дʧ��!");
        }
        class2NumMap.put(CommissionHVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getHeadInsertSQL(lists,pk_commission_h));
        }*/
        // ��Ʒ��
       /* lists = getInsertSQLByMap(FirstWriteBackStaticMaping.BODY_SAMPLE_MAPPING, CommissionBVO.class,
                headCond, ncPK2LimsPkMap, ncPK2NCObjMap, taskPk2CommissionPkMap,projectList,testFirstExtendList);
        class2NumMap.put(CommissionBVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getSampleInsertSQL(lists));
        }*/
        //�����дSample��
        /*sampleFirstExtends = getSampleInsertSQLByMap(pk_commission_h,FirstWriteBackStaticMaping.BODY_SAMPLE_EXTEND_MAPPING, CommissionBVO.class,
                headCond, ncPK2LimsPkMap, ncPK2NCObjMap,boxMap);*/

        // ʵ��ǰ����
        /* subCondition = "pk_commission_b in (select pk_commission_b from qc_commission_b where "
                + headCond + ") and dr = 0 ";
        lists = getInsertSQLByMap(FirstWriteBackStaticMaping.GRAND_BEFORE_MAPPING, CommissionRVO.class,
                subCondition, ncPK2LimsPkMap, ncPK2NCObjMap,
                taskPk2CommissionPkMap,projectList,testFirstExtendList);
        if (lists != null && class2NumMap.get(CommissionBVO.class) <= 0
                && lists.length > 0) {
            throw new BusinessException("ί�е����[ʵ��ǰ����]������Ч����,����ϵ���ݹ���Ա.");
        }*/
   /*     class2NumMap.put(CommissionRVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getBeforeInsertSQL(lists));
        }*/

        // ������
        /* subCondition = "pk_task_h in (select pk_task_h from qc_task_h where "
                + headCond + ") and dr = 0 ";*/
        //
       /* lists = getInsertSQLByMap(FirstWriteBackStaticMaping.BODY_TASK_MAPPING, TaskBVO.class,
                subCondition, ncPK2LimsPkMap, ncPK2NCObjMap,
                taskPk2CommissionPkMap,projectList,testFirstExtendList);
        class2NumMap.put(TaskBVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getTaskInsertSQL(lists,pk_commission_h,ncPK2LimsPkMap));
        }*/

        // ��������
         String subCondition = "pk_task_b in (select pk_task_b from qc_task_b where "
                + " pk_task_h in (select pk_task_h from qc_task_h where "
                + headCond + " ) and dr = 0 ) and dr = 0 ";
        lists = getInsertSQLByMap(FirstWriteBackStaticMaping.GRAND_CONDITION_MAPPING, TaskSVO.class,
                subCondition, ncPK2LimsPkMap, ncPK2NCObjMap,
                taskPk2CommissionPkMap,projectList,testFirstExtendList);
        if (lists != null && class2NumMap.get(TaskBVO.class) <= 0
                && lists.length > 0) {
            throw new BusinessException("�������[ʵ������]������Ч����,����ϵ���ݹ���Ա.");
        }
        class2NumMap.put(TaskSVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getConditionInsertSQL(lists));
        }

        /*// ʵ������
        subCondition = "pk_task_b in (select pk_task_b from qc_task_b where "
                + " pk_task_h in (select pk_task_h from qc_task_h where "
                + headCond + " ) and dr = 0 ) and dr = 0 ";*/
        /*lists = getInsertSQLByMap(FirstWriteBackStaticMaping.GRAND_AFTER_MAPPING, TaskRVO.class,
                subCondition, ncPK2LimsPkMap, ncPK2NCObjMap,
                taskPk2CommissionPkMap,projectList,testFirstExtendList);*/
       /* if (lists != null && class2NumMap.get(TaskBVO.class) <= 0
                && lists.length > 0) {
            throw new BusinessException("�������[ʵ������]������Ч����,����ϵ���ݹ���Ա.");
        }
        class2NumMap.put(TaskRVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getAfterInsertSQL(lists));
        }*/
        //����Ļ�дsample��(��һ��)
        /*if (sampleFirstExtends != null && sampleFirstExtends.length > 0) {
            sqlList.addAll(getSampleExtendInsertSQL(sampleFirstExtends));
        }*/
        //����Ļ�дtest��(��һ��)
       // testFirstExtendArray = testFirstExtendList.toArray(new String[0]);
        /*if (testFirstExtendArray != null && testFirstExtendArray.length > 0) {
            sqlList.addAll(getTestExtendInsertSQL(testFirstExtendArray,boxMap));
        }*/
        return sqlList.toArray(new String[0]);
    }
    

	private Map<String, String> initRelated(String pk_commission_h)
            throws DAOException {
        Map<String, String> rsMap = new HashMap();
        String sql = "select pk_task_h from qc_task_h where pk_commission_h = '"
                + pk_commission_h + "'";
        List<String> pkTaskhList = (List<String>) baseDao.executeQuery(sql,
                new ColumnListProcessor());
        if (pkTaskhList != null && pkTaskhList.size() > 0) {
            for (String pk : pkTaskhList) {
                rsMap.put(pk, pk_commission_h);
            }
        }
        return rsMap;
    }

    /**
     * ʵ������
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     * @throws BusinessException 
     */
    private List<String> getAfterInsertSQL(String[] lists) throws BusinessException {
        List<String> rsList = new ArrayList<>();
        StringBuilder colNameSB = new StringBuilder(lists[0]);
        StringBuilder colValSB = new StringBuilder();
   
        //����ENTRY_CODE(ѡȡC_PROJ_PARA_A�����ֵ)
        //Ԥ����ENTRY_CODE
        List<Integer> entryCodeList = utils.getPrePk("entry_code","c_proj_task_para_b",lists.length - 1);
        colNameSB.append(",").append("entry_code");
        if (lists != null && lists.length > 1) {
            StringBuilder sqlSB = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO c_proj_task_para_b").append("(")
                        .append(colNameSB.toString()).append(")  values (");
                sqlSB.append(lists[i]).append(",").append(entryCodeList.get(i-1));
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }

    /**
     * ʵ������
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     */
    private List<String> getConditionInsertSQL(String[] lists) {
    	List<String> rsList = new ArrayList<>();
        if (lists != null && lists.length > 1) {
        	StringBuilder colNameSB = new StringBuilder(lists[0]);
        	StringBuilder colValSB = new StringBuilder();
            //����̶�ֵ�ֶ�
        	for(String colName : FirstWriteBackStaticMaping.TASK_CONDITION_STATIC_MAP.keySet()){
        		colNameSB.append(",").append(colName);
        		colValSB.append(",").append(FirstWriteBackStaticMaping.TASK_CONDITION_STATIC_MAP.get(colName));
        	}
        	//����д��
        	//����̶�ֵ�ֶ�
        	for(String colName : SecWriteBackStaticMaping.TASK_CONDITION_STATIC_MAP.keySet()){
        		colNameSB.append(",").append(colName);
        		colValSB.append(",").append(SecWriteBackStaticMaping.TASK_CONDITION_STATIC_MAP.get(colName));
        	}
        	
            StringBuilder sqlSB = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO result").append("(")
                        .append(colNameSB.toString()).append(")  values (");
                temp.delete(0, temp.length());
                temp.append(lists[i]).append(colValSB);
                sqlSB.append(temp.toString());
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }

    /**
     * ������
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     */
    @SuppressWarnings("unchecked")
	private List<String> getTaskInsertSQL(String[] lists,String pk_commission_h,Map<String, Object> ncPK2LimsPkMap) {
    	List<String> rsList = new ArrayList<>();
        if (lists != null && lists.length > 1) {
        	StringBuilder colNameSB = new StringBuilder(lists[0]);
        	StringBuilder colValSB = new StringBuilder();
            //����̶�ֵ�ֶ�
        	for(String colName : FirstWriteBackStaticMaping.TASK_BODY_STATIC_MAP.keySet()){
        		colNameSB.append(colName).append(",");
        		colValSB.append(FirstWriteBackStaticMaping.TASK_BODY_STATIC_MAP.get(colName)).append(",");
        	}
        	//project 
        	colNameSB.append("project").append(",");
    		colValSB.append("'").append(String.valueOf(ncPK2LimsPkMap.get(pk_commission_h))).append("',");
        	
        	//colNameSB = colNameSB.delete(colNameSB.length()-1, colNameSB.length());
        	//colValSB = colValSB.delete(colValSB.length()-1, colValSB.length());
        	String sql = "select job.psncode changed_by,taskh.modifiedtime changed_on,"
        				+ " job2.psncode c_submit_by,ch.creationtime c_submit_date from qc_task_h taskh "
        				+ " left join sm_user sm on sm.cuserid = taskh.modifier "
        				+ " left join (select * from bd_psnjob jobinner where ismainjob = 'Y' ) job on rownum = 1 and job.pk_psndoc = sm.pk_psndoc "
        				+ " left join qc_commission_h ch on ch.pk_commission_h = taskh.pk_commission_h "
        				+ " left join sm_user sm2 on sm2.cuserid = ch.creator "
        				+ " left join (select * from bd_psnjob jobinner where ismainjob = 'Y' ) job2 on rownum = 1 and job2.pk_psndoc = sm2.pk_psndoc "
        				+ " where taskh.pk_commission_h = '"+pk_commission_h+"' ";
        	Map<String,String> rs = null;
        	try {
        		rs = (Map<String,String>)baseDao.executeQuery(sql, new MapProcessor());
			} catch (DAOException e) {
				rs = new HashMap<>();;
			}
			if (null != rs) {
				// ί�е��Ƶ���,�Ƶ�ʱ�� �޸���.�޸�ʱ�� realValue =
				// "to_timestamp('"+realValue+"','yyyy-mm-dd hh24:mi:ss.ff')"
				colNameSB.append("changed_by").append(",").append("changed_on").append(",")
					.append("c_submit_by").append(",").append("c_submit_date");

				colValSB.append(" '").append(rs.get("changed_by")).append("', ")
					.append("to_timestamp('"+rs.get("changed_on")+"','yyyy-mm-dd hh24:mi:ss.ff')")
					.append(", '").append(rs.get("c_submit_by")).append("',")
					.append("to_timestamp('"+rs.get("c_submit_date")+"','yyyy-mm-dd hh24:mi:ss.ff') ");
			}
        	
            StringBuilder sqlSB = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO c_proj_task").append("(")
                        .append(colNameSB.toString()).append(")  values (");
                temp.delete(0, temp.length());
                temp.append(lists[i]).append(colValSB);
                sqlSB.append(temp.toString());
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }

    /**
     * ʵ��ǰ����
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     * @throws DAOException 
     */
    private List<String> getBeforeInsertSQL(String[] lists) throws DAOException {
        List<String> rsList = new ArrayList<>();
        StringBuilder colNameSB = new StringBuilder(lists[0]);
        StringBuilder colValSB = new StringBuilder();
   
        //����ENTRY_CODE(ѡȡC_PROJ_PARA_A�����ֵ)
        //Ԥ����ENTRY_CODE
        List<Integer> entryCodeList = utils.getPrePk("entry_code","c_proj_para_a",lists.length - 1);
        colNameSB.append(",").append("entry_code");
        if (lists != null && lists.length > 1) {
            StringBuilder sqlSB = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO C_PROJ_PARA_A").append("(")
                        .append(colNameSB.toString()).append(")  values (");
                sqlSB.append(lists[i]).append(",").append(entryCodeList.get(i-1));
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }

    /**
     * ��Ʒ��
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     */
    private List<String> getSampleInsertSQL(String[] lists) {
    	List<String> rsList = new ArrayList<>();
        if (lists != null && lists.length > 1) {
        	StringBuilder colNameSB = new StringBuilder(lists[0]);
        	StringBuilder colValSB = new StringBuilder();
            //����̶�ֵ�ֶ�
        	for(String colName : FirstWriteBackStaticMaping.COMMISSION_BODY_STATIC_MAP.keySet()){
        		colNameSB.append(",").append(colName);
        		colValSB.append(",").append(FirstWriteBackStaticMaping.COMMISSION_BODY_STATIC_MAP.get(colName));
        	}
        	
            StringBuilder sqlSB = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO C_PROJ_LOGIN_SAMPLE").append("(")
                        .append(colNameSB.toString()).append(")  values (");
                temp.delete(0, temp.length());
                temp.append(lists[i]).append(colValSB);
                sqlSB.append(temp.toString());
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }
    /**
     * Sample ��һ�λ�д
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     */
    private List<String> getSampleExtendInsertSQL(String[] lists) {
        List<String> rsList = new ArrayList<>();
        if (lists != null && lists.length > 1) {
        	StringBuilder colNameSB = new StringBuilder(lists[0]);
        	StringBuilder colValSB = new StringBuilder();
            //����̶�ֵ�ֶ�
        	for(String colName : FirstWriteBackStaticMaping.SAMPLE_STATIC_MAP.keySet()){
        		colNameSB.append(",").append(colName);
        		colValSB.append(",").append(FirstWriteBackStaticMaping.SAMPLE_STATIC_MAP.get(colName));
        	}
        	
        	//����̶�ֵ�ֶ� �ڶ��λ�д
        	for(String colName : SecWriteBackStaticMaping.SAMPLE_STATIC_MAP.keySet()){
        		colNameSB.append(",").append(colName);
        		colValSB.append(",").append(SecWriteBackStaticMaping.SAMPLE_STATIC_MAP.get(colName));
        	}
        	
            StringBuilder sqlSB = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO SAMPLE").append("(")
                        .append(colNameSB.toString()).append(")  values (");
                temp.delete(0, temp.length());
                temp.append(lists[i]).append(colValSB);
                sqlSB.append(temp.toString());
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }
    
    
    
    

    /**
     * ί�е���ͷ
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     * @throws BusinessException 
     */
    private List<String> getHeadInsertSQL(String[] lists,String pk_commission_h) throws BusinessException {
        List<String> rsList = new ArrayList();
        if (lists != null && lists.length > 1) {
        	StringBuilder colNameSB = new StringBuilder(lists[0]);
        	StringBuilder colValSB = new StringBuilder();
        	//��ѯ��Ʒ������,���ڻ�дproject.NUM_SAMPLES �ֶ�
        	UFDouble bNum = new UFDouble(baseDao.executeQuery(
        			"select sum(quantity) from qc_commission_b where pk_commission_h = '"+pk_commission_h+"' and dr = 0 ", 
        			new ColumnProcessor()).toString());
        	if(bNum == null){
        		bNum = UFDouble.ZERO_DBL;
        	}
        	colNameSB.append(",").append("num_samples");
    		colValSB.append(",").append(bNum.toDouble());
            //����̶�ֵ�ֶ�
        	for(String colName : FirstWriteBackStaticMaping.COMMISSION_HEARD_STATIC_MAP.keySet()){
        		colNameSB.append(",").append(colName);
        		colValSB.append(",").append(FirstWriteBackStaticMaping.COMMISSION_HEARD_STATIC_MAP.get(colName));
        	}
        	
            StringBuilder sqlSB = new StringBuilder();
            StringBuilder temp = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO PROJECT").append("(")
                        .append(colNameSB.toString()).append(")  values (");
                temp.delete(0, temp.length());
                temp.append(lists[i]).append(colValSB);
                sqlSB.append(temp.toString());
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }

    /**
     * ��ȡInsert���Ƭ��
     *
     * @param fieldMap
     * @param clazz
     * @param pkname
     * @param pkvalue
     * @param ncPK2LimsPkMap
     *            ncpk��limsϵͳpk��Ӧ��ϵ
     * @param ncPK2ObjectMap
     *            ���α��������NCOBJ
     * @param testFirstExtends sample���һ�λ�д�Ĳ���sql
     * 
     * @return fieldValues[0]: �ֶ���Ƭ��<br />
     *         fieldValues[1-n]��ֵƬ��
     * @return project ί�е����,���ڸ������project
     * @throws BusinessException
     */
    private String[] getInsertSQLByMap(Map<String, String> fieldMap,
                                       Class<?> clazz, String condition,
                                       Map<String, Object> ncPK2LimsPkMap,
                                       Map<String, Object> ncPK2NCObjMap,
                                       Map<String, String> taskPk2CommissionPkMap,List<String> projectList,List<String> testFirstExtendList)
            throws BusinessException {

		// ����ĳ���ֶε�ֵ,һ�����ݶ�Ӧarray�е�һ��
		StringBuilder[] fieldValues = null;
		IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
		List<?> srcData = (List<?>) query.retrieveByClause(clazz, condition);
		List<Integer> pkList = null;
		if (null == srcData || srcData.size() <= 0) {
			return null;
		}

		// Ԥ����pk-����ί�е�֮��(ί�е���������billNo)
		pkList = utils.getPrePk(clazz, srcData.size());

		fieldValues = new StringBuilder[srcData.size() + 1];
		// ����ƴ���ֶ���
		StringBuilder insertFields = new StringBuilder();
		// ������ѭ��
		for (Entry<String, String> map : fieldMap.entrySet()) {
			String fieldName = map.getKey();

			String[] fields = null;
			if (map.getValue().contains(";")) {
				fields = utils.getWriteBackFields(map.getValue().split(";"));
			} else {
				fields = utils.getWriteBackFields(new String[] { map.getValue() });
			}
			// times�Ǵ���һ�Զ��ϵ��
			int times = fields.length;
			for (String field : fields) {
				insertFields.append(field).append(",");
			}

			if (srcData != null && srcData.size() > 0) {

				int row = 1;
				for (Object data : srcData) {
					ISuperVO vo = (ISuperVO) data;
					Object unDofieldValue = vo.getAttributeValue(fieldName);
					// �������
					Object realValue = utils.dealRefValue(clazz, fieldName, unDofieldValue);

					if (null == fieldValues[row]) {
						fieldValues[row] = new StringBuilder();
					}
					if (null != realValue && ("creationtime".equals(fieldName) || "modifiedtime".equals(fieldName))) {
						realValue = "to_timestamp('" + realValue + "','yyyy-mm-dd hh24:mi:ss.ff')";
					}
					if (null != realValue && ("productdate".equals(fieldName))) {
						realValue = "to_timestamp('" + realValue + "','yyyy-mm-dd')";
					}
					for (int j = 0; j < times; j++) {
						if (realValue != null) {
							if (realValue instanceof Integer || realValue instanceof UFDouble || realValue instanceof Double) {
								fieldValues[row].append(realValue).append(",");
							} else if (realValue instanceof UFBoolean) {
								if ("Y".equals(String.valueOf(realValue))) {
									fieldValues[row].append("'").append("T").append("',");
								} else {
									fieldValues[row].append("'").append("F").append("',");
								}

							} else {
								if (null != realValue
										&& ("creationtime".equals(fieldName) || "modifiedtime".equals(fieldName) || "productdate"
												.equals(fieldName))) {
									fieldValues[row].append(String.valueOf(realValue)).append(",");
								} else {
									fieldValues[row].append("'").append(utils.dealEscapse(String.valueOf(realValue))).append("',");
								}

							}
						} else {
							fieldValues[row].append("null").append(",");
						}
					}
					row++;
				}
			}
		}

		if (srcData != null && srcData.size() > 0) {
			fieldValues[0] = insertFields;
		}
		String[] rsString = null;
		if (fieldValues != null) {
			rsString = new String[fieldValues.length];
			for (int i = 0; i < fieldValues.length; i++) {
				// д�������͸�����(ֻ֧�ֵ�����,Ҫ������,��Ҫ�ο�������ֶ�����߼�)
				if (0 == i) {
					fieldValues[i].append(utils.getWriteBackFields(new String[] { CommonUtils.LIMS_PK_MAP.get(clazz) })[0]).append(",");
					fieldValues[i].append(utils.getWriteBackFields(new String[] { LIMS_FK_MAP.get(clazz) })[0]);
				} else {
					// ��ȡ�ϲ������:
					String fatherPk = (String) (((ISuperVO) srcData.get(i - 1)).getAttributeValue(utils
							.getWriteBackFields(new String[] { NC_FK_MAP.get(clazz) })[0]));
					String realfatherPk = getRealFatherPk(clazz, fatherPk, taskPk2CommissionPkMap);
					if (null == pkList.get(i - 1) || pkList.get(i - 1) instanceof Integer) {
						// pk
						fieldValues[i].append(pkList.get(i - 1)).append(",");
					} else {
						// pk
						fieldValues[i].append("'").append(pkList.get(i - 1)).append("',");
					}
					if (null == ncPK2LimsPkMap.get(realfatherPk) || ncPK2LimsPkMap.get(realfatherPk) instanceof Integer) {
						// ���
						fieldValues[i].append(ncPK2LimsPkMap.get(realfatherPk));
					} else {
						// pk
						// ���
						fieldValues[i].append("'").append(ncPK2LimsPkMap.get(realfatherPk)).append("'");
					}

				}

				rsString[i] = fieldValues[i].toString();
			}

		}
		return rsString;
	}
  

	

    // //�����������,��Ҫ�������񵥱�ͷ����ת����ί�е���������
    private String getRealFatherPk(Class<?> clazz, String fatherPk,
                                   Map<String, String> taskPk2CommissionPkMap) {
        if (TaskBVO.class == clazz) {
            fatherPk = taskPk2CommissionPkMap.get(fatherPk);
        }
        return fatherPk;
    }

   
    /**
     * ͨ��������,��ȡ�����汾
     * @param analysisName
     * @return
     * @throws DAOException 
     */
    private String getAnalysisVerionFromName(String analysisName) throws DAOException {
		String sql = " select VERSION from nc_analysis_list where name  = '"+analysisName+"'"; 
		Integer ver = (Integer)baseDao.executeQuery(sql, new ColumnProcessor());
		return String.valueOf(ver);
	}


   

    
}
