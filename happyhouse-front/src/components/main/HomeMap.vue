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
import { sidoGugunCode } from "@/api/house";
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
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
  },
  updated() {
    console.log("herehere");
    console.log(this.houses);
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList"]),
    // async getStreetAddressFrom(lat, long, vthis) {
    //   console.log("하이");
    //   console.log(lat, long);
    //   try {
    //     var { data } = await axios.get(
    //       "https://maps.googleapis.com/maps/api/geocode/json?latlng=" +
    //         lat +
    //         "," +
    //         long +
    //         "&key=AIzaSyASKTABus6UEFjZ_wBGW4ZqOMWhA38QCSM",
    //     );
    //     if (data.error_message) {
    //       console.log(data.error_message);
    //     } else {
    //       this.address = data.results[0].formatted_address;
    //       this.mysido = this.address.split(" ")[1];
    //       this.mygugun = this.address.split(" ")[2];
    //       console.log(this.mysido, this.mygugun);
    //       sidoGugunCode(
    //         { sido: this.mysido, gugun: this.mygugun },
    //         function (response) {
    //           console.log("여기여기");
    //           console.log(response.data);
    //           console.log(vthis);
    //           vthis.getHouseList({
    //             gugunCode: response.data,
    //             ymd: "202204",
    //           });
    //         },
    //         // function (error) {
    //         //   console.log("아파트목록 에러발생!!", error);
    //         // },
    //       );
    //       // this.getHouseList();
    //     }
    //   } catch (error) {
    //     console.log(error.message);
    //   }
    // },
  },
};
</script>

<style></style>
