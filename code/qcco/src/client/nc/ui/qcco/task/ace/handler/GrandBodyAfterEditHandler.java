package nc.ui.qcco.task.ace.handler;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.pub.beans.UIRefPane;
import nc.ui.pub.bill.BillCellEditor;
import nc.ui.pub.bill.BillItem;
import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.qcco.task.refmodel.TaskAnalyseComponentRefModel;
import nc.vo.pub.BusinessException;

public class GrandBodyAfterEditHandler implements IAppEventHandler<CardBodyAfterEditEvent> {

	@Override
	public void handleAppEvent(CardBodyAfterEditEvent e) {
		if ("textvalue".equals(e.getKey())) {
			if (e.getValue() != null) {
				e.getBillCardPanel().setBodyValueAt("已修改", e.getRow(), "conditionstatus");
			} else {
				e.getBillCardPanel().setBodyValueAt("未录入", e.getRow(), "conditionstatus");
			}

			e.getBillCardPanel().setBodyValueAt(e.getValue(), e.getRow(), "formatted_entry");
		} else if ("refvalue".equals(e.getKey())) {
			if (e.getValue() != null) {
				e.getBillCardPanel().setBodyValueAt("已修改", e.getRow(), "conditionstatus");
			} else {
				e.getBillCardPanel().setBodyValueAt("未录入", e.getRow(), "conditionstatus");
			}
		} else if("instrument".equals(e.getKey())){
			if(e.getValue()!=null && !"".equals(e.getValue())){
				e.getBillCardPanel().setBodyValueAt(e.getValue(), e.getRow(), "pk_instrument");
			}
		}
		else if("valuetype".equals(e.getKey())){
			if(e.getValue()!=null && !"".equals(e.getValue())){
				e.getBillCardPanel().setBodyValueAt(e.getValue(), e.getRow(), "pk_valuetype");
			}
		}
		else if("samplegroup".equals(e.getKey())){
			if(e.getValue()!=null && !"".equals(e.getValue())){
				e.getBillCardPanel().setBodyValueAt(e.getValue(), e.getRow(), "pk_samplegroup");
			}
		}
		else if("component".equals(e.getKey())){
			if(e.getValue()!=null && !"".equals(e.getValue())){
				e.getBillCardPanel().setBodyValueAt(e.getValue(), e.getRow(), "pk_component");
			}
			// 手工输入参照值,需要进行过滤
			BillCellEditor bitem = (BillCellEditor) e.getSource();
			if (bitem.getComponent() instanceof UIRefPane) {
				UIRefPane refPane = (UIRefPane) bitem.getComponent();
				((TaskAnalyseComponentRefModel)refPane.getRefModel()).setAnalysisName(null);
			}
			
			String findAnaliyStrSql = "SELECT DISTINCT TRIM(NC_ANALYSIS_NAME)   analysisname "
					+" FROM NC_TEST_AFTER p "
					+" INNER JOIN NC_RESULT_TYPE t ON t.PK_RESULT_TYPE = p.PK_RESULT_TYPE "
					+" INNER JOIN NC_UNITS_TYPE u ON u.PK_UNITS_TYPE = p.PK_UNITS_TYPE "
					+" INNER JOIN NC_SAMPLE_GROUP g ON g.NC_SAMPLE_NAME IN ( 'A','B','C','D' ) "
					+" INNER JOIN qc_commission_b c ON c.PK_COMMISSION_H = '1001ZZ100000000043DO' "
					+" AND c.PK_SAMPLEGROUP = g.PK_SAMPLE_GROUP "
					+" WHERE p.nc_enstard = ( SELECT NC_BBASEN_NAME FROM NC_BASEN_TYPE "
					+" WHERE PK_BASEN_TYPE = c.PK_ENTERPRISESTANDARD) AND p.nc_sample_point = "
					+" ( SELECT TRIM(NC_BASPRODPOINT_NAME) FROM "
					+" NC_BASPROD_POINT WHERE PK_BASPROD_POINT = c.PK_PRODUCTSPEC) "
					+" AND ' ' || p.Nc_contact_type || ',' LIKE '% '|| c.CONTACTTYPE ||',%' "
					+" AND ' ' || p.NC_COIL_TYPE || ',' LIKE '% '|| "
					+" ( SELECT NC_BASPRODSTRUCT_NAME  FROM NC_BASPROD_STRUCT "
					+" WHERE PK_BASPROD_STRUCT = c.pk_structuretype ) ||',%' "
					+" AND p.nc_coil_current = ( "
					+" SELECT  NC_BASPRODSTRUCT_NAME FROM "
					+" NC_BASPROD_STRUCT WHERE PK_BASPROD_STRUCT = c.pk_structuretype) and rownum = 1 ";
			IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
			String analiy = null;
			try {
				analiy = (String)bs.executeQuery(findAnaliyStrSql, new ColumnProcessor());
			} catch (BusinessException e1) {
				e1.printStackTrace();
			}
			String anaName = null;
			if(analiy!=null && !"".equals(analiy)){
				anaName = String.valueOf(analiy);
			}
			e.getBillCardPanel().setBodyValueAt(anaName, e.getRow(), "analysisname", "pk_task_r");
		}

	}
}
