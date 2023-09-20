import {createRouter, createWebHistory} from "vue-router";
import ChatList from "../pages/ChatList.vue";
import ChatRoom from "../pages/ChatRoom.vue";

const routes = [
    {
        path: '/',
        component: ChatList
    },
    {
        path: '/chat/room/:roomId',
        component: ChatRoom
    }
]

const router = createRouter({
    history: createWebHistory(), routes

})
export default router