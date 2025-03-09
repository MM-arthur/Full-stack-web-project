<template>
  <div id="app">
    <nav v-if="$route.path !== '/Management' && 'workspace'">
      <router-link to="/Management" class="nav-link">Basic Management System</router-link>
    </nav>
    <nav v-if="$route.path !== '/Management' && 'workspace'">
      <router-link to="/workspace" class="nav-link">workspace</router-link>
    </nav>
    <div class="content">
      <router-view></router-view>
    </div>
    <div ref="container" class="canvas-container" @mouseover="pause" @mouseout="resume"></div>
  </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount } from 'vue'
import * as THREE from 'three'
import { OrbitControls } from 'three/addons/controls/OrbitControls.js'

const container = ref(null)
let scene, camera, renderer, controls // 全局声明three.js场景、相机、渲染器和相机控制器
let particleSystem, isPaused = false // 声明粒子系统和暂停状态变量。
let frameId = null // 声明动画帧ID

// 粒子参数
const PARTICLE_COUNT = 4000
const TRAIN_LENGTH = 150
const SPEED = 0.6

function initScene() {
  // 场景创建
  scene = new THREE.Scene()

  // 相机创建
  // 透视相机配置参数：视角、宽高比、近裁远裁剪面(近裁切面距离:小于此值的物体不显示. 远裁切面距离:大于此值的物体不显示)
  camera = new THREE.PerspectiveCamera(75, container.value.clientWidth / container.value.clientHeight, 0.1, 1000)
  // 初始相机位置
  camera.position.set(30, 20, 60)
  // 看向场景中心
  camera.lookAt(0, 1, 0)

  // WebGL渲染器
  // 开启抗锯齿和透明背景
  renderer = new THREE.WebGLRenderer({ antialias: true, alpha: true })
  // 渲染器尺寸,设置画布分辨率与容器尺寸一致
  renderer.setSize(container.value.clientWidth, container.value.clientHeight)
  // canvas挂载到DOM
  container.value.appendChild(renderer.domElement)

  // 相机控制器（自动将DOM事件转换为相机参数变化）
  controls = new OrbitControls(camera, renderer.domElement)
  
  // 光照系统
  // 环境光
  const ambientLight = new THREE.AmbientLight(0xffffff, 0.5)
  scene.add(ambientLight)
  // 点光源
  const pointLight = new THREE.PointLight(0x00ff00, 1, 100)
  pointLight.position.set(0, 20, 0)
  scene.add(pointLight)
  
}

function createParticles() {
  // 粒子系统创建

  // 创建高性能几何体对象
  const particles = new THREE.BufferGeometry()
  // 存储粒子位置和颜色
  const positions = []
  const colors = []

  // Define engine parameters
  const ENGINE_LENGTH = 15
  const engineCount = Math.floor(PARTICLE_COUNT * 0.15) // 15% particles for engine
  const carriageCount = PARTICLE_COUNT - engineCount // 计算车厢粒子数量

  // 车厢粒子生成
  for (let i = 0; i < carriageCount; i++) {
    // x in [-TRAIN_LENGTH/2, TRAIN_LENGTH/2 - ENGINE_LENGTH]
    const x = Math.random() * (TRAIN_LENGTH - ENGINE_LENGTH) - TRAIN_LENGTH / 2
    const y = (Math.random() - 0.5) * 4 // narrow width
    const z = Math.random() * 2 - 1     // short height
    positions.push(x, y, z)

    // Metallic blueish-dark color
    const color = new THREE.Color()
    color.setHSL(
      0.6 + Math.random() * 0.05, // blue hue variation
      0.8,
      0.2 + Math.random() * 0.2   // darker shades
    )
    colors.push(color.r, color.g, color.b)
  }

  // 车头粒子生成，具有锥形分布
  for (let i = 0; i < engineCount; i++) {
    // x坐标在[TRAIN_LENGTH/2 - ENGINE_LENGTH, TRAIN_LENGTH/2]范围内
    const engineProgress = Math.random();
    const x = TRAIN_LENGTH / 2 - ENGINE_LENGTH + engineProgress * ENGINE_LENGTH;

    // 向车头方向锥形扩散，前端更集中
    const spreadFactor = 1 - engineProgress;
    const y = (Math.random() - 0.5) * 4 * spreadFactor; // 调整Y轴分布，与车身宽度一致
    const z = (Math.random() * 2 - 1) * spreadFactor; // 调整Z轴分布，与车身高度一致
    positions.push(x, y, z);

    // 生成车头粒子的颜色，蓝色调，较亮
    const color = new THREE.Color();
    color.setHSL(
      0.6 + Math.random() * 0.05,
      0.9, // 更高的饱和度
      0.3 + Math.random() * 0.2 // 较亮的亮度变化
    );
    colors.push(color.r, color.g, color.b);
  }


  particles.setAttribute('position', new THREE.Float32BufferAttribute(positions, 3))
  particles.setAttribute('color', new THREE.Float32BufferAttribute(colors, 3))

  // 每个粒子单独计算颜色，但共享材质参数
  const material = new THREE.PointsMaterial({
    size: 0.5, // 粒子基准尺寸（像素）
    vertexColors: true,
    transparent: true,
    opacity: 0.8,
    sizeAttenuation: true
  })

  // 创建粒子系统
  particleSystem = new THREE.Points(particles, material)
  // 将粒子系统添加到场景中
  scene.add(particleSystem)

  // 创建车头灯
  const headlight = new THREE.PointLight(0xffffff, 1, 50)
  headlight.position.set(TRAIN_LENGTH / 2, 0, 0)
  // 将车头灯添加到场景中
  scene.add(headlight)
}


function animate() {
  // 动画函数

  // 如果暂停状态，则退出函数
  if (isPaused) return

  // 更新粒子位置
  // 获取顶点位置数组的引用，内存布局：[x1,y1,z1, x2,y2,z2,...]
  const positions = particleSystem.geometry.attributes.position.array
  
  // 直接操作类型化数组，避免创建新对象（性能）
  for (let i = 0; i < positions.length; i += 3) {
    positions[i] += SPEED  // X轴方向移动
    
    // 循环移动
    if (positions[i] > TRAIN_LENGTH / 2) {
      positions[i] = -TRAIN_LENGTH / 2
    }
  }
  
  // 标记粒子位置需要更新
  particleSystem.geometry.attributes.position.needsUpdate = true
  // 更新控制器
  controls.update()
  // 渲染场景
  renderer.render(scene, camera)
  // 以显示器刷新率（通常60Hz）循环调用函数。每次循环中更新粒子位置并重新渲染
  frameId = requestAnimationFrame(animate)
}

function onWindowResize() {
  // 窗口响应式处理
  camera.aspect = container.value.clientWidth / container.value.clientHeight
  camera.updateProjectionMatrix()
  renderer.setSize(container.value.clientWidth, container.value.clientHeight)
}

function pause() {
  isPaused = true
}

function resume() {
  if (!isPaused) return
  isPaused = false
  animate()
}

onMounted(() => {
  initScene()
  createParticles()
  animate()
  window.addEventListener('resize', onWindowResize)
})

onBeforeUnmount(() => {
  window.removeEventListener('resize', onWindowResize)
  cancelAnimationFrame(frameId)

  // 资源释放关键步骤
  scene.remove(particleSystem)
  particleSystem.geometry.dispose()
  particleSystem.material.dispose()
  renderer.dispose()
})
</script>

<style scoped>
nav {
  display: flex;
  justify-content: space-around;
  background-color: #24242473;
  padding: 1rem;
}
.nav-link {
  color: white;
  text-decoration: none;
  font-size: 1.2rem;
  transition: color 0.3s;
}
.nav-link:hover {
  color: #b9af42;
  text-decoration: underline;
}
.content {
  padding: 1rem;
}
.canvas-container {
  width: 40vw;
  height: 35vh;
  cursor: pointer;
  background: transparent;
}
</style>
