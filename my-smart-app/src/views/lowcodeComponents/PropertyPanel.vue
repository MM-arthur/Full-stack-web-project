<!-- src/components/PropertyPanel.vue -->
<template>
    <div class="property-panel">
      <n-form v-if="selected" :model="selected.props">
        <template v-for="prop in getProperties(selected.type)" :key="prop.name">
          <n-form-item :label="prop.label">
            <component 
              :is="prop.editorComponent"
              v-model="selected.props[prop.name]"
              v-bind="prop.editorProps"
            />
          </n-form-item>
        </template>
      </n-form>
    </div>
  </template>
  
  <script setup>
  import { NForm, NFormItem } from 'naive-ui'
  import { componentProperties } from '../meta/componentMap.js'
  
  const props = defineProps({
    selected: Object
  })
  
  const getProperties = (type) => {
    return componentProperties[type] || []
  }
  </script>