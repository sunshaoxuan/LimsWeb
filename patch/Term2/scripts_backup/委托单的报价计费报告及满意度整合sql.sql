select name,
       title,
       template,
       (case
         when ready_for_approval = 'F' then
          '未提交'
         when ready_for_approval = 'T' then
          '已提交'
       end),
       (case
         when c_quotes_verifyed = 'F' and c_quotes_created = 'F' then
          ''
         when c_quotes_verifyed = 'F' and c_quotes_created = 'T' then
          '查看并确认'
         when c_quotes_verifyed = 'T' and c_quotes_created = 'T' then
          '查看'
       end),
       (case
         when c_invoice_verifyed = 'F' and c_invoice_created = 'F' then
          ''
         when c_invoice_verifyed = 'F' and c_invoice_created = 'T' then
          '查看并确认'
         when c_invoice_verifyed = 'T' and c_invoice_created = 'T' then
          '查看'
       end),
       (case
         when c_rpt_authorized = 'F' then
          ''
         when c_rpt_authorized = 'T' then
          '满意度评价'
       end),
       (case
         when c_rpt_authorized = 'F' then
          ''
         when c_rpt_authorized = 'T' and c_invoice_verifyed = 'F' then
          '查看&驳回'
         when c_rpt_authorized = 'T' and c_invoice_verifyed = 'T' then
          '下载报告'
       end),
       (case
         when status = 'U' then
          '未送样'
         when status = 'I' then
          '未开始'
         when status = 'C' then
          '完成'
         when status = 'V' then
          '已审核'
         when status = 'X' then
          '已取消'
         when status = 'P' then
          '进行中'
       end),
       '查看进度',
       (case
         when a.approval_status = 'X' and p.ready_for_approval = 'F' then
          '<font color=red>驳回原因</font>'
       end),
       p.customer_contact
  from project p
  left join (select record_key, approval_status, max(approval_id)
               from APPROVAL
              where approval_status = 'X'
              group by record_key, approval_status) a
    on p.name = a.record_key
 where closed = 'F'
   and c_is_template = 'F'
   and template not in ('流程测试', '并行运行')
 order by date_created desc
