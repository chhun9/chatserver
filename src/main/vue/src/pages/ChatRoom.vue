<template>
  <div>
    <div>
      <h2>{{ room.name }}</h2>
    </div>
    <div>
      <div>
        <label>내용</label>
      </div>
      <input type="text" v-model="message" v-on:keyup.enter="sendMessage">
      <div>
        <button type="button" @click="sendMessage">보내기</button>
      </div>
    </div>
    <ul>
      <li v-for="message in messages" v-bind:key="message">
        {{ message.sender }} - {{ message.message }}
      </li>
    </ul>
  </div>
</template>
<script>
import axios from 'axios'
import sockjs from 'sockjs-client'
import webStomp from 'webstomp-client'

export default {
  name: 'ChatRoom',
  data() {
    return {
      roomId: '',
      room: {},
      sender: '',
      message: '',
      messages: [],
      url: {
        findRoom: '/chat/room/{roomId}',
        sendMessage: '/pub/chat/message',
        connectRoom: '/sub/chat/room/{roomId}'
      },
      sock: '',
      ws: ''
    }
  },
  mounted() {
    this.roomId = localStorage.getItem('wschat.roomId')
    this.sender = localStorage.getItem('wschat.sender')
    this.findRoom()
    this.connect()
  },
  methods: {
    connect() {
      this.sock = new sockjs('http://localhost:8080/ws-stomp')
      this.ws = webStomp.over(this.sock)
      this.ws.debug = () => {
      }

      this.ws.connect({}, (frame) => {
        let url = this.url.connectRoom.replace('{roomId}', this.roomId)

        this.ws.subscribe(url, (msg) => {
          let recv = JSON.parse(msg.body)
          this.recvMessage(recv)
        })

        this.ws.send(this.url.sendMessage, JSON.stringify({
          type: 'ENTER',
          roomId: this.roomId,
          sender: this.sender
        }))
      }, (err) => {
        console.log(err)
      })
    },
    findRoom() {
      let url = this.url.findRoom.replace('{roomId}', this.roomId)
      axios.get(url).then(res => {
        this.room = res.data
      })
    },
    sendMessage() {
      this.ws.send(this.url.sendMessage, JSON.stringify({
        type: 'TALK',
        roomId: this.roomId,
        sender: this.sender,
        message: this.message
      }))
      this.message = ''
    },
    recvMessage(recv) {
      this.messages.push({
        "type": recv.type,
        "sender": recv.type == 'ENTER' ? '[알림]' : recv.sender,
        "message": recv.message
      })
    }
  }
}
</script>
