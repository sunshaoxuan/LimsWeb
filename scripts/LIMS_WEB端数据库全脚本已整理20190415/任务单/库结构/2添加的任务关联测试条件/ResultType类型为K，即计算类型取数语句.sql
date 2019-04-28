--测试结果类型为K，即计算型SQL语句取数方法
/*
*   首先获取到分项表(nc_component_table)的analysis
*   然后用此analysis获取公式及变量定义情况
*   calc_variables表中的componet字段为需要计算的字段叫什么名字，name为在labware中定义的变量名,其它没什么用
*   calculation表中componet字段同样为需要计算的字段叫什么名字，source_code的CLOB字段为用上面变量定义的公式。
*   注：source_code必定以"return 变量名" 结尾，可以做为编程依据，
*   另外，编程时请注意计算单位的统一，例如持续时间在lims中是以小时为单位存储，逻辑为：不足一小时，强制为1，其它时间同理;
*   此处为后台逻辑运算部分，无关显示，故不提供脚本，仅提供取值SQL;
*   下面为例子
*/
--取变量定义
select * from calc_variables cv where cv.analysis = 'IEC61810-7-2006_4.31-3';   --analysis为程序取出，此处仅为示例
--取变更计算公式
select * calculation c where c.analysis = 'IEC61810-7-2006_4.31-3';             --备注同上
