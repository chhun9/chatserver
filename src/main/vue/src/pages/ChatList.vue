<template>
  <div>
    <div>
      <div>
        <h3>채팅방 리스트</h3>
      </div>
    </div>
    <div>
      <div>
        <label>방제목</label>
      </div>
      <input type="text" v-model="roomName" v-on:keyup.enter="createRoom">
      <div>
        <button type="button" @click="createRoom">채팅방 개설</button>
      </div>
    </div>
    <ul>
      <li v-for="item in chatRooms" v-bind:key="item.roomId" v-on:click="enterRoom(item.roomId)">
        {{ item.name }}
      </li>
    </ul>
  </div>
</template>
<script>
import axios from "axios"

export default {
  name: 'ChatList',
  data() {
    return {
      roomName: '',
      chatRooms: [],
      url: {
        findAllRooms: '/chat/rooms',
        createRoom: '/chat/room'
      },
      path: {
        enterRoom: '/chat/room/{roomId}'
      }
    }
  },
  created() {
    this.findAllRooms()
  },
  methods: {
    findAllRooms() {
      axios.get(this.url.findAllRooms).then(res => {
        this.chatRooms = res.data
      })
    },
    createRoom() {
      if (!this.roomName) {
        alert("방 제목 입력")
        return
      } else {
        let url = this.url.createRoom
        let searchParam = new URLSearchParams
        searchParam.append('name', this.roomName)
        axios.post(url, searchParam)
            .then(
                res => {
                  console.log(res.data)
                  this.roomName = ''
                  this.findAllRooms()
                }
            )
            .catch(err => {
              alert(this.roomName + '방 개설 실패')
            })
      }
    },
    enterRoom(roomId) {
      let sender = prompt('대화명을 입력해 주세요.')
      if (!sender) {
        localStorage.setItem('wschat.sender', sender)
        localStorage.setItem('wschat.roomId', roomId)

        let path = this.path.enterRoom
        path.replace('{roomId}', roomId)
        this.$router.push(path)
      }
    }
  }
}

</script>