<!-- src/components/CanvasComponent.vue -->
<template>
    <div 
      class="component-wrapper"
      :class="{ 'selected': selected }"
      :style="positionStyle"
    >
      <component 
        :is="getComponentType(component.type)"
        v-bind="component.props"
        v-model="component.props.value"
      />
    </div>
  </template>
  
  <script setup>
  import { computed } from 'vue'
  import { componentMap } from '../meta/componentMap'
  
  const props = defineProps({
    component: Object,
    selected: Boolean
  })
  
  const positionStyle = computed(() => ({
    position: 'absolute',
    left: `${props.component.layout.x}px`,
    top: `${props.component.layout.y}px`
  }))
  
  const getComponentType = (type) => {
    return componentMap[type] || 'div'
  }
  </script>