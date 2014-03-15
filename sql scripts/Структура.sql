CREATE table service(
  ID_service number CONSTRAINT service_pk_service_id   PRIMARY KEY,
  ID_type number ,
  name_service   varchar(255),
  cost    number
);


Create table type_service(
  ID_type number CONSTRAINT type_service_pk_type_id   PRIMARY KEY,
  name_type varchar(255),
  measure   varchar(255)
);



Create table tariff_list(
  ID_tariff number CONSTRAINT tariff_list_pk_tariff_id   PRIMARY KEY,
  name_tariff varchar(255),
  description varchar(255)
);

Create table service_in_tariff(
  ID_tariff number CONSTRAINT sit_fk_tariff_id REFERENCES tariff_list(ID_tariff)
  ON DELETE CASCADE,
  ID_service number CONSTRAINT sit_fk_service_id REFERENCES service(ID_service)
  ON DELETE CASCADE,
  CONSTRAINT key PRIMARY KEY( ID_tariff,ID_service)
);

create table sim
(
  sim_id            NUMBER   NOT NULL     CONSTRAINT sim_pk_sim_id   PRIMARY KEY,       
  ID_tariff         NUMBER NOT NULL CONSTRAINT sim_fk_tariff_id REFERENCES tariff_list(ID_tariff),
  account           NUMBER 
);        

create table traffic(
  sim_id    number NOT NULL CONSTRAINT traffic_fk_sim_id REFERENCES sim(sim_id)
  ON DELETE CASCADE,
  ID_service number CONSTRAINT traffic_fk_service_id REFERENCES service(ID_service)
  ON DELETE CASCADE,
  amount    number,
  cost      number,
  time       date
);

CREATE  table legal_entity
(
  company_id     NUMBER  CONSTRAINT legal_entity_pk_company_id   PRIMARY KEY,
  name_company VARCHAR(255),
  address    VARCHAR(255),  
  telephone   number(11),
  e_mail     VARCHAR(255),  
  details     varchar(255)--cсылка
);
CREATE  table legal_contr
(
  contr_id     NUMBER  CONSTRAINT legal_contr_pk_contr_id   PRIMARY KEY,
  company_id   number CONSTRAINT legal_contr_fk_company_id REFERENCES legal_entity(company_id )
  ON DELETE CASCADE, 
  contr_doc    varchar(255), --ссылка на файл,
  begin_date         DATE NOT NULL
);


create table client(
  client_id    NUMBER  CONSTRAINT client_pk_client_id   PRIMARY KEY,
  passport_series  number,
  passport_number number,
  firstname    VARCHAR(255) NOT NULL,
  lastname     VARCHAR(255),
  middlename   VARCHAR(255),
  telephone_number NUMBER(11)
);

create table  client_contr
(
  client_id    number  NOT NULL CONSTRAINT client_contr_fk_client_id REFERENCES client(client_id)
  ON DELETE CASCADE, 
  contr_id     NUMBER  CONSTRAINT phys_contr_pk_contr_id   PRIMARY KEY,
  sim_id       number NOT NULL unique CONSTRAINT client_contr_fk_sim_id REFERENCES sim(sim_id)
  ON DELETE CASCADE, 
  contr_doc    VARCHAR(255), --ссылка на файл,
  begin_date         DATE NOT NULL
);

create table sim_contr
(
  sim_id            NUMBER   NOT NULL  unique   CONSTRAINT sim_contr_fk_sim_id REFERENCES sim(sim_id)
  ON DELETE CASCADE,   
  contr_id         NUMBER  CONSTRAINT legal_contr_fk_contr_id REFERENCES legal_contr(contr_id)
  ON DELETE CASCADE, 
  CONSTRAINT key_sim PRIMARY KEY (sim_id,contr_id )
);
--------------------------------------------------------------------------------------------------------------
alter table service
add CONSTRAINT service_fk_type_id foreign key(ID_type) REFERENCES type_service(ID_type)
;


----------------------------------------------------------------------------------------------------------------
create index client_i$_all_infa on client (passport_series,passport_number,firstname,lastname,middlename,telephone_number);
create index legal_entity_i$_all_infa on legal_entity ( name_company,address,telephone,e_mail,details);
create index tariff_list_i$_all_infa on tariff_list ( name_tariff,description);
create index service_i$_all_infa on service (name_service, cost);

create unique index client_i$passport on client(passport_series, passport_number);
-----------------------------------------------------------------------------

CREATE SEQUENCe type_seq$type_id increment by 1 Start with 1;
CREATE  or replace  TRIGGER trig_type
  BEFORE INSERT  ON type_service
  FOR EACH ROW
  BEgin
    SELECT type_seq$type_id.NEXTVAL INTO:NEW.ID_type FROM dual;
  end;
/
---------------
CREATE SEQUENCe tariff_seq$tariff_id increment by 1 Start with 1;
CREATE  or replace  TRIGGER trig_tariff_list
  BEFORE INSERT  ON tariff_list
  FOR EACH ROW
  BEgin
    SELECT tariff_seq$tariff_id.NEXTVAL INTO:NEW.ID_tariff FROM dual;
  end;
/
---------------
CREATE SEQUENCe client_seq$client_id increment by 1 Start with 1;
CREATE  or replace  TRIGGER trig_client
  BEFORE INSERT  ON client
  FOR EACH ROW
  BEgin
    SELECT client_seq$client_id.NEXTVAL INTO:NEW.client_id FROM dual;
  end;
/
--------------

CREATE SEQUENCe service_seq$ID_service increment by 1 Start with 1;
CREATE  or replace  TRIGGER trig_service
  BEFORE INSERT  ON service
  FOR EACH ROW
  BEgin
    SELECT service_seq$ID_service.NEXTVAL INTO:NEW.ID_service FROM dual;
  end;
/
---------------

CREATE SEQUENCe c_contr_seq$contr_id increment by 1 Start with 1;
CREATE  or replace  TRIGGER trig_c_contr
  BEFORE INSERT  ON client_contr
  FOR EACH ROW
  BEgin
    SELECT c_contr_seq$contr_id.NEXTVAL INTO:NEW.contr_id FROM dual;
  end;
/
---------------

CREATE SEQUENCe l_contr_seq$contr_id increment by 1 Start with 1;
CREATE  or replace  TRIGGER trig_l_contr
  BEFORE INSERT  ON legal_contr
  FOR EACH ROW
  BEgin
    SELECT l_contr_seq$contr_id.NEXTVAL INTO:NEW.contr_id FROM dual;
  end;
/
----------------
CREATE SEQUENCe l_entity_seq$company_id increment by 1 Start with 1;
CREATE  or replace  TRIGGER trig_l_entity
  BEFORE INSERT  ON legal_entity
  FOR EACH ROW
  BEgin
    SELECT l_entity_seq$company_id.NEXTVAL INTO:NEW.company_id FROM dual;
  end;
/
------------------
CREATE SEQUENCe sim_seq$sim_id increment by 1 Start with 1;
CREATE  or replace  TRIGGER trig_sim
  BEFORE INSERT  ON SIM
  FOR EACH ROW
  BEgin
    SELECT sim_seq$sim_id.NEXTVAL INTO:NEW.sim_id FROM dual;
  end;
/
---------------------------------------------------------------------------------------------------------------------------

create table numbers
(
  sim_id number null unique constraint numbers_fk_sim_id references sim(sim_id) on delete set null,
  phone_number number(11) constraint numbers_pk_phone_number primary key
);

create or replace trigger sim_insert_trigger
after insert on sim
for each row
begin
  execute immediate 'update numbers set sim_id='||:new.sim_id||' where sim_id is null and rownum=1';
end;
/