<template>
  <div class="testJsonp flex fd-c">
    <Head />
    <div class="bottom f-a">
      jsonp得到的数据，是json对象：
      <div v-html="jsonpData"></div>
      <br/>
      jsonpObject得到的数据，是字符串：
      <div v-html="jsonpObjectData"></div>
    </div>
  </div>
</template>
<script>
import Head from "@/components/Head";
import {putJsonpScript} from '@/utils/utils';
export default {
  data() {
    return {
      jsonpData: {},      
      jsonpObjectData:{}
    };
  },
  components: { Head },
  mounted() {
    window.getTestJsonpData = this.getTestJsonpData;
    window.getTestJsonpObjectData = this.getTestJsonpObjectData;    
    //put jsonp
    putJsonpScript(this.$urls.jsonpScriptSrc,"getTestJsonpData");
    putJsonpScript(this.$urls.jsonpObjectScriptSrc,"getTestJsonpObjectData");
  },
  methods: {
    getTestJsonpData(res){
      console.log(res);
      this.jsonpData = res;
    },    
    getTestJsonpObjectData(res){
      console.log(res);
      this.jsonpObjectData = res;
    },            
  }
};
</script>
<style lang="scss" scoped>
.testJsonp {
  height: 100%;
  .bottom {}
}
</style>


