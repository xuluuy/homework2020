/*
包含n个接口请求函数的模块
函数的返回值: promise对象
 */
import ajax from './ajax'
//const BASE_URL = '/api'
// 1、用户名密码登陆
export const reqPwdLogin = ({u_username, u_password, u_type}) => ajax('/login', {u_username, u_password, u_type}, 'POST');
//2.注册用户
export const reqRegister = (user) => ajax('/register',user,'POST');
//3.用户登出
export const reqLogout = () => ajax('/logout');
//4.获取用户列表
export const reqUserList = () => ajax('/admin/user');
//5.获取角色列表
export const reqRoleList = () => ajax('/admin/role');
//6.查询hims_user_role 根据u_id 得到 r_id
export const reqRidByUid = (u_id) => ajax('/admin/getU_R',{u_id},'POST');
//7.更新用户
export const reqUpdateUser = (updateuser) => ajax('/admin/updateUser',updateuser,'POST');
//8.重置密码
export const reqResetPassword = (u_username) => ajax('/admin/user/password',{u_username},'POST');
//9.删除用户
export const reqDeleteUser = (u_id) => ajax('/admin/deleteUser',{u_id},'POST');
//10.更改用户可用状态
export const reqUpdateUstatus = ({u_enabled,u_username}) => ajax('/admin/user/status',{u_enabled,u_username},'POST');
//11.获取菜单
export const reqMenuList = () => ajax('/admin/role/menu');
//12.更改角色update
export const reqUpdateRole = (updaterole) => ajax('/admin/role/update',updaterole,'POST');
//13.获取当前角色菜单
export const reqSelectMids = (r_id) => ajax('/admin/currentMenu',{r_id},'POST');
//14.改变角色状态
export const reqChangeRole = ({r_enabled,r_id}) => ajax('/admin/role/status',{r_enabled,r_id},'POST');
//15.删除角色
export const reqDeleteRole = (r_id) => ajax('/admin/role/delete',{r_id},'POST');
//16.改变用户密码
export const reqResetPass = (user) => ajax('/resetPassword',user,'POST');
//17.查询员工信息
export const reqStaffList = () => ajax('/admin/staff');
//18.修改员工信息
export const reqUpdateStaff = (updatestaff) => ajax('/admin/staff/update',updatestaff,'POST');
//19.日志列表
export const reqsysLogList = (data) => ajax('/sysLogList',data,'POST');
//20.获取病房列表
export const reqWardList = () => ajax('/system/ward');
//21.修改病房列表
export const reqUpdateWard = (updateward) => ajax('/system/ward/update',updateward,'POST');
//22.删除病房
export const reqDeleteWard = (ward_id) => ajax('/system/ward/delete',{ward_id},'POST');
//23.查询药品列表
export const reqDurgList = () => ajax('/drug/drugList');
//24.修改药品信息
export const reqUpdateDurg = (updatedurg) => ajax('/drug/update',updatedurg,'POST');
//25.删除药品
export const reqDeleteDurg = (drug_id) => ajax('/drug/delete',{drug_id},'POST');
//26.查询入院登记id序列
export const reqCreateRegId = () => ajax('/inout/createRegId');
//27.根据u_username查询user
export const reqQueryUserByUname = (u_username) => ajax('/admin/user/query',{u_username},'POST');
//28.查询预缴金id序列
export const reqPreId = () => ajax('/cost/prement/id');
//29.添加入院登记
export const reqCreateReg = (registrations) => ajax('/inout/in/create',registrations,'POST');
//30.添加预缴费
export const reqCreatePre = (pre_payment) => ajax('/cost/prepayment/create',pre_payment,'POST');
//31.查询入院登记列表
export const reqRegList = () => ajax('/inout/in/query');
//32.查询床位列表
export const reqBedList = (ward) =>ajax('/ward/bed/query',ward,'POST');
//33.获取病房列表
export const reqWardsList = () => ajax('/system/wards');
//34.根据病区查询病床类型
export const reqBedType = (ward_name) => ajax('/ward/bed/bedType',{ward_name},'POST');
//35.根据科室查询医生
export const reqDocs = (dep_name) =>ajax('/staff/queryDoc',{dep_name},'POST');
//36.修改入院登记
export const reqUpdateReg = (updatesreg) => ajax('/inout/in/update',updatesreg,'POST');
//37.根据regid查询regname
export const reqQuaryRegname = (reg_id) => ajax('/inout/in/queryRegName',{reg_id},'POST');
//38.获取advice列表
export const reqAdviceList = () => ajax('/treatement/advice/query');
//39.获取档案列表
export const reqArchsList =() => ajax('/inout/archs/query');
//40.添加药品分发
export const reqCreateDispatch = (create) => ajax('/drug/dispatch/create',create,'POST');
//41.查询药品分发
export const reqDispatchList = () => ajax('/drug/dispatch/query');
//42.查询患者相关信息
export const reqQuaryRegDetails = (reg_id) => ajax('/treatement/nurse/query/patientDeatails',{reg_id},'POST');
//43.查询护理列表
export const reqNurseList = () => ajax('/treatement/nurse/query');
//44.查询护理基础信息
export const reqGetDetails =(reg_id) => ajax('/treatement/nurse/query/createTime',{reg_id},'POST');
//45.查询费用列表
export const reqCostList = () => ajax('/cost/query/costList');
//46.根据u_username查询reg_id
export const reqQueryId = (u_username) =>ajax('/cost/query/regId',{u_username},'POST');
//47.根据reg_id查询患者信息
export const reqQueryPatientInfo = (reg_id) =>ajax('/cost/query/patientInfo',{reg_id},'POST');
//48.查询费用明细
export const reqQueryCostInfo =(info) => ajax('/cost/query/costDetailList',info,'POST');
//49.reqQueryUname
export const reqQueryUname = (u_name) => ajax('/queryUname',{u_name},'POST');
//50.获取结算清单
export const reqQuerySettlement =(reg_id,end_time) =>ajax('/cost/query/Settlement',{reg_id,end_time},'POST');
//51.结算
export const reqUpdateCost =(done) => ajax('/cost/settlement/update',done,'POST');
//52.获取出院列表
export const reqOutList = () => ajax('/inout/out/outList');
//53.更新出院
export const reqUpdateOut =(info) => ajax('/inout/out/update',info,'POST');
//54.档案
export const reqQueryArchivesDetail = (reg_id) => ajax('/inout/archives/detail',{reg_id},'POST');