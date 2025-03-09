<!-- src/components/CanvasArea.vue -->
<template>
  <div class="canvas-area" @dragover.prevent @drop="handleDrop">
    <vuedraggable 
      v-bind="components"
      item-key="id"
      @start="drag=true"
      @end="drag=false"
    >
      <template #item="{ element }">
        <CanvasComponent 
          :component="element"
          :selected="selectedId === element.id"
          @click="handleSelect(element)"
          @update-props="handleUpdateProps"
        />
      </template>
    </vuedraggable>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue'
import vuedraggable from 'vuedraggable/src/vuedraggable'

const props = defineProps(['components'])
const emit = defineEmits(['select'])

const selectedId = ref(null)

const handleSelect = (component) => {
  selectedId.value = component.id
  emit('select', component)
}

const handleUpdateProps = (id, newProps) => {
  const index = props.components.findIndex(c => c.id === id)
  if (index > -1) {
    props.components[index].props = { 
      ...props.components[index].props,
      ...newProps
    }
  }
}
</script>
