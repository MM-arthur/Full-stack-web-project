<!-- src/App.vue -->
<template>
    <div class="workspace-container">
      <ComponentPanel @drag-start="handleDragStart" />
      <CanvasArea :components="activeComponents" @select="handleSelect" />
      <PropertyPanel :selected="selectedComponent" />
      <PreviewPanel :schema="currentSchema" />
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import ComponentPanel from './lowcodeComponents/ComponentPanel.vue'
  import CanvasArea from './lowcodeComponents/CanvasArea.vue'
  import PropertyPanel from './lowcodeComponents/PropertyPanel.vue'
  import CanvasComponent from './lowcodeComponents/CanvasComponent.vue'
  
  const activeComponents = ref([])
  const selectedComponent = ref(null)
  const currentSchema = ref({})
  
  const handleDragStart = (componentType) => {
    // 创建新组件实例
    const newComponent = {
      id: `comp_${Date.now()}`,
      type: componentType,
      props: getDefaultProps(componentType),
      layout: { x: 0, y: 0 }
    }
    activeComponents.value.push(newComponent)
  }
  
  const handleSelect = (component) => {
    selectedComponent.value = component
  }
  </script>