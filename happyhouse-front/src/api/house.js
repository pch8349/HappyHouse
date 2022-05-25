import { apiInstance, houseInstance } from "./index.js";

const api = apiInstance(); //스프링부트
const house = houseInstance(); //open api. db에 아파트정보가 들어와있으면 필요없음

function sidoList(success, fail) {
  api.get(`/map/sido`).then(success).catch(fail);
}

function gugunList(params, success, fail) {
  api.get(`/map/gugun`, { params: params }).then(success).catch(fail);
}

function houseList(params, success, fail) {
  house.get(``, { params: params }).then(success).catch(fail);
}

function sidoGugunCode(params, success, fail) {
  console.log(params.sido, params.gugun);
  api
    .get(`/map/sidoguguncode/${params.sido}/${params.gugun}`)
    .then(success)
    .catch(fail);
}

export { sidoList, gugunList, houseList, sidoGugunCode };
