<template>
  <div>
    <div><input type="text" placeholder="id" v-model="id"/></div>
    <div><input type="password" placeholder="pw" v-model="pw"/></div>
    <div>
      <button v-on:click="join">join</button>
    </div>
  </div>
</template>
<script>

import axios from "axios";

export default {
  name: 'JoinPage',
  data() {
    return {
      id: '',
      pw: ''
    }
  },
  methods: {
    join() {
      axios.post("/user", {chatUserId: this.id, chatUserPw: this.pw})
          .then(() => {
            alert('success join')
            this.$router.push('/login')
          })
          .catch((e) => {
            console.log(e)
          })
    }
  }
}

</script>