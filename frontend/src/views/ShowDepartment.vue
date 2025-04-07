<template>
    <div>
      <div class="table-responsive small">
        <table class="table table-striped table-sm">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Header</th>
              <th scope="col">Header</th>
              <th scope="col">Header</th>
              <th scope="col">Header</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="row in tableData" :key="row.id">
              <td>{{ row.id }}</td>
              <td>{{ row.col1 }}</td>
              <td>{{ row.col2 }}</td>
              <td>{{ row.col3 }}</td>
              <td>{{ row.col4 }}</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </template>
  

<script>
import { onMounted } from 'vue';
import { reactive, toRefs } from 'vue'

export default {
    setup () {
        const state = reactive({
            tableData: [
    { id: 1001, col1: "random", col2: "data", col3: "placeholder", col4: "text" },
    { id: 1002, col1: "placeholder", col2: "irrelevant", col3: "visual", col4: "layout" },
  ]
        })
    
        return {
            ...toRefs(state),
        }
    }

}
</script>

<style lang="scss" scoped>

</style>