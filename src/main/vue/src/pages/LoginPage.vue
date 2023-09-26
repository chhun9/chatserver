<template>
  <div v-html="pageHtml"/>
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
          .catch(err => {
            console.log(err)
          })
    }
  }
}

</script>