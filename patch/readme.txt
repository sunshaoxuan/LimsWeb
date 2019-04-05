补丁安装步骤

1. 按编号执行/scripts文件夹下的所有脚本
2. 将/modules文件夹直接覆盖至nchome的同级文件夹下
3. 检查NC数据库中以下同义词是否创建，未创建的需补充：
	NC_THIRD_TYPE;
	NC_SECOND_TYPE;
	NC_FIRST_TYPE;
	NC_PROJ_TYPE;
	NC_PROJ_PREFIX;
	NC_REPORT_TYPE;
	NC_REPORT_LANG;
	NC_RATAIN_HANDLE;
	NC_STATUS_TYPE;
	NC_CUSTOMER_TYPE;
	NC_TESTREQUEST_TYPE;
	NC_TEST_TYPE;
	NC_PRODAUTH_TYPE;
	NC_SAFE_TYPE;
	NC_SAMPLE_INFO;
	NC_PROD_TYPE;
	NC_SAMPLE_GROUP;
	NC_TEST_INIT;
	NC_UNITS_TYPE;
	NC_RESULT_TYPE;
4. 检查所有被引用的LIMS_DATA表格中ISENABLE字段的拼写，如有错误需修改，否则无法参照到数据。另ISENABLE统一为是否有效，不作为DR使用
5. 启动中间件
6. 创建用户，分配权限
7. 职责分配时委托单节点位置：C0质量管理->COJ0质检委托->C0J002委托单->C0J00210委托单维护
