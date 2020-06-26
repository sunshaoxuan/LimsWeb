select name,
       title,
       template,
       (case
         when ready_for_approval = 'F' then
          'δ�ύ'
         when ready_for_approval = 'T' then
          '���ύ'
       end),
       (case
         when c_quotes_verifyed = 'F' and c_quotes_created = 'F' then
          ''
         when c_quotes_verifyed = 'F' and c_quotes_created = 'T' then
          '�鿴��ȷ��'
         when c_quotes_verifyed = 'T' and c_quotes_created = 'T' then
          '�鿴'
       end),
       (case
         when c_invoice_verifyed = 'F' and c_invoice_created = 'F' then
          ''
         when c_invoice_verifyed = 'F' and c_invoice_created = 'T' then
          '�鿴��ȷ��'
         when c_invoice_verifyed = 'T' and c_invoice_created = 'T' then
          '�鿴'
       end),
       (case
         when c_rpt_authorized = 'F' then
          ''
         when c_rpt_authorized = 'T' then
          '���������'
       end),
       (case
         when c_rpt_authorized = 'F' then
          ''
         when c_rpt_authorized = 'T' and c_invoice_verifyed = 'F' then
          '�鿴&����'
         when c_rpt_authorized = 'T' and c_invoice_verifyed = 'T' then
          '���ر���'
       end),
       (case
         when status = 'U' then
          'δ����'
         when status = 'I' then
          'δ��ʼ'
         when status = 'C' then
          '���'
         when status = 'V' then
          '�����'
         when status = 'X' then
          '��ȡ��'
         when status = 'P' then
          '������'
       end),
       '�鿴����',
       (case
         when a.approval_status = 'X' and p.ready_for_approval = 'F' then
          '<font color=red>����ԭ��</font>'
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
   and template not in ('���̲���', '��������')
 order by date_created desc
