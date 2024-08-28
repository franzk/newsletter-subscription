import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import SubscriptionView from '../views/SubscriptionView.vue'
import MailingListView from '../views/MailingListView.vue'

const routes: Array<RouteRecordRaw> = [
  { path: '/', component: SubscriptionView, name: 'subscribe' },
  { path: '/mailing-list', component: MailingListView, name: 'mailingList' },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

router.beforeEach(async (_to, _from, next) => {
  next()
})

export default router
