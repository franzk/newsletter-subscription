import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';
import Subscription from '../components/Subscription.vue';
import MailingList from '../components/MailingList.vue';

const routes: Array<RouteRecordRaw> = [
  { path: '/', component: Subscription, name: 'subscribe', props: {action: 'subscribe'} },
  { path: '/unsubscribe', component: Subscription, name: 'unsubscribe', props: {action: 'unsubscribe'} },
  { path: '/mailing-list', component: MailingList, name: 'mailingList' }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach(async (to, from, next) => { 
  next();
})

export default router