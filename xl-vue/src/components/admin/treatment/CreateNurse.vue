<template>
  <div style="text-align: left;display: inline-block;
    float:right;margin-right:23px;">
    <el-button  class="add-button" type="warning" @click="dialogFormVisible = true">添加护理记录</el-button>
    <el-dialog
      title="添加护理记录"
      :visible.sync="dialogFormVisible"
      @close="clear"
      >
      <el-form :model="nurse" label-position="center" ref="nurse"
               label-width="0px">
        <el-form-item label="入院id" label-width="120px" prop="reg_id">
         <el-input v-model="nurse.reg_id" @blur="queryPname"></el-input>
        </el-form-item>
        <el-form-item label="患者姓名" label-width="120px" >
          <el-input v-model="nurse.reg_name"></el-input>
        </el-form-item>
        <el-form-item label="床位号" label-width="120px" >
          <el-input v-model="nurse.bed_id"></el-input>
        </el-form-item>
          <el-form-item label="体温" label-width="120px">
          <el-input v-model="nurse.p_temperature" style="width:120px;margin-right:3px;"></el-input><span>℃</span>
        </el-form-item>
         <el-form-item label="心率" label-width="120px">
          <el-input v-model="nurse.heart_rate" style="width:120px;margin-right:3px;"></el-input><span>次/分</span>
        </el-form-item>
         <el-form-item label="血压" label-width="120px">
          <el-input v-model="nurse.blood_pressure" style="width:120px;margin-right:3px;"></el-input><span>收缩压（mmHg）/舒张压（mmHg）</span>
        </el-form-item>
        <el-form-item label="血糖" label-width="120px">
          <el-input v-model="nurse.blood_sugar" style="width:120px;margin-right:3px;"></el-input><span>mmol/L</span>
        </el-form-item>
        <el-form-item label="备注" label-width="120px" >
          <template>
            <el-input
                        type="textarea"
                        autosize
                        placeholder="请输入内容"
                        v-model="nurse.remarks">
                        </el-input>
          </template>
        </el-form-item>
        <el-form-item label="护理时间" label-width="120px" prop="end_time">
          <template>
            <el-date-picker
              v-model="nurse.create_time"
              value-format="yyyy-MM-dd HH:mm:ss"
              type="datetime"
              placeholder="选择日期时间">
            </el-date-picker>
          </template>
        </el-form-item>
         <el-form-item label="护理人" label-width="120px" prop="staff_id">
        {{nurse.staff_id}}
        </el-form-item>
        <el-form-item >
          <el-button type="primary" style="width: 30%;background: #505458;border: none;margin-left:30%;" @click="addNurse">添加</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {defaultData} from '@/util/data.js'
import {reqQuaryRegDetails} from '@/api';
    export default {
        name: 'CreateNurse',
      data () {
        return {
          dialogFormVisible: false,
          
          nurse: {
            reg_id :'',
            reg_name:'',
            bed_id :'',
            p_temperature :'',
            heart_rate :'',
            blood_pressure:'',
            blood_sugar :'',
            remarks :'',
            create_time :'',
            staff_id :this.$store.state.user.username, 
          }
        }
      },
      methods: {
        
        clear () {
          this. nurse = {
            reg_id :'',
            reg_name:'',
            bed_id :'',
            p_temperature :'',
            heart_rate :'',
            blood_pressure:'',
            blood_sugar :'',
            remarks :'',
            create_time :'',
            staff_id :this.$store.state.user.username, 
          }
        },
       async queryPname(){
            const reg_id = this.nurse.reg_id
            const result = await reqQuaryRegDetails(reg_id)
            if(result && result.status ===200){
                this.nurse.reg_name = result.data.reg_name
                this.nurse.bed_id = result.data.bed_id
            }
        },
        addNurse () {
          this.$axios
            .post('/treatement/nurse/create', {
            reg_id :this.nurse.reg_id,
            bed_id :this.nurse.bed_id,
            p_temperature :this.nurse.p_temperature,
            heart_rate :this.nurse.heart_rate,
            blood_pressure:this.nurse.blood_pressure,
            blood_sugar :this.nurse.blood_sugar,
            remarks :this.nurse.remarks,
            create_time :this.nurse.create_time,
            staff_id :this.nurse.staff_id,
            })
            .then(resp => {
              if (resp.status === 200) {
                this.$message({
                    message: '添加成功！',
                    type: 'success'
                    })
                
                this.clear()
                this.$emit('onSubmit')
              } else {
                this.$message.error('添加失败！');
              }
              this.dialogFormVisible = false
            })
            .catch(failResponse => {})
        }
      } 
    }
</script>

<style scoped>
 
</style>