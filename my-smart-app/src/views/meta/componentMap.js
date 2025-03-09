// src/meta/componentMap.js
import { NInput, NSelect, NDatePicker } from 'naive-ui'

export const components = [
  {
    type: 'input',
    label: '文本输入',
    icon: 'i-carbon:text-input',
    defaultProps: {
      placeholder: '请输入',
      clearable: true
    }
  },
  {
    type: 'select',
    label: '下拉选择',
    icon: 'i-carbon:list-dropdown',
    defaultProps: {
      options: [],
      filterable: true
    }
  }
]

export const componentMap = {
  input: NInput,
  select: NSelect,
  date: NDatePicker
}

export const componentProperties = {
  input: [
    {
      name: 'placeholder',
      label: '提示文字',
      editorComponent: 'NInput',
      editorProps: { type: 'text' }
    },
    {
      name: 'clearable',
      label: '可清空',
      editorComponent: 'NSwitch'
    }
  ]
}