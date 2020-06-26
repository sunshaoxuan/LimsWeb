--通过任务结果筛选到试验条件
select * from nc_analysis_list nal2 where nal2.name in (
select distinct nal.nc_test_condition
from nc_analysis_list nal , nc_task_addunion nta 
where nal.nc_analysis_name = nta.nc_task_addname)

