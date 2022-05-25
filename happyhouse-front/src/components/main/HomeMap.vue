<template>
  <b-container class="bv-example-row mt-3 text-center">
    <h3 class="underline-steelblue">
      <b-icon icon="house"></b-icon> 내주변 매물
    </h3>
    <b-row>
      <b-col></b-col>
      <GmapMap
        ref="mapRef"
        :center="{
          lat: Number(latitude),
          lng: Number(longitude),
        }"
        :zoom="14"
        style="width: 100vw; height: 50vh"
      >
        <GmapMarker
          v-for="(m, index) in markers"
          :key="index"
          :position="m.position"
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
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import axios from "axios";
import { mapActions, mapState } from "vuex";
import { sidoGugunCode } from "@/api/house";
import $ from "jquery";

const houseStore = "houseStore";
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
      markers: [],
      infoWindow: {
        position: { lat: 0, lng: 0 },
        open: false,
        template: "",
      },
    };
  },
  async created() {
    let vthis = this;
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
        console.log(this.latitude, this.longitude);
        console.log("하이");
        this.markers.push({
          position: {
            lat: this.latitude,
            lng: this.longitude,
          },
        });
        try {
          axios
            .get(
              "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +
                this.latitude +
                "," +
                this.longitude +
                "&key=AIzaSyASKTABus6UEFjZ_wBGW4ZqOMWhA38QCSM",
            )
            .then(function ({ data }) {
              console.log("asdfasdfadsfasdf");
              console.log(data.results[0].formatted_address);
              vthis.address = data.results[0].formatted_address;
              vthis.mysido = vthis.address.split(" ")[1];
              vthis.mygugun = vthis.address.split(" ")[2];
              console.log(vthis.mysido, vthis.mygugun);
              sidoGugunCode(
                { sido: vthis.mysido, gugun: vthis.mygugun },
                function (response) {
                  console.log("여기여기");
                  console.log(response.data);
                  vthis.getHouseList({
                    gugunCode: response.data,
                    ymd: "202204",
                  });
                },
                // function (error) {
                //   console.log("아파트목록 에러발생!!", error);
                // },
              );
            });
        } catch (error) {
          console.log(error.message);
        }
      },
      (err) => {
        this.textContent = err.message;
      },
    );
    setTimeout(function () {
      for (let i = 0; i < vthis.houses.length; i++) {
        console.log(vthis.houses[i].법정동);
        $.ajax({
          url: `https://maps.googleapis.com/maps/api/geocode/json?address=
        ${vthis.mysido} ${vthis.mygugun} ${vthis.houses[i].법정동} ${vthis.houses[i].지번}
        &key=AIzaSyASKTABus6UEFjZ_wBGW4ZqOMWhA38QCSM&sensor=false`,
          type: "POST",
          async: false,
          success: function (myJSONResult) {
            if (myJSONResult.status == "OK") {
              vthis.center = {
                lat: myJSONResult.results[0].geometry.location.lat,
                lng: myJSONResult.results[0].geometry.location.lng,
              };
              vthis.markers.push({
                position: {
                  lat: myJSONResult.results[0].geometry.location.lat,
                  lng: myJSONResult.results[0].geometry.location.lng,
                },
              });
            } else {
              console.log(myJSONResult);
            }
            console.log(vthis.house.법정동);
          },
        });
      }

      console.log("herehere");
      console.log(vthis.houses);
    }, 1);
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList"]),
    openInfoWindowTemplate(index) {
      if (index != 0) {
        this.infoWindow.position = this.markers[index].position;
        this.infoWindow.template = `${this.houses[index - 1].아파트}`;
        this.infoWindow.open = true;
      } else {
        this.infoWindow.position = this.markers[index].position;
        this.infoWindow.template = `내위치`;
        this.infoWindow.open = true;
      }
    },
  },
};
</script>

<style></style>
