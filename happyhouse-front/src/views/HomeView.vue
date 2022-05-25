<template>
  <div>
    <home-map></home-map>
    <br /><br /><br />
  </div>
</template>

<script>
import HomeMap from "@/components/main/HomeMap.vue";

export default {
  name: "HomeView",
  components: {
    HomeMap,
  },
  props: {
    msg: String,
  },
};
</script>

<style scoped>
@import url("https://fonts.googleapis.com/css2?family=Do+Hyeon&display=swap");
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(72, 190, 233, 0.3) 30%
  );
}
</style>
