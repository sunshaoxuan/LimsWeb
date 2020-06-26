--LISTÖ÷±í
drop table  NC_LIST_TABLE ;

/*==============================================================*/
/* Table: NC_LIST_TABLE                                         */
/*==============================================================*/
create table NC_LIST_TABLE 
(
   PK_LIST_TABLE        CHAR(20 CHAR)        not null,
   NC_LIST_CODE         CHAR(50 CHAR),
   NC_LIST_NAME         CHAR(200 CHAR),
   NAME                 VARCHAR2(20 char),
   GROUP_NAME           VARCHAR2(20 char),
   DESCRIPTION          VARCHAR2(254 char),
   CHANGED_ON           DATE,
   CHANGED_BY           VARCHAR2(20 char),
   REMOVED              VARCHAR2(1 char),
   EXT_LINK             VARCHAR2(254 char),
   SORT                 VARCHAR2(1 char),
   constraint SYS_LIST_TABLE primary key (PK_LIST_TABLE)

);

---- 
insert into NC_LIST_TABLE
select  substr(SYS_GUID(),1,20),RANK() over(order by l.name asc),l.name,l.*
from list l
