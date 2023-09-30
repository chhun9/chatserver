<template>
  <div v-html="pageHtml" ref="loginPage"/>
</template>
<script>
import axios from "axios"

export default {
  name: 'LoginPage',
  data() {
    return {
      pageHtml: ''
    }
  },
  mounted() {
    this.getLoginPage()
  },
  methods: {
    getLoginPage() {
      axios.get("/login")
          .then(res => {
            this.pageHtml = res.data
          })
          .then(() => {
            this.addElement()
          })
          .catch(err => {
            console.log(err)
          })
    },
    addElement() {
      let signForm = this.$refs.loginPage.getElementsByClassName('form-signin').item(0)
      let joinBtn = document.createElement('button')
      joinBtn.setAttribute('class', 'btn btn-lg btn-primary btn-block')
      joinBtn.innerText = 'Join in'
      joinBtn.addEventListener('click', (e) => {
        this.$router.push('/join')
      })
      signForm.appendChild(joinBtn)
    }
  }
}

</script>