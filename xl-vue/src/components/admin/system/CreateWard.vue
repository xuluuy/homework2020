<template>
  <div style="text-align: left;display: inline-block;
    float:right;margin-right:23px;">
    <el-button  class="add-button" type="primary" @click="dialogFormVisible = true">添加病房</el-button>
    <el-dialog
      title="添加病房"
      :visible.sync="dialogFormVisible"
      @close="clear"
      >
      <el-form :model="wards" :rules="rules" label-position="center" ref="wards"
               label-width="0px">
        <el-form-item label="病房名称" label-width="120px" prop="ward_name">
          <el-input v-model="wards.ward_name" style="width:222px;"></el-input>
        </el-form-item>
        <el-form-item label="所属科室" label-width="120px" prop="dep_name">
         <template>
              <el-select v-model="wards.dep_name" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
          </template>
        </el-form-item>
        <el-form-item label="病房类型" label-width="120px" prop="bed_type">
          <template>
              <el-select v-model="wards.bed_type" placeholder="请选择">
                <el-option
                  v-for="item in wardType"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value">
                </el-option>
              </el-select>
          </template>
        </el-form-item>
        <el-form-item label="总床位" label-width="120px" prop="total_bed">
          <template>
            <el-input-number v-model="wards.total_bed" controls-position="right"  :min="1" :max="100"></el-input-number>
          </template>
        </el-form-item>      
        <el-form-item label="剩余床位" label-width="120px" prop="remain_bed">
          <template>
            <el-input-number v-model="wards.remain_bed" controls-position="right" :min="0" :max="100"></el-input-number>
          </template>
        </el-form-item>
        <el-form-item label="床位价格" label-width="120px" prop="bed_price">
          <template>
            <el-input-number v-model="wards.bed_price" :precision="2" :step="1" ></el-input-number>
          </template>
        </el-form-item>
        <el-form-item >
          <el-button type="primary" style="width: 30%;background: #505458;border: none;margin-left:30%;" @click="submitForm('wards')">添加</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import {defaultData} from '@/util/data.js'
    export default {
        name: 'CreateWard',
      data () {
        return {
          dialogFormVisible: false,
          rules: {
            ward_name: [{required: true, message: '病房名不能为空', trigger: 'blur'}],
            
          },
          options: defaultData.options,
          wardType: defaultData.wardType,
          wards: {
            ward_name: '',
            dep_name: '',
            bed_type: '',
            total_bed: '',
            remain_bed: '',
            bed_price:''
          }
        }
      },
      methods: {
        submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.addward();
          } else {
           this.$alert('校验不通过，请重新输入！')
            return false;
          }
        });
      },
        clear () {
          this.wards= {
            ward_name: '',
            dep_name: '',
            bed_type: '',
            total_bed: '',
            remain_bed: '',
            bed_price:''
          }
        },
        addward () {
          this.$axios
            .post('/system/ward/add', {
              ward_name: this.wards.ward_name,
              dep_name: this.wards.dep_name,
              bed_type: this.wards.bed_type,
              total_bed: this.wards.total_bed,
              remain_bed: this.wards.remain_bed,
              bed_price:this.wards.bed_price
            })
            .then(resp => {
              if (resp.data.code === 200) {
                this.$alert('添加成功', '提示', {
                  confirmButtonText: '确定'
                })
                
                this.clear()
                this.$emit('onSubmit')
              } else {
                this.$alert(resp.data.message, '提示', {
                  confirmButtonText: '确定'
                })
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