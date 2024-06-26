<template>
    <div v-if="visible" class="dialog-overlay">
      <div class="dialog-box">
        <p>{{ message }}</p>
        <button @click="confirm">Yes</button>
        <button @click="cancel">No</button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    props: {
      visible: {
        type: Boolean,
        required: true
      },
      message: {
        type: String,
        default: 'Are you sure?'
      }
    },
    methods: {
      confirm() {
        this.$emit('confirm');
      },
      cancel() {
        this.$emit('cancel');
      }
    }
  };
  </script>
  
  <style scoped>
  .dialog-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background: rgba(0, 0, 0, 0.5);
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .dialog-box {
    background: white;
    padding: 20px;
    border-radius: 5px;
    text-align: center;
  }
  button {
    margin: 5px;
  }
  </style>
  