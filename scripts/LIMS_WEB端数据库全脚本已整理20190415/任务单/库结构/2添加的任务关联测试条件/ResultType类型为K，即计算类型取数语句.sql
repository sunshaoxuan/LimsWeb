--���Խ������ΪK����������SQL���ȡ������
/*
*   ���Ȼ�ȡ�������(nc_component_table)��analysis
*   Ȼ���ô�analysis��ȡ��ʽ�������������
*   calc_variables���е�componet�ֶ�Ϊ��Ҫ������ֶν�ʲô���֣�nameΪ��labware�ж���ı�����,����ûʲô��
*   calculation����componet�ֶ�ͬ��Ϊ��Ҫ������ֶν�ʲô���֣�source_code��CLOB�ֶ�Ϊ�������������Ĺ�ʽ��
*   ע��source_code�ض���"return ������" ��β��������Ϊ������ݣ�
*   ���⣬���ʱ��ע����㵥λ��ͳһ���������ʱ����lims������СʱΪ��λ�洢���߼�Ϊ������һСʱ��ǿ��Ϊ1������ʱ��ͬ��;
*   �˴�Ϊ��̨�߼����㲿�֣��޹���ʾ���ʲ��ṩ�ű������ṩȡֵSQL;
*   ����Ϊ����
*/
--ȡ��������
select * from calc_variables cv where cv.analysis = 'IEC61810-7-2006_4.31-3';   --analysisΪ����ȡ�����˴���Ϊʾ��
--ȡ������㹫ʽ
select * calculation c where c.analysis = 'IEC61810-7-2006_4.31-3';             --��עͬ��
