<!-- src/components/ComponentPanel.vue -->
<template>
    <div class="component-palette">
      <div 
        v-for="comp in components"
        :key="comp.type"
        class="component-item"
        draggable="true"
        @dragstart="handleDragStart(comp.type)"
      >
        <n-icon :component="comp.icon" />
        {{ comp.label }}
      </div>
    </div>
  </template>
  
  <script setup>
  import { NIcon } from 'naive-ui'
  import { components } from '../meta/componentMap.js'
  
  const emit = defineEmits(['drag-start'])
  
  const handleDragStart = (type) => {
    emit('drag-start', type)
  }
  </script>
  
  <style scoped>
  .component-palette {
    width: 240px;
    border-right: 1px solid #eee;
  }
  .component-item {
    padding: 8px;
    margin: 4px;
    border: 1px dashed #ccc;
    cursor: move;
  }
  </style>