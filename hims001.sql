
--创建员工序列--废除
CREATE SEQUENCE seq_staff
START WITH 10023001
INCREMENT BY 1
MAXVALUE 10123001
NOCYCLE
CACHE 20
--员工 hims_staff--
CREATE TABLE hims_staff(
staff_id VARCHAR2(32) PRIMARY KEY NOT NULL,---即u_id
staff_name VARCHAR2(32) DEFAULT NULL,  --即u_name
staff_phone VARCHAR2(32)DEFAULT NULL, --即u_phone
dep_name VARCHAR2(128)DEFAULT NULL,
role_name VARCHAR2(32)DEFAULT NULL,  --即r_name_zh
staff_sex VARCHAR2(32) DEFAULT NULL,
position VARCHAR2(32)DEFAULT NULL,--岗位
professional_title VARCHAR2(32)DEFAULT NULL,--职称
staff_flag VARCHAR2(32)DEFAULT NULL,--0空闲 1占用,
create_time DATE DEFAULT NULL
);

ALTER TABLE hims_staff ADD patient_num NUMBER DEFAULT 0; 

alter table hims_staff add CONSTRAINT ch_num check(patient_num>=0 and patient_num<6);
--“职务指的是行政职务,比如院长、主任、护士长、科长了等。职称指的是业务级别比如主任医师、副主任医师、主治医师、住院医师了
--护士、护师、主管护师、副主任护士、主任护师
INSERT INTO hims_staff VALUES('10023001','赵管理','17853116660','','系统管理员','男','系统管理','','1',SYSDATE);
INSERT INTO hims_staff VALUES('10023002','钱医生','17800000000','呼吸与重症医学科','医生','男','科室主任','主任医师','1',SYSDATE);
INSERT INTO hims_staff VALUES('10023003','孙药品','17800000001','呼吸与重症医学科','药品管理员','女','采购','','1',SYSDATE);
INSERT INTO hims_staff VALUES('10023021','郑护士','17890098847','呼吸与重症医学科','护士','女','护士长','主管护师','1',SYSDATE);
INSERT INTO hims_staff VALUES('10023022','王财务','13098778965','','财务','女','会计','','1',SYSDATE);

INSERT INTO hims_staff VALUES(seq_staff.nextval,'','M','123456','admin','','','超级管理员','',1,'','0',SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP01','D','123456','王昌盛','男','13098790035','科室主任','主任医师',1,SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP01','D','123456','李欣','女','17867890098','医生','住院医师',1,SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP01','D','123456','刘琦','女','13043890098','医生','住院医师',0,SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP01','N','123456','闻雯','女','13078231167','护士长','主管护师',1,SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP02','D','123456','黄毅','男','13589780234','科室主任','副主任医师',1,SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP02','N','123456','曹羽心','女','17833562987','护士','护师',1,SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP03','D','123456','陈晨','女','13534578769','科室主任','副主任医师',1,SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP03','N','123456','李思思','女','17845662987','护士','护师',1,SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP04','D','123456','刘铭','男','13039780234','科室主任','副主任医师',1,SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP05','N','123456','钱多多','女','17890562987','护士','护师',1,SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP01','M','123456','李亚男','女','13534690035','药品管理','',1,SYSDATE);
INSERT INTO hims_staff VALUES(seq_staff.nextval,'DEP01','F','123456','赵文锐','女','13086690035','财务管理','',1,SYSDATE);

SELECT * FROM hims_staff;
select staff_id,staff_password,user_salt from hims_staff where staff_id='10023003'
--员工完成--
--药品 hims_drug--
CREATE TABLE hims_drug(
drug_id VARCHAR2(32)PRIMARY KEY NOT NULL,
drug_name VARCHAR2(64),
drug_price NUMBER(8,2),
drug_supply VARCHAR2(64),
drug_quantity NUMBER,
drug_exp DATE,--有效期
drug_operator VARCHAR2(32)
);
INSERT INTO hims_drug VALUES ('1','伏立康唑',4500.00,'美联医药',100,to_date('2024-10-12','yyyy-mm-dd'),'sunyp');--dep1 呼吸 肺曲菌病
INSERT INTO hims_drug VALUES ('2','沙丁胺醇气雾剂',18.00,'福乐康药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');--dep1 慢性阻塞性肺病（支气管炎、哮喘、肺气肿）
INSERT INTO hims_drug VALUES ('3','布地奈德福莫特罗粉吸入剂',303.00,'瑞典药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');--dep1 
INSERT INTO hims_drug VALUES ('4','利福平胶囊',30.00,'华安药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');--dep2 淋巴结核
INSERT INTO hims_drug VALUES ('5','异烟肼',30.00,'瑞典药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');--dep2 结核性脓胸
INSERT INTO hims_drug VALUES ('6','地高辛',50.00,'瑞典药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');--dep3 充血性心力衰竭
INSERT INTO hims_drug VALUES ('7','硝苯地平控释片',33.00,'瑞典药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');--dep3 1.高血压。 2.冠心病。 3.慢性稳定性心绞痛（劳累性心绞痛）。
INSERT INTO hims_drug VALUES ('8','复方甲氧那明胶囊(阿斯美)',725.00,'瑞典药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');--dep4 用于支气管哮喘和喘息性支气管炎，以及其它呼吸系统疾病引起的咳嗽、咳痰、喘息等症状。
INSERT INTO hims_drug VALUES ('9','乙酰半胱氨酸泡腾片(金康速力) ',28.00,'瑞典药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');--dep4 用于治疗分泌大量浓稠痰液的慢性阻塞性肺病（COPD）、慢性支气管炎（CB）、肺气肿（PE）等慢性呼吸系统感染。
INSERT INTO hims_drug VALUES ('10','0.9%氯化钠注射液 250ml',48.00,'瑞典药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');
INSERT INTO hims_drug VALUES ('11','5%葡萄糖注射液 250ml',28.00,'瑞典药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');
INSERT INTO hims_drug VALUES ('12','生理盐水 250ml',18.00,'瑞典药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');
INSERT INTO hims_drug VALUES ('13','维生素B6注射液 100ml',38.00,'瑞典药业',1000,to_date('2023-01-12','yyyy-mm-dd'),'sunyp');

SELECT drug_id FROM hims_drug;
select * from hims_drug order by drug_id;
SELECT MAX(cast(drug_id   as   int)) FROM hims_drug;
--药品完成--
--病房 hims_ward--
CREATE TABLE hims_ward(
ward_id VARCHAR2(32)PRIMARY KEY NOT NULL,
ward_name VARCHAR2(64),
dep_name VARCHAR2(32),
bed_type VARCHAR2(32),
total_bed NUMBER, --总床位
remain_bed NUMBER, --剩余床位
bed_price NUMBER(8,2)
);
INSERT INTO hims_ward VALUES ('1','呼吸与重症医学科一病区','呼吸与重症医学科','VIP(1人)',5,5,380.00);
INSERT INTO hims_ward VALUES ('2','呼吸与重症医学科一病区','呼吸与重症医学科','高等(2人)',10,10,280.00);
INSERT INTO hims_ward VALUES ('3','呼吸与重症医学科一病区','呼吸与重症医学科','初级(4人)',20,20,180.00);
INSERT INTO hims_ward VALUES ('4','呼吸与重症医学科一病区','呼吸与重症医学科','重症监护室(ICU_1人)',5,5,580.00);
INSERT INTO hims_ward VALUES ('5','呼吸与重症医学科二病区','呼吸与重症医学科','VIP(1人)',5,5,380.00);
INSERT INTO hims_ward VALUES ('6','呼吸与重症医学科二病区','呼吸与重症医学科','初级(4人)',20,19,180.00);
select * from hims_ward order by ward_id;

select bed_type from hims_ward where ward_name = '呼吸与重症医学科一病区'
--病房完成--

--入院序列--
CREATE SEQUENCE seq_reg
START WITH 1000
INCREMENT BY 1
MAXVALUE 9999999
NOCYCLE
--入院登记 hims_registration
CREATE TABLE hims_registration(
reg_id VARCHAR2(32) PRIMARY KEY NOT NULL,
patient_id VARCHAR2(32) DEFAULT NULL,--u_id
reg_name VARCHAR2(32)DEFAULT NULL,
reg_sex VARCHAR2(32)DEFAULT NULL,
reg_age NUMBER DEFAULT NULL,
reg_birth DATE DEFAULT NULL,
reg_identity VARCHAR2(32) DEFAULT NULL,--身份证
reg_workplace VARCHAR2(64) DEFAULT NULL,--工作地
reg_phone VARCHAR2(32) DEFAULT NULL,--电话
carte_vital VARCHAR2(32) DEFAULT NULL,--医保卡
pre_amount NUMBER(8,2) DEFAULT NULL,--预缴费
dep_name VARCHAR2(32) DEFAULT NULL,
operator_uname VARCHAR2(32) DEFAULT NULL,
condition VARCHAR2(256) DEFAULT NULL,--病情
out_flag VARCHAR2(32) DEFAULT '0', --0未出院 1出院
ward_name VARCHAR2(128) DEFAULT NULL,
bed_type VARCHAR2(128) DEFAULT NULL,
bed_name VARCHAR2(32) DEFAULT NULL,
doc_name VARCHAR2(32)DEFAULT NULL,
create_time DATE DEFAULT NULL
);
SELECT * FROM hims_registration;

 
--入院完成
--床位
CREATE SEQUENCE seq_bed
START WITH 0
INCREMENT BY 1
MINVALUE 0
MAXVALUE 9999
CYCLE
--床位 hims_bed
CREATE TABLE hims_bed(
bed_id VARCHAR2(32) PRIMARY KEY NOT NULL,
bed_name VARCHAR2(32) DEFAULT null,
ward_id VARCHAR2(32) DEFAULT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
bed_flag VARCHAR2(32) DEFAULT '0',--0未使用 1使用
doc_id VARCHAR2(32) DEFAULT NULL  --staff_name
);
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-1','1');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-2','1');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-3','1');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-4','1');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-5','1');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'10-1','2');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'10-2','2');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'10-3','2');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'10-4','2');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'10-5','2');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'10-6','2');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'10-7','2');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'10-8','2');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'10-9','2');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'10-10','2');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-1','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-2','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-3','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-4','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-5','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-6','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-7','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-8','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-9','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-10','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-11','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-12','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-13','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-14','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-15','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-16','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-17','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-18','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-19','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-20','3');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-1','4');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-2','4');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-3','4');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-4','4');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-5','4');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-1','5');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-2','5');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-3','5');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-4','5');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'5-5','5');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-1','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-2','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-3','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-4','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-5','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-6','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-7','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-8','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-9','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-10','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-11','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-12','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-13','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-14','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-15','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-16','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-17','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-18','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-19','6');
INSERT INTO hims_bed (bed_id,bed_name,ward_id) VALUES(seq_bed.nextval,'20-20','6');

INSERT INTO hims_bed VALUES('BED0001','WD0006','1001','5');
INSERT INTO hims_bed VALUES('BED0002','WD0001','','');
INSERT INTO hims_bed VALUES('BED0003','WD0001','','');
INSERT INTO hims_bed VALUES('BED0004','WD0002','','');
SELECT * FROM hims_bed;
--床位完成
--档案序列--
CREATE SEQUENCE seq_arch
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE
--患者档案 hims_archives   ---应该再加一个u_id
CREATE TABLE hims_archives(
arch_id VARCHAR2(32) PRIMARY KEY NOT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
reg_name VARCHAR2(32) DEFAULT NULL,
staff_id VARCHAR2(32) DEFAULT NULL,
create_time DATE DEFAULT NULL
);
INSERT INTO hims_archives VALUES(seq_arch.nextval,'1003','李患者','admin',SYSDATE);

INSERT INTO hims_archives VALUES ('AR0001','1001','范承亿','3',Sysdate);
SELECT * FROM hims_archives;
--预缴费序列--
CREATE SEQUENCE seq_pre_payment
START WITH 1000000
INCREMENT BY 1
MAXVALUE 9999999
NOCYCLE
--预缴费 hims_prepayment
CREATE TABLE hims_prepayment(
pre_id VARCHAR2(32) PRIMARY KEY NOT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
pre_amount NUMBER(8,2),
operator_uname VARCHAR2(32),
create_time DATE
);
SELECT * FROM hims_prepayment;
--医嘱序列--
CREATE SEQUENCE seq_advice
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE
--医嘱 hims_advice
CREATE TABLE hims_advice(
advice_id VARCHAR2(32) PRIMARY KEY NOT NULL,
advice_type VARCHAR2(32) DEFAULT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
reg_name VARCHAR2(64) DEFAULT NULL,
staff_id VARCHAR2(32)DEFAULT NULL, --doc_name 
diagnostic_code VARCHAR2(32)DEFAULT NULL,--诊断代码
advice_detail VARCHAR2(512)DEFAULT NULL,
create_time DATE DEFAULT NULL,
end_time DATE DEFAULT NULL
);
--肺曲菌病  F12.001
--慢性阻塞性肺病 COPD03（支气管炎 COPD03.023、哮喘 COPD03.024、肺气肿 COPD03.025）
--淋巴结核 L07.111
--结核性脓胸 J23.078
--充血性心力衰竭 C02.099
--1.高血压 G01.001。 2.冠心病G02.023。 3.慢性稳定性心绞痛（劳累性心绞痛）X11.067。
--慢性支气管炎（CB02.054）、肺气肿（PE05.23）
INSERT INTO hims_advice VALUES('AD001','1001','3','COPD03.023','临时：血、尿等常规检查;心电图;血脂、血糖、乙肝系列;长期：常规护理;低脂低盐饮食;沙丁胺醇气雾剂 口服 2次/日;维生素B6注射液 100ml',SYSDATE);
SELECT * FROM hims_advice ;
--治疗记录 hims_treatment 废弃
CREATE TABLE hims_treatment(
tre_id VARCHAR2(32) PRIMARY KEY NOT NULL,
reg_id VARCHAR2(32),
staff_id VARCHAR2(32),
tre_detail VARCHAR2(512),
treate_price NUMBER(8,2),
create_time DATE
);
INSERT INTO hims_treatment VALUES('TRE0001','1001','3','经诊断：患者为支气管炎，需住院观察治疗。','120',SYSDATE);
SELECT * FROM hims_treatment;
--用药序列--
CREATE SEQUENCE seq_medrecord
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE
--用药记录 hims_medrecord
CREATE TABLE hims_medrecord(
med_id VARCHAR2(32) PRIMARY KEY NOT NULL,
drug_id VARCHAR2(32) DEFAULT NULL,
drug_num NUMBER DEFAULT NULL,
drug_price NUMBER(8,2) DEFAULT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
staff_id VARCHAR2(32) DEFAULT NULL,
create_time DATE  DEFAULT NULL
);

SELECT * FROM hims_medrecord;
--护理序列--
CREATE SEQUENCE seq_nurse
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE
--护理记录 hims_nurserecord
CREATE TABLE hims_nurserecord(
nur_id  VARCHAR2(32) PRIMARY KEY NOT NULL,
reg_id VARCHAR2(32)DEFAULT NULL,
bed_id VARCHAR2(32)DEFAULT NULL,
staff_id VARCHAR2(32)DEFAULT NULL,
p_temperature VARCHAR2(32)DEFAULT NULL,
heart_rate VARCHAR2(32)DEFAULT NULL,
blood_pressure VARCHAR2(32)DEFAULT NULL,
blood_sugar VARCHAR2(32)DEFAULT NULL,
remarks VARCHAR2(512)DEFAULT NULL,
create_time DATE DEFAULT NULL
);
--高压为180，低压为95，脉搏为72时，正确记录方式为血压180/95mmHg。脉搏72次/分。血糖正常值在3.9-6.1mmol/L
INSERT INTO hims_nurserecord VALUES ('NUR0001','1001','BED0001','5','36.7','76','120/85','4.2','',SYSDATE);
SELECT * FROM hims_nurserecord;
SELECT create_time FROM hims_nurserecord WHERE reg_id = '1003' ORDER BY create_time;
--费用明细序列--
CREATE SEQUENCE seq_expdetail
START WITH 1
INCREMENT BY 1
MAXVALUE 99999
NOCYCLE
--费用明细 hims_expdetail
CREATE TABLE hims_expdetail(
exp_id VARCHAR2(32) PRIMARY KEY NOT NULL,
cost_id VARCHAR2(32) DEFAULT NULL,
charge_code VARCHAR2(32) DEFAULT NULL,--收费代码  --药品 DRU01 --治疗费 TR01 --床位费 BED01
charge_amount NUMBER(8,2) DEFAULT NULL --金额
);
INSERT INTO hims_expdetail VALUES (seq_expdetail.nextval,'1601000001','BED01',380);
INSERT INTO hims_expdetail VALUES (seq_expdetail.nextval,'1601000001','DRU01',28);
INSERT INTO hims_expdetail VALUES (seq_expdetail.nextval,'1601000001','TR01',150);

SELECT * FROM hims_expdetail;
SELECT SUM(charge_amount) FROM hims_expdetail WHERE cost_id = '1601000001'
 select * from hims_expdetail where cost_id ='1601000001' and charge_code = 'BED01'

--费用序列--
CREATE SEQUENCE seq_cost
START WITH 1601000001
INCREMENT BY 1
MAXVALUE 1609999999
NOCYCLE

--费用 hims_cost
CREATE TABLE hims_cost(
cost_id VARCHAR2(32) PRIMARY KEY NOT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
cost_amount NUMBER(8,2) DEFAULT NULL,
cost_operator VARCHAR2(32) DEFAULT NULL,--u_name
create_time DATE DEFAULT NULL,
cost_type VARCHAR2(32) DEFAULT '未结算'
);
INSERT INTO hims_cost (cost_id,reg_id)VALUES('1601000001','1003');
INSERT INTO hims_cost (cost_id,reg_id)VALUES('1601000002','1005');
SELECT seq_cost.currval FROM dual
SELECT * FROM hims_cost;


INSERT INTO hims_cost VALUES(seq_cost.nextval,'1001','医保卡','648','','','13',SYSDATE);
--出院序列--
CREATE SEQUENCE seq_out
START WITH 1301000001
INCREMENT BY 1
MAXVALUE 1609999999
NOCYCLE
--出院登记 hims_out  出院：医生、病床关系解除 入院中出院标志
CREATE TABLE hims_out(
out_id VARCHAR2(32) PRIMARY KEY NOT NULL,
reg_id VARCHAR2(32) DEFAULT NULL,
is_cost VARCHAR2(32) DEFAULT null,--已结清 未结清 
is_out VARCHAR2(32) DEFAULT NULL,
out_operator VARCHAR2(32) DEFAULT NULL,
create_time DATE DEFAULT NULL
);
INSERT INTO hims_out (out_id,reg_id,is_cost,is_out)VALUES(seq_out.nextval,'1003','未结算','未出院');
SELECT * FROM hims_out;

--new 角色 hims_role
CREATE TABLE hims_role(
r_id VARCHAR2(32) PRIMARY KEY NOT NULL,
r_name VARCHAR2(128) DEFAULT NULL,
r_name_zh VARCHAR2(128) DEFAULT NULL,
r_enabled VARCHAR2(32) DEFAULT NULL
);
INSERT INTO hims_role VALUES('1','Admin','系统管理员','1');
INSERT INTO hims_role VALUES('6','Drug','药品管理员','1');
INSERT INTO hims_role VALUES('2','Doctor','医生','1');
INSERT INTO hims_role VALUES('3','Nurse','护士','1');
INSERT INTO hims_role VALUES('4','Finance','财务','1');
INSERT INTO hims_role VALUES('5','Patient','患者','1');
SELECT * FROM hims_role;
 select max(r_id) from hims_role
--菜单 hims_menu
CREATE TABLE hims_menu(
m_id VARCHAR2(32) PRIMARY KEY NOT NULL,
m_path VARCHAR2(64) DEFAULT NULL,
m_name VARCHAR2(64) DEFAULT NULL,
m_name_zh VARCHAR2(64) DEFAULT NULL,
m_icon_cls VARCHAR2(64) DEFAULT NULL,
m_component VARCHAR2(64) DEFAULT NULL,
m_parent_id VARCHAR2(32) DEFAULT NULL
);
INSERT INTO hims_menu VALUES('1','/admin','AdminIndex','首页','el-icon-s-home','AdminIndex','0');
INSERT INTO hims_menu VALUES('2','/admin/dashboard','DashboardAdmin','快捷菜单',null, 'dashboard/index', '1');
INSERT INTO hims_menu VALUES('3','/admin','User','用户管理','el-icon-user','AdminIndex','0');
INSERT INTO hims_menu VALUES('4','/admin','InOut','出入院管理','iconfont icon-zhuyuanguanli','AdminIndex','0');
INSERT INTO hims_menu VALUES('5','/admin','Treatment','医嘱护理','iconfont icon-huli','AdminIndex','0');
INSERT INTO hims_menu VALUES('6','/admin','Drug','药品管理','iconfont icon-yaopin','AdminIndex','0');
INSERT INTO hims_menu VALUES('7','/admin','Finance','费用管理','iconfont icon-feiyongguanli','AdminIndex','0');
INSERT INTO hims_menu VALUES('8','/admin','System','系统管理','iconfont icon-xitongguanli','AdminIndex','0');
INSERT INTO hims_menu VALUES('9','/admin/user/profile','Profile','用户信息',null,'user/UserProfile','3');
INSERT INTO hims_menu VALUES('10','/admin/user/role', 'Role', '角色配置', null, 'user/Role', '3');
INSERT INTO hims_menu VALUES('11','/admin/inout/archives', 'Archives', '档案管理', null, 'inout/Archives', '4');
INSERT INTO hims_menu VALUES('12','/admin/inout/inhospital', 'InHospital', '入院管理', null, 'inout/InHospital', '4');
INSERT INTO hims_menu VALUES('13','/admin/inout/outhospital', 'OutHospital', '出院管理', null, 'inout/OutHospital', '4');
INSERT INTO hims_menu VALUES('14','/admin/treatment/advice', 'Advice', '医嘱管理', null, 'treatment/Advice', '5');
INSERT INTO hims_menu VALUES('15','/admin/treatment/nurse', 'Nurse', '护理管理', null, 'treatment/Nurse', '5');
INSERT INTO hims_menu VALUES('16','/admin/drug/druglist', 'DrugList', '药品清单', null, 'drug/DrugList', '6');
INSERT INTO hims_menu VALUES('17','/admin/drug/drugdispatch', 'DrugDispatch', '药品发放', null, 'drug/DrugDispatch', '6');
INSERT INTO hims_menu VALUES('18','/admin/cost/costDetail', 'CostDetail', '费用明细', null, 'cost/CostDetail', '7'); 
INSERT INTO hims_menu VALUES('19','/admin/cost/costsettlement', 'CostSettlement', '费用结算', null, 'cost/CostSettlement', '7');
INSERT INTO hims_menu VALUES('21','/admin/system/ward', 'Ward', '病房管理', null, 'system/Ward', '8');
INSERT INTO hims_menu VALUES('22','/admin/system/syslog', 'Syslog', '日志管理', null, 'system/Syslog', '8');
INSERT INTO hims_menu VALUES('23','/admin/user/staff', 'Staff', '员工管理', null, 'user/Staff', '3');
INSERT INTO hims_menu VALUES('24','/admin/inout/registration', 'Registration', '入院登记', null, 'inout/Registration', '4');
INSERT INTO hims_menu VALUES('25','/admin/inout/archivesDetail', 'ArchivesDetail', '档案明细', null, 'inout/ArchivesDetail', '4');

--未完待续。。。
SELECT * FROM hims_menu;
--用户序列
CREATE SEQUENCE seq_user
START WITH 10023001
INCREMENT BY 1
MAXVALUE 10123001
NOCYCLE

--用户 hims_user
CREATE TABLE hims_user(
u_id VARCHAR2(32) PRIMARY KEY NOT NULL,
u_username VARCHAR2(64) DEFAULT NULL,
u_password VARCHAR2(64) DEFAULT NULL,
u_salt VARCHAR2(255) DEFAULT NULL,
u_name VARCHAR2(255) DEFAULT NULL,
u_phone VARCHAR2(255) DEFAULT NULL,
u_email VARCHAR2(255) DEFAULT NULL,
create_time DATE DEFAULT SYSDATE,
u_enabled VARCHAR2(32) DEFAULT '1', --0 禁用 1启用
u_type VARCHAR2(32) DEFAULT NULL    --P 患者 S 员工
);
INSERT INTO hims_user VALUES(seq_user.nextval,'zhaogl','54b2d6bf97add4aa8b8c768281c1c8b7','o24FduHwyfOLdox8n+h0Sg==',
'赵管理','17853116660','zhmanager@163.com',SYSDATE,'1','S');
INSERT INTO hims_user VALUES(seq_user.nextval,'qianys','d255230f4de2cfd6895739799ff04cea','CP3HoL7AHljPipI4rZ6H8A==',
'钱医生','17800000000','qiandoc@163.com',SYSDATE,'1','S');
INSERT INTO hims_user(u_id,u_username,u_password,u_salt,u_name,u_phone,u_email,u_type) VALUES(seq_user.nextval,'sunyp','d255230f4de2cfd6895739799ff04cea','CP3HoL7AHljPipI4rZ6H8A==',
'孙药品','17800000001','sundrug@163.com','S');
INSERT INTO hims_user VALUES(seq_user.nextval,'lihz','d255230f4de2cfd6895739799ff04cea','CP3HoL7AHljPipI4rZ6H8A==',
'李患者','17800000000','qiandoc@163.com',SYSDATE,'1','P');
SELECT * FROM hims_user;
SELECT MAX(u_id) FROM hims_user;
SELECT COUNT(1) FROM hims_user WHERE u_username='zhaogl' AND u_type='S'
INSERT INTO hims_user (u_id,u_username,u_password,u_salt) VALUES(seq_user.nextval,'bbb',
'f9f69aa8dbef0cef2e05a0421518e76e','BSYopqwOR1+lu574uGb8/g==')
--用户角色序列
CREATE SEQUENCE seq_user_role
START WITH 1
INCREMENT BY 1
MAXVALUE 10000
NOCYCLE
--用户-角色 hims_user_role
CREATE TABLE hims_user_role(
ur_id VARCHAR2(32) PRIMARY KEY NOT NULL,
u_id VARCHAR2(32) DEFAULT NULL ,
r_id VARCHAR2(32) DEFAULT NULL 
);
INSERT INTO hims_user_role VALUES(seq_user_role.nextval,'10023001','1');
INSERT INTO hims_user_role VALUES(seq_user_role.nextval,'10023002','2');
INSERT INTO hims_user_role VALUES(seq_user_role.nextval,'10023003','6');
INSERT INTO hims_user_role VALUES(seq_user_role.nextval,'10023004','5');
INSERT INTO hims_user_role VALUES(seq_user_role.nextval,'10023056','2');
SELECT * FROM hims_user_role;

--角色-菜单序列
CREATE SEQUENCE seq_role_menu
START WITH 1
INCREMENT BY 1
MAXVALUE 10000
NOCYCLE
--角色-菜单 hims_role_menu
CREATE TABLE hims_role_menu(
rm_id varchar2(32) PRIMARY KEY NOT NULL,
r_id VARCHAR2(32) DEFAULT NULL,
m_id VARCHAR2(32) DEFAULT NULL
);
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','1');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','2');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','3');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','4');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','5');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','6');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','7');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','8');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','9');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','10');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','11');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','12');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','13');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','14');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','15');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','16');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','17');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','18');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','19');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','21');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','22');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','23');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','24');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'1','25');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'6','6');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'6','1');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'4','25');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'5','25');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'2','1');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'2','11');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'2','12');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'2','5');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'2','14');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'2','17');
INSERT INTO hims_role_menu VALUES(seq_role_menu.nextval,'2','2');

DELETE FROM hims_role_menu WHERE r_id = '2'

SELECT * FROM hims_role_menu WHERE r_id = '2'

select * from hims_menu where m_id='6'

select * from hims_menu where m_parent_id='6' AND m_id = '17'

--日志
CREATE SEQUENCE seq_syslog
START WITH 1
INCREMENT BY 1
MAXVALUE 10000
CYCLE

CREATE TABLE tbsyslog (
 sys_id VARCHAR2(32) PRIMARY KEY NOT NULL,
 logId VARCHAR2(32) DEFAULT NULL,
 accessDate DATE DEFAULT NULL,
 requestType VARCHAR2(32) DEFAULT NULL,
 accessInterface VARCHAR2(128) DEFAULT NULL,
 interfaceParams VARCHAR2(256) DEFAULT NULL,
 accessIp VARCHAR2(64) DEFAULT NULL
);
SELECT * FROM tbsyslog

--创建存储过程
CREATE OR REPLACE PROCEDURE proc_delLog AS
BEGIN
  DELETE FROM tbsyslog WHERE accessDate< TRUNC(SYSDATE)-1;
  COMMIT;
END;  

--Oracle定时器
declare
  job number;  /*job:定时器名称*/
BEGIN
  DBMS_JOB.SUBMIT(  
        JOB => job,  /*自动生成JOB_ID*/  
        WHAT => 'proc_delLog;',  /*需要执行的存储过程名称或SQL语句*/  
        NEXT_DATE => sysdate+1/(24*60),  /*初次执行时间-下一个1分钟*/  
        INTERVAL =>'TRUNC(sysdate) + 1 +0 / (24)' /*每天的凌晨0点执行一次*/
      );  
  commit;
end;


----------------------------------------
--获取两时间的相差天数 
select To_date('2008-05-02 01:00:00' , 'yyyy-mm-dd hh24-mi-ss') - To_date('2008-04-30 23:59:59' , 'yyyy-mm-dd hh24-mi-ss') FROM dual

select to_date('07/06/2015','mm/dd/yyyy')-to_date('07/01/2015','mm/dd/yyyy') from dual;
select to_date('2019-01-01','yyyy-MM-dd')-to_date('2019-01-03','yyyy-MM-dd') from dual







