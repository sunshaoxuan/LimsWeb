--µ¥Î»±í
create table NC_UNITS_TYPE 
(
   PK_UNITS_TYPE        CHAR(20 CHAR)        not null,
   UNIT_CODE            VARCHAR2(20 char),
   NC_UNITS_CODE        CHAR(50 CHAR),
   NC_UNITS_NAME        CHAR(200 CHAR),
   NC_UNITS_DESCRIPTION VARCHAR(100),
   NC_UNITS_DISP        CHAR(200 CHAR),
   constraint PK_NC_UNITS_TYPE primary key (PK_UNITS_TYPE)
);

alter table NC_UNITS_TYPE
   add constraint FK_NC_UNITS_REFERENCE_UNITS foreign key (UNIT_CODE)
      references LIMS_DATA.UNITS (UNIT_CODE);

--
Insert into NC_UNITS_TYPE
select substr(SYS_GUID(),1,20), u.unit_code ,RANK() over(order by u.unit_code asc),
u.display_string,u.description,u.display_string
from units u;
