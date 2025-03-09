// src/utils/schemaGenerator.js
export const generateJSONSchema = (components) => {
    return {
      type: 'object',
      properties: components.reduce((schema, comp) => {
        schema[comp.props.fieldName] = {
          type: getFieldType(comp.type),
          title: comp.props.label,
          ...comp.validation
        }
        return schema
      }, {})
    }
  }
  
  const getFieldType = (type) => {
    const typeMap = {
      input: 'string',
      number: 'number',
      date: 'string',
      select: 'string'
    }
    return typeMap[type] || 'string'
  }