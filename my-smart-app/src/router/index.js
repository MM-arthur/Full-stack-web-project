import { createRouter, createWebHistory } from 'vue-router'
import Management from '../views/Management.vue'
import workspace from '../views/workspace.vue'

const routes = [
  {
    path: '/Management',
    name: 'Management',
    component: Management
  },

  {
    path: '/workspace',
    name: 'workspace',
    component: workspace
  },
]

const router = createRouter({
    history: createWebHistory(import.meta.env.VITE_BASE_URL),
    routes
})

export default router
