<template>
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
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";
import houseStore from "@/store/modules/houseStore";

export default {
  name: "HomeMap",
  data() {
    return {
      latitude: "",
      longitude: "",
      // eslint-disable-next-line prettier/prettier
      textContent: "",
      address: "",
      mysido: "",
      mygugun: "",
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
        this.getStreetAddressFrom(this.latitude, this.longitude);
        console.log(this.address);
      },
      (err) => {
        this.textContent = err.message;
      },
    );
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList"]),
    async getStreetAddressFrom(lat, long) {
      console.log("하이");
      console.log(lat, long);
      try {
        var { data } = await axios.get(
          "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +
            lat +
            "," +
            long +
            "&key=AIzaSyASKTABus6UEFjZ_wBGW4ZqOMWhA38QCSM",
        );
        if (data.error_message) {
          console.log(data.error_message);
        } else {
          this.address = data.results[0].formatted_address;
          this.mysido = this.address.split(" ")[1];
          this.mygugun = this.address.split(" ")[2];
          console.log(this.mysido, this.mygugun);
          this.getHouseList();
        }
      } catch (error) {
        console.log(error.message);
      }
    },
  },
};
</script>

<style></style>
