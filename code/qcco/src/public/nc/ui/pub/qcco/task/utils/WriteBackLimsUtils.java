package nc.ui.pub.qcco.task.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.bd.ref.AbstractRefModel;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.commission.CommissionHVO;
import nc.vo.qcco.commission.CommissionRVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskRVO;
import nc.vo.qcco.task.TaskSVO;

import org.apache.commons.lang.StringUtils;
public class WriteBackLimsUtils {
    private BaseDAO baseDao = new BaseDAO();

    private static Map<String, String> bodySimple2ChildrenMapping;
    // lims主键
    public static Map<Class<?>, String> LIMS_PK_MAP;
    {
        LIMS_PK_MAP = new HashMap<>();
        LIMS_PK_MAP.put(CommissionHVO.class, "PROJECT.NAME");
        LIMS_PK_MAP.put(CommissionBVO.class, "C_PROJ_LOGIN_SAMPLE.SEQ_NUM");
        LIMS_PK_MAP.put(CommissionRVO.class, "C_PROJ_PARA_A.SEQ_NUM");

        LIMS_PK_MAP.put(TaskBVO.class, "c_proj_task.SEQ_NUM");
        LIMS_PK_MAP.put(TaskSVO.class, "result.RESULT_NUMBER");
        LIMS_PK_MAP.put(TaskRVO.class, "c_proj_task_para_b.SEQ_NUM");
    }

    // lims主键-额外回写的表
    public static Map<Class<?>, String> LIMS_PK_MAP_EXTEND;
    {
        LIMS_PK_MAP_EXTEND = new HashMap<>();
        LIMS_PK_MAP_EXTEND.put(CommissionBVO.class, "Sample.SAMPLE_NUMBER");


    }
    // NC 外键
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

    // LIMS 外键
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

    // LIMS 外键-额外回写的表
    public static Map<Class<?>, String> LIMS_FK_MAP_EXTEND;
    {
        LIMS_FK_MAP_EXTEND = new HashMap<>();
        LIMS_FK_MAP_EXTEND.put(CommissionBVO.class, "SAMPLE.PROJECT");

    }

    // 委托单
    private Map<String, String> headMapping; // qc_commission_h
    private Map<String, String> bodySampleMapping; // qc_commission_b
    private Map<String, String> bodySampleExtendMapping;//qc_commission_b 额外的回写

    private Map<String, String> grandBeforeMapping; // qc_commission_r

    // 任务单
    private Map<String, String> bodyTaskMapping; // qc_task_b
    private Map<String, String> grandConditionMapping; // qc_task_s
    private Map<String, String> grandAfterMapping; // qc_task_r

    // 记录每个vo的上层主键
    public static Map<Class<?>, String> FATHER_PK_KEY_MAP;
    {
        FATHER_PK_KEY_MAP = new HashMap();

        FATHER_PK_KEY_MAP.put(CommissionHVO.class, null);
        FATHER_PK_KEY_MAP.put(CommissionBVO.class, "pk_commission_h");
        FATHER_PK_KEY_MAP.put(CommissionRVO.class, "pk_commission_b");
        FATHER_PK_KEY_MAP.put(TaskBVO.class, "pk_task_h");
        FATHER_PK_KEY_MAP.put(TaskSVO.class, "pk_task_b");
        FATHER_PK_KEY_MAP.put(TaskRVO.class, "pk_task_b");
    }

    /**
     * 注释: 史上最垃圾代码合集,面向对象?不存在的,面向过程?行吧,面向切面?不清楚
     * 取委托单/任务单回写LIMS的插入SQL
     *
     * @param pk_commission_h
     * @return
     * @throws BusinessException
     */
    public String[] getInsertLIMSSQL(String pk_commission_h)
            throws BusinessException {
        List<String> sqlList = new ArrayList<String>();
        // 主子孙对应的数据量--用来校验
        Map<Class<?>, Integer> class2NumMap = new HashMap<>();
        // 构建任务单到委托单的映射表
        Map<String, String> taskPk2CommissionPkMap = initRelated(pk_commission_h);
        Map<String, Object> ncPK2NCObjMap = new HashMap();
        String[] lists = null;
        List<String> projectList = new ArrayList();
        //额外的回写字段
        String[] listsExtends = null;

        // 存储nc中的pk和lims系统的pk的对应关系
        Map<String, Object> ncPK2LimsPkMap = new HashMap<>();
        String headCond = "pk_commission_h = '" + pk_commission_h
                + "' and dr = 0 ";
        // 委托单表头
        lists = getInsertSQLByMap(getHeadMapping(), CommissionHVO.class,
                headCond, ncPK2LimsPkMap, ncPK2NCObjMap, taskPk2CommissionPkMap,projectList);
        if (lists == null || lists.length < 1) {
            throw new BusinessException("委托单主档回写失败!");
        }
        class2NumMap.put(CommissionHVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getHeadInsertSQL(lists,pk_commission_h));
        }
        // 样品行
        lists = getInsertSQLByMap(getBodySampleMapping(), CommissionBVO.class,
                headCond, ncPK2LimsPkMap, ncPK2NCObjMap, taskPk2CommissionPkMap,projectList);
        class2NumMap.put(CommissionBVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getSampleInsertSQL(lists));
        }
        //额外回写Sample表
        listsExtends = getSampleInsertSQLByMap(getBodySampleExtendMapping(), CommissionBVO.class,
                headCond, ncPK2LimsPkMap, ncPK2NCObjMap);

        // 实验前参数
        String subCondition = "pk_commission_b in (select pk_commission_b from qc_commission_b where "
                + headCond + ") and dr = 0 ";
        lists = getInsertSQLByMap(getGrandBeforeMapping(), CommissionRVO.class,
                subCondition, ncPK2LimsPkMap, ncPK2NCObjMap,
                taskPk2CommissionPkMap,projectList);
        if (lists != null && class2NumMap.get(CommissionBVO.class) <= 0
                && lists.length > 0) {
            throw new BusinessException("委托单孙表[实验前参数]发现无效数据,请联系数据管理员.");
        }
        class2NumMap.put(CommissionRVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getBeforeInsertSQL(lists));
        }

        // 任务行
        subCondition = "pk_task_h in (select pk_task_h from qc_task_h where "
                + headCond + ") and dr = 0 ";
        //
        lists = getInsertSQLByMap(getBodyTaskMapping(), TaskBVO.class,
                subCondition, ncPK2LimsPkMap, ncPK2NCObjMap,
                taskPk2CommissionPkMap,projectList);
        class2NumMap.put(TaskBVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getTaskInsertSQL(lists));
        }

        // 实验条件
        subCondition = "pk_task_b in (select pk_task_b from qc_task_b where "
                + " pk_task_h in (select pk_task_h from qc_task_h where "
                + headCond + " ) and dr = 0 ) and dr = 0 ";
        lists = getInsertSQLByMap(getGrandConditionMapping(), TaskSVO.class,
                subCondition, ncPK2LimsPkMap, ncPK2NCObjMap,
                taskPk2CommissionPkMap,projectList);
        if (lists != null && class2NumMap.get(TaskBVO.class) <= 0
                && lists.length > 0) {
            throw new BusinessException("任务单孙表[实验条件]发现无效数据,请联系数据管理员.");
        }
        class2NumMap.put(TaskSVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getConditionInsertSQL(lists));
        }

        // 实验后参数
        subCondition = "pk_task_b in (select pk_task_b from qc_task_b where "
                + " pk_task_h in (select pk_task_h from qc_task_h where "
                + headCond + " ) and dr = 0 ) and dr = 0 ";
        lists = getInsertSQLByMap(getGrandAfterMapping(), TaskRVO.class,
                subCondition, ncPK2LimsPkMap, ncPK2NCObjMap,
                taskPk2CommissionPkMap,projectList);
        if (lists != null && class2NumMap.get(TaskBVO.class) <= 0
                && lists.length > 0) {
            throw new BusinessException("任务单孙表[实验后参数]发现无效数据,请联系数据管理员.");
        }
        class2NumMap.put(TaskRVO.class, lists!=null?(lists.length - 1):0);
        if (lists != null && lists.length > 0) {
            sqlList.addAll(getAfterInsertSQL(lists));
        }
        //额外的回写sample表
        if (listsExtends != null && listsExtends.length > 0) {
            sqlList.addAll(getSampleExtendInsertSQL(listsExtends));
        }
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
     * 实验后参数
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     */
    private List<String> getAfterInsertSQL(String[] lists) {
        List<String> rsList = new ArrayList();
        if (lists != null && lists.length > 1) {
            StringBuilder sqlSB = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO c_proj_task_para_b").append("(")
                        .append(lists[0]).append(")  values (");
                sqlSB.append(lists[i]);
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }

    /**
     * 实验条件
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     */
    private List<String> getConditionInsertSQL(String[] lists) {
        List<String> rsList = new ArrayList();
        if (lists != null && lists.length > 1) {
            StringBuilder sqlSB = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO result").append("(").append(lists[0])
                        .append(")  values (");
                sqlSB.append(lists[i]);
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }

    /**
     * 任务行
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     */
    private List<String> getTaskInsertSQL(String[] lists) {
        List<String> rsList = new ArrayList();
        if (lists != null && lists.length > 1) {
            StringBuilder sqlSB = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO c_proj_task").append("(")
                        .append(lists[0]).append(")  values (");
                sqlSB.append(lists[i]);
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }

    /**
     * 实验前参数
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     */
    private List<String> getBeforeInsertSQL(String[] lists) {
        List<String> rsList = new ArrayList();
        if (lists != null && lists.length > 1) {
            StringBuilder sqlSB = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO C_PROJ_PARA_A").append("(")
                        .append(lists[0]).append(")  values (");
                sqlSB.append(lists[i]);
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }

    /**
     * 样品行
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     */
    private List<String> getSampleInsertSQL(String[] lists) {
        List<String> rsList = new ArrayList();
        if (lists != null && lists.length > 1) {
            StringBuilder sqlSB = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO C_PROJ_LOGIN_SAMPLE").append("(")
                        .append(lists[0]).append(")  values (");
                sqlSB.append(lists[i]);
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }
    /**
     * 样品行
     *
     * @param lists
     * @param ncPK2LimsPk
     * @param fatherPkList
     * @param selfPkList
     * @return
     */
    private List<String> getSampleExtendInsertSQL(String[] lists) {
        List<String> rsList = new ArrayList();
        if (lists != null && lists.length > 1) {
            StringBuilder sqlSB = new StringBuilder();
            for (int i = 1; i < lists.length; i++) {
                sqlSB.append("INSERT INTO SAMPLE").append("(")
                        .append(lists[0]).append(")  values (");
                sqlSB.append(lists[i]);
                sqlSB.append(" ) ");
                rsList.add(sqlSB.toString());
                sqlSB.delete(0, sqlSB.length());
            }
        }
        return rsList;
    }
    /**
     * 委托单主表固定值回写
     */
    private static Map<String,String> COMMISSION_HEARD_STATIC_MAP = new HashMap<>();
    {
    	COMMISSION_HEARD_STATIC_MAP.put("TEMPLATE","1");
    	COMMISSION_HEARD_STATIC_MAP.put("C_MAIL_LAB_APPROVAL","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("TEMPLATE_VERSION","1");
    	COMMISSION_HEARD_STATIC_MAP.put("STATUS","'U'");
    	COMMISSION_HEARD_STATIC_MAP.put("OLD_STATUS","'I'");
    	COMMISSION_HEARD_STATIC_MAP.put("COST_FACTOR","0.0000");
    	COMMISSION_HEARD_STATIC_MAP.put("GROUP_NAME","'DEFAULT'");
    	COMMISSION_HEARD_STATIC_MAP.put("CLOSED","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("SAMPLE_TEMPLATE","'HF-MAIN'");
    	COMMISSION_HEARD_STATIC_MAP.put("STABILITY","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("USE_GROUP_LOGIN","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("USE_GRID_LOGIN","'T'");
    	COMMISSION_HEARD_STATIC_MAP.put("ALIQUOT","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("SIGNED","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("T_COA_TEMPLATE","'HF_COA_DEFAULT'");
    	COMMISSION_HEARD_STATIC_MAP.put("T_INVOICE_NUMBER","0");
    	COMMISSION_HEARD_STATIC_MAP.put("T_LOGIN_VERIF_REQD","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("T_LOGIN_VERIFIED","'T'");
    	COMMISSION_HEARD_STATIC_MAP.put("T_PRE_INVOICE_NUMBER","0");
    	COMMISSION_HEARD_STATIC_MAP.put("APPROVED","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("APPROVAL_GROUP","'PROJECT'");
    	COMMISSION_HEARD_STATIC_MAP.put("READY_FOR_APPROVAL","'T'");
		COMMISSION_HEARD_STATIC_MAP.put("APPROVAL_ID", "0");
		COMMISSION_HEARD_STATIC_MAP.put("NUM_U", "0");
		COMMISSION_HEARD_STATIC_MAP.put("NUM_I", "0");
		COMMISSION_HEARD_STATIC_MAP.put("NUM_P", "0");
		COMMISSION_HEARD_STATIC_MAP.put("NUM_C", "0");
		COMMISSION_HEARD_STATIC_MAP.put("NUM_A", "0");
		COMMISSION_HEARD_STATIC_MAP.put("NUM_R", "0");
		COMMISSION_HEARD_STATIC_MAP.put("NUM_X", "0");
    	COMMISSION_HEARD_STATIC_MAP.put("APPROVAL_ROUTING","'HF'");
    	COMMISSION_HEARD_STATIC_MAP.put("C_QUOTES_CREATED","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("C_QUOTES_VERIFYED","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("C_PRIORITY_LEVEL","3");
    	COMMISSION_HEARD_STATIC_MAP.put("C_TOTAL_CHARGE","0.00");
    	COMMISSION_HEARD_STATIC_MAP.put("HAS_ANSWER_SET","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("C_RPT_AUTHORIZED","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("C_INVOICE_CREATED","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("C_INVOICE_VERIFYED","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("C_RPT_REPORT_NUMBER","0");
    	COMMISSION_HEARD_STATIC_MAP.put("C_ALLTASK_COA_AUTHORIZED","'F'");
    	COMMISSION_HEARD_STATIC_MAP.put("C_RPT_CNAS_LOGO","'T'");
    }
    /**
     * 参照需要code写入的字段
     */
    private static Map<Class<?>,Set<String>> NEED_CODE_WRITE_BACK_MAP = new HashMap<>();
    {
		Set<String> COMMISSIONHVO_SET = new HashSet<>();
		COMMISSIONHVO_SET.add("cuserid");
		COMMISSIONHVO_SET.add("pk_maincategory");
		COMMISSIONHVO_SET.add("pk_subcategory");
		COMMISSIONHVO_SET.add("pk_lastcategory");
		COMMISSIONHVO_SET.add("reportlang");
		COMMISSIONHVO_SET.add("productproperty");
		COMMISSIONHVO_SET.add("customername");
		COMMISSIONHVO_SET.add("customertype");
		COMMISSIONHVO_SET.add("testrequirement");
		COMMISSIONHVO_SET.add("checkingproperty");
		COMMISSIONHVO_SET.add("productline");
		COMMISSIONHVO_SET.add("batchnumber");
		COMMISSIONHVO_SET.add("productdate");
		COMMISSIONHVO_SET.add("batchserial");
		COMMISSIONHVO_SET.add("identificationtype");
		COMMISSIONHVO_SET.add("certificationtype");
		NEED_CODE_WRITE_BACK_MAP.put(CommissionHVO.class, COMMISSIONHVO_SET);

    }
    /**
     * 委托单表头
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
        	//查询样品行数量,用于回写project.NUM_SAMPLES 字段
        	Integer bNum = (Integer)baseDao.executeQuery(
        			"select count(*) from qc_commission_b where pk_commission_h = '"+pk_commission_h+"'", 
        			new ColumnProcessor());
        	if(bNum == null){
        		bNum = 0;
        	}
        	colNameSB.append(",").append("num_samples");
    		colValSB.append(",").append(bNum);
            //处理固定值字段
        	for(String colName : COMMISSION_HEARD_STATIC_MAP.keySet()){
        		colNameSB.append(",").append(colName);
        		colValSB.append(",").append(COMMISSION_HEARD_STATIC_MAP.get(colName));
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
     * 获取Insert语句片断
     *
     * @param fieldMap
     * @param clazz
     * @param pkname
     * @param pkvalue
     * @param ncPK2LimsPkMap
     *            ncpk和lims系统pk对应关系
     * @param ncPK2ObjectMap
     *            本次保存的所有NCOBJ
     * @return fieldValues[0]: 字段名片断<br />
     *         fieldValues[1-n]：值片断
     * @return project 委托单编号,用于各个表的project
     * @throws BusinessException
     */
    private String[] getInsertSQLByMap(Map<String, String> fieldMap,
                                       Class<?> clazz, String condition,
                                       Map<String, Object> ncPK2LimsPkMap,
                                       Map<String, Object> ncPK2NCObjMap,
                                       Map<String, String> taskPk2CommissionPkMap,List<String> projectList)
            throws BusinessException {

        // 用于某个字段的值,一行数据对应array中的一行
        StringBuilder[] fieldValues = null;
        IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
        List<?> srcData = (List<?>) query.retrieveByClause(clazz, condition);
        List<Integer> pkList = null;

        List<Integer> test_numberList = null;
        List<Integer> sample_numberList = null;

        if (CommissionHVO.class != clazz) {
            // 预申请pk-出来委托单之外(委托单的主键是billNo)
            pkList = getPrePk(clazz, srcData.size());
        }

        if(TaskSVO.class == clazz){
            //任务单子表还需要申请test_number和sample_number
            test_numberList = getPrePk("test_number","test",srcData.size());
            sample_numberList = getPrePk("sample_number","sample",srcData.size());
        }
        if (null == srcData || srcData.size() <= 0) {
            return null;
        }
        fieldValues = new StringBuilder[srcData.size() + 1];
        // 用于拼接字段名
        StringBuilder insertFields = new StringBuilder();
        // 进行列循环
        for (Entry<String, String> map : fieldMap.entrySet()) {
            String fieldName = map.getKey();

            String[] fields = null;
            if (map.getValue().contains(";")) {
                fields = getWriteBackFields(map.getValue().split(";"));
            } else {
                fields = getWriteBackFields(new String[] { map.getValue() });
            }
            // times是处理一对多关系的
            int times = fields.length;
            for (String field : fields) {
                insertFields.append(field).append(",");
            }

            if (srcData != null && srcData.size() > 0) {

                int row = 1;
                for (Object data : srcData) {
                    ISuperVO vo = (ISuperVO) data;
                    Object unDofieldValue = vo.getAttributeValue(fieldName);
                    // 处理参照
                    Object realValue = dealRefValue(clazz, fieldName,
                            unDofieldValue);

                    if (null == fieldValues[row]) {
                        fieldValues[row] = new StringBuilder();
                    }
                    if(null!=realValue &&("creationtime".equals(fieldName)||"modifiedtime".equals(fieldName))){
                        realValue = "to_timestamp('"+realValue+"','yyyy-mm-dd hh24:mi:ss.ff')";}
                    if(null!=realValue &&("productdate".equals(fieldName))){
                        realValue = "to_timestamp('"+realValue+"','yyyy-mm-dd')";}
                    for (int j = 0; j < times; j++) {
                        if (realValue != null) {
                            if (realValue instanceof Integer || realValue instanceof UFDouble || realValue instanceof Double) {
                                fieldValues[row].append(realValue).append(",");
                            }else if(realValue instanceof UFBoolean){
                            	if("Y".equals(String.valueOf(realValue))){
                            		fieldValues[row].append("'")
                                    .append("T")
                                    .append("',");
                            	}else{
                            		fieldValues[row].append("'")
                                    .append("F")
                                    .append("',");
                            	}
                            	
                            }else {
                            	if(null!=realValue &&("creationtime".equals(fieldName)||"modifiedtime".equals(fieldName)||"productdate".equals(fieldName))){
                                    fieldValues[row]
                                            .append(String.valueOf(realValue))
                                            .append(",");
                                }else{
                                    fieldValues[row].append("'")
                                            .append(dealEscapse(String.valueOf(realValue)))
                                            .append("',");
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
                if (CommissionHVO.class != clazz) {
                    // 写入主键和父主键(只支持单主键,要多主键,需要参考上面的字段添加逻辑)
                    if (0 == i) {
                        // 处理委托单子表字段需要写到孙表
                        if (CommissionRVO.class == clazz) {
                            Map<String, String> LimsFiled2ncFieldMap = getBodySimple2ChildrenMapping();
                            for (String limsField : LimsFiled2ncFieldMap
                                    .keySet()) {
                                fieldValues[i]
                                        .append(getWriteBackFields(new String[] { limsField })[0])
                                        .append(",");
                            }
                        }
                        fieldValues[i].append(
                                getWriteBackFields(new String[] { LIMS_PK_MAP
                                        .get(clazz) })[0]).append(",");

                        if(TaskSVO.class==clazz){
                            //如果是任务单子表,需要回写testnumber和sample_number
                            fieldValues[i]
                                    .append("test_number").append(",");
                            fieldValues[i]
                                    .append("sample_number");
                        }else{
                            fieldValues[i]
                                    .append(getWriteBackFields(new String[] { LIMS_FK_MAP
                                            .get(clazz) })[0]);
                        }
                        //实验前后参数,需要另写入委托单的billno
                        if(CommissionRVO.class == clazz || TaskRVO.class == clazz){
                            fieldValues[i]
                                    .append(",").append("project");
                        }

                    } else {
                        // 获取上层的主键:
                        String fatherPk = (String) (((ISuperVO) srcData
                                .get(i - 1)).getAttributeValue(getWriteBackFields(new String[] { NC_FK_MAP
                                .get(clazz) })[0]));

                        String realfatherPk = getRealFatherPk(clazz, fatherPk,
                                taskPk2CommissionPkMap);

                        // 处理委托单子表字段需要写到孙表
                        if (CommissionRVO.class == clazz) {
                            Map<String, String> LimsFiled2ncFieldMap = getBodySimple2ChildrenMapping();
                            for (String limsField : LimsFiled2ncFieldMap
                                    .keySet()) {
                                // 上层的NCVO 需要回写到子表的字段
                                String ncField = LimsFiled2ncFieldMap
                                        .get(limsField);
                                // 获取上层的数据
                                ISuperVO fatherObj = (ISuperVO) ncPK2NCObjMap
                                        .get(realfatherPk);
                                // 获取需要回写的值
                                Object oldFieldValue = fatherObj == null ? null
                                        : fatherObj.getAttributeValue(ncField);
                                // 处理参照
                                Object realValue = dealRefValue(clazz, ncField,
                                        oldFieldValue);
                                if (null == realValue
                                        || realValue instanceof Integer|| realValue instanceof UFDouble || realValue instanceof Double) {
                                	
                                    fieldValues[i].append(realValue)
                                            .append(",");
                                } else if(realValue instanceof UFBoolean){
                                	if("Y".equals(String.valueOf(realValue))){
                                		fieldValues[i].append("'")
                                        .append("T")
                                        .append("',");
                                	}else{
                                		fieldValues[i].append("'")
                                        .append("F")
                                        .append("',");
                                	}
                                	
                                }else {
                                    fieldValues[i].append("'")
                                            .append(dealEscapse(String.valueOf(realValue)))
                                            .append("',");
                                }
                            }
                        }
                        if (null == pkList.get(i - 1)
                                || pkList.get(i - 1) instanceof Integer ) {
                            // pk
                            fieldValues[i].append(pkList.get(i - 1))
                                    .append(",");
                        } else {
                            // pk
                            fieldValues[i].append("'").append(pkList.get(i - 1))
                                    .append("',");
                        }




                        //任务单子表需要另外会写test_number和sample_number
                        if(TaskSVO.class == clazz){
                            if (null == test_numberList.get(i - 1)
                                    || test_numberList.get(i - 1) instanceof Integer) {
                                // test_numberList
                                fieldValues[i].append(test_numberList.get(i - 1))
                                        .append(",");
                            } else {
                                // test_numberList
                                fieldValues[i].append("'").append(test_numberList.get(i - 1))
                                        .append("',");
                            }

                            if (null == sample_numberList.get(i - 1)
                                    || sample_numberList.get(i - 1) instanceof Integer) {
                                // sample_numberList
                                fieldValues[i].append(sample_numberList.get(i - 1))
                                        .append("");
                            } else {
                                // sample_numberList
                                fieldValues[i].append("'").append(sample_numberList.get(i - 1))
                                        .append("'");
                            }
                        }else{
                            if (null == ncPK2LimsPkMap.get(realfatherPk)
                                    || ncPK2LimsPkMap.get(realfatherPk) instanceof Integer) {
                                // 外键
                                fieldValues[i].append(ncPK2LimsPkMap.get(realfatherPk));
                            } else {
                                // pk
                                // 外键
                                fieldValues[i].append("'").append(ncPK2LimsPkMap.get(realfatherPk)).append("'");
                            }
                        }
                        //实验前后参数,需要另写入委托单的billno
                        if(CommissionRVO.class == clazz || TaskRVO.class == clazz){
                            fieldValues[i]
                                    .append(",").append("'").append(projectList.get(0)).append("'");
                        }

                        // 记录主键
                        ncPK2LimsPkMap
                                .put(((ISuperVO) srcData.get(i - 1))
                                        .getPrimaryKey(), pkList.get(i - 1));
                        // 记录存储的obj
                        ncPK2NCObjMap
                                .put(((ISuperVO) srcData.get(i - 1))
                                        .getPrimaryKey(), srcData.get(i - 1));

                    }

                } else {
                    // 委托单的主键就是他的billno,没有父主键
                    if (i > 0) {
                        //记录主键
                        ncPK2LimsPkMap
                                .put(((ISuperVO) srcData.get(i - 1))
                                        .getPrimaryKey(), ((ISuperVO) srcData
                                        .get(i - 1))
                                        .getAttributeValue("billno"));

                        projectList.add(String.valueOf(((ISuperVO) srcData
                                .get(i - 1))
                                .getAttributeValue("billno")));

                        // 记录存储的obj
                        ncPK2NCObjMap
                                .put(((ISuperVO) srcData.get(i - 1))
                                        .getPrimaryKey(), srcData.get(i - 1));
                    }
                    fieldValues[i].setLength(fieldValues[i].length() - 1);
                }

                rsString[i] = fieldValues[i].toString();
            }

        }
        return rsString;
    }
    private String dealEscapse(String value){
    	if(value!=null){
    		if(value.contains("'")){
    			value = value.replaceAll("'", "''");
    		}
    	}
    	return value;
    }
    /**
     * 获取Insert语句片断--Sample表额外回写方法
     *
     * @param fieldMap
     * @param clazz
     * @param pkname
     * @param pkvalue
     * @param ncPK2LimsPkMap
     *            ncpk和lims系统pk对应关系
     * @param ncPK2ObjectMap
     *            本次保存的所有NCOBJ
     * @return fieldValues[0]: 字段名片断<br />
     *         fieldValues[1-n]：值片断
     * @throws BusinessException
     */
    private String[] getSampleInsertSQLByMap(Map<String, String> fieldMap,
                                             Class<?> clazz, String condition,Map<String, Object> ncPK2LimsPkMap,
                                             Map<String, Object> ncPK2NCObjMap)
            throws BusinessException {

        // 用于某个字段的值,一行数据对应array中的一行
        StringBuilder[] fieldValues = null;
        IUAPQueryBS query = NCLocator.getInstance().lookup(IUAPQueryBS.class);
        List<?> srcData = (List<?>) query.retrieveByClause(clazz, condition);
        List<Integer> pkList = null;
        if (CommissionHVO.class != clazz) {
            // 预申请pk-出来委托单之外(委托单的主键是billNo)
            pkList = getPrePkExtend(clazz, srcData.size());
        }
        if (null == srcData || srcData.size() <= 0) {
            return null;
        }
        fieldValues = new StringBuilder[srcData.size() + 1];
        // 用于拼接字段名
        StringBuilder insertFields = new StringBuilder();
        // 进行列循环
        for (Entry<String, String> map : fieldMap.entrySet()) {
            String fieldName = map.getKey();

            String[] fields = null;
            if (map.getValue().contains(";")) {
                fields = getWriteBackFields(map.getValue().split(";"));
            } else {
                fields = getWriteBackFields(new String[] { map.getValue() });
            }
            // times是处理一对多关系的
            int times = fields.length;
            for (String field : fields) {
                insertFields.append(field).append(",");
            }

            if (srcData != null && srcData.size() > 0) {

                int row = 1;
                for (Object data : srcData) {
                    ISuperVO vo = (ISuperVO) data;
                    Object unDofieldValue = vo.getAttributeValue(fieldName);
                    // 处理参照
                    Object realValue = dealRefValue(clazz, fieldName,
                            unDofieldValue);
                    if (null == fieldValues[row]) {
                        fieldValues[row] = new StringBuilder();
                    }
                    for (int j = 0; j < times; j++) {
                        if (realValue != null) {
                            if (realValue instanceof Integer || realValue instanceof UFDouble || realValue instanceof Double) {
                                fieldValues[row].append(realValue).append(",");
                            } else if(realValue instanceof UFBoolean){
                            	if("Y".equals(String.valueOf(realValue))){
                            		fieldValues[row].append("'")
                                    .append("T")
                                    .append("',");
                            	}else{
                            		fieldValues[row].append("'")
                                    .append("F")
                                    .append("',");
                            	}
                            	
                            }else {
                                fieldValues[row].append("'")
                                        .append(dealEscapse(String.valueOf(realValue)))
                                        .append("',");
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
                if (CommissionHVO.class != clazz) {
                    // 写入主键和父主键(只支持单主键,要多主键,需要参考上面的字段添加逻辑)
                    if (0 == i) {
                        // 处理委托单子表字段需要写到孙表
                        if (CommissionRVO.class == clazz) {
                            Map<String, String> LimsFiled2ncFieldMap = getBodySimple2ChildrenMapping();
                            for (String limsField : LimsFiled2ncFieldMap
                                    .keySet()) {
                                fieldValues[i]
                                        .append(getWriteBackFields(new String[] { limsField })[0])
                                        .append(",");
                            }
                        }
                        fieldValues[i].append(
                                getWriteBackFields(new String[] { LIMS_PK_MAP_EXTEND
                                        .get(clazz) })[0]).append(",");
                        fieldValues[i]
                                .append(getWriteBackFields(new String[] { LIMS_FK_MAP_EXTEND
                                        .get(clazz) })[0]);

                    } else {
                        // 获取上层的主键:
                        String fatherPk = (String) (((ISuperVO) srcData
                                .get(i - 1)).getAttributeValue(getWriteBackFields(new String[] { NC_FK_MAP
                                .get(clazz) })[0]));

                        String realfatherPk = fatherPk;

                        // 处理委托单子表字段需要写到孙表
                        if (CommissionRVO.class == clazz) {
                            Map<String, String> LimsFiled2ncFieldMap = getBodySimple2ChildrenMapping();
                            for (String limsField : LimsFiled2ncFieldMap
                                    .keySet()) {
                                // 上层的NCVO 需要回写到子表的字段
                                String ncField = LimsFiled2ncFieldMap
                                        .get(limsField);
                                // 获取上层的数据
                                ISuperVO fatherObj = (ISuperVO) ncPK2NCObjMap
                                        .get(realfatherPk);
                                // 获取需要回写的值
                                Object oldFieldValue = fatherObj == null ? null
                                        : fatherObj.getAttributeValue(ncField);
                                // 处理参照
                                Object realValue = dealRefValue(clazz, ncField,
                                        oldFieldValue);
                                if (null == realValue
                                        || realValue instanceof Integer || realValue instanceof UFDouble || realValue instanceof Double) {
                                    fieldValues[i].append(realValue)
                                            .append(",");
                                } else if(realValue instanceof UFBoolean){
                                	if("Y".equals(String.valueOf(realValue))){
                                		fieldValues[i].append("'")
                                        .append("T")
                                        .append("',");
                                	}else{
                                		fieldValues[i].append("'")
                                        .append("F")
                                        .append("',");
                                	}
                                	
                                }else {
                                    fieldValues[i].append("'")
                                            .append(dealEscapse(String.valueOf(realValue)))
                                            .append("',");
                                }
                            }
                        }
                        if (null == pkList.get(i - 1)
                                || pkList.get(i - 1) instanceof Integer) {
                            // pk
                            fieldValues[i].append(pkList.get(i - 1))
                                    .append(",");
                        } else {
                            // pk
                            fieldValues[i].append("'").append(pkList.get(i - 1))
                                    .append("',");
                        }

                        if (null == ncPK2LimsPkMap.get(realfatherPk)
                                || ncPK2LimsPkMap.get(realfatherPk) instanceof Integer) {
                            // 外键
                            fieldValues[i].append(ncPK2LimsPkMap.get(realfatherPk));
                        } else {
                            // pk
                            // 外键
                            fieldValues[i].append("'").append(ncPK2LimsPkMap.get(realfatherPk)).append("'");
                        }



                    }

                } else {
                    // 委托单的主键就是他的billno,没有父主键
                    if (i > 0) {

                    }
                    fieldValues[i].setLength(fieldValues[i].length() - 1);
                }

                rsString[i] = fieldValues[i].toString();
            }

        }
        return rsString;
    }


    /**
     * 根据实体和实体数量申请lims的pk
     *
     * @param clazz
     * @param size
     * @return
     * @throws DAOException
     */
    private List<Integer> getPrePk(Class<?> clazz, int size)
            throws DAOException {
        List<Integer> rs = new ArrayList<>();
        // 获取表
        String tableName = null;
        String pk_filed = null;
        if (LIMS_PK_MAP.get(clazz) != null) {
            tableName = LIMS_PK_MAP.get(clazz).split("\\.")[0];
            pk_filed = LIMS_PK_MAP.get(clazz).split("\\.")[1];
        }
        String pkFileld = null;
        if(TaskSVO.class == clazz){
            pkFileld = "RESULT_NUMBER";
        }else{
            pkFileld = "SEQ_NUM";
        }
        if (tableName != null) {
            String sql = "select MAX("+pkFileld+") from " + tableName;
            Integer startNum = (Integer) baseDao.executeQuery(sql,
                    new ColumnProcessor());
            if (startNum != null && startNum >= 0) {
                for (int i = 1; i <= size; i++) {
                    rs.add(startNum + i);
                }
            }
        }
        return rs;
    }


    /**
     * 预申请pk
     * @param tableName
     * @param size
     * @return
     * @throws DAOException
     */
    private List<Integer> getPrePk(String pkFiled,String tableName, int size) throws DAOException {
        List<Integer> rs = new ArrayList<>();

        if (tableName != null) {
            String sql = "select max("+pkFiled+")+1  from " + tableName;
            Integer startNum = (Integer) baseDao.executeQuery(sql,
                    new ColumnProcessor());
            if (startNum != null && startNum >= 0) {
                for (int i = 1; i <= size; i++) {
                    rs.add(startNum + i);
                }
            }
        }
        return rs;
    }

    /**
     * 根据实体和实体数量申请lims的pk
     *
     * @param clazz
     * @param size
     * @return
     * @throws DAOException
     */
    private List<Integer> getPrePkExtend(Class<?> clazz, int size)
            throws DAOException {
        List<Integer> rs = new ArrayList<>();
        // 获取表
        String tableName = null;
        String pk_filed = null;
        if (LIMS_PK_MAP_EXTEND.get(clazz) != null) {
            tableName = LIMS_PK_MAP_EXTEND.get(clazz).split("\\.")[0];
            pk_filed = LIMS_PK_MAP_EXTEND.get(clazz).split("\\.")[1];
        }
        if (tableName != null) {
            String sql = "select max("+pk_filed+") from " + tableName;
            Integer startNum = (Integer) baseDao.executeQuery(sql,
                    new ColumnProcessor());
            if (startNum != null && startNum >= 0) {
                for (int i = 1; i <= size; i++) {
                    rs.add(startNum + i);
                }
            }
        }
        return rs;
    }

    // //如果是任务单行,需要将此任务单表头主键转换成委托单主表主键
    private String getRealFatherPk(Class<?> clazz, String fatherPk,
                                   Map<String, String> taskPk2CommissionPkMap) {
        if (TaskBVO.class == clazz) {
            fatherPk = taskPk2CommissionPkMap.get(fatherPk);
        }
        return fatherPk;
    }

    private String[] getWriteBackFields(String[] splitFields) {
        List<String> fieldList = new ArrayList<String>();
        for (String field : splitFields) {
            fieldList.add(field.split("\\.")[1]);
        }
        return fieldList.toArray(new String[0]);
    }

    public Map<String, String> getHeadMapping() {
        if (headMapping == null) {
            headMapping = new HashMap<String, String>();
            
            headMapping.put("pk_commissiontype", "project.C_APPLY_TYPE");// 委托单编号
            headMapping.put("billno", "project.name");// 委托单编号
            headMapping.put("billname", "project.title");// 委托单名称
            headMapping.put("pk_owner", "project.customer");// 委托单位
            headMapping.put("pk_dept", "project.c_user_department");// 部门
            headMapping.put("pk_payer", "project.t_source_customer");// 付费单位
            headMapping.put("contract", "project.customer_contact");// 联系人
            headMapping.put("cuserid", "project.owner;project.created_by;");// 创建人
            headMapping.put("email", "project.c_email_address");// Email
            headMapping.put("teleno", "project.c_phone_number");// 联系电话
            headMapping.put("pk_maincategory", "project.c_product_type");// 产品大类
            headMapping.put("pk_subcategory", "project.c_prod_type_c1");// 二级分类
            headMapping.put("pk_lastcategory", "project.c_prod_type_c2");// 三级分类
            headMapping.put("reportformat", "project.c_coa_format");// 报告格式
            headMapping.put("reportlang", "project.c_coa_language");// 报告语言
            headMapping.put("taskbeginsendflag", "project.c_mail_task_end");// 任务开始发送邮件
            headMapping.put("taskendsendflag", "project.c_mail_task_start");// 任务结束发送邮件
            headMapping.put("reportsendflag", "project.c_mail_coa_sign");// 报告签发发送邮件
            headMapping.put("savetotemplateflag", "project.c_is_template");// 是否保存为模板
            headMapping.put("receiptsendflag", "project.c_mail_charge");// 计费单发送给客户邮件提醒
            headMapping.put("quotaionsendflag", "project.c_mail_quotes");// 报价单发送给客户邮件提醒
            headMapping.put("testaim", "project.c_test_purpose");// 测试目的
            headMapping.put("progressneed", "project.description");// 进度要求
            headMapping.put("sampledealtype", "project.c_retain_handle");// 检后样品处理
            headMapping.put("productproperty", "project.c_product_property");// 产品属性
            headMapping.put("customername", "project.c_terminal_client");// 客户名称
            headMapping.put("customertype", "project.c_client_type");// 客户类型
            headMapping.put("testrequirement", "project.c_product_requirement");// 测试需求
            headMapping.put("checkingproperty", "project.c_checking_property");// 检测性质
            headMapping.put("productline", "project.c_product_line");// 生产产线
            headMapping.put("batchnumber", "project.c_batch_number");// 生产批量
            headMapping.put("productdate", "project.c_product_date");// 生产日期
            headMapping.put("batchserial", "project.c_batch_serial");// 生产批号
            headMapping.put("identificationtype",
                    "project.c_identification_type");// 产品鉴定类型
            headMapping
                    .put("certificationtype", "project.c_certification_type");// 认证类型
            headMapping.put("itemnumber", "project.c_item_number");// 项目号
            headMapping.put("creationtime", "project.date_created");// 制单时间
            headMapping.put("modifiedtime", "project.date_updated");// 制单时间
        }

        return headMapping;
    }

    /**
     * 委托单子表
     *
     * @return
     */
    public Map<String, String> getBodySampleMapping() {
        if (bodySampleMapping == null) {
            bodySampleMapping = new HashMap<String, String>();

            bodySampleMapping.put("pk_productserial", "C_PROJ_LOGIN_SAMPLE.product_series");// 产品系列
            bodySampleMapping.put("pk_enterprisestandard", "C_PROJ_LOGIN_SAMPLE.product_standard");// 企业标准
            bodySampleMapping.put("typeno", "C_PROJ_LOGIN_SAMPLE.prodname");// 规格型号
            bodySampleMapping.put("pk_productspec", "C_PROJ_LOGIN_SAMPLE.production_spec");// 规格号
            bodySampleMapping.put("pk_structuretype", "C_PROJ_LOGIN_SAMPLE.structure_type");// 结构类型
            bodySampleMapping.put("contacttype", "C_PROJ_LOGIN_SAMPLE.contact_type");// 触点类型
            bodySampleMapping.put("quantity", "C_PROJ_LOGIN_SAMPLE.sample_quantity");// 样品数量
            bodySampleMapping.put("manufacturer", "C_PROJ_LOGIN_SAMPLE.manufacturer");// 制造商
            bodySampleMapping.put("pk_contactbrand", "C_PROJ_LOGIN_SAMPLE.contact_brand");// 触点牌号
            bodySampleMapping.put("contactmodel", "C_PROJ_LOGIN_SAMPLE.contact_model");// 触点型号
            bodySampleMapping.put("productstage", "C_PROJ_LOGIN_SAMPLE.product_stage");// 温度
            bodySampleMapping.put("pk_samplegroup", "C_PROJ_LOGIN_SAMPLE.sample_group");// 样品组别
            bodySampleMapping.put("otherinfo", "C_PROJ_LOGIN_SAMPLE.other_req");// 其他信息
        }

        return bodySampleMapping;
    }

    /**
     * 委托单子表-额外回写Sample表
     *
     * @return
     */
    public Map<String, String> getBodySampleExtendMapping() {
        if (bodySampleExtendMapping == null) {
            bodySampleExtendMapping = new HashMap<String, String>();

            bodySampleExtendMapping.put("pk_productserial", "Sample.PRODUCT");// 产品系列
            bodySampleExtendMapping.put("pk_structuretype", "Sample.PRODUCT_GRADE");// 结构类型


        }

        return bodySampleExtendMapping;
    }

    /**
     * 委托单子表->孙表的回写字段
     *
     * @return
     */
    public Map<String, String> getBodySimple2ChildrenMapping() {
        if (bodySimple2ChildrenMapping == null) {
            bodySimple2ChildrenMapping = new HashMap<String, String>();
            bodySimple2ChildrenMapping.put("c_proj_para_a.product_standard",
                    "pk_enterprisestandard");// 企业标准
            bodySimple2ChildrenMapping.put("c_proj_para_a.production_spec",
                    "pk_productspec");// 规格号
            bodySimple2ChildrenMapping.put("C_PROJ_PARA_A.structure_type",
                    "pk_structuretype");// 结构类型
            bodySimple2ChildrenMapping.put("C_PROJ_PARA_A.stage",
                    "productstage");// 温度
        }

        return bodySimple2ChildrenMapping;
    }

    public Map<String, String> getGrandBeforeMapping() {
        if (grandBeforeMapping == null) {
            grandBeforeMapping = new HashMap<String, String>();

            grandBeforeMapping.put("analysisname", "C_PROJ_PARA_A.analysis");// 实验前参数名称
            grandBeforeMapping.put("pk_samplegroup",
                    "C_PROJ_PARA_A.sample_group");// 样品组别
            grandBeforeMapping.put("pk_component", "C_PROJ_PARA_A.component");// 参数项
            grandBeforeMapping.put("stdmaxvalue", "C_PROJ_PARA_A.max_value");// 最大值
            grandBeforeMapping.put("stdminvalue", "C_PROJ_PARA_A.min_value");// 最小值
            grandBeforeMapping.put("unitname", "C_PROJ_PARA_A.units");// 单位
            grandBeforeMapping.put("judgeflag", "C_PROJ_PARA_A.check_spec");// 是否判定
            grandBeforeMapping.put("testflag", "C_PROJ_PARA_A.is_added");// 是否测试

        }

        return grandBeforeMapping;
    }

    public Map<String, String> getBodyTaskMapping() {
        if (bodyTaskMapping == null) {
            bodyTaskMapping = new HashMap<String, String>();

            bodyTaskMapping.put("taskcode", "c_proj_task.task_ID");// 任务编号
            bodyTaskMapping.put("testitem", "c_proj_task.task_reported_name");// 测试项目
            bodyTaskMapping.put("pk_testresultname", "c_proj_task.analysis");// 测试结果名称
            bodyTaskMapping.put("runorder", "c_proj_task.order_number");// 顺序
            bodyTaskMapping.put("sampleallocation",
                    "c_proj_task.assigned_sample_display");// 样品分配
            bodyTaskMapping.put("samplequantity",
                    "c_proj_task.assigned_sample_quantity");// 样品数量
        }

        return bodyTaskMapping;
    }

    public Map<String, String> getGrandConditionMapping() {
        if (grandConditionMapping == null) {
            grandConditionMapping = new HashMap<String, String>();

            grandConditionMapping.put("pk_testconditionitem", "result.name");// 测试条件项
            grandConditionMapping.put("conditionstatus", "result.status");// 状态
            grandConditionMapping.put("isoptional", "result.optional");// 是否可选
            grandConditionMapping.put("isallow_out", "result.allow_out");// 是否可报告
            //grandConditionMapping.put("textvalue", "result.entry");// 值 //TODO
            // 处理空的问题,暂缓,因为这两个值需要合并
            grandConditionMapping.put("refvalue;", "result.entry");// 值
            grandConditionMapping.put("unit", "result.units");// 单位
            grandConditionMapping.put("formatted_entry", "result.formatted_entry");// 格式化值
            grandConditionMapping.put("min_limit", "result.min_limit");// 最小值
            grandConditionMapping.put("max_limit", "result.max_limit");// 最大值
        }

        return grandConditionMapping;
    }

    /**
     * 委托单孙表
     *
     * @return
     */
    public Map<String, String> getGrandAfterMapping() {
        if (grandAfterMapping == null) {
            grandAfterMapping = new HashMap<String, String>();

            grandAfterMapping
                    .put("analysisname", "c_proj_task_para_b.analysis");// 实验参数名称
            grandAfterMapping.put("pk_samplegroup",
                    "c_proj_task_para_b.sample_group");// 样品组别
            grandAfterMapping.put("pk_component",
                    "c_proj_task_para_b.component");// 参数项
            grandAfterMapping
                    .put("stdmaxvalue", "c_proj_task_para_b.max_value");// 最大值
            grandAfterMapping
                    .put("stdminvalue", "c_proj_task_para_b.min_value");// 最小值
            grandAfterMapping.put("pk_unit", "c_proj_task_para_b.units");// 单位
            grandAfterMapping.put("judgeflag", "c_proj_task_para_b.check_spec");// 是否判定
            grandAfterMapping.put("testflag", "c_proj_task_para_b.is_added");// 是否测试
            grandAfterMapping.put("pk_testtemperature",
                    "c_proj_task_para_b.stage");// 测试温度

        }

        return grandAfterMapping;
    }

    /**
     * 根据实体,字段名,参照pk获取参照的值
     *
     * @param clazz
     *            NC实体类
     * @param fieldName
     *            字段名
     * @param fieldValue
     *            参照PK
     * @return 如果该字段是参照,则返回参照的值,如果不是参照,原封不动返回fieldValue
     * @throws BusinessException
     */
    private Object dealRefValue(Class<?> clazz, String fieldName,
                                Object fieldValue) throws BusinessException {
    	//系统参照特殊处理
    	if(isSystemRef(clazz,fieldName)){
    		return dealSystemRef(clazz,fieldName,fieldValue);
    	}
        String strSQL = "select reftype from md_property ppt " + "inner join md_class cls on cls.id = ppt.classid "
                + "inner join pub_billtemplet_b btb on btb.metadataproperty = 'qcco.' || cls.name || '.' || ppt.name "
                + "where btb.reftype is not null and cls.fullclassname = '" + clazz.getName() + "' and ppt.name='"
                + fieldName + "'";

        String refType = (String) baseDao.executeQuery(strSQL, new ColumnProcessor());

        if (!StringUtils.isEmpty(refType)) {
            refType = refType.replace("<", "").replace(">", "").split(",")[0].trim();
        }
        
        try {
            if(TaskSVO.class==clazz && "conditionstatus".equals(fieldName)){
                if(null == fieldValue){
                    fieldValue = 0;
                }else if("已录入".equals(fieldValue)){
                    fieldValue = 1;
                }else {
                    fieldValue = 2;
                }
            }else if(CommissionHVO.class==clazz && "reportlang".equals(fieldName)){
            	//非系统特殊参照 : 报告语言 需要写入编码(非code)
            	return dealNotSystemRefButSpec(clazz,fieldName,fieldValue);
            }/*else if(CommissionHVO.class==clazz && "pk_commissiontype".equals(fieldName)){
            	//非系统特殊参照 : 委托单类型 中文
            	return dealNotSystemRefButSpec(clazz,fieldName,fieldValue);
            }*/
            
            if(null == refType){
                return fieldValue;
            }
            Class<?> refModalClass = Class.forName(refType);

            AbstractRefModel refModel = null;
            try {
                refModel = (AbstractRefModel) refModalClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new BusinessException(e.getMessage());
            }

            Vector value = null;
            if (refModel != null) {
                value = refModel.matchPkData((String) fieldValue);
            }

            if (value != null) {
            	if(NEED_CODE_WRITE_BACK_MAP.get(clazz)!=null && NEED_CODE_WRITE_BACK_MAP.get(clazz).contains(fieldName.toLowerCase())){
            		//写入code
            		fieldValue = ((Vector) value.get(0)).get(0);
            	}else{
            		//写入name
            		fieldValue = ((Vector) value.get(0)).get(1);
            	}
            }
        } catch (Exception e) {
            Logger.error(e);
            return fieldValue;
        }

        return fieldValue;
    }
    //非系统参照缓存
    private Map<String,String> notSystemRefCacheMap = new HashMap<>();
    private Object dealNotSystemRefButSpec(Class<?> clazz, String fieldName, Object fieldValue) throws BusinessException {
    	//缓存大小为100 
		if (notSystemRefCacheMap.size() > 100) {
			notSystemRefCacheMap = new HashMap<>();
		}

		if (CommissionHVO.class == clazz && "reportlang".equals(fieldName)) {
			// 报告语言写入 编码
			if (notSystemRefCacheMap.containsKey(String.valueOf(fieldValue))) {
				return notSystemRefCacheMap.get(String.valueOf(fieldValue));
			}
			String newValue = (String) baseDao.executeQuery("select LIS_NAME from NC_REPORT_LANG WHERE ISENABLE=1 and PK_REPORT_LANG = '" + String.valueOf(fieldValue)
					+ "' ", new ColumnProcessor());
			notSystemRefCacheMap.put(String.valueOf(fieldValue), newValue);
			return newValue;
		}/*else if(CommissionHVO.class==clazz && "pk_commissiontype".equals(fieldName)){
			// 委托单类型写入 编码
			if (notSystemRefCacheMap.containsKey(String.valueOf(fieldValue))) {
				return notSystemRefCacheMap.get(String.valueOf(fieldValue));
			}
			String newValue = (String) baseDao.executeQuery("select NAME from NC_PROJ_TYPE WHERE ISENABLE=1 and PK_PROJ_TYPE = '"
					+ String.valueOf(fieldValue) + "' ", new ColumnProcessor());
			notSystemRefCacheMap.put(String.valueOf(fieldValue), newValue);
			return newValue;
		}*/
		return fieldValue;
		
	}
	//系统参照缓存
    private Map<String,String> systemRefCacheMap = new HashMap<>();
    private Object dealSystemRef(Class<?> clazz, String fieldName, Object fieldValue) throws BusinessException {
    	if(IS_SYSREF_MAP.get(clazz)!=null && IS_SYSREF_MAP.get(clazz).keySet()!=null && IS_SYSREF_MAP.get(clazz).get(fieldName.toLowerCase())!=null){
    		String refCode = IS_SYSREF_MAP.get(clazz).get(fieldName.toLowerCase());
    		//缓存大小为10 
    		if(systemRefCacheMap.size() > 10){
    			systemRefCacheMap = new HashMap<>();
    		}
    		if(refCode!=null){
    			
    			if("org_orgs".equals(refCode)){
    				//组织参照 name
    				if(systemRefCacheMap.containsKey(String.valueOf(fieldValue))){
    					return systemRefCacheMap.get(String.valueOf(fieldValue));
    				}
    				String newValue = 
    						(String)baseDao.executeQuery("select name from org_orgs where pk_org = '"+String.valueOf(fieldValue)+"' ", new ColumnProcessor());
    				systemRefCacheMap.put(String.valueOf(fieldValue), newValue);
    				return newValue;
    			}else if("bd_psndoc".equals(refCode)){
    				//人员参照 code
    				if(systemRefCacheMap.containsKey(String.valueOf(fieldValue))){
    					return systemRefCacheMap.get(String.valueOf(fieldValue));
    				}
    				String newValue = 
    						(String)baseDao.executeQuery("select code from bd_psndoc where pk_psndoc = '"+String.valueOf(fieldValue)+"' ", new ColumnProcessor());
    				systemRefCacheMap.put(String.valueOf(fieldValue), newValue);
    				return newValue;
    			}else if("org_dept".equals(refCode)){
    				//部门参照 name
    				if(systemRefCacheMap.containsKey(String.valueOf(fieldValue))){
    					return systemRefCacheMap.get(String.valueOf(fieldValue));
    				}
    				String newValue = 
    						(String)baseDao.executeQuery("select name from org_dept where pk_dept = '"+String.valueOf(fieldValue)+"' ", new ColumnProcessor());
    				systemRefCacheMap.put(String.valueOf(fieldValue), newValue);
    				return newValue;
    			}
    		}
    	}
		return fieldValue;
	}

	/**
     * 判断是否系统参照
     * @param clazz
     * @param fieldName
     * @return
     */
    /**
     * 系统参照对照表
     */
    private static Map<Class<?>,HashMap<String,String>> IS_SYSREF_MAP = new HashMap<>();
    {
    	HashMap<String,String> COMMISSIONHVO_MAP = new HashMap<>();
    	COMMISSIONHVO_MAP.put("pk_payer","org_orgs");
    	COMMISSIONHVO_MAP.put("cuserid","bd_psndoc");
    	COMMISSIONHVO_MAP.put("pk_owner","org_orgs");
    	COMMISSIONHVO_MAP.put("pk_dept","org_dept");
		IS_SYSREF_MAP.put(CommissionHVO.class, COMMISSIONHVO_MAP);

    }
	private boolean isSystemRef(Class<?> clazz, String fieldName) {
		if(IS_SYSREF_MAP.get(clazz)!=null && IS_SYSREF_MAP.get(clazz).keySet()!=null && IS_SYSREF_MAP.get(clazz).keySet().contains(fieldName.toLowerCase())){
			return true;
		}
		return false;
	}
	
    
}
