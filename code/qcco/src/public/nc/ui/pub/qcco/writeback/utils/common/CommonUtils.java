package nc.ui.pub.qcco.writeback.utils.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.logging.Logger;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.bd.ref.AbstractRefModel;
import nc.ui.pub.qcco.writeback.utils.WriteBackProcessData;
import nc.ui.pub.qcco.writeback.utils.LIMSVO.Project;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import nc.vo.qcco.commission.CommissionBVO;
import nc.vo.qcco.commission.CommissionHVO;
import nc.vo.qcco.commission.CommissionRVO;
import nc.vo.qcco.task.TaskBVO;
import nc.vo.qcco.task.TaskRVO;
import nc.vo.qcco.task.TaskSVO;

import org.apache.commons.lang.StringUtils;


/**
 * 公用方法
 * @author 91967
 *
 */
public class CommonUtils {

	private BaseDAO baseDao = new BaseDAO();
	
	private WriteBackProcessData processData;
	
	//用于SQL转换
	private StringBuilder sb = new StringBuilder();
	/**
	 * 用于一对多的回写
	 * @param splitFields
	 * @return
	 */
	public String[] getWriteBackFields(String[] splitFields) {
		List<String> fieldList = new ArrayList<String>();
		for (String field : splitFields) {
			fieldList.add(field.split("\\.")[1]);
		}
		return fieldList.toArray(new String[0]);
	}
	/**
     * 根据实体和实体数量申请lims的pk
     *
     * @param clazz
     * @param size
     * @return
     * @throws DAOException
     */
    public List<Integer> getPrePk(Class<?> clazz, int size)
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
    public List<Integer> getPrePk(String pkFiled,String tableName, int size) throws DAOException {
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
    public List<Integer> getPrePkExtend(Class<?> clazz, int size)
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
	
	public String dealEscapse(String value){
    	if(value!=null){
    		if(value.contains("'")){
    			value = value.replaceAll("'", "''");
    		}
    	}
    	return value;
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
    @SuppressWarnings("rawtypes")
	public Object dealRefValue(Class<?> clazz, String fieldName,
                                Object fieldValue) throws BusinessException {
    	//系统参照特殊处理
    	if(isSystemRef(clazz,fieldName)){
    		return dealSystemRef(clazz,fieldName,fieldValue);
    	}
    	//有些父表写入子表的字段,需要把类型改成父表
    	if(clazz==CommissionRVO.class){
    		switch(fieldName){
    		case "pk_enterprisestandard":
    			clazz=CommissionBVO.class;
    			break;
    		case "pk_productspec":
    			clazz=CommissionBVO.class;
    			break;
    		case "pk_structuretype":
    			clazz=CommissionBVO.class;
    			break;
    		default: break;
    		}
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
    
	private boolean isSystemRef(Class<?> clazz, String fieldName) {
		if(IS_SYSREF_MAP.get(clazz)!=null && IS_SYSREF_MAP.get(clazz).keySet()!=null && IS_SYSREF_MAP.get(clazz).keySet().contains(fieldName.toLowerCase())){
			return true;
		}
		return false;
	}
    
	//系统参照缓存
    private Map<String,String> systemRefCacheMap = new HashMap<>();
    public Object dealSystemRef(Class<?> clazz, String fieldName, Object fieldValue) throws BusinessException {
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
    				String sql = "select nvl(job.psncode,sm.user_code) psncode "
    						+" from  sm_user sm  "
    						+" left join (select * from bd_psnjob jobinner where ismainjob = 'Y' ) job on rownum = 1 and job.pk_psndoc = sm.pk_psndoc   "
    						+" where sm.cuserid = '"+String.valueOf(fieldValue)+"' or sm.pk_psndoc = '"+String.valueOf(fieldValue)+"'";
    				String newValue = 
    						(String)baseDao.executeQuery(sql, new ColumnProcessor());
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
		//COMMISSIONHVO_SET.add("checkingproperty");
		COMMISSIONHVO_SET.add("productline");
		//COMMISSIONHVO_SET.add("batchnumber");
		COMMISSIONHVO_SET.add("productdate");
		COMMISSIONHVO_SET.add("batchserial");
		COMMISSIONHVO_SET.add("identificationtype");
		//COMMISSIONHVO_SET.add("certificationtype");
		NEED_CODE_WRITE_BACK_MAP.put(CommissionHVO.class, COMMISSIONHVO_SET);

    }
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
    
    /**
     * 处理NC字段的值
     * @param unDofieldValue
     * @return
     * @throws BusinessException 
     */
	public Object getRealValue(Object unDofieldValue,String fieldName,Class<?> clazz) throws BusinessException {
		
		// 处理参照
		Object realValue = 
				dealRefValue(clazz, fieldName, unDofieldValue);

		if (null != realValue && ("creationtime".equals(fieldName) || "modifiedtime".equals(fieldName))) {
			realValue = "to_timestamp('" + realValue + "','yyyy-mm-dd hh24:mi:ss.ff')";
		}
		if (null != realValue && ("productdate".equals(fieldName))) {
			realValue = "to_timestamp('" + realValue + "','yyyy-mm-dd')";
		}
		if (realValue instanceof UFBoolean) {
			if ("Y".equals(String.valueOf(realValue))) {
				realValue = "T";
			} else {
				realValue = "F";
			}
		}
		
		return realValue;
	}
	/**
	 * 通过nc主键获取与之对应的LIMS主键
	 * @param fatherPk
	 * @param clazz
	 * @return
	 */
	public Object getLIMSPKByNCPK(String fatherPk, Class clazz) {
		Object LIMSPk = null;
		if(clazz == CommissionBVO.class){
			ISuperVO[] vos = processData.getAggCommissionHVO().getChildren(CommissionBVO.class);
			
			for(int i = 0; i<vos.length; i++){
				if(vos[i]!=null && fatherPk.equals(vos[i].getPrimaryKey())){
					//LIMS样品组  顺序和委托单子表一致,所以按index 对应
					LIMSPk = (Integer)processData.getLoginSampleList().get(i).getAttributeValue("seq_num");
				}
			}
		}else if(clazz == TaskBVO.class){
			ISuperVO[] vos = processData.getAggTaskHVO().getChildren(TaskBVO.class);
			
			for(int i = 0; i<vos.length; i++){
				if(vos[i]!=null && fatherPk.equals(vos[i].getPrimaryKey())){
					//LIMS样品组  顺序和委托单子表一致,所以按index 对应
					LIMSPk = (Integer)processData.getTaskList().get(i).getAttributeValue("seq_num");
				}
			}
		}
		
		return LIMSPk;
	}
	/**
	 * 根据ncpk 获取ncVO
	 * @param fatherPk
	 * @param processData 
	 * @param class1
	 * @return
	 */
	public ISuperVO getNCObjByPK(String fatherPk, Class clazz) {
		ISuperVO rtn = null;
		if(clazz == CommissionBVO.class){
			ISuperVO[] bvos = 
					processData.getAggCommissionHVO().getChildren(CommissionBVO.class);
			for(ISuperVO bvo : bvos){
				if(bvo!=null && fatherPk.equals(bvo.getPrimaryKey())){
					return bvo;
				}
			}
		}else if(clazz == TaskBVO.class){
			ISuperVO[] bvos = 
					processData.getAggTaskHVO().getChildren(TaskBVO.class);
			for(ISuperVO bvo : bvos){
				if(bvo!=null && fatherPk.equals(bvo.getPrimaryKey())){
					return bvo;
				}
			}
		}
		
		return rtn;
	}
	/**
	 * 根据ncpk 获取ncVO在数组中的index
	 * @param fatherPk
	 * @param processData 
	 * @param class1
	 * @return
	 */
	public Integer getNCObjIndexByPK(String fatherPk, Class clazz) {
		if(clazz == CommissionBVO.class){
			ISuperVO[] bvos = 
					processData.getAggCommissionHVO().getChildren(CommissionBVO.class);
			for(int i = 0 ; i< bvos.length;i++){
				if(bvos[i]!=null && fatherPk.equals(bvos[i].getPrimaryKey())){
					return i;
				}
			}
		}else if(clazz == TaskBVO.class){
			ISuperVO[] bvos = 
					processData.getAggTaskHVO().getChildren(TaskBVO.class);
			for(int i = 0 ; i< bvos.length;i++){
				if(bvos[i]!=null && fatherPk.equals(bvos[i].getPrimaryKey())){
					return i;
				}
			}
		}
		
		
		return -1;
	}
	
	
	
	public void setData(WriteBackProcessData data) {
		this.processData = data;
	}
    
	
	

	/**
	 * 把此数据体转换成lims系统的SQL
	 * @return
	 */
	public List<String> toLIMSSQL(){
		List<String> rs = new ArrayList<>();
		
		if(processData.getProject()!=null){
			List<SuperVO> temp = new ArrayList<>();
			temp.add(processData.getProject());
			rs.addAll(VOToInserSQL(temp,"project"));
		}
		
	

		return rs;
	}
	
	private List<String> VOToInserSQL(List<SuperVO> voList,String tableName){
		List<String> rsList = new ArrayList<>();
		StringBuilder nameSB = new StringBuilder();
		StringBuilder valueSB = new StringBuilder();
		
		StringBuilder sqlSB = new StringBuilder();
		
		for(SuperVO vo : voList){
			String[] names = vo.getAttributeNames();
			
			for(String name : names){
				nameSB.append(name).append(",");
				
				Object realValue = processData.getProject().getAttributeValue(name);
				//可直接add的sqlvalue
				String sqlValue = dealValue4Sql(name,realValue);
				valueSB.append(sqlValue).append(",");
			}
			nameSB.delete(nameSB.length()-1, nameSB.length());
			valueSB.delete(valueSB.length()-1, valueSB.length());
			
			sqlSB.append("INSERT INTO ").append(tableName).append(" (")
            .append(nameSB.toString()).append(")  values (");
			sqlSB.append(valueSB);
			sqlSB.append(" ) ");
			
			rsList.add(sqlSB.toString());
			
			sqlSB.delete(0, sqlSB.length());
			nameSB.delete(0, nameSB.length());
			valueSB.delete(0, valueSB.length());
		}
		
		
		
		
		return rsList;
	}

	/**
	 * 一些sql 的处理
	 * @param name
	 * @param realValue
	 * @return
	 */
	
	private String dealValue4Sql(String name, Object realValue) {
		String rt = null;
		sb.delete(0, sb.length());
		if (realValue != null) {
			if (realValue instanceof Integer || realValue instanceof UFDouble || realValue instanceof Double) {
				rt = String.valueOf(realValue) ;
			}else{
				if (getTimeColumnSet().contains(name)) {
					rt = String.valueOf(realValue);
				} else {
					rt = sb.append("'").append(dealEscapse(String.valueOf(realValue))).append("'").toString();
				}
			}
		} else {
			rt = "null";
		}
		return rt;
	}
	
	/**
	 * 一些时间字段
	 */
	private Set<String> TIME_COLUMN_SET = new HashSet<>();
	
	private Set<String> getTimeColumnSet(){
		if(null == TIME_COLUMN_SET){
			TIME_COLUMN_SET = new HashSet<>(); 
		}
		TIME_COLUMN_SET.add("changed_on");
		TIME_COLUMN_SET.add("date_created");
		TIME_COLUMN_SET.add("login_date");
		TIME_COLUMN_SET.add("recd_date");
		TIME_COLUMN_SET.add("c_submit_date");
		TIME_COLUMN_SET.add("date_received");
		TIME_COLUMN_SET.add("date_started");
		TIME_COLUMN_SET.add("t_date_enabled");
		
		return TIME_COLUMN_SET;
	}
	
	/**
     * 通过分析名,获取分析版本
     * @param analysisName
     * @return
     * @throws DAOException 
     */
    public String getAnalysisVerionFromName(String analysisName) throws DAOException {
		String sql = " select VERSION from nc_analysis_list where name  = '"+analysisName+"'"; 
		Integer ver = (Integer)baseDao.executeQuery(sql, new ColumnProcessor());
		return String.valueOf(ver);
	}
	
	
    
}
