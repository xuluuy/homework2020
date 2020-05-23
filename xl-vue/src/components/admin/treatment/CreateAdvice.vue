<template>
  <div style="text-align: left;display: inline-block;
    float:right;margin-right:23px;">
    <el-button  class="add-button" type="warning" @click="dialogFormVisible = true">添加医嘱</el-button>
    <el-dialog
      title="添加医嘱"
      :visible.sync="dialogFormVisible"
      @close="clear"
      >
      <el-form :model="advice" label-position="center" ref="advice"
               label-width="0px">
        <el-form-item label="医嘱期效" label-width="120px" prop="advice_type">
          <el-select  v-model="advice.advice_type" placeholder="请选择">
              <el-option
                  v-for="item in adviceType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="入院id" label-width="120px" prop="reg_id">
         <el-input v-model="advice.reg_id" @blur="queryPname"></el-input>
        </el-form-item>
        <el-form-item label="患者姓名" label-width="120px" prop="reg_name">
          <el-input v-model="advice.reg_name"></el-input>
        </el-form-item>
        <el-form-item label="诊断代码" label-width="120px" prop="diagnostic_code">
          <template>
            <el-select  v-model="advice.diagnostic_code" placeholder="请选择">
              <el-option
                  v-for="item in adviceCode"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
          </el-select>
          </template>
        </el-form-item>      
        <el-form-item label="详细医嘱" label-width="120px" prop="advice_detail">
          <template>
            <el-input
                        type="textarea"
                        autosize
                        placeholder="请输入内容"
                        v-model="advice.advice_detail">
                        </el-input>
          </template>
        </el-form-item>
        <el-form-item label="开始时间" label-width="120px" prop="create_time">
          <template>
            <el-date-picker
              v-model="advice.create_time"
              value-format="yyyy-MM-dd"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </template>
        </el-form-item>
        <el-form-item label="结束时间" label-width="120px" prop="end_time">
          <template>
            <el-date-picker
              v-model="advice.end_time"
              value-format="yyyy-MM-dd"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </template>
        </el-form-item>
         <el-form-item label="开嘱医生" label-width="120px" prop="staff_id">
        {{advice.staff_id}}
        </el-form-item>
        <el-form-item >
          <el-button type="primary" style="width: 30%;background: #505458;border: none;margin-left:30%;" @click="addAdvice">添加</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {defaultData} from '@/util/data.js'
import {reqQuaryRegname} from '@/api';
    export default {
        name: 'CreateAdvice',
      data () {
        return {
          dialogFormVisible: false,
          adviceType:[{
              value:'长期',
              label:'长期'
          },
          {
              value:'短期',
              label:'短期'
          }],
         adviceCode:defaultData.advice,
          advice: {
            advice_type :'',
            reg_id :'',
            reg_name :'',
            diagnostic_code :'',
            advice_detail :'',
            create_time :'',
            end_time :'',
            staff_id:this.$store.state.user.username, 
          }
        }
      },
      methods: {
        
        clear () {
          this.advice={
            advice_type :'',
            reg_id :'',
            reg_name :'',
            staff_id:this.$store.state.user.username, 
            diagnostic_code :'',
            advice_detail :'',
            create_time :'',
            end_time :''
          }
        },
       async queryPname(){
            const reg_id = this.advice.reg_id
            const result = await reqQuaryRegname(reg_id)
            if(result && result.status ===200){
                this.advice.reg_name = result.data
            }
        },
        addAdvice () {
          this.$axios
            .post('/treatement/advice/create', {
            advice_type :this.advice.advice_type,
            reg_id :this.advice.reg_id,
            reg_name :this.advice.reg_name,
            staff_id :this.advice.staff_id,
            diagnostic_code :this.advice.diagnostic_code,
            advice_detail :this.advice.advice_detail,
            create_time :this.advice.create_time,
            end_time :this.advice.end_time,
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