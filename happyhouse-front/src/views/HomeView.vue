<template>
  <div>
    <div
      class="img"
      style="display: flex; justify-content: center; align-items: center"
    >
      <img src="@/assets/happyhouse2.png" />
    </div>
    <b-container class="bv-example-row mt-3 text-center">
      <h3 class="underline-steelblue"><b-icon icon="house"></b-icon> SSAFY</h3>
      <b-row>
        <b-col></b-col>
        <GmapMap
          ref="mapRef"
          :center="{
            lat: Number(latitude),
            lng: Number(longitude),
          }"
          :zoom="16"
          style="width: 100vw; height: 50vh"
        >
          <GmapMarker
            :position="{
              lat: Number(latitude),
              lng: Number(longitude),
            }"
          />
        </GmapMap>
        <b-col></b-col>
      </b-row>
    </b-container>
    <br /><br /><br />
  </div>
</template>

<script>
export default {
  name: "HomeView",
  props: {
    msg: String,
  },
  data() {
    return {
      latitude: "",
      longitude: "",
      // eslint-disable-next-line prettier/prettier
      textContent: "",
    };
  },
  created() {
    if (!("geolocation" in navigator)) {
      this.textContent = "Geolocation is not available.";
      return;
    }
    this.textContent = "Locating...";

    // get position
    navigator.geolocation.getCurrentPosition(
      (pos) => {
        this.latitude = pos.coords.latitude;
        this.longitude = pos.coords.longitude;
        this.textContent =
          "Your location data is " + this.latitude + ", " + this.longitude;
      },
      (err) => {
        this.textContent = err.message;
      },
    );
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
