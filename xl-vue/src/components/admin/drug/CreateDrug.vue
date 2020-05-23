<template>
  <div style="text-align: left;display: inline-block;
    float:right;margin-right:23px;">
    <el-button  class="add-button" type="primary" @click="dialogFormVisible = true">添加药品</el-button>
    <el-dialog
      title="添加药品"
      :visible.sync="dialogFormVisible"
      @close="clear"
      >
      <el-form :model="drug" :rules="rules" label-position="center" ref="drug"
               label-width="0px">
        <el-form-item label="药品名称" label-width="120px" prop="drug_name">
          <el-input v-model="drug.drug_name" style="width:222px;"></el-input>
        </el-form-item>
        <el-form-item label="药品价格" label-width="120px" prop="drug_price">
         <template>
            <el-input-number v-model="drug.drug_price" :precision="2" :step="1" ></el-input-number> 元
          </template>
        </el-form-item>
        <el-form-item label="供应商" label-width="120px" prop="drug_supply">
         <el-input v-model="drug.drug_supply" style="width:222px;"></el-input>
        </el-form-item>
        <el-form-item label="药品数量" label-width="120px" prop="drug_quantity">
          <template>
            <el-input-number v-model="drug.drug_quantity" controls-position="right"  ></el-input-number>
          </template>
        </el-form-item>      
        <el-form-item label="有效日期" label-width="120px" prop="drug_exp">
          <template>
            <el-date-picker
              v-model="drug.drug_exp"
              value-format="yyyy-MM-dd"
              type="date"
              placeholder="选择日期">
            </el-date-picker>
          </template>
        </el-form-item>
        <el-form-item label="操作员" label-width="120px" prop="drug_operator">
          <span>{{drug.drug_operator}}</span>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="submitForm('drug')">确 定</el-button>
      </div>
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
            drug_name: [{required: true, message: '药品名不能为空', trigger: 'blur'}],
            drug_supply: [{required: true, message: '供应商不能为空', trigger: 'blur'}],
          },
          options: defaultData.options,
          wardType: defaultData.wardType,
          drug: {
            drug_name: '',
            drug_price: '',
            drug_supply: '',
            drug_quantity: '',
            drug_exp: '',
            drug_operator:this.$store.state.user.username
          }
        }
      },
      methods: {
        submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
            this.addDrug();
          } else {
           this.$alert('校验不通过，请重新输入！')
            return false;
          }
        });
      },
        clear () {
          this.drug= {
            drug_name: '',
            drug_price: '',
            drug_supply: '',
            drug_quantity: '',
            drug_exp: '',
            drug_operator:''
          }
        },
        addDrug () {
          this.$axios
            .post('/drug/add', {
              drug_name: this.drug.drug_name,
              drug_price: this.drug.drug_price,
              drug_supply: this.drug.drug_supply,
              drug_quantity: this.drug.drug_quantity,
              drug_exp: this.drug.drug_exp,
              drug_operator:this.drug.drug_operator
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