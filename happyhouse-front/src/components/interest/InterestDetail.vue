<template>
  <b-container v-if="interest" class="bv-example-row">
    <b-row>
      <b-col
        ><h3>{{ interest.sidoName }} {{ interest.gugunName }}</h3></b-col
      >
    </b-row>
    <b-row class="mb-2 mt-1">
      <!-- <b-col
        ><b-img :src="require('@/assets/apt.png')" fluid-grow></b-img
      ></b-col> -->
      <!-- <GmapMap
        ref="mapRef"
        :center="center"
        :zoom="16"
        style="width: 100vw; height: 50vh"
      >
        <GmapMarker
          :key="index"
          v-for="(m, index) in markers"
          :position="m.position"
        />
      </GmapMap> -->
      <GmapMap
        :center="{
          lat: Number(shophospitals[0].lat),
          lng: Number(shophospitals[0].lng),
        }"
        :zoom="16"
        map-type-id="roadmap"
        style="width: 100%; height: 600px"
      >
        <GmapMarker
          v-for="(m, index) in shophospitals"
          :key="index"
          :position="{ lat: Number(m.lat), lng: Number(m.lng) }"
          :clickable="true"
          :draggable="false"
          @click="openInfoWindowTemplate(index)"
        />
        <gmap-info-window
          :options="{
            maxWidth: 300,
            pixelOffset: { width: 0, height: -35 },
          }"
          :position="infoWindow.position"
          :opened="infoWindow.open"
          @closeclick="infoWindow.open = false"
        >
          <div v-html="infoWindow.template"></div>
        </gmap-info-window>
      </GmapMap>
    </b-row>
  </b-container>
</template>

<script>
import { mapState } from "vuex";

const interestStore = "interestStore";

export default {
  name: "InterestDetail",
  data() {
    return {
      center: {
        lat: 37.500131499999995,
        lng: 127.03242579999998,
      },
      markers: [
        {
          position: {
            lat: 37.500131499999995,
            lng: 127.03242579999998,
          },
        },
      ],
      infoWindow: {
        position: { lat: 0, lng: 0 },
        open: false,
        template: "",
      },
    };
  },
  computed: {
    ...mapState(interestStore, ["interest", "shophospitals"]),
  },

  methods: {
    openInfoWindowTemplate(index) {
      const { lat, lng, info } = this.shophospitals[index];
      this.infoWindow.position = { lat: Number(lat), lng: Number(lng) };
      this.infoWindow.template = info;
      this.infoWindow.open = true;
    },
  },
};
</script>

<style></style>
