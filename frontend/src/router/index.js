import { createRouter, createWebHistory } from 'vue-router';

import BaseLayout from '@/viewsBaseLayout.vue';
import LoginPage from '@/views/LoginPage.vue';


const routes = [
  {
    path: '/',
    redirect:'/'
  },
  {
    path: '/',
    name: 'BaseLayout',
    component: BaseLayout,
    children: 
    [
      { path: '/login', name: 'Login', component: LoginPage },                                 // 알림, btnLogin,  txtSearch, btnSearch, dropbutton                   // 로그인 성공 처리
    ]
  },  
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;
