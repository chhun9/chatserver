<template>
  <div>
    <div>
      <h3>{{ titleText }}</h3>
    </div>
  </div>
  <div>
    <label>{{ labelText }}</label>
    <input type="text" v-model="roomName" v-on:keyup.enter="confirm">
    <button type="button" @click="confirm">{{ buttonText }}</button>
  </div>
  <ul>
    <li v-for="item in chatRooms" v-bind:key="item.chatRoomSeq" v-on:click="enterRoom(item.chatRoomSeq)">
      {{ item.chatRoomName }}
    </li>
  </ul>
</template>
<script>
export default {
  name: 'EnterChatList',
  props: {
    titleText: {type: String},
    labelText: {type: String},
    buttonText: {type: String},
    chatRooms: {type: Object}
  },
  emits: ['confirm','enterRoom'],
  data() {
    return {
      roomName: ''
    }
  },
  methods: {
    confirm() {
      this.$emit('confirm', this.roomName)
      this.roomName=''
    },
    enterRoom(chatRoomSeq){
      this.$emit('enterRoom', chatRoomSeq)
    }
  }
}
</script>
<style scoped>
li{
  cursor: pointer;
}
</style>