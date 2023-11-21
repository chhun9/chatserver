import {createRouter, createWebHistory} from "vue-router";
import ChatList from "@/pages/ChatList.vue";
import ChatRoom from "@/pages/ChatRoom.vue";
import LoginPage from "@/pages/LoginPage.vue";
import JoinPage from "@/pages/JoinPage.vue";

const routes = [
    {
        path: '/list',
        component: ChatList
    },
    {
        path: '/chat/room/:roomId',
        component: ChatRoom
    },
    {
        path: '/',
        redirect: '/login'
    },
    {
        path: '/login',
        component: LoginPage
    },
    {
        path: '/join',
        component: JoinPage
    }
]

const router = createRouter({
    history: createWebHistory(), routes

})
export default router