import {createRouter, createWebHistory} from "vue-router";
import ChatList from "../pages/ChatList.vue";
import ChatRoom from "../pages/ChatRoom.vue";

const router = createRouter({
    history: createWebHistory(''),
    routes: [
        {
            path: '/',
            component: ChatList
        },
        {
            path: '/chat/room/:roomId',
            component: ChatRoom
        }
    ]
})
export default router