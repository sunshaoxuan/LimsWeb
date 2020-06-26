drop table NC_LIST_ENTRY;
--NC_List_ENTRY¸¨±í
create table NC_LIST_ENTRY 
(
   PK_LIST_ENTRY        CHAR(20 CHAR)        not null,
   PK_LIST_TABLE        CHAR(20 CHAR),
   NC_LIST_CODE         CHAR(50 CHAR),
   NC_LIST_NAME         CHAR(2000 CHAR),
   LIST                 VARCHAR2(200 char),
   NAME                 VARCHAR2(200 char),
   VALUE                VARCHAR2(2540 char),
   ORDER_NUMBER         NUMBER(10),
   C_EN_VALUE           VARCHAR2(254 char),
   C_CONT_VALUE         NUMBER(10),
   constraint SYS_LIST_ENTRY primary key (PK_LIST_ENTRY)
)

--
insert into NC_LIST_ENTRY
select substr(SYS_GUID(),1,20),l.pk_list_table,
RANK() over(order by le.name,le.value asc),le.value,le.*
from list_entry le , nc_list_table l
where le.list = l.name;
