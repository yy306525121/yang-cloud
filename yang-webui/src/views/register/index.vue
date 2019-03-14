<template>
  <div class="register-container">
    <el-form ref="registerForm" :model="registerForm" :rules="registerRules" class="register-form" label-width="80px">
      <h3 class="title">注册</h3>
      <el-form-item label="用户名" prop="username">
        <el-input v-model="registerForm.username" name="username" type="text" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <span class="svg-container">
          <svg-icon icon-class="password" />
        </span>
        <el-input v-model="registerForm.password" name="password" :type="pwdType" placeholder="请输入密码"></el-input>
        <span class="show-pwd" @click="showPwd">
          <svg-icon :icon-class="pwdType === 'password' ? 'eye' : 'eye-open'" />
        </span>
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input v-model="registerForm.email" name="email" placeholder="请输入邮箱地址"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :loading="loading" @click-native.prevent="handleRegister">注册</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script>
  export default {
    name: "Register",
    data() {
      return {
        pwdType: 'password',
        loading: false,
        registerForm: {
          username: '',
          password: ''
        },
        registerRules: {
          username: [{require: true, trigger: 'blur'}],
          password: [{require: true, trigger: 'blur'}],
          email: [{type:'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change']}]
        }
      }
    },
    methods: {
      showPwd() {
        if (this.pwdType === 'password') {
          this.pwdType = ''
        } else {
          this.pwdType = 'password'
        }
      },
      handleRegister() {
        this.$refs.registerForm.validate(valid => {
          if (valid) {
            this.loading = true
            this.$store.dispatch('')
          }
        })
      }
    }
  }
</script>

<style scoped>


  .register-container {

  }

  .show-pwd {
    position: absolute;
    right: 10px;
    top: 7px;
    font-size: 16px;
    color: #eee;
    cursor: pointer;
    user-select: none;
  }
</style>
