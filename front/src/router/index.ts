import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import SubscriptionView from '../views/SubscriptionView.vue'
import MailingListView from '../views/MailingListView.vue'

const routes: Array<RouteRecordRaw> = [
  { path: '/', component: SubscriptionView, name: 'subscribe', props: {action: 'Subscribe'} },
  { path: '/unsubscribe', component: SubscriptionView, name: 'unsubscribe', props: {action: 'Unsubscribe'} },
  { path: '/mailing-list', component: MailingListView, name: 'mailingList' }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach(async (_to, _from, next) => { 
  next();
})

export default router