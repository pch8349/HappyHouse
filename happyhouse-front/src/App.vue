<template>
  <div id="app">
    <navi-bar />
    <div
      class="img"
      style="display: flex; justify-content: center; align-items: center"
    >
      <img src="@/assets/happyhouse2.png" />
    </div>
    <router-view />
  </div>
</template>

<script>
import NaviBar from "@/components/layout/HeaderNaviBar.vue";

export default {
  name: "App",
  components: {
    NaviBar,
  },
};
</script>
@import
url("https://fonts.googleapis.com/css2?family=Do+Hyeon&family=Jua&display=swap");
<style>
a {
  text-decoration: none;
}

a:hover {
  text-decoration: none;
  font-weight: bold;
  color: chartreuse;
}

a.router-link-exact-active {
  color: #3aa4ca;
}

* {
  /* font-family: "Do Hyeon", sans-serif; */
  font-family: "Jua", sans-serif;
}

.img {
  position: relative;
  background-image: url("@/assets/background.png");
  height: 40vh;
  min-height: 300px;
  background-color: rgba(0, 0, 0, 0.7);
  background-size: cover;
}

.img_2 {
  position: absolute;
  background-image: url("@/assets/happyhouse2.png");
  width: 10%;
  background-size: cover;
}

.img-cover {
  position: absolute;
  height: 100%;
  width: 100%;
  background-color: rgba(0, 0, 0, 0.7);
  z-index: 1;
}

.img .content {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  font-size: 5rem;
  color: white;
  z-index: 2;
  text-align: center;
}
</style>
