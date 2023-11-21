<template>
  <div class="container">
    <div class="left-panel">
      <enter-chat-list :title-text="openChatRooms.title" :label-text="openChatRooms.label"
                       :button-text="openChatRooms.button" :chat-rooms='openChatRooms.chatRooms'
                       @confirm="createRoom" @enter-room="enterRoom"/>
    </div>
    <div class="right-panel">
      <enter-chat-list :title-text="connectChatRooms.title" :label-text="connectChatRooms.label"
                       :button-text="connectChatRooms.button" :chat-rooms="connectChatRooms.chatRooms"
                       @confirm="searchRoom" @enter-room="enterRoom"/>
    </div>
  </div>
</template>
<script>
import axios from "axios"
import EnterChatList from "@/components/EnterChatList.vue";

export default {
  name: 'ChatList',
  components: {
    EnterChatList
  },
  data() {
    return {
      openChatRooms: {
        title: '오픈 채팅방 리스트',
        label: '오픈 방제목',
        button: '채팅방 개설',
        chatRooms: [],
      },
      connectChatRooms: {
        title: '접속 채팅방 리스트',
        label: '접속 방제목',
        button: '채팅방 검색',
        chatRooms: [],
      },
      url: {
        findAllRooms: '/chat/rooms',
        findAllConnectRooms: '/chat/connect/rooms',
        createRoom: '/chat/room'
      },
      path: {
        enterRoom: '/chat/room/{chatRoomSeq}'
      }
    }
  },
  mounted() {
    this.findAllRooms()
  },
  methods: {
    findAllRooms() {
      axios.get(this.url.findAllRooms).then(res => {
        this.openChatRooms.chatRooms = res.data
      })
      this.findAllConnectRooms()
    },
    findAllConnectRooms() {
      axios.get(this.url.findAllConnectRooms).then(res => {
        this.connectChatRooms.chatRooms = res.data
      })
    },
    createRoom(roomName) {
      console.log(roomName)
      if (!roomName) {
        alert("방 제목 입력")
        return
      } else {
        let url = this.url.createRoom
        let searchParam = new URLSearchParams
        searchParam.append('chatRoomName', roomName)
        axios.post(url, searchParam)
            .then(
                res => {
                  this.findAllRooms()
                }
            )
            .catch(err => {
              alert(this.openChatRooms.button + ' 실패')
            })
      }
    },
    searchRoom(roomName) {
      alert('searchRoom은 나중에')
    },
    enterRoom(chatRoomSeq) {
      let path = this.path.enterRoom.replace('{chatRoomSeq}', chatRoomSeq)
      this.$router.push(path)
    }
  }
}

</script>
<style scoped>
.container {
  display: flex; /* Flexbox 레이아웃 사용 */
  width: 100vw; /* 화면 너비 전체 사용 */
  height: 100vh; /* 화면 높이 전체 사용 */
}

.left-panel,
.right-panel {
  flex: 1; /* 두 패널이 동일한 너비를 갖도록 설정 */
  overflow: hidden; /* 내용이 넘칠 경우 숨김 처리 */
}
</style>